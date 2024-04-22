package ex1

import org.scalatest.matchers.should.Matchers.*

class ParserTest extends org.scalatest.funsuite.AnyFunSuite:
    def basicParser = new BasicParser(Set('a', 'b', 'c'))
    def notEmptyParser = new NonEmptyParser(Set('0', '1'))
    test ("Test Basic Parser operations"):
        basicParser.parseAll("aabc".toList) should be (true)
        basicParser.parseAll("aabcdc".toList) should be (false)
        basicParser.parseAll("".toList) should be (true)

    test("Test Non Empty Parser operations"):
        notEmptyParser.parseAll("0101".toList) should be (true)
        notEmptyParser.parseAll("0123".toList) should be (false)
        notEmptyParser.parseAll(List()) should be (false)


