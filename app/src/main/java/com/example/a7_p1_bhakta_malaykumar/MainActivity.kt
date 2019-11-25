package com.example.a7_p1_bhakta_malaykumar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    //for single decimal in number
    var decimal : Boolean = false
    //after evaluation result, keep the answer or discard
    var newStep = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Input numbers
        tvZero.setOnClickListener { appendOnExpression("0", canClear = true, opearation = false) }
        tvOne.setOnClickListener { appendOnExpression("1", true, opearation = false) }
        tvTwo.setOnClickListener { appendOnExpression("2", true, opearation = false) }
        tvThree.setOnClickListener { appendOnExpression("3", true, opearation = false) }
        tvFour.setOnClickListener { appendOnExpression("4", true, opearation = false) }
        tvFive.setOnClickListener { appendOnExpression("5", true, opearation = false) }
        tvSix.setOnClickListener { appendOnExpression("6", true, opearation = false) }
        tvSeven.setOnClickListener { appendOnExpression("7", true, opearation = false) }
        tvEight.setOnClickListener { appendOnExpression("8", true, opearation = false) }
        tvNine.setOnClickListener { appendOnExpression("9", true, opearation = false) }

        //operations
        tvPlus.setOnClickListener { appendOnExpression("+", false, opearation = true) }
        tvMinus.setOnClickListener { appendOnExpression("-", false, opearation = true) }
        tvMultiply.setOnClickListener { appendOnExpression("*", false, opearation = true) }
        tvDivide.setOnClickListener { appendOnExpression("/", false, opearation = true) }
        tvModulas.setOnClickListener { appendOnExpression("%", false, opearation = true) }
        //TODO parenthesis
        //tvParen.setOnClickListener { appendOnExpression("()", false, opearation = false) }
        //Clear function
        tvClealear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
            tvExpression.setBackgroundColor(Color.TRANSPARENT)
            tvExpression.setTextColor(Color.GRAY)
            decimal = false
            newStep = false
            print("ALL CLEAR")
        }

        // "="
        tvResult.setOnClickListener {
            newStep = true
            try {

                //building expression to evaluate
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                //Negative number
                if (result < 0) {
                    tvExpression.setBackgroundColor(Color.BLACK)
                    tvExpression.setTextColor(Color.WHITE)
                }
                //type saftey
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    tvExpression.text = longResult.toString()
                } else
                    tvExpression.text = result.toString()

            } catch (e: Exception) {
                //error in evaluation expression
                Log.d("Exception", "message: " + e.message)
                tvExpression.text = "0"
                //tvResult.text = "0"
                tvExpression.setBackgroundColor(Color.RED)
            }
            //tvChangeSign.isActivated = true
        }
        // "."
        tvDecimal.setOnClickListener {
            if(!decimal) {
                appendOnExpression(".", true, opearation = false)
                decimal = true
            }
            else
                print("Already Decimal")
        }

        //TODO optional sign change app crashes if sign changed between operation eg:- 8+
        //uncomment for other functionality
        tvChangeSign.setOnClickListener {
            //tvExpression.text = (tvExpression.text.toString().toDouble() * -1 ).toString()
            //Log.d( "Message", (tvExpression.text.toString().toDouble() * -1).toString())
        }
    }

    //method to display the text on lable
    fun appendOnExpression(string: String, canClear: Boolean, opearation: Boolean){

        //Setting/resetting color of text and background
        tvExpression.setTextColor(Color.GRAY)
        tvExpression.setBackgroundColor(Color.TRANSPARENT)

        //if number entered instead of operator
        if(newStep && canClear){
            newStep = false
            tvExpression.text = ""
        }
        else if(newStep)
            newStep = false


        //allowing decimal for new number
        if (opearation){
            decimal = false
        }

        //number input
        if ( canClear){
            tvResult.text = ""
            tvExpression.append(string)
//            tvExpression.setBackgroundColor(Color.TRANSPARENT)
//            tvExpression.setTextColor(Color.GRAY)
        }

        //operation called + - * / etc
        else{
            //tvResult.text = ""
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
        }
    }


}
