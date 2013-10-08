package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("weight of a leaf") {
    assert(weight(Leaf('a',3)) === 3)
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("times of [a,b,a,c,b]") {
    assert(times(List('a','b','a','c','b')) === List(('a',2),('b',2),('c',1)))
  }

  test("times of an empty list") {
    assert(times(List()) === List())
  }

  test("times of [a,a,a,a,a]") {
    assert(times(List('a','a','a','a','a')) === List(('a',5)))
  }

  test("times of a longer word") {
    assert(times(string2Chars("some-longer-and-weird-word-wtf"))
      === List(('s',1),('o',3),('m',1),('e',3),('-',5),('l',1),('n',2),('g',1),('r',3),('a',1),('d',3),('w',3),('i',1),('t',1),('f',1)))
  }
  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("singleton") {
    new TestTrees {
      assert(singleton(List(t1)))
      assert(!singleton(List(t1,t2)))
    }
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("createCodeTree") {
    assert(createCodeTree(List('a','b','a')) === Fork(Leaf('b',1), Leaf('a',2), List('b','a'),3))
  }

  test("createCodeTree 2") {
    assert(createCodeTree("sometree".toList) === )
  }

  test("decode secret") {
    assert(decodedSecret === string2Chars("huffmanestcool"))
  }

  test("encode using french code") {
    assert(decode(frenchCode, encode(frenchCode)("huffmanestcool".toList)) === "huffmanestcool".toList)
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("codeBits") {
    new TestTrees {
      val table = List(('a',List(1,0,1)),('b',List(1,1)))
      assert(codeBits(table)('b') === List(1,1))
    }
  }

  test("convert") {
    new TestTrees {
      val table = List(('a',List(0)),('b',List(1)))
      assert(convert(t1) === table)
    }
  }

  test("convert larger tree") {
    new TestTrees {
      val table = List(('a',List(0,0)),('b',List(0,1)),('d',List(1)))
      assert(convert(t2) === table)
    }
  }

  test("decode and quick encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }
}
