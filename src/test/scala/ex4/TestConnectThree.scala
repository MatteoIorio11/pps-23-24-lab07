package ex4

import org.scalatest.matchers.should.Matchers.*
import ex4.ConnectThree.*
import ex4.ConnectThree.Player.*

class TestConnectThree extends org.scalatest.funsuite.AnyFunSuite:
    val game = ConnectThree
    test("Test Find method"):
        game.find(List(Disk(0, 0, X)), 0, 0) should be (Some(X))
        game.find(List(Disk(0, 0, X), Disk(0, 1, O), Disk(0, 2, X)), 0, 1) should be (Some(O))
        game.find(List(Disk(0, 0, X), Disk(0, 1, O), Disk(0, 2, X)), 1, 1) should be (None)
        
