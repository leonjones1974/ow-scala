package uk.camsw.ow

import org.scalacheck.Gen
import org.scalatest.FreeSpec
import org.scalatest.prop.GeneratorDrivenPropertyChecks._
import org.scalatest.Matchers._
import uk.camsw.ow.Exercise.{isPow2, roundUpPow2}

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
      forAll(nonPowersOf2)(n =>
        isPow2(n) shouldBe false
      )
    }
  }

  "roundUpPow2" - {
    "must return the given value for any input which is a power of two" in {
      forAll(powersOf2)(n =>
        roundUpPow2(n) shouldBe n
      )
    }

    "must return a value that is a power of two" in {
      forAll(intsWithinRange)(n =>
        isPow2(roundUpPow2(n)) shouldBe true
      )
    }

    "must return something greater than or equal to the given value" in {
      forAll(intsWithinRange)(n =>
        roundUpPow2(n) should be >= n
      )
    }

    "must round up specific examples correctly" in {
      roundUpPow2(0) shouldBe 1
      roundUpPow2(1) shouldBe 1
      roundUpPow2(2) shouldBe 2
      roundUpPow2(3) shouldBe 4
      roundUpPow2(5) shouldBe 8
    }
  }
}


object SupportFunctionSpec {
  val intsWithinRange: Gen[Int] = Gen.choose(0, 100000)
  val powersOf2: Gen[Int] = Gen.choose(0, 20).map(1 << _)
  val nonPowersOf2: Gen[Int] = Gen.choose(2, 20).map(n => (1 << n) + 1)
}