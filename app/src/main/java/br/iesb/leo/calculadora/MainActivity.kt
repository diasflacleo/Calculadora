package br.iesb.leo.calculadora

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    var operand : CharSequence = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textView_display: TextView = findViewById(R.id.textView_display)

        //numeros
       /* var textView_num0: TextView = findViewById(R.id.textView_num0)
        var textView_num1: TextView = findViewById(R.id.textView_num1)
        var textView_num2: TextView = findViewById(R.id.textView_num2)
        var textView_num3: TextView = findViewById(R.id.textView_num3)
        var textView_num4: TextView = findViewById(R.id.textView_num4)
        var textView_num5: TextView = findViewById(R.id.textView_num5)
        var textView_num6: TextView = findViewById(R.id.textView_num6)
        var textView_num7: TextView = findViewById(R.id.textView_num7)
        var textView_num8: TextView = findViewById(R.id.textView_num8)
        var textView_num9: TextView = findViewById(R.id.textView_num9)*/

        //operações
        /*var textView_op_add: TextView = findViewById(R.id.textView_op_add)
        var textView_op_min: TextView = findViewById(R.id.textView_op_min)
        var textView_op_mult: TextView = findViewById(R.id.textView_op_mult)
        var textView_op_div: TextView = findViewById(R.id.textView_op_div)*/

        //não numericos
        /*var textView_ac: TextView = findViewById(R.id.textView_ac)
        var textView_left_arrow: TextView = findViewById(R.id.textView_left_arrow)
        var textView_percent: TextView = findViewById(R.id.textView_percent)
        var textView_point: TextView = findViewById(R.id.textView_point)*/




        textView_display.setOnClickListener {
            //  textView_display.setBackgroundColor(Color.BLUE)


            var context: Context = getApplicationContext()
            var text: CharSequence = "Você pressionou o display"
            var duration: Int = Toast.LENGTH_SHORT
            Toast.makeText(context, text, duration).show()

        }

        // escreve no display os números e o ponto conforme digitados
        textView_num0.setOnClickListener { writeOnDisplayNumber(textView_num0) }
        textView_num1.setOnClickListener { writeOnDisplayNumber(textView_num1) }
        textView_num2.setOnClickListener { writeOnDisplayNumber(textView_num2) }
        textView_num3.setOnClickListener { writeOnDisplayNumber(textView_num3) }
        textView_num4.setOnClickListener { writeOnDisplayNumber(textView_num4) }
        textView_num5.setOnClickListener { writeOnDisplayNumber(textView_num5) }
        textView_num6.setOnClickListener { writeOnDisplayNumber(textView_num6) }
        textView_num7.setOnClickListener { writeOnDisplayNumber(textView_num7) }
        textView_num8.setOnClickListener { writeOnDisplayNumber(textView_num8) }
        textView_num9.setOnClickListener { writeOnDisplayNumber(textView_num9) }
        textView_point.setOnClickListener { writeOnDisplayNumber(textView_point) }

        //
        textView_ac.setOnClickListener { clearDisplay()}
        textView_op_add.setOnClickListener{ writeOnDisplayOperation(textView_op_add.text)}
        textView_op_mult.setOnClickListener{ writeOnDisplayOperation(textView_op_mult.text)}
        textView_op_min.setOnClickListener{ writeOnDisplayOperation(textView_op_min.text)}
        textView_op_div.setOnClickListener{ writeOnDisplayOperation(textView_op_div.text)}
        textView_percent.setOnClickListener { writeOnDisplayOperation(textView_percent.text) }

        textView_equals.setOnClickListener { defineCalc()}

    }

    // essa função será chamada toda vez que for necessário escrever no display um numero.
    fun writeOnDisplayNumber(text : TextView) {
        if(textView_display.text == "0"){
            textView_display.setText("")
        }
        var display : CharSequence = "${textView_display.text}${text.text}"
        textView_display.setText(display)
        blinkEffect(Color.BLUE)
        //Log.i("button value--->",text as String)
        //Log.i("display-->",display as String)
    }

    // essa função será chamada toda vez que for necessário escrever no display um numero.
    fun writeOnDisplayOperation(opSymbol : CharSequence) {
        if(textView_display.text.contains("+") ||
           textView_display.text.contains("x") ||
           textView_display.text.contains("-") ||
           textView_display.text.contains("/")){

            printToast("Você já tem uma operação para calcular.")
            return
        }


        var display : CharSequence = "${textView_display.text}${opSymbol}"
        textView_display.setText(display)
        operand = opSymbol
    }

    fun defineCalc (){

        var exp: List<String> = textView_display.text.split("+","-","x","\u00F7","%")
        // \u00F7 é o encoding C/C++/Java do operador de divisão.
        if(exp[0].equals("") || exp[1].equals("")){

            printToast("Expressão inválida. Verifique a expressão.")
            return
        }

        //var calc = Calculo()
        var calc = CalculoDecoratorA()

        textView_display.setText(
                calc.defineOperation(textView_display.text as String, operand))
        //Log.i("add result --->",result as String)
    }

    //limpeza do display
    fun clearDisplay(){
        textView_display.setText("0");
        operand = ""
        blinkEffect(Color.RED)
    }

    fun blinkEffect(colorHint: Int){

        var anim:ObjectAnimator = ObjectAnimator.ofInt(textView_display,"textColor", colorHint, Color.WHITE)
        anim.setDuration(1000)
        anim.setEvaluator(ArgbEvaluator())
        //anim.setRepeatMode(Animation.REVERSE)
        anim.setRepeatCount(0)
        anim.start()

    }


    fun printToast(message: CharSequence){
        var context: Context = getApplicationContext()
        var duration: Int = Toast.LENGTH_LONG
        Toast.makeText(context, message, duration).show()
    }

}


