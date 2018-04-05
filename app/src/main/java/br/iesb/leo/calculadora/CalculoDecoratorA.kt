package br.iesb.leo.calculadora

import java.text.DecimalFormat

class CalculoDecoratorA {

    fun defineOperation(expression: String, operand: CharSequence): CharSequence{

        var exp: List<String> = expression.split("+","-","x","\u00F7","%")
        var firstNumber : Double = exp[0].toDouble()
        var secondNumber : Double = exp[1].toDouble()

        val resultOperation: Double = when(operand){

            "+" -> calc(firstNumber , secondNumber, object: MathOps{
                override fun operation(num1: Double, num2: Double) = num1+num2
            })

            "-" -> calc(firstNumber , secondNumber, object: MathOps{
                override fun operation(num1: Double, num2: Double) = num1-num2
            })

            "x" -> calc(firstNumber , secondNumber, object: MathOps{
                override fun operation(num1: Double, num2: Double) = num1*num2
            })

            "\u00F7" -> calc(firstNumber , secondNumber, object: MathOps{
                override fun operation(num1: Double, num2: Double) = num1/num2
            })

            "%" -> calc(firstNumber , secondNumber, object: MathOps{
                override fun operation(num1: Double, num2: Double) = (num1*num2)/100
            })

            else -> 0.0
        }

        //var res : String = resultOperation.toString()

        var res: String = resultOperation.format(4)
        return res

    }


    fun calc(num1: Double, num2: Double, mathOps: MathOps): Double{
        return mathOps.operation(num1,num2)
    }

    // formatar a String para que retire o '.0' desnecessário
    fun Double.format(fracDigits: Int): String{
        val decimalFormat = DecimalFormat()
        decimalFormat.setMaximumFractionDigits(fracDigits)
        return decimalFormat.format(this)

    }

}


