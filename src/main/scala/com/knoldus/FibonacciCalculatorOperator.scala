package com.knoldus

import com.typesafe.scalalogging.Logger

import scala.annotation.tailrec

class FibonacciCalculatorOperator extends Operator {
  override def validate(operands: Seq[Double]): Boolean = {
    operands.nonEmpty && operands.head >= 1
  }

  override def validateAndExecute(operands: Seq[Double]): Seq[Double] = {
    if (validate(operands)) execute(operands)
    else
      throw new CalculatorException("Invalid Operand for Fibonacci Series of List")
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    val number = operands.map(_.toInt)
    fibonacciOfAllElements(number)
  }

  val logger: Logger = Logger(getClass.getName)
  //logger to check program flow
  logger.info("Checked FibonacciCalculatorOperator  executed successfully")

  private def fibonacciOfAllElements(elementsToCalculate: Seq[Int]): Seq[Double] = {
    var fibonacci = Seq.empty[Double]

    @tailrec
    def helperOfFibonacciSeries(helperNumber: Int, first: Int, second: Int): Seq[Double] = {
      if (helperNumber == 0) fibonacci
      else {
        val sum = first + second
        fibonacci = fibonacci :+ sum.toDouble // append the sum to the fibonacci sequence
        helperOfFibonacciSeries(helperNumber - 1, second, sum) // recursively calculate the next Fibonacci number
      }
    }

    // Initialize the fibonacci sequence with the first two elements (0 and 1)
    fibonacci = Seq(0, 1)
    helperOfFibonacciSeries(elementsToCalculate.head - 2, 0, 1)
  }
}