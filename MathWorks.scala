object MathWorks extends App {
	
	def isPrime(n: Int) = {
		n > 1 && Range(2, math.sqrt(n).asInstanceOf[Int] + 1).forall(divisor => n % divisor != 0)
	}

	def isEven(n: Int) = {
		n % 2 == 0
	}

	def isOdd(n: Int) = {
		!isEven(n)
	}

	def multiplesOf3(n: Int) = {
		n % 3 == 0
	}

	def multiplesOf5(n: Int) = {
		n % 5 == 0
	}

	def filterChain(numbers: Seq[Int], fs: ((Int) => (Boolean))*): Seq[Int] = {
		fs match {
			case list if list.isEmpty => numbers
			case l => filterChain(numbers.filter(fs.head), fs.tail:_*)
		}
	}

	override def main(args: Array[String]) = {
		val numbers = Range(1, 50)
		println("Even numbers : " + filterChain(numbers, isEven))
		println("Odd numbers : " + filterChain(numbers, isOdd))
		println("Prime numbers : " + filterChain(numbers, isPrime))
		println("Odd Prime numbers : " + filterChain(numbers, isOdd, isPrime))
		println("Even Prime numbers : " + filterChain(numbers, isEven, isPrime))
		println("Multiples of 3 : " + filterChain(numbers, multiplesOf3))
		println("Multiples of 5 : " + filterChain(numbers, multiplesOf5))
		println("Multiples of 3 and 5 : " + filterChain(numbers, multiplesOf3, multiplesOf5))
	}
}