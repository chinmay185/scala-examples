package com.example.complex

import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith
import org.specs2.mutable._

@RunWith(classOf[JUnitRunner])
class ComplexNumberSpec extends Specification {

  "Addition" should {
    "return a complex number that is sum of two complex numbers" in {
      val c1 = Complex(2.0, 3.0)
      val c2 = Complex(3.0, 4.0)

      val c3 = c1 + c2

      val expectedComplexNumber = Complex(5.0, 7.0)

      c3 must_== expectedComplexNumber

    }
    "return a complex number that is sum of a real and a complex number" in {
      val c1 = 5
      val c2 = new Complex(2, 3)

      val c3 = c1 + c2

      val expectedComplexNumber = new Complex(7, 3)

      c3 must_== expectedComplexNumber

    }
    "return a complex number that is sum of a complex number in form of a tuple and another complex number" in {
      val c1 = (5, 3)
      val c2 = new Complex(2, 3)

      import Complex.tupleOfIntsToComplex
      val c3 = c1 + c2

      val expectedComplexNumber = new Complex(7, 6)

      c3 must_== expectedComplexNumber

    }
  }
}
