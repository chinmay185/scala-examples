object SpringerSunSpotAnalyser {

  private def calculateSolarActivity(m: Matrix) = {
    val heatScores = Array.ofDim[Int](m.gridSize, m.gridSize)

    for (x <- 0 until m.gridSize) {
      for (y <- 0 until m.gridSize) {
        val adjacentScores = List[Option[Int]](Some(m.heatData(x)(y)), m.north(x, y), m.northEast(x, y), m.east(x, y),
          m.southEast(x, y), m.south(x, y), m.southWest(x, y), m.west(x, y), m.northWest(x, y))
        heatScores(x)(y) = adjacentScores.foldLeft(0) {
          (acc, e) =>
            val v = e match {
              case Some(value) => value
              case None => 0
            }
            acc + v
        }
      }
    }
    heatScores
  }

  def calculateSolarActivityScore(m: Matrix) = {
    calculateSolarActivity(m).toList.flatten
  }

  /*def calculateRelevantSolarActivityScore(m: Matrix, t: Int) = {
    calculateSolarActivity(m)
  }*/
}

case class Matrix(heatData: Array[Array[Int]], gridSize: Int) {

  def outsideBounds(x: Int, y: Int): Boolean = {
    x < 0 || x >= gridSize || y < 0 || y >= gridSize
  }

  def north(x: Int, y: Int): Option[Int] = {
    if (!outsideBounds(x - 1, y))
      Some(heatData(x - 1)(y))
    else
      None
  }

  def northEast(x: Int, y: Int): Option[Int] = {
    if (!outsideBounds(x - 1, y + 1))
      Some(heatData(x - 1)(y + 1))
    else
      None
  }

  def east(x: Int, y: Int): Option[Int] = {
    if (!outsideBounds(x, y + 1))
      Some(heatData(x)(y + 1))
    else
      None
  }

  def southEast(x: Int, y: Int): Option[Int] = {
    if (!outsideBounds(x + 1, y + 1))
      Some(heatData(x + 1)(y + 1))
    else
      None
  }

  def south(x: Int, y: Int): Option[Int] = {
    if (!outsideBounds(x + 1, y))
      Some(heatData(x + 1)(y))
    else
      None
  }

  def southWest(x: Int, y: Int): Option[Int] = {
    if (!outsideBounds(x + 1, y - 1))
      Some(heatData(x + 1)(y - 1))
    else
      None
  }

  def west(x: Int, y: Int): Option[Int] = {
    if (!outsideBounds(x, y - 1))
      Some(heatData(x)(y - 1))
    else
      None
  }

  def northWest(x: Int, y: Int): Option[Int] = {
    if (!outsideBounds(x - 1, y - 1))
      Some(heatData(x - 1)(y - 1))
    else
      None
  }

}

class Cell(val x: Int, val y: Int, val v: Int)