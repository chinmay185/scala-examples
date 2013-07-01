object MathWorks extends App {
	
	def isPrime(n: Int) = n > 1 && Range(2, math.sqrt(n).asInstanceOf[Int] + 1).forall(divisor => n % divisor != 0)

	def isEven(n: Int) = n % 2 == 0

	def isOdd(n: Int) = !isEven(n)

	def isMultipleOf3(n: Int) = n % 3 == 0

	def isMultipleOf5(n: Int) = n % 5 == 0

	def between(min: Int, max: Int) = (n: Int) => (n > min && n < max)

	def filterChain[T](numbers: Seq[T], fs: ((T) => (Boolean))*): Seq[T] = {
		fs match {
			case list if list.isEmpty => numbers
			case l => filterChain(numbers.filter(fs.head), fs.tail:_*)
		}
	}

	override def main(args: Array[String]) = {
		val numbers = Range(1, 50)
		println("Even numbers : " + filterChain[Int](numbers, isEven))
		println("Numbers between 3 and 15 : " + filterChain[Int](numbers, between(3, 15)))
		println("Odd numbers : " + filterChain[Int](numbers, isOdd))
		println("Prime numbers : " + filterChain[Int](numbers, isPrime))
		println("Odd Prime numbers : " + filterChain[Int](numbers, isOdd, isPrime))
		println("Even Prime numbers : " + filterChain[Int](numbers, isEven, isPrime))
		println("Multiples of 3 : " + filterChain[Int](numbers, isMultipleOf3))
		println("Multiples of 5 : " + filterChain[Int](numbers, isMultipleOf5))
		println("Multiples of 3 and 5 : " + filterChain[Int](numbers, isMultipleOf3, isMultipleOf5))
		println("Even Multiples of 3 and 5 between 10 and 40 : " + filterChain[Int](numbers, isEven, isMultipleOf3, isMultipleOf5, between(10, 40)))
		println("Odd primes between 2 and 32 : " + filterChain[Int](numbers, isOdd, isPrime, between(2, 32)))
	}
}