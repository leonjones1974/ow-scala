package uk.camsw.ow

import scala.annotation.tailrec

object Exercise {

  val isPow2: Int => Boolean = n => n != 0 && ((n & -n) == n)

  def roundUpPow2(n: Int): Int = {

    @tailrec
    def go(x: Int, acc: Int = 0): Int =
      if (x == 0) 1 << acc
      else go(x >> 1, acc + 1)

    if (isPow2(n)) n
    else go(n)
  }


  def populationCount(n: Int): Int = {


//    def bitPos(n: Int): Int =



    def go(acc: Int, n: Int): Int = n match {
      case 0 => acc
      case x => ???
    }

    go(0, n)
  }
}
