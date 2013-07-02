package test.scala

import org.junit.Test
import main.scala._
import junit.framework.Assert
import main.scala.Position
import main.scala.Dropper

class DropAndGatherSpecs {

  @Test
  def itFindsTheAmountOfPheromAtAParticularInstanceAtAParticularPlaceInMatrix1() {
    // given
    val d1 = new Dropper(new Position(1, 0), "ESE")
    val g1 = new Gatherer(new Position(5, 2), "ENES")
    val d2 = new Dropper(new Position(2, 9), "S")
    val g2 = new Gatherer(new Position(7, 5), "ESEN")
    val d3 = new Dropper(new Position(3, 8), "WSWN")

    val matrix = Array.ofDim[Int](10, 10)
    val insects = List(d1, g1, d2, g2, d3)
    val timer = 9
    val target = new Position(4, 5)

    val dropAndGather = new DropAndGather(matrix, target)

    // when
    val pheromAmount = dropAndGather.solve(timer, insects)

    // then
    Assert.assertEquals(pheromAmount, 1)
  }

  @Test
  def itFindsTheAmountOfPheromAtAParticularInstanceAtAParticularPlaceInMatrix2() {
    // given
    val d = new Dropper(new Position(5, 1), "E")
    val g = new Gatherer(new Position(5, 8), "W")

    val matrix = Array.ofDim[Int](10, 10)
    val insects = List(d, g)
    val timer = 5
    val target = new Position(5, 3)

    val dropAndGather = new DropAndGather(matrix, target)

    // when
    val pheromAmount = dropAndGather.solve(timer, insects)

    // then
    Assert.assertEquals(0, pheromAmount)
  }

  @Test
  def itFindsTheAmountOfPheromAtAParticularInstanceAtAParticularPlaceInMatrix3() {
    // given
    val matrix = Array.ofDim[Int](10, 10)
    val insects: List[Insect] = List()
    val timer = 10
    val target = new Position(5, 3)

    val dropAndGather = new DropAndGather(matrix, target)

    // when
    val pheromAmount = dropAndGather.solve(timer, insects)

    // then
    Assert.assertEquals(0, pheromAmount)
  }

  @Test
  def itFindsTheAmountOfPheromAtAParticularInstanceAtAParticularPlaceInMatrix4() {
    // given
    val g1 = new Gatherer(new Position(3, 4), "NWESNE")
    val g2 = new Gatherer(new Position(4, 3), "ENSEWN")
    val g3 = new Gatherer(new Position(7, 1), "SEWNEE")
    val g4 = new Gatherer(new Position(5, 5), "E")
    val matrix = Array.ofDim[Int](10, 10)
    val insects= List(g1, g2, g3, g4)
    val timer = 9
    val target = new Position(0, 0)

    val dropAndGather = new DropAndGather(matrix, target)

    // when
    val pheromAmount = dropAndGather.solve(timer, insects)

    // then
    Assert.assertEquals(0, pheromAmount)
  }

  @Test
  def itFindsTheAmountOfPheromAtAParticularInstanceAtAParticularPlaceInMatrix5() {
    // given
    val d1 = new Dropper(new Position(1, 1), "S")
    val d2 = new Dropper(new Position(3, 1), "E")
    val d3 = new Dropper(new Position(9, 1), "S")
    val matrix = Array.ofDim[Int](10, 10)
    val insects= List(d1, d2, d3)
    val timer = 5
    val target = new Position(8, 8)

    val dropAndGather = new DropAndGather(matrix, target)

    // when
    val pheromAmount = dropAndGather.solve(timer, insects)

    // then
    Assert.assertEquals(0, pheromAmount)
  }

}
