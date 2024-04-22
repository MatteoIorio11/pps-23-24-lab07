package ex2
import org.scalatest.matchers.should.Matchers.*

class TestRobotFail extends org.scalatest.funsuite.AnyFunSuite:
    val robot = new RobotCanFail(new SimpleRobot((0,0), Direction.North), 100)
