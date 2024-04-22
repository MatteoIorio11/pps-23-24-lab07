package ex3

import org.scalatest.matchers.should.Matchers.*
import ex2.Robot
import ex2.Direction
import ex2.SimpleRobot

class TestRobot extends org.scalatest.funsuite.AnyFunSuite:
    def robot = new SimpleRobot((0,0), Direction.North)
    def robotWithBattery = new RobotWithBattery(robot)
