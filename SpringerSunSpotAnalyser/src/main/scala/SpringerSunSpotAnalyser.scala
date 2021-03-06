class SpringerSunSpotAnalyser(input: Array[Array[Int]], gridSize: Int) {

  // constructor
  private val cells = Array.ofDim[Cell](gridSize, gridSize)
  for (row <- 0 until gridSize) {
    for (col <- 0 until gridSize) {
      cells(col)(row) = Cell(col, row, input(col)(row))
    }
  }
  val matrix = Matrix(cells, gridSize)

  private def calculateSolarActivity() = {
    val heatScores = Array.ofDim[Cell](matrix.gridSize, matrix.gridSize)

    for (row <- 0 until matrix.gridSize) {
      for (col <- 0 until matrix.gridSize) {
        val currentCell = matrix.heatData(col)(row)

        // find all adjacent cells to current cell
        val adjacentCells = List[Option[Cell]](Some(currentCell), matrix.north(currentCell), matrix.northEast(currentCell),
          matrix.east(currentCell), matrix.southEast(currentCell), matrix.south(currentCell),
          matrix.southWest(currentCell), matrix.west(currentCell), matrix.northWest(currentCell))

        // calculate adjacent solar activity score for current cell based on its adjacent cells
        val adjacentSolarActivities = adjacentCells.map(cell =>
          cell match {
            case Some(c) => c.score
            case _ => 0
          })
        heatScores(col)(row) = Cell(row, col, adjacentSolarActivities.sum)
      }
    }
    //printMatrix(heatScores)
    heatScores
  }

  def printMatrix(heatScores: Array[Array[Cell]]) {
    heatScores.foreach {
      row =>
        row.foreach(cell =>
          print(cell + " "))
        println()
    }
  }

  def calculateSolarActivityScore() =
    calculateSolarActivity().toList.flatten.map(cell => cell.score)

  def calculateRelevantSolarActivityScore(t: Int) =
    calculateSolarActivity().toList.flatten.sorted.take(t)

}

case class Matrix(heatData: Array[Array[Cell]], gridSize: Int) {

  def outsideBounds(x: Int, y: Int) = x < 0 || x >= gridSize || y < 0 || y >= gridSize

  def north(cell: Cell) = {
    if (!outsideBounds(cell.x, cell.y - 1))
      Some(heatData(cell.x)(cell.y - 1))
    else
      None
  }

  def northEast(cell: Cell) = {
    if (!outsideBounds(cell.x + 1, cell.y - 1))
      Some(heatData(cell.x + 1)(cell.y - 1))
    else
      None
  }

  def east(cell: Cell) = {
    if (!outsideBounds(cell.x + 1, cell.y))
      Some(heatData(cell.x + 1)(cell.y))
    else
      None
  }

  def southEast(cell: Cell) = {
    if (!outsideBounds(cell.x + 1, cell.y + 1))
      Some(heatData(cell.x + 1)(cell.y + 1))
    else
      None
  }

  def south(cell: Cell) = {
    if (!outsideBounds(cell.x, cell.y + 1))
      Some(heatData(cell.x)(cell.y + 1))
    else
      None
  }

  def southWest(cell: Cell) = {
    if (!outsideBounds(cell.x - 1, cell.y + 1))
      Some(heatData(cell.x - 1)(cell.y + 1))
    else
      None
  }

  def west(cell: Cell) = {
    if (!outsideBounds(cell.x - 1, cell.y))
      Some(heatData(cell.x - 1)(cell.y))
    else
      None
  }

  def northWest(cell: Cell) = {
    if (!outsideBounds(cell.x - 1, cell.y - 1))
      Some(heatData(cell.x - 1)(cell.y - 1))
    else
      None
  }

}

case class Cell(x: Int, y: Int, score: Int) extends Ordered[Cell] {

  // for sorting by decreasing value of solar activity score
  def compare(that: Cell): Int = that.score compareTo score

  override def toString = "Cell(x:" + x + ", y:" + y + ", score:" + score + ")"

}