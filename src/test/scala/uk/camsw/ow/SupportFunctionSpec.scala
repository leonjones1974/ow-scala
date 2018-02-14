package uk.camsw.ow

import org.scalacheck.Gen
import org.scalatest.FreeSpec
import org.scalatest.prop.GeneratorDrivenPropertyChecks._
import org.scalatest.Matchers._
import uk.camsw.ow.Exercise.isPow2


class SupportFunctionSpec extends FreeSpec {

  import SupportFunctionSpec._

  "isPow2" - {
    "must return true for all powers of two" in {
      forAll(powersOf2)(n =>
        isPow2(n) shouldBe true
      )
    }

    "must return false for zero" in {
      isPow2(0) shouldBe false
    }

    "must return false for non powers of two" in {
      forAll(powersOf2)(n =>
        whenever(n > 1) {
          isPow2(n + 1) shouldBe false
        }
      )
    }
  }
}


object SupportFunctionSpec {
  val powersOf2: Gen[Int] = Gen.choose(0, 20).map(1 << _)


}