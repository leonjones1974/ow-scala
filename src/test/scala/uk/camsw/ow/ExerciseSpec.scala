package uk.camsw.ow

import org.scalatest.FreeSpec
import uk.camsw.ow.Exercise.populationCount
import org.scalatest.Matchers._

class ExerciseSpec extends FreeSpec {

  "The population count function" - {

    "must return zero when applied to zero" in {
      populationCount(0) shouldBe 0
    }
  }

}
