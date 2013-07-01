package test.scala

import org.junit.{Before, Test}
import scala.Array
import main.scala._
import main.scala.DisplacementCell


class GoldHuntSpecs {

  var matrix: Array[Array[Cell]] = null
  var players: Array[Player] = null
  var goldHunt: GoldHunt = null

  @Before
  def setup() {
    matrix = createMatrix1()
    players = createPlayers1()
    goldHunt = GoldHunt(matrix)
  }

  @Test
  def checkIfGameIsOver() {
    // when
    val winner = goldHunt.findWinner(players)

    // then
    assert(winner == None)
  }

  @Test
  def itMovesPlayerWithinMatrix() {
    // given
    val p = Player(1, 1, 1)
    val dispCell = DisplacementCell(1, 2)

    // when
    val movedPlayer = goldHunt.movePlayer(p, dispCell)

    // then
    assert(movedPlayer.x == 2)
    assert(movedPlayer.y == 3)
  }

  @Test
  def itMovesPlayerWrappingAroundTheMatrix() {
    // given
    val p = Player(1, 4, 3)
    val dispCell = DisplacementCell(3, 4)

    // when
    val movedPlayer = goldHunt.movePlayer(p, dispCell)

    // then
    assert(movedPlayer.x == 2)
    assert(movedPlayer.y == 3)
  }

  @Test
  def itMovesPlayerWrappingAroundTheMatrixForLargerDisplacements() {
    // given
    val goldHunt = GoldHunt(createMatrix3())
    val p = Player(1, 6, 1)
    val dispCell = DisplacementCell(7, 3)

    // when
    val movedPlayer = goldHunt.movePlayer(p, dispCell)

    // then
    assert(movedPlayer.x == 5)
    assert(movedPlayer.y == 0)
  }

  @Test
  def itMovesPlayerWrappingAroundTheMatrixWithNegativeIndexes() {
    // given
    val p = Player(1, 1, 2)
    val dispCell = DisplacementCell(-3, -3)

    // when
    val movedPlayer = goldHunt.movePlayer(p, dispCell)

    // then
    assert(movedPlayer.x == 3)
    assert(movedPlayer.y == 3)
  }

  @Test
  def findWinner() {

    // given
    val m2 = createMatrix2()
    val players2 = createPlayers2()
    val m3 = createMatrix3()
    val players3 = createPlayers3()

    // when
    val winner1 = goldHunt.play(players)
    val winner2 = GoldHunt(m2).play(players2)
    val winner3 = GoldHunt(m3).play(players3)

    // then
    assert(winner1.id == 4)
    assert(winner2.id == 2)
    assert(winner3.id == 1)
  }

  def createMatrix1() = {
    val m = Array.ofDim[Cell](5, 4) //new Array[Array[Cell]](5)
    m(0) = Array(DisplacementCell(2, 0), DisplacementCell(0, 2), TrapCell, DisplacementCell(3, 0))
    m(1) = Array(TrapCell, DisplacementCell(6, 0), DisplacementCell(0, 3), DisplacementCell(2, 0))
    m(2) = Array(DisplacementCell(0, -1), DisplacementCell(1, 0), GoldCell, DisplacementCell(0, -1))
    m(3) = Array(DisplacementCell(0, 3), DisplacementCell(0, 2), DisplacementCell(0, 2), DisplacementCell(0, 3))
    m(4) = Array(DisplacementCell(-1, 0), TrapCell, DisplacementCell(0, -3), TrapCell)
    m
  }

  def createPlayers1() = {
    Array(Player(1, 4, 2), Player(2, 2, 0), Player(3, 0, 3), Player(4, 2, 3))
  }

  def createMatrix2() = {
    val m = Array.ofDim[Cell](1, 2) //new Array[Array[Cell]](1)
    m(0) = Array(GoldCell, TrapCell)
    m
  }

  def createPlayers2() = {
    Array(Player(1, 0, 1), Player(2, 0, 0))
  }

  def createMatrix3() = {
    val m = Array.ofDim[Cell](8, 2) //new Array[Array[Cell]](8)
    m(0) = Array(GoldCell, DisplacementCell(-1, 5))
    m(1) = Array(DisplacementCell(0, 2), TrapCell)
    m(2) = Array(TrapCell, DisplacementCell(8, 0))
    m(3) = Array(DisplacementCell(-3, 0), TrapCell)
    m(4) = Array(TrapCell, DisplacementCell(0, -2))
    m(5) = Array(DisplacementCell(0, 0), TrapCell)
    m(6) = Array(TrapCell, DisplacementCell(7, 3))
    m(7) = Array(DisplacementCell(-4, 0), TrapCell)
    m
  }

  def createPlayers3() = {
    Array(Player(1, 7, 0), Player(2, 6, 1), Player(3, 2, 1))
  }

  def printMatrix(m: Array[Array[Cell]]) = {
    for (row <- m) {
      for (col <- row) {
        print(col + " | ")
      }
      println()
    }
  }
}
