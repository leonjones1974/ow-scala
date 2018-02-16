package uk.camsw.ow

import scala.annotation.tailrec

object Exercise extends App {

  def populationCount(n: Int): Int = {

    @tailrec
    def go(pow2: Int, x: Int, acc: Int = 0): Int = {
      def nextPow2 = pow2 >> 1

      if (x == 0) acc
      else if (pow2 <= x) go(nextPow2, x - pow2, acc + 1)
      else go(nextPow2, x, acc)
    }

    go(roundUpPow2(n), n)
  }

  def isPow2(n: Int): Boolean =
    n != 0 && ((n & -n) == n)

  def roundUpPow2(n: Int): Int = {

    @tailrec
    def go(x: Int, acc: Int = 0): Int =
      if (x == 0) 1 << acc
      else go(x >> 1, acc + 1)

    if (isPow2(n)) n
    else go(n)
  }


  for {n <- List(0, 5, 8, 15, 19)}{
    println(s"populationCount($n) = ${populationCount(n)}")
  }
}
