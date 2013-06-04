package main.scala

import annotation.tailrec

/*
MGPT Problem - Hunt for Gold
*/
class GoldHunt(m: Array[Array[Cell]]) {

  val maxX = m.size
  val maxY = m(0).size

  @tailrec
  final def play(players: Array[Player]): Player = {
    val (gameOver, winner) = isGameOver(players)
    if (gameOver) {
      println("Winner " + winner)
      winner
    }
    else {
      val movedPlayers: Array[Player] = players.map(p =>
        m(p.x)(p.y) match {
          case disp: DisplacementCell => movePlayer(p, disp)
          case _ => p
        }
      )
      play(movedPlayers)
    }
  }


  def movePlayer(p: Player, disp: DisplacementCell) = {

    def moveTo(cellDisplacementValue: Int, playerPosition: Int, bound: Int) = {
      val toMoveBy = math.abs(cellDisplacementValue) % bound
      var moveTo = (if (cellDisplacementValue > 0) playerPosition + toMoveBy else if (cellDisplacementValue < 0) playerPosition - toMoveBy else playerPosition) % bound
      if (moveTo < 0)
        moveTo = moveTo + bound
      moveTo
    }

    p.x = moveTo(disp.dx, p.x, maxX)
    p.y = moveTo(disp.dy, p.y, maxY)

    p
  }

  def isGameOver(players: Array[Player]): (Boolean, Player) = {
    var actuallyOver = false
    var actualWinner: Player = null
    for (p <- players) {
      val (gameOver, winningPlayer) = m(p.x)(p.y) match {
        case GoldCell => (true, p)
        case _ => (false, null)
      }
      if (gameOver) {
        actuallyOver = true
        actualWinner = winningPlayer
      }
    }
    (actuallyOver, actualWinner)
  }

}

object GoldHunt {
  def apply(matrix: Array[Array[Cell]]) = new GoldHunt(matrix)
}

abstract class Cell

case object GoldCell extends Cell {
  override def toString = "Gold"
}

case object TrapCell extends Cell {
  override def toString = "Trap"
}

case class DisplacementCell(dx: Int, dy: Int) extends Cell {
  override def toString = "Disp"
}

class Player(val id: Int, var x: Int, var y: Int) {
  override def toString = "Player " + id + " is at (" + x + "," + y + ")"
}

object Player {
  def apply(id: Int, x: Int, y: Int) = new Player(id, x, y)
}

