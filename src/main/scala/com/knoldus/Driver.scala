package com.knoldus

import scala.util.Failure
import scala.util.Success

object Driver extends App {
  implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global

  Calculator.calculate("!", Seq(5)).onComplete {
    case Success(result) => println(s"Factorial is ${result.head}")
    case Failure(_) => println("Exception Enter Valid Operand for Factorial")
  }

  Calculator.calculate("sqrt", Seq(25)).onComplete {
    case Success(result) => println(f"Square root is ${result.head}%.2f")
    case Failure(_) => println("Exception Invalid operand for SquareRoot")
  }

  Calculator.calculate("+", Seq(10, 25)).onComplete {
    case Success(result) => println(s"Addition is ${result.head}")
    case Failure(_) => println("Exception in Addition Enter Valid Operands")
  }

  Calculator.calculate("-", Seq(100, 25)).onComplete {
    case Success(result) => println(s"Subtraction is ${result.head}")
    case Failure(_) => println("Exception in Subtraction Enter Valid Operands")
  }

  Calculator.calculate("*", Seq(10, 25)).onComplete {
    case Success(result) => println(s"Product is ${result.head}")
    case Failure(_) => println("Exception in Product Enter Valid Operands")
  }

  Calculator.calculate("/", Seq(10, 25)).onComplete {
    case Success(result) => println(s"Division is ${result.head}")
    case Failure(_) => println("Exception in Division Enter Valid Operands")
  }

  Calculator.calculate("sum", Seq(10, 15, 25)).onComplete {
    case Success(result) => println(s"Sum of all elements  is ${result.head}")
    case Failure(_) => println("Exception in Sum Enter Valid Operands")
  }

  Calculator.calculate("^", Seq(10, 2)).onComplete {
    case Success(result) => println(s"Power of  elements  is ${result.head}")
    case Failure(_) => println("Exception in Power Enter Valid Operands")
  }

  Calculator.calculate("odd", Seq(10, 2, 1, 2, 3, 4, 5, 6, 7)).onComplete {
    case Success(result) => println(s"Odd among elements  is $result")
    case Failure(_) => println("Exception in Odd Enter Valid Operands")
  }

  Calculator.calculate("even", Seq(10, 2, 1, 2, 3, 4, 5, 6, 7)).onComplete {
    case Success(result) => println(s"Even among elements  is $result")
    case Failure(_) => println("Exception in Even Enter Valid Operands")
  }

  Calculator.calculate("fibonacci", Seq(8)).onComplete {
    case Success(result) => println(s"Fibonacci is $result")
    case Failure(_) => println("Exception in Fibonacci Enter Valid Operands")
  }

  Calculator.calculate("gcd", Seq(12, 18)).onComplete {
    case scala.util.Success(result) => println(s"Greatest Common Divisor is $result")
    case scala.util.Failure(_) => println("Exception in GCD Enter Valid Operands")
  }
  println(s"Result of Expression : " + Calculator.squareOfExpression(5, 5)) // return Equals

  Calculator.find(Seq(1, -2, 3, 4, 5)).onComplete {
    case Success(result) => println(s"Finding greatest is : $result")
    case Failure(_) => println("Exception in Find Method")
  }

  Calculator.findAverageAfterChainingOperations(Seq(4, 5, 3, 8)).onComplete {
    case Success(result) => println(s"Finding Fibonacci add is : $result")
    case Failure(_) => println("Exception in Find Fibonacci Add Method")
  }

  Thread.sleep(1000)
}

