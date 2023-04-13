package com.knoldus

import com.typesafe.scalalogging.Logger

import scala.annotation.tailrec

class GreatestCommonDivisorOperator extends Operator {
  override def validate(operands: Seq[Double]): Boolean = {
    operands.length == 2 && operands.head >= 1 && operands.tail.forall(_ > 0)
  }

  override def validateAndExecute(operands: Seq[Double]): Seq[Double] = {
    if (validate(operands)) execute(operands)
    else
      throw new CalculatorException("Invalid Operand for GCD of List")
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    val number = operands.map(_.toInt)
    calculateGreatestCommonDivisor(number)
  }

  val logger: Logger = Logger(getClass.getName)
  //logger to check program flow
  logger.info("Checked GreatestCommonDivisorOperator  executed successfully")

  private def calculateGreatestCommonDivisor(elementsToCalculate: Seq[Int]): Seq[Double] = {
    var result: Seq[Double] = Seq()

    // Find the GCD of the first two elements in the sequence
    var gcd = elementsToCalculate.headOption.getOrElse(1) // default to 1 if sequence is empty
    for (num <- elementsToCalculate.drop(1)) {
      gcd = calculateGCD(gcd, num) // Call a helper function to calculate GCD
    }

    // Add the GCD to the result sequence as a Double
    result = result :+ gcd.toDouble

    // Return the result sequence
    result
  }

  // Helper function to calculate GCD using the Euclidean Algorithm
  @tailrec
  private def calculateGCD(firstNumber: Int, secondNumber: Int): Int = {
    if (secondNumber == 0) firstNumber
    else calculateGCD(secondNumber, firstNumber % secondNumber)
  }
}
