import org.junit.{Assert, Test}
import SpringerSunSpotAnalyser._

class SpringerSunSpotAnalyserSpecs {


  @Test
  def itCalculatesSolarActivityScoreForGridOne() {
    // given
    val gridSize = 5
    val heatData = Array.ofDim[Int](gridSize, gridSize)
    heatData(0) = Array(5, 3, 1, 2, 0)
    heatData(1) = Array(4, 1, 1, 3, 2)
    heatData(2) = Array(2, 3, 2, 4, 3)
    heatData(3) = Array(0, 2, 3, 3, 2)
    heatData(4) = Array(1, 0, 2, 4, 3)

    val matrix = Matrix(heatData, gridSize)

    // when
    val actualScore = calculateSolarActivityScore(matrix)

    val expectedScore = List(13, 15, 11, 9, 7, 18, 22, 20, 18, 14, 12, 18, 22, 23, 17, 8, 15, 23, 26, 19, 3, 8, 14, 17, 12)

    // then
    Assert.assertEquals(expectedScore, actualScore)
  }

  @Test
  def itCalculatesSolarActivityScoreForGridTwo() {
    // given
    val gridSize = 3

    val heatData = Array.ofDim[Int](gridSize, gridSize)
    heatData(0) = Array(4, 2, 3)
    heatData(1) = Array(2, 2, 1)
    heatData(2) = Array(3, 2, 1)

    val matrix = Matrix(heatData, gridSize)

    // when
    val actualScore = calculateSolarActivityScore(matrix)

    val expectedScore = List(10, 14, 8, 15, 20, 11, 9, 11, 6)

    // then
    Assert.assertEquals(expectedScore, actualScore)
  }

  /* @Test
   def itCalculatesRelevantSolarActivityScoreForGridOne() {
     // given
     val gridSize = 5
     val t = 1

     val heatData = Array.ofDim[Int](gridSize, gridSize)
     heatData(0) = Array(5, 3, 1, 2, 0)
     heatData(1) = Array(4, 1, 1, 3, 2)
     heatData(2) = Array(2, 3, 2, 4, 3)
     heatData(3) = Array(0, 2, 3, 3, 2)
     heatData(4) = Array(1, 0, 2, 4, 3)

     val matrix = Matrix(heatData, gridSize)

     // when
     val actualScore = calculateRelevantSolarActivityScore(matrix, t)

     val expectedScore = (3, 3, 26)

     // then
     Assert.assertEquals(expectedScore, actualScore)
   }*/


}
