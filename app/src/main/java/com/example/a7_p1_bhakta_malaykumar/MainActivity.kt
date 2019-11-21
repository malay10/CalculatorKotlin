package com.example.a7_p1_bhakta_malaykumar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvOne.setOnClickListener { appendOnExpression("1",true) }
        tvTwo.setOnClickListener { appendOnExpression("2",true) }
        tvThree.setOnClickListener { appendOnExpression("3",true) }
        tvFour.setOnClickListener { appendOnExpression("4",true) }
        tvFive.setOnClickListener { appendOnExpression("5",true) }
        tvSix.setOnClickListener { appendOnExpression("6",true) }
        tvSeven.setOnClickListener { appendOnExpression("7",true) }
        tvEight.setOnClickListener { appendOnExpression("8",true) }
        tvNine.setOnClickListener { appendOnExpression("9",true) }


        tvPlus.setOnClickListener { appendOnExpression("+",false) }
        tvMinus.setOnClickListener { appendOnExpression("-",false) }
        tvMultiply.setOnClickListener { appendOnExpression("*",false) }
        tvDivide.setOnClickListener { appendOnExpression("/",false) }
        tvModulas.setOnClickListener { appendOnExpression("%",false) }
        tvParen.setOnClickListener { appendOnExpression("()",false) }
        tvClealear.setOnClickListener {
            tvExpression.text= ""
            tvResult.text = ""}
        //tvChangeSign.setOnClickListener { appendOnExpression(1,true) }

        tvResult.setOnClickListener { try {
            val expression = ExpressionBuilder(tvExpression.text.toString()).build()
            val result = expression.evaluate()
            val longResult = result.toLong()
            if (result == longResult.toDouble())
                tvResult.text = longResult.toString()
            else
                tvResult.text = result.toString(0)

        }catch (e: Exception){
            Log.d("Exception","message: " + e.message)
        }}


    }

    fun appendOnExpression(string: String, canClear: Boolean){
        if ( canClear){
            tvResult.text = ""
            tvExpression.append(string)
        }
        else{
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text = ""
        }
    }
}
