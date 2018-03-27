package br.iesb.leo.calculadora

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

        var exp: List<String> = expression.split("+","-","x","/")

        exp[1]



        val resultOperation: Double = when(operand){
            "+" -> add(exp[0] as Double, exp[1] as Double)

            else -> 0.0
        }

        var resultConverted : CharSequence = resultOperation as CharSequence
        return resultConverted;

    }


    private fun add(numA: Double, numB: Double): Double{

       return this.num1+this.num2

    }

    fun sub(numA: Double, numB: Double): Double{

        return this.num1-this.num2

    }

    fun mult(numA: Double, numB: Double): Double{

        return this.num1*this.num2

    }

    fun div(numA: Double, numB: Double): Double{

        return this.num1/this.num2

    }


}