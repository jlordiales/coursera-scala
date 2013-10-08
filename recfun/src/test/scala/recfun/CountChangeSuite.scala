package recfun

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CountChangeSuite extends FunSuite {
  import Main.countChange
  
  test("countChange: 1 solution when money is 0") {
    assert(countChange(0, List(1,2)) === 1)
  }
  
  test("countChange: no solution for negative money values") {
    assert(countChange(-4, List(1,2,3,4,5)) === 0)
  }
  
  test("countChange: no solution when no coins") {
    assert(countChange(4, List()) === 0)
  }
  
  test("countChange: just 1 solution") {
    assert(countChange(4, List(4)) === 1)
  }
  
  test("countChange: example given in instructions") {
    assert(countChange(4,List(1,2)) === 3)
  }

  test("countChange: sorted CHF") {
    assert(countChange(300,List(5,10,20,50,100,200,500)) === 1022)
  }

  test("countChange: no pennies") {
    assert(countChange(301,List(5,10,20,50,100,200,500)) === 0)
  }

  test("countChange: unsorted CHF") {
    assert(countChange(300,List(500,5,50,100,20,200,10)) === 1022)
  }
}
