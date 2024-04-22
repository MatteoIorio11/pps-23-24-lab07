package ex3

import org.scalatest.matchers.should.Matchers.*

class TestSolitaire extends org.scalatest.funsuite.AnyFunSuite:
    val solitaire = Solitaire

    test("Test Solitaire logic in a 3x3 board"):
        solitaire.placeMarks((3, 3)) should be (Seq((0, 0), (2, 1)))


