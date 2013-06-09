import scala.io.Source

// object declares (kind of) static methods.
object GettingStarted {

	// defining functions
	def max(x: Int, y: Int): Int = if (x > y) x else y

	// main method
	def main(args: Array[String]): Unit = {

		sampleDataTypes()
		declaringAndLoopingArrays()
		objectCasting()

		// see why this doesn't work
		/*val k = List(1, 2, 3, 4, 5)
		java.util.Arrays.deepToString(k.asInstanceOf[Array[Object]])
		java.util.Arrays.deepToString(k)
		val l = Array(1, 2, 3, 4, 5)
		java.util.Arrays.deepToString(l)
		java.util.Arrays.deepToString(l.asInstanceOf[Array[Object]])*/

	}

	def sampleDataTypes() {
		// Scala is pure object oriented. There are no primitive types.
		val x = 5 // x is an Int (it infers the type)
		val y = false // y is a boolean
		val u = Unit // u: Unit.type = object scala.Unit
		val n = null // n: Null = null
		val s = 'symbolLiteral // s: Symbol = 'symbolLiteral
		val s1 = "single line string" // s1: String = single line string
		val s2 = """multi
					line 
					string"""
	}
	def objectCasting() = {
		println(30.isInstanceOf[Int]) // true
		// casting object to anther types
		math.sqrt(30).asInstanceOf[Int] 
	}
	def declaringAndLoopingArrays() {
		// declaring array
		val array = new Array[String](3)
		array(0) = "first element"
		array(1) = "second element"
		array(2) = "last element"

		for( s <- array) {
			println(s)
		}

		// another way to declare array
		val numStrs = Array.apply("one", "two", "three", "four")
		numStrs.foreach(println)

		// yet another way to declare array
		val nums = Array(1, 2, 3, 4, 5, 6)

		// sequence comprehension using for 
		val evens = for {
			n <- nums
			if n % 2 == 0
		} yield n

		val odds = for(
			n <- nums
			if n % 2 != 0
		) yield n
	}
}
