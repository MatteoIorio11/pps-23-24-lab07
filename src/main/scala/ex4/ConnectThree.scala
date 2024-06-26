package ex4

import java.util.OptionalInt
import javax.swing.text.Position
import scala.compiletime.ops.double

// Optional!
object ConnectThree extends App:
  val bound = 3
  enum Player:
    case X, O
    def other: Player = this match
      case X => O
      case _ => X

  case class Disk(x: Int, y: Int, player: Player)
  /**
   * Board:
   * y
   *
   * 3
   * 2
   * 1
   * 0
   *   0 1 2 3 <-- x
   */
  type Board = Seq[Disk]
  type Game = Seq[Board]

  import Player.*

  def find(board: Board, x: Int, y: Int): Option[Player] = 
    board.find(disk => disk.x == x && disk.y == y).map(disk => disk.player).orElse(None)

  def firstAvailableRow(board: Board, x: Int): Option[Int] = board.filter(disk => disk.x == x) match
    case Nil => Some(0)
    case list => list
      .sortBy(disk => disk.y)
      .lastOption.map(disk => disk.y + 1).filter(y => y <= 3).orElse(None)
      

  def placeAnyDisk(board: Board, player: Player): Seq[Board] = 
    // (0,3) -> (0,2) -> (0, 1) -> (0,0)
    var sequence: Seq[Board] = LazyList()
    sequence = 
      for
        x <- 0 to 3
        row = firstAvailableRow(board, x)
        if row.isDefined
        pos = Disk(x, row.get, player)
      yield
        board :+ (pos)
    sequence


  def computeAnyGame(player: Player, moves: Int): LazyList[Game] = 
    val board: Seq[Disk] = List()
    var game: LazyList[Game] = LazyList()
    val boards = placeAnyDisk(board, player)
    var currPlayer = player
    for i <- 0 until moves do
      for b <- boards do 
        currPlayer match
          case X => 
            game = game.appended(placeAnyDisk(b, Player.O))
          case O => 
            game = game.appended(placeAnyDisk(b, Player.X))  
    game


  def printBoards(game: Seq[Board]): Unit =
    for
      y <- bound to 0 by -1
      board <- game.reverse
      x <- 0 to bound
    do
      print(find(board, x, y).map(_.toString).getOrElse("."))
      if x == bound then
        print(" ")
        if board == game.head then println()

@main def main(): Unit = 
  import ex4.ConnectThree.* 
  import ex4.ConnectThree.Player.*
  // Exercise 1: implement find such that..
  println("EX 1: ")
  println(find(List(Disk(0, 0, X)), 0, 0)) // Some(X)
  println(find(List(Disk(0, 0, X), Disk(0, 1, O), Disk(0, 2, X)), 0, 1)) // Some(O)
  println(find(List(Disk(0, 0, X), Disk(0, 1, O), Disk(0, 2, X)), 1, 1)) // None

  // Exercise 2: implement firstAvailableRow such that..
  println("EX 2: ")
  println(firstAvailableRow(List(), 0)) // Some(0)
  println(firstAvailableRow(List(Disk(0, 0, X)), 0)) // Some(1)
  println(firstAvailableRow(List(Disk(0, 0, X), Disk(0, 1, X)), 0)) // Some(2)
  println(firstAvailableRow(List(Disk(0, 0, X), Disk(0, 1, X), Disk(0, 2, X)), 0)) // Some(3)
  println(firstAvailableRow(List(Disk(0, 0, X), Disk(0, 1, X), Disk(0, 2, X), Disk(0, 3, X)), 0)) // None
  // Exercise 2: implement placeAnyDisk such that..
  printBoards(placeAnyDisk(List(), X))
  // .... .... .... ....
  // .... .... .... ....
  // .... .... .... ....
  // ...X ..X. .X.. X...
  printBoards(placeAnyDisk(List(Disk(0, 0, O)), X))
  // .... .... .... ....
  // .... .... .... ....
  // ...X .... .... ....
  // ...O ..XO .X.O X..O
  println("EX 4: ")
// Exercise 3 (ADVANCED!): implement computeAnyGame such that..
  computeAnyGame(O, 4).foreach { g =>
    printBoards(g)
    println()
  }
//  .... .... .... .... ...O
//  .... .... .... ...X ...X
//  .... .... ...O ...O ...O
//  .... ...X ...X ...X ...X
//
//
// .... .... .... .... O...
// .... .... .... X... X...
// .... .... O... O... O...
// .... X... X... X... X...

// Exercise 4 (VERY ADVANCED!) -- modify the above one so as to stop each game when someone won!!
