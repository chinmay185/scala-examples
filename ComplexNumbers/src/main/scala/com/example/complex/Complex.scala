package com.example.complex


case class Complex(real: Double, imag: Double) {

  def this(real: Double) = this(real, 0)

  def +(other: Complex): Complex = new Complex(real + other.real, imag + other.imag)
}

object Complex {
  implicit def tupleOfIntegersToTupleOfDoubles(n: (Int, Int)): (Double, Double) = (n._1.asInstanceOf[Double], n._2.asInstanceOf[Double])

  implicit def intToComplex(n: Int) = Complex(n, 0)

  implicit def tupleOfDoublesToComplex(tuple: (Double, Double)): Complex = new Complex(tuple._1, tuple._2)

  implicit def tupleOfIntsToComplex(tuple: (Int, Int)): Complex = new Complex(tuple._1, tuple._2)

}

