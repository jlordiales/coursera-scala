package recfun
import common._
import scala.annotation.tailrec

object Main {
  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (r <= 1 || c == 0 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }
  /**
   * Exercise 2
   * Adds 1 for each ( and substracts 1 for each ). If at any time the count is negative
   * it means that there was a ) without a previous ( and returns false
   */
  def balance(chars: List[Char]): Boolean = {
    @tailrec
    def innerBalance(chars: List[Char], count: Int): Boolean = {
      if (chars.isEmpty && count == 0) true
      else if ((chars.isEmpty && count != 0) || count < 0) false
      else {
        val newCount =
          if (chars.head == '(') count + 1
          else if (chars.head == ')') count - 1
          else count

        innerBalance(chars.tail, newCount)
      }
    }
    innerBalance(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money < 0 || coins.isEmpty) 0
    else if (money == 0) 1
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
}
