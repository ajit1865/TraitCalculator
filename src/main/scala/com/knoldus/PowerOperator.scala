package com.knoldus

import com.typesafe.scalalogging.Logger

class PowerOperator extends Operator {
  override def validate(operands: Seq[Double]): Boolean = {
    operands.length == 2 && operands.head >= 0 && operands.tail.forall(_ >= 0)
  }

  override def validateAndExecute(operands: Seq[Double]): Seq[Double] = {
    if (validate(operands)) execute(operands)
    else
      throw new CalculatorException("Invalid operand for Power")
  }

  override def execute(operands: Seq[Double]): Seq[Double] = {
    val number = operands.map(_.toInt)
    Seq(powerCalculator(number))
  }

  val logger: Logger = Logger(getClass.getName)
  //logger to check program flow
  logger.info("Checked PowerOperator Method executed successfully")

  private def powerCalculator(elementsToCalculate: Seq[Int]): Double = {
    val base = elementsToCalculate.head
    val exponent = elementsToCalculate.tail.head

    if (exponent == 0) 1 // base case: anything raised to the power of 0 is 1
    else if (exponent == 1) base // base case: anything raised to the power of 1 is itself
    else {
      def powerHelper(base: Int, exponent: Int): Int = {
        if (exponent == 0) 1 // base case: anything raised to the power of 0 is 1
        else if (exponent % 2 == 0) {
          val result = powerHelper(base, exponent / 2)
          result * result
        } else {
          base * powerHelper(base, exponent - 1)
        }
      }

      powerHelper(base, exponent).toDouble
    }
  }
}

