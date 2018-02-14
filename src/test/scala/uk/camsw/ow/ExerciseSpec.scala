package uk.camsw.ow

import org.scalacheck.Gen
import org.scalatest.FreeSpec
import uk.camsw.ow.Exercise.populationCount
import org.scalatest.Matchers._
import org.scalatest.matchers.{BeMatcher, MatchResult}
import org.scalatest.prop.GeneratorDrivenPropertyChecks._

class ExerciseSpec extends FreeSpec {

  import ExerciseSpec._

  "The population count function" - {

    "must return zero when applied to zero" in {
      populationCount(0) shouldBe 0
    }

    "must return a count greater than or equal to one for any value > 0" in {
      forAll(numbersGreaterThanZero)(
        populationCount(_) should be >= 1
      )
    }

    "must return the correct result for hand-picked acceptance criteria" in {
      populationCount(8) shouldBe 1
      populationCount(15) shouldBe 4
      populationCount(19) shouldBe 3
    }

    "must return the same result as the control function" in {
      /*
        Scala has a toBinaryString which could be used to realise this requirement.
        As that doesn't seem particularly in the spirit of the exercise I've used it
        as a control function to prove my own implementation.
        Performance benchmarking aside I'd probably use this in real-life because
        it is much simpler
       */
      val controlFunc = (n: Int) => n.toBinaryString.count(_ == '1')

      forAll(numbersGreaterThanZero)(n =>
        populationCount(n) shouldBe controlFunc(n)
      )
    }
  }
}

object ExerciseSpec {
  private val isEven: Int => Boolean = _ % 2 == 0

  val numbersGreaterThanZero: Gen[Int] = Gen.choose(1, 1000000)

  class EvenMatcher extends BeMatcher[Int] {
    def apply(left: Int) =
      MatchResult(
        isEven(left),
        left.toString + " was odd",
        left.toString + " was even"
      )
  }

  val even = new EvenMatcher
}