package ex1

import org.scalatest.matchers.should.Matchers.*

class ParserTest extends org.scalatest.funsuite.AnyFunSuite:
    def basicParser = new BasicParser(Set('a', 'b', 'c'))
    test ("Test Basic Parser operations"):
        basicParser.parseAll("aabc".toList) should be (true)
        basicParser.parseAll("aabcdc".toList) should be (false)
        basicParser.parseAll("".toList) should be (true)

