package ex3

import scala.collection.immutable.SortedSet
import scala.collection.immutable.Queue
type Board = (Int, Int)
type BoardPosition = (Int, Int)
type Solution = Queue[BoardPosition]
type IterableFactory = Solution => Iterable[Solution]
given IterableFactory = LazyList(_)

object Solitaire extends App:
  def placeMarks(board: Board)(using factory: IterableFactory): Iterable[Solution] = 
    require(board._1 >= 3)
    require(board._2 >= 3)
    val goal = board._1 * board._2
    this.solitaireLogic(board = board)(goal)


  private def solitaireLogic(board: Board)(remainingPositions: Int)(using factory: IterableFactory): Iterable[Solution] = remainingPositions match
    case 0 => factory(Queue((board._1 / 2, board._2 / 2)))
    case _ => 
      for
        positions <- solitaireLogic(board = board)(remainingPositions = remainingPositions - 1)
        possibleX <- 0 until board._1
        possibleY <- 0 until board._2
        possiblePosition = (possibleX, possibleY)
        if positions.forall(pos => !pos.eq(possiblePosition)) && isPlaceable(possiblePosition = possiblePosition, positions = positions)
      yield
        positions.enqueue(possiblePosition)
  

  private def isPlaceable(possiblePosition: BoardPosition, positions: Solution): Boolean = 
    val lastPosition = positions.last
    ((lastPosition._1 - possiblePosition._1).abs == 2 && (lastPosition._2 - possiblePosition._2).abs == 0 ||
    (lastPosition._1 - possiblePosition._1).abs == 1 && (lastPosition._2 - possiblePosition._2).abs == 1)
 

  def render(solution: Seq[BoardPosition], width: Int, height: Int): String =
    //val reversed = solution.reverse
    val rows =
      for y <- 0 until height
          row = for x <- 0 until width
          number = solution.indexOf((x, y)) + 1
          yield if number > 0 then "%-2d ".format(number) else "X  "
      yield row.mkString
    rows.mkString("\n")
  placeMarks((3, 3)).foreach(p => println(p))
  //println(render(solution = Seq((0, 0), (2, 1)), width = 3, height = 3))