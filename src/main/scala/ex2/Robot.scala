package ex2

import java.util.Random

type Position = (Int, Int)
type Percentage = Int
type Probability = Int
type Repetition = Int
enum Direction:
  case North, East, South, West
  def turnRight: Direction = this match
    case Direction.North => Direction.East
    case Direction.East => Direction.South
    case Direction.South => Direction.West
    case Direction.West => Direction.North

  def turnLeft: Direction = this match
    case Direction.North => Direction.West
    case Direction.West => Direction.South
    case Direction.South => Direction.East
    case Direction.East => Direction.North

trait Robot:
  def position: Position
  def direction: Direction
  def turn(dir: Direction): Unit
  def act(): Unit

class SimpleRobot(var position: Position, var direction: Direction) extends Robot:
  def turn(dir: Direction): Unit = direction = dir
  def act(): Unit = position = direction match
    case Direction.North => (position._1, position._2 + 1)
    case Direction.East => (position._1 + 1, position._2)
    case Direction.South => (position._1, position._2 - 1)
    case Direction.West => (position._1 - 1, position._2)

  override def toString(): String = s"robot at $position facing $direction"

class DumbRobot(val robot: Robot) extends Robot:
  export robot.{position, direction, act}
  override def turn(dir: Direction): Unit = {}
  override def toString(): String = s"${robot.toString} (Dump)"

class LoggingRobot(val robot: Robot) extends Robot:
  export robot.{position, direction, turn}
  override def act(): Unit =
    robot.act()
    println(robot.toString)

class RobotWithBattery(robot: Robot, private val batteryUsage: Percentage) extends Robot:
  require(batteryUsage > 0 && batteryUsage <= 100)
  private var battery: Percentage = 100
  private val checkBattery: (Percentage, Percentage) => Boolean =
     ((percentage, currBatter) => percentage > 0 &&
        percentage <= 100 &&
        currBatter - batteryUsage >= 0)

  export robot.{position, direction, turn}
     
  override def act(): Unit = battery match
      case percentage if checkBattery(percentage, battery) => {battery = battery - batteryUsage; robot.act()}
      case _ => println("The battery is empty, go charge it.")

class RobotCanFail(robot: Robot, private val fail: Probability) extends Robot: 
  require(fail >= 0 && fail <= 100)
  export robot.{position, direction, turn}

  override def act(): Unit = Random().nextInt(100) match
    case prob if(prob > fail) => robot.act()
    case _ => println("The robot can not perform the action, unlucky")
  
class RobotRepeat(robot: Robot, private val times: Repetition) extends Robot:
  require(times >= 0)
  export robot.{position, direction, turn}
  override def act(): Unit = (1 to times).foreach(_ => robot.act())

@main def testRobot: Unit =
  val robot = LoggingRobot(SimpleRobot((0, 0), Direction.North))
  robot.act() // robot at (0, 1) facing North
  robot.turn(robot.direction.turnRight) // robot at (0, 1) facing East
  robot.act() // robot at (1, 1) facing East
  robot.act() // robot at (2, 1) facing East
