package pl.jacekkosy

import scala.language.postfixOps

object ChainOfResponsibility {
  def main(args: Array[String]): Unit = {
    val addition = new Addition(new Subtraction(new Multiplication(new Division(null))))
    addition.calculate(new CalcExpression(1, 5, '+'))
    addition.calculate(new CalcExpression(2, 5, '-'))
    addition.calculate(new CalcExpression(3, 5, '*'))
    addition.calculate(new CalcExpression(10, 5, '/'))
    addition.calculate(new CalcExpression(10, 0, '/'))
    addition.calculate(new CalcExpression(3, 5, '^'))
  }
}

class CalcExpression(val numberOne: Int, val numberTwo: Int, val operator : Char)

trait Chain {
  def calculate(calcExpression: CalcExpression) : Unit
}

class Addition(val chain: Chain) extends Chain {
  override def calculate(calcExpression: CalcExpression): Unit = {
    if (calcExpression.operator.==('+')) {
      println(calcExpression.numberOne + " + " + calcExpression.numberTwo
        + " = " + (calcExpression.numberOne + calcExpression.numberTwo))
    } else {
      chain.calculate(calcExpression)
    }
  }
}

class Subtraction(val chain: Chain) extends Chain {
  override def calculate(calcExpression: CalcExpression): Unit = {
    if (calcExpression.operator.==('-')) {
      println(calcExpression.numberOne + " - " + calcExpression.numberTwo
       + " = " + (calcExpression.numberOne - calcExpression.numberTwo))
    } else {
      chain.calculate(calcExpression)
    }
  }
}
class Multiplication(val chain: Chain) extends Chain {
  override def calculate(calcExpression: CalcExpression): Unit = {
    if (calcExpression.operator.==('*')) {
      println(calcExpression.numberOne + " * " + calcExpression.numberTwo
        + " = " + (calcExpression.numberOne * calcExpression.numberTwo))
    } else {
      chain.calculate(calcExpression)
    }
  }
}
class Division(val chain: Chain) extends Chain {
  override def calculate(calcExpression: CalcExpression): Unit = {
    if (calcExpression.operator == ('/') && calcExpression.numberTwo != 0) {
      println(calcExpression.numberOne + " / " + calcExpression.numberTwo
        + " = " + (calcExpression.numberOne / calcExpression.numberTwo))
    } else if (chain != null) {
      chain.calculate(calcExpression)
    } else {
      println("Calculator doesn't support this operation")
    }
  }
}
