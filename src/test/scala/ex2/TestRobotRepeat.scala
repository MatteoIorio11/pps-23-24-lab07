package ex2

import ex2.RobotRepeat
import org.scalatest.matchers.should.Matchers.*

class TestRobotRepeat extends org.scalatest.funsuite.AnyFunSuite:
    val robot = new RobotRepeat(new SimpleRobot((0,0), Direction.North), 2)

    test("Test Robot Repeat action movement"):
        robot.act()
        robot.position should be ((0, 2))
        robot.act()
        robot.position should be ((0, 4))