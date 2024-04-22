package ex2

import org.scalatest.matchers.should.Matchers.*
import ex2.Robot
import ex2.Direction
import ex2.RobotWithBattery
import ex2.SimpleRobot

class TestRobot extends org.scalatest.funsuite.AnyFunSuite:
    val robot = new RobotWithBattery(SimpleRobot((0,0), Direction.North), 50)

    test("Test Robot movement"):
        robot.direction should be (Direction.North)
        robot.turn(robot.direction.turnRight)
        robot.direction should be (Direction.East)

    test("Test Robot act"): 
        robot.act()
        robot.position should be ((0,1))
        robot.act()
        robot.position should be ((0,2))
        robot.act()
        robot position should not be ((0,3))