package com.knoldus

import com.typesafe.scalalogging.Logger

class OddCalculatorOperator extends Operator {
  override def validate(operands: Seq[Double]): Boolean = {
    operands.nonEmpty && operands.head >= 1
  }

  override def validateAndExecute(operands: Seq[Double]): Seq[Double] = {
    if (validate(operands)) execute(operands)
    else
      throw new CalculatorException("Invalid Operand for Odd of List")
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    val number = operands.map(_.toInt)
    oddOfAllElements(number)
  }

  val logger: Logger = Logger(getClass.getName)
  //logger to check program flow
  logger.info("Checked OddCalculatorOperator Method executed successfully")

  private def oddOfAllElements(elementsToCalculate: Seq[Int]): Seq[Double] = {
    val oddNumbers = elementsToCalculate.filter(_ % 2 == 1)
    oddNumbers.map(_.toDouble)
  }
}
