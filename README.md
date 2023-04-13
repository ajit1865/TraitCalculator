## What is scala

Scala is a general-purpose programming language providing support for both object-oriented programming and functional programming. The language has a strong static type system. Designed to be concise, many of Scala's design decisions are aimed to address criticisms of Java.

## Question
## Calculator
### This calculator program supports various mathematical operations and can work with different numbers of operands. Here's how it works:

### Operations Supported
#### Addition (+)

Number of operands: 2 
Result: Adds the two operands together.

#### Subtraction (-)
Number of operands: 2

Result: Subtracts the second operand from the first operand.
#### Multiplication (*)
Number of operands: 2

Result: Multiplies the two operands together.
#### Division (/)
Number of operands: 2

Result: Divides the first operand by the second operand.
#### Exponentiation (^)
Number of operands: 2

Result: Raises the first operand to the power of the second operand, or multiplies the first operand by itself the number of times specified by the second operand.
#### Square Root (sqrt)
Number of operands: 1

Result: Calculates the square root of the operand.
#### Factorial (!)
Number of operands: 1

Result: Calculates the factorial of the number.
#### Sum (sum)
Number of operands: Variable

Result: Adds up all the operands.
#### Greatest Common Divisor (GCD)
Number of operands: 2

Result: Calculates the greatest common divisor (GCD) of the two operands.
#### Odd Numbers (odd)
Number of operands: Variable

Result: Returns all the odd operands.
#### Even Numbers (even)
Number of operands: Variable

Result: Returns all the even operands.
#### Fibonacci Series (fibonacci)
Number of operands: 1

Result: Generates a Fibonacci series up to the specified operand.

### For Logger
Use Dependencies

libraryDependencies ++= Seq(

  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
  "ch.qos.logback" % "logback-classic" % "1.3.5"
)
### How to Use
- Clone the repository to your local machine.
- Run the calculator program.
- Enter the desired operation and operands.
- View the calculated result.


## Run Locally

Clone the project

```bash
run this command in terminal
git clone https://github.com/ajit1865/TraitCalculator.git

```
