package main.scala

import annotation.tailrec

/*
MGPT Problem - Hunt for Gold
*/
class GoldHunt(matrix: Array[Array[Cell]]) {

  val maxX = matrix.size
  val maxY = matrix(0).size

  @tailrec
  final def play(players: Array[Player]): Player = {
    findWinner(players) match {
      case Some(winner) => winner
      case _ => {
        val movedPlayers: Array[Player] = players.map(p =>
          matrix(p.x)(p.y) match {
            case disp: DisplacementCell => movePlayer(p, disp)
            case _ => p
          }
        )
        play(movedPlayers)
      }
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

  def findWinner(players: Array[Player]): Option[Player] = {
    players.find(p =>
      matrix(p.x)(p.y) match {
        case GoldCell => true
        case _ => false
      }
    )
  }

}

object GoldHunt {
  def apply(matrix: Array[Array[Cell]]) = new GoldHunt(matrix)
}

trait Cell

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

