package com.example.a7_p1_bhakta_malaykumar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    var decimal : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvZero.setOnClickListener { appendOnExpression("0", canClear = true) }
        tvOne.setOnClickListener { appendOnExpression("1",true) }
        tvTwo.setOnClickListener { appendOnExpression("2",true) }
        tvThree.setOnClickListener { appendOnExpression("3",true) }
        tvFour.setOnClickListener { appendOnExpression("4",true) }
        tvFive.setOnClickListener { appendOnExpression("5",true) }
        tvSix.setOnClickListener { appendOnExpression("6",true) }
        tvSeven.setOnClickListener { appendOnExpression("7",true) }
        tvEight.setOnClickListener { appendOnExpression("8",true) }
        tvNine.setOnClickListener { appendOnExpression("9",true) }
        tvDecimal.setOnClickListener { appendOnExpression(".", true)  }

        tvPlus.setOnClickListener { appendOnExpression("+",false) }
        tvMinus.setOnClickListener { appendOnExpression("-",false) }
        tvMultiply.setOnClickListener { appendOnExpression("*",false) }
        tvDivide.setOnClickListener { appendOnExpression("/",false) }
        tvModulas.setOnClickListener { appendOnExpression("%",false) }
        tvParen.setOnClickListener { appendOnExpression("()",false) }
        tvClealear.setOnClickListener {
            tvExpression.text= ""
            tvResult.text = ""
            tvExpression.setBackgroundColor(Color.WHITE)
            tvExpression.setTextColor(Color.GRAY)
        }
        //tvChangeSign.setOnClickListener { appendOnExpression(1,true) }

        tvResult.setOnClickListener { try {
            //val expression = (1+2).build()
            val expression = ExpressionBuilder(tvExpression.text.toString()).build()
            val result = expression.evaluate()
            if(result < 0){
                tvExpression.setBackgroundColor(Color.BLACK)
                tvExpression.setTextColor(Color.WHITE)
            }
            val longResult = result.toLong()
            if (result == longResult.toDouble()) {
                tvExpression.text = longResult.toString()
            }
            else
                tvExpression.text = result.toString()

        }catch (e: Exception){
            Log.d("Exception","message: " + e.message)
            tvExpression.text = "0"
            //tvResult.text = "0"
            tvExpression.setBackgroundColor(Color.RED)
        }}
    }

    fun appendOnExpression(string: String, canClear: Boolean){

        tvExpression.setTextColor(Color.GRAY)
        tvExpression.setBackgroundColor(Color.WHITE)

        if(decimal && string == "."){
            tvExpression.append(string)
            Log.d("message", "decimal: " +decimal)
            decimal = false
            return
        }
        if ( canClear){
            tvResult.text = ""
            tvExpression.append(string)
            tvExpression.setBackgroundColor(Color.WHITE)
            tvExpression.setTextColor(Color.GRAY)
        }
        else{
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text = ""
        }
    }
}
