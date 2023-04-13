package com.knoldus

import com.typesafe.scalalogging.Logger
import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.math.pow

object Calculator {
  // creating logger to check flow of program.
  private val logger: Logger = Logger(getClass.getName)

  def calculate(operator: String, operands: Seq[Double]): Future[Seq[Double]] = {

    operator match {
      case "+" => execute(new AdditionOperator(), operands)
      case "-" => execute(new SubtractionOperator(), operands)
      case "*" => execute(new ProductOperator(), operands)
      case "/" => execute(new DivisionOperator(), operands)
      case "sum" => execute(new SumOperator(), operands)
      case "!" => execute(new FactorialOperator(), operands)
      case "sqrt" => execute(new SquareRootOperator(), operands)
      case "^" => execute(new PowerOperator(), operands)
      case "odd" => execute(new OddCalculatorOperator(), operands)
      case "even" => execute(new EvenCalculatorOperator(), operands)
      case "fibonacci" => execute(new FibonacciCalculatorOperator(), operands)
      case "gcd" => execute(new GreatestCommonDivisorOperator(), operands)
      case _ => throw new CalculatorException("Invalid Exception")
    }
  }

  private def execute(operator: Operator, operands: Seq[Double]): Future[Seq[Double]] = {
    Future {
      operator.validateAndExecute(operands)
    }
  }

  def squareOfExpression(firstOperand: Double, secondOperand: Double): String = {
    val finalCalculate = if (firstOperand > 0 && secondOperand > 0) {
      val firstOperandSum = firstOperand + secondOperand
      val powerOfFirstOperand = firstOperandSum * firstOperandSum
      val secondOperandSum = (firstOperand * firstOperand) + (secondOperand * secondOperand) + (2 * firstOperand * secondOperand)
      if (powerOfFirstOperand == secondOperandSum) 1
      else
        -1
    }
    logger.info("Checked : SquareOfExpression Executed Successfully")
    if (finalCalculate == 1) "Equal"
    else
      "Not Equal"
  }

  def find(numbers: Seq[Double]): Future[Seq[Double]] = {
    // Define a helper function to calculate factorial asynchronously
    def factorialAsync(n: Double): Future[Double] = Future {
      (1 to n.toInt).foldLeft(1.0)(_ * _)
    }

    // Define a helper function to calculate 6 ^ number asynchronously
    def powerOfElement(n: Double): Future[Double] = Future {
      pow(6, n)
    }
    //logger to check program flow
    logger.info("Checked find Method executed successfully")

    // Use a foldLeft to iterate through the numbers sequence and filter the numbers
    // whose factorial is greater than 6 ^ number
    numbers.foldLeft(Future.successful(Seq.empty[Double])) { (acc, n) =>
      for {
        accRes <- acc
        fact <- factorialAsync(n)
        powSix <- powerOfElement(n)
      } yield {
        if (fact > powSix) {
          accRes :+ n
        } else {
          accRes
        }
      }
    }
  }

  def findAverageAfterChainingOperations(numbers: Seq[Double]): Future[Double] = {
    // Helper function to calculate fibonacci of a number
    def fibonacci(n: Double): BigInt = {
      if (n <= 1) n.toInt
      else (fibonacci(n - 1) + fibonacci(n - 2)).toInt
    }

    // Helper function to check if a number is odd
    def isOdd(n: Int): Boolean = n % 2 != 0

    // Asynchronously perform the operations on each number in the sequence
    val futureResults = Future.traverse(numbers)(num => Future {
      val fib = fibonacci(num)
      val oddNumbers = (0 to fib.toInt).filter(isOdd)
      oddNumbers.sum.toDouble
    })
    //logger to check program flow
    logger.info("Checked findAverageAfterChainingOperations Method executed successfully")

    // Calculate the average of the results when all futures complete
    futureResults.map(results => results.sum / numbers.length.toDouble)
  }

}

class AdditionOperator extends Operator {
  private val logger: Logger = Logger(getClass.getName)

  override def validate(operands: Seq[Double]): Boolean = {
    operands.length == 2 && operands.head >= 0 && operands.tail.forall(_ >= 0)
  }

  override def validateAndExecute(operands: Seq[Double]): Seq[Double] = {
    if (validate(operands)) execute(operands)
    else
      throw new CalculatorException("Invalid operand for Addition")
  }

  override def execute(operands: Seq[Double]): Seq[Double] = {
    val number = operands.map(_.toInt)
    Seq(additionOfTwoNumber(number))
  }
  //logger to check program flow
  logger.info("Checked AdditionOperator Method executed successfully")

  private def additionOfTwoNumber(elementsToCalculate: Seq[Int]): Double = {
    val sum = elementsToCalculate.reduce((a, b) => a + b)
    sum.toDouble
  }
}

class SubtractionOperator extends Operator {
  val logger: Logger = Logger(getClass.getName)

  override def validate(operands: Seq[Double]): Boolean = {
    operands.length == 2 && operands.head > 0 && operands.tail.forall(_ < operands.head)
  }

  override def validateAndExecute(operands: Seq[Double]): Seq[Double] = {
    if (validate(operands)) execute(operands)
    else
      throw new CalculatorException("Invalid Operand for Subtraction")
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    val number = operands.map(_.toInt)
    Seq(subtractionOfTwoNumber(number))
  }
  //logger to check program flow
  logger.info("Checked subtractionOfTwoNumber Method executed successfully")

  private def subtractionOfTwoNumber(elementsToCalculate: Seq[Int]): Double = {
    val subtraction = elementsToCalculate.reduce((a, b) => a - b)
    subtraction.toDouble
  }
}

class ProductOperator extends Operator {
  val logger: Logger = Logger(getClass.getName)

  override def validate(operands: Seq[Double]): Boolean = {
    operands.length == 2 && operands.head >= 0 && operands.tail.forall(_ >= 0)
  }

  override def validateAndExecute(operands: Seq[Double]): Seq[Double] = {
    if (validate(operands)) execute(operands)
    else
      throw new CalculatorException("Invalid Operand for Multiplication")
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    val number = operands.map(_.toInt)
    Seq(productOfElements(number))
  }
  //logger to check program flow
  logger.info("Checked productOfElements Method executed successfully")

  private def productOfElements(elementsToProduct: Seq[Int]): Double = {
    val product = elementsToProduct.reduce((a, b) => a * b)
    product.toDouble
  }
}

class DivisionOperator extends Operator {
  val logger: Logger = Logger(getClass.getName)

  override def validate(operands: Seq[Double]): Boolean = {
    operands.length == 2 && operands.head > 0 && operands.tail.forall(_ > 0)
  }

  override def validateAndExecute(operands: Seq[Double]): Seq[Double] = {
    if (validate(operands)) execute(operands)
    else
      throw new CalculatorException("Invalid Operand for Division")
  }

  override def execute(operands: Seq[Double]): Seq[Double] = {
    Seq(divisionOfElements(operands))
  }
  //logger to check program flow
  logger.info("Checked divisionOfElements Method executed successfully")

  private def divisionOfElements(elementsToDivide: Seq[Double]): Double = {
    val division = elementsToDivide.reduce((x, y) => x / y)
    division
  }
}

class FactorialOperator extends Operator {
  val logger: Logger = Logger(getClass.getName)

  override def validate(operands: Seq[Double]): Boolean = {
    operands.length == 1 && operands.head > 0
  }

  override def validateAndExecute(operands: Seq[Double]): Seq[Double] = {
    if (validate(operands))
      execute(operands)
    else
      throw new CalculatorException("Invalid operands for factorial")
  }
  //logger to check program flow
  logger.info("Checked FactorialOperator Method executed successfully")

  protected def execute(operands: Seq[Double]): Seq[Double] = {
    val number = operands.head.toInt
    Seq(factorial(number))
  }

  private def factorial(numberToCalculate: Int): Double = {
    if (numberToCalculate <= 1) 1
    else
      numberToCalculate * factorial(numberToCalculate - 1)
  }
}

class SquareRootOperator extends Operator {
  val logger: Logger = Logger(getClass.getName)

  override def validate(operands: Seq[Double]): Boolean = {
    operands.length == 1 && operands.head > 0
  }

  override def validateAndExecute(operands: Seq[Double]): Seq[Double] = {
    if (validate(operands)) execute(operands)
    else
      throw new CalculatorException("Invalid operands for SquareRoot")
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    val number = operands.head.toInt
    Seq(squareRoot(number))
  }
  //logger to check program flow
  logger.info("Checked SquareRootOperator Method executed successfully")

  private def squareRoot(numberToCalculateSquareRoot: Int, precision: Double = 0.0001): Double = {
    if (numberToCalculateSquareRoot < 1) 0
    else {
      @tailrec
      def sqrtIterator(helperNumber: Double): Double = {
        val nextNumber = 0.5 * (helperNumber + numberToCalculateSquareRoot / helperNumber)
        if (Math.abs(nextNumber - helperNumber) < precision) nextNumber
        else sqrtIterator(nextNumber)
      }

      sqrtIterator(numberToCalculateSquareRoot / 2)
    }
  }
}

class SumOperator extends Operator {
  val logger: Logger = Logger(getClass.getName)

  override def validate(operands: Seq[Double]): Boolean = {
    operands.nonEmpty && operands.head >= 0
  }

  override def validateAndExecute(operands: Seq[Double]): Seq[Double] = {
    if (validate(operands)) execute(operands)
    else
      throw new CalculatorException("Invalid Operand for Sum of List")
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    val number = operands.map(_.toInt)
    Seq(sumOfAllElements(number, 0))
  }
  //logger to check program flow
  logger.info("Checked SumOperator Method executed successfully")

  @tailrec
  private def sumOfAllElements(elementsToCalculate: Seq[Int], sum: Int): Double = {
    elementsToCalculate match {
      case Seq() => sum
      case head +: tail => sumOfAllElements(tail, sum + head)
    }
  }
}

class CalculatorException(message: String) extends Exception(message)
