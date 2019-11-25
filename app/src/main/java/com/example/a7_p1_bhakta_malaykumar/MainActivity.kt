package com.example.a7_p1_bhakta_malaykumar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    var decimal : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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


        tvPlus.setOnClickListener { appendOnExpression("+", false, opearation = true) }
        tvMinus.setOnClickListener { appendOnExpression("-", false, opearation = true) }
        tvMultiply.setOnClickListener { appendOnExpression("*", false, opearation = true) }
        tvDivide.setOnClickListener { appendOnExpression("/", false, opearation = true) }
        tvModulas.setOnClickListener { appendOnExpression("%", false, opearation = true) }
        tvParen.setOnClickListener { appendOnExpression("()", false, opearation = false) }
        tvClealear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
            tvExpression.setBackgroundColor(Color.TRANSPARENT)
            tvExpression.setTextColor(Color.GRAY)
            decimal = false
            print("ALL CLEAR")
        }
        //tvChangeSign.setOnClickListener { appendOnExpression(1,true) }

        tvResult.setOnClickListener {

            try {

                //Calculation()
                //return
                //val expression = (1+2).build()
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                if (result < 0) {
                    tvExpression.setBackgroundColor(Color.BLACK)
                    tvExpression.setTextColor(Color.WHITE)
                }
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    tvExpression.text = longResult.toString()
                } else
                    tvExpression.text = result.toString()

            } catch (e: Exception) {
                Log.d("Exception", "message: " + e.message)
                tvExpression.text = "0"
                //tvResult.text = "0"
                tvExpression.setBackgroundColor(Color.RED)
            }
            tvChangeSign.isActivated = true
        }

        tvDecimal.setOnClickListener {
            if(!decimal) {
                appendOnExpression(".", true, opearation = false)
                decimal = true
            }
            else
                print("Already Decimal")
        }

        tvChangeSign.setOnClickListener {
            tvExpression.text = (tvExpression.text.toString().toDouble() * -1 ).toString()
            Log.d( "Message", (tvExpression.text.toString().toDouble() * -1).toString())
        }
    }

    fun appendOnExpression(string: String, canClear: Boolean, opearation: Boolean){

        tvExpression.setTextColor(Color.GRAY)
        tvExpression.setBackgroundColor(Color.TRANSPARENT)
        if (opearation){
            decimal = false
        }

        if ( canClear){
            tvResult.text = ""
            tvExpression.append(string)
            tvExpression.setBackgroundColor(Color.TRANSPARENT)
            tvExpression.setTextColor(Color.GRAY)
        }
        else{
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
        }
    }

    fun calculation(){
        print("========================================================")
        print(tvExpression.text)
    }

}
