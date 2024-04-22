package ex2
import org.scalatest.matchers.should.Matchers.*

class TestRobotFail extends org.scalatest.funsuite.AnyFunSuite:
    val robot = new RobotCanFail(new SimpleRobot((0,0), Direction.North), 100)
    val luckyRobot = new RobotCanFail(new SimpleRobot((0,0), Direction.North), 0)

    test("Test Robot in action super unlucky with 100% of act fail"):
        robot.act()
        robot.position should be ((0,0))
        robot.act()
        robot.position should be((0,0))
    
    test("Test Robot in action super lucky with 0% of act fail"):
        luckyRobot.act()
        luckyRobot.position should be ((0, 1))
        luckyRobot.act()
        luckyRobot.position should be ((0, 2))

