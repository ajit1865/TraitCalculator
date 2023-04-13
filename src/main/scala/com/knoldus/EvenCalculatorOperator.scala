package com.knoldus

import com.typesafe.scalalogging.Logger

class EvenCalculatorOperator extends Operator {
  override def validate(operands: Seq[Double]): Boolean = {
    operands.nonEmpty && operands.head >= 1
  }

  override def validateAndExecute(operands: Seq[Double]): Seq[Double] = {
    if (validate(operands)) execute(operands)
    else
      throw new CalculatorException("Invalid Operand for Even of List")
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    val number = operands.map(_.toInt)
    evenOfAllElements(number)
  }

  private val logger: Logger = Logger(getClass.getName)
  //logger to check program flow
  logger.info("Checked EvenCalculatorOperator  executed successfully")

  private def evenOfAllElements(elementsToCalculate: Seq[Int]): Seq[Double] = {
    val evenNumbers = elementsToCalculate.filter(_ % 2 == 0)
    evenNumbers.map(_.toDouble)
  }
}