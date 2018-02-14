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

    "must return an even number for any odd number > 1" in {
      forAll(oddNumbersGreaterThanOne)(
        populationCount(_) shouldBe odd
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
       */
      val controlFunc: Int => Int = _.toBinaryString.count(_ == '1')
      forAll(numbersGreaterThanZero)(n =>
        populationCount(n) shouldBe controlFunc(n)
      )
    }
  }
}

object ExerciseSpec {
  private val isOdd: Int => Boolean = _ % 2 == 1

  val numbersGreaterThanZero: Gen[Int] = Gen.choose(1, 1000000)
  val oddNumbersGreaterThanOne: Gen[Int] =
    numbersGreaterThanZero.filter(n => n > 1 && isOdd(n))



  class OddMatcher extends BeMatcher[Int] {
    def apply(left: Int) =
      MatchResult(
        isOdd(left),
        left.toString + " was even",
        left.toString + " was odd"
      )
  }

  val odd = new OddMatcher
}
