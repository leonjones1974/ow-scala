package uk.camsw.ow

import org.scalacheck.Gen
import org.scalatest.FreeSpec
import uk.camsw.ow.Exercise.populationCount
import org.scalatest.Matchers._
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class ExerciseSpec extends FreeSpec {

  private val positiveNumbers = Gen.choose(1, Int.MaxValue)

  "The population count function" - {

    "must return zero when applied to zero" in {
      populationCount(0) shouldBe 0
    }


    "must return a count greater than or equal to one for any value > 0" in {
      GeneratorDrivenPropertyChecks.forAll(positiveNumbers){n => {
        populationCount(n) should be >= 1
      }}

    }
  }

}
