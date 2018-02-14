package uk.camsw.ow

import scala.annotation.tailrec

object Exercise {

  val isPow2: Int => Boolean = n => n != 0 && ((n & -n) == n)

  @tailrec
  def roundUpPow2(n: Int, acc: Int = 0): Int =
    if (isPow2(n)) n
    else roundUpPow2(n >> 1, acc + 1)


  def populationCount(n: Int): Int = {


//    def bitPos(n: Int): Int =



    def go(acc: Int, n: Int): Int = n match {
      case 0 => acc
      case x => ???
    }

    go(0, n)
  }
}
