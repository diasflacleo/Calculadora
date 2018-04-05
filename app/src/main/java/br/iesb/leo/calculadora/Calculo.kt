package br.iesb.leo.calculadora

import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * Created by Leonardo on 22/03/2018.
 */
public class Calculo{

    var num1: Double = 0.0
    var num2: Double = 0.0

    constructor (num1: Double, num2: Double){
        this.num1 = num1
        this.num2 = num2
    }

    constructor(){

    }



    fun defineOperation(expression: String, operand: CharSequence): CharSequence{

        var exp: List<String> = expression.split("+","-","x","/","%")
        var firstNumber : Double = exp[0].toDouble()
        var secondNumber : Double = exp[1].toDouble()

        val resultOperation: Double = when(operand){
            "+" -> add(firstNumber, secondNumber)
            "-" -> sub(firstNumber, secondNumber)
            "x" -> mult(firstNumber, secondNumber)
            "/" -> div(firstNumber, secondNumber)
            "%" -> percent(firstNumber,secondNumber)
            else -> 0.0
        }

        //var res : String = resultOperation.toString()

        var res: String = resultOperation.format(4)
        return res

    }


    private fun add(numA: Double, numB: Double): Double{

       return numA+numB

    }

    private fun sub(numA: Double, numB: Double): Double{

        return numA-numB

    }

    private fun mult(numA: Double, numB: Double): Double{

        return numA*numB

    }

    private fun div(numA: Double, numB: Double): Double{

        return numA/numB

    }

    private fun percent(numA: Double, numB: Double): Double{


        return (numA*numB)/100

    }

    // formatar a String para que retire o '.0' desnecessário
    fun Double.format(fracDigits: Int): String{
        val decimalFormat = DecimalFormat()
        decimalFormat.setMaximumFractionDigits(fracDigits)
        return decimalFormat.format(this)

    }



}