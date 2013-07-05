package main.scala

import collection.mutable
import annotation.tailrec

class DropAndGather(val matrix: Array[Array[Int]], target: Position) {

  def printMatrix(matrix: Array[Array[Int]]): Unit = {
    matrix.foreach(row =>
      println(row.foreach(cell =>
        print(cell + " ")
      ))
    )
    println()
  }

  def solve(timer: Int, insects: List[Insect]): Int = {

    @tailrec
    def solve0(timer: Int, insects: List[Insect]): Int = {

      if (timer == 0)
        matrix(target.x)(target.y)
      else {
        val movedInsects = insects.map(insect =>
          insect.move(matrix))

        solve0(timer - 1, movedInsects)
      }
    }

    val droppersFirstList = insects.sortBy(insect =>
      insect match {
        case g: Gatherer => true
        case d: Dropper => false
      })
    def moved = droppersFirstList.map(insect =>
      insect.move(matrix))

    val insectsStillInMatrix = moved.filter(insect => insect.hasNextMove)

    solve0(timer, insectsStillInMatrix)
  }

}

case class Position(p: (Int, Int)) {
  val x = p._1
  val y = p._2
}

abstract class Insect(private val directions: String) {
  private val nextPositions = directions.map(_ match {
    case 'E' => new Position(0, 1)
    case 'W' => new Position(0, -1)
    case 'S' => new Position(1, 0)
    case 'N' => new Position(-1, 0)
  })
  val sequence = new Circular[Position](nextPositions.toList)

  def move(matrix: Array[Array[Int]]): Insect

  def hasNextMove: Boolean
}

case class Dropper(var p: Position, directions: String) extends Insect(directions) {
  override def toString = "D" + p

  def move(matrix: Array[Array[Int]]): Dropper = {

    if (hasNextMove) {
      matrix(p.x)(p.y) += 1
      val moveByPosition = sequence.next()
      p = new Position(p.x + moveByPosition.x, p.y + moveByPosition.y)
    }
    new Dropper(p, directions.substring(1, directions.length).concat(directions.substring(0, 1)))
  }

  override def hasNextMove = (p.x >= 0 && p.x < 10) && (p.y >= 0 && p.y < 10)
}

case class Gatherer(var p: Position, directions: String) extends Insect(directions) {
  override def toString = "G" + p

  def move(matrix: Array[Array[Int]]): Gatherer = {
    if (hasNextMove) {
      if (matrix(p.x)(p.y) > 0)
        matrix(p.x)(p.y) -= 1
      val moveByPosition = sequence.next()
      p = new Position(p.x + moveByPosition.x, p.y + moveByPosition.y)
    }
    new Gatherer(p, directions.substring(1, directions.length).concat(directions.substring(0, 1)))
  }

  override def hasNextMove = (p.x >= 0 && p.x < 10) && (p.y >= 0 && p.y < 10)
}

class Circular[A](list: List[A]) extends Iterator[A] {

  val elements = new mutable.Queue[A] ++= list
  var pos = 0

  def next() = {
    if (pos == elements.length)
      pos = 0
    val value = elements(pos)
    pos = pos + 1
    value
  }

  def hasNext = !elements.isEmpty

  def add(a: A) {
    elements += a
  }

  override def toString() = elements.toString()
}
