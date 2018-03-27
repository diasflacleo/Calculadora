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
        var textView_num0: TextView = findViewById(R.id.textView_num0)
        var textView_num1: TextView = findViewById(R.id.textView_num1)
        var textView_num2: TextView = findViewById(R.id.textView_num2)
        var textView_num3: TextView = findViewById(R.id.textView_num3)
        var textView_num4: TextView = findViewById(R.id.textView_num4)
        var textView_num5: TextView = findViewById(R.id.textView_num5)
        var textView_num6: TextView = findViewById(R.id.textView_num6)
        var textView_num7: TextView = findViewById(R.id.textView_num7)
        var textView_num8: TextView = findViewById(R.id.textView_num8)
        var textView_num9: TextView = findViewById(R.id.textView_num9)

        //operações
        var textView_op_add: TextView = findViewById(R.id.textView_op_add)
        var textView_op_min: TextView = findViewById(R.id.textView_op_min)
        var textView_op_mult: TextView = findViewById(R.id.textView_op_mult)
        var textView_op_div: TextView = findViewById(R.id.textView_op_div)

        //não numericos
        var textView_ac: TextView = findViewById(R.id.textView_ac)
        var textView_left_arrow: TextView = findViewById(R.id.textView_left_arrow)
        var textView_percent: TextView = findViewById(R.id.textView_percent)
        var textView_point: TextView = findViewById(R.id.textView_point)




        textView_display.setOnClickListener {
            //  textView_display.setBackgroundColor(Color.BLUE)


            var context: Context = getApplicationContext()
            var text: CharSequence = "Você pressionou o display"
            var duration: Int = Toast.LENGTH_SHORT
            Toast.makeText(context, text, duration).show()

        }

        // escreve no display os números e o ponto conforme digitados
        textView_num0.setOnClickListener { writeOnDisplayNumber(textView_num0.getText()) }
        textView_num1.setOnClickListener { writeOnDisplayNumber(textView_num1.getText()) }
        textView_num2.setOnClickListener { writeOnDisplayNumber(textView_num2.getText()) }
        textView_num3.setOnClickListener { writeOnDisplayNumber(textView_num3.getText()) }
        textView_num4.setOnClickListener { writeOnDisplayNumber(textView_num4.getText()) }
        textView_num5.setOnClickListener { writeOnDisplayNumber(textView_num5.getText()) }
        textView_num6.setOnClickListener { writeOnDisplayNumber(textView_num6.getText()) }
        textView_num7.setOnClickListener { writeOnDisplayNumber(textView_num7.getText()) }
        textView_num8.setOnClickListener { writeOnDisplayNumber(textView_num8.getText()) }
        textView_num9.setOnClickListener { writeOnDisplayNumber(textView_num9.getText()) }
        textView_point.setOnClickListener { writeOnDisplayNumber(textView_point.getText()) }

        //
        textView_ac.setOnClickListener { clearDisplay()}
        textView_op_add.setOnClickListener{ writeOnDisplayOperation(textView_op_add.getText())}
        textView_op_mult.setOnClickListener{ writeOnDisplayOperation(textView_op_mult.getText())}
        textView_op_min.setOnClickListener{ writeOnDisplayOperation(textView_op_min.getText())}
        textView_op_div.setOnClickListener{ writeOnDisplayOperation(textView_op_div.getText())}

        textView_equals.setOnClickListener { defineCalc()}

    }

    // essa função será chamada toda vez que for necessário escrever no display um numero.
    fun writeOnDisplayNumber(text : CharSequence) {
        if(textView_display.getText() == "0"){
            textView_display.setText("")
        }
        var display : CharSequence = "${textView_display.getText()}${text}"
        textView_display.setText(display)
        blinkEffect(Color.BLUE)
        //Log.i("button value--->",text as String)
        //Log.i("display-->",display as String)
    }

    // essa função será chamada toda vez que for necessário escrever no display um numero.
    fun writeOnDisplayOperation(opSymbol : CharSequence) {
        if(textView_display.getText().contains("+") ||
           textView_display.getText().contains("x") ||
           textView_display.getText().contains("-") ||
           textView_display.getText().contains("/")){

            printToast("Você já tem uma operação para calcular.")
            return
        }


        var display : CharSequence = "${textView_display.getText()}${opSymbol}"
        textView_display.setText(display)
        operand = opSymbol
    }

    fun defineCalc (){

        var calc = Calculo()

        textView_display.setText(
                calc.defineOperation(textView_display.getText() as String, operand))
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
        anim.setRepeatCount(1)
        anim.start()

    }

    fun printToast(message: CharSequence){
        var context: Context = getApplicationContext()
        var duration: Int = Toast.LENGTH_LONG
        Toast.makeText(context, message, duration).show()
    }

}


