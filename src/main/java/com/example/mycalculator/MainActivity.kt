package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    var lastNumeric = false
    var lastDot = false
    fun onDigit(view: View)
    {
        if((view as Button).text=="CLR") {
            Display.text = ""
            lastNumeric=false
            lastDot=false
        }
        else {
            Display.append((view as Button).text)
            lastNumeric=true
        }
//        Getting the text of the button with te hhepl of view
//        Toast.makeText(this,"Seven was clicked",Toast.LENGTH_SHORT).show()
    }
    fun onDecimal(view:View)
    {
        if(lastNumeric && !lastDot)
        {
            Display.append(".")
            lastNumeric = false
            lastDot=true
        }
    }
    fun onEqual(view: View)
    {
           if(lastNumeric)
           {
               var Displayvalue=Display.text.toString()
               var prefix= ""
               try{
                   if(Displayvalue.startsWith("-"))
                   {
                       prefix="-"
                       Displayvalue=Displayvalue.substring(1)
                   }
                     if(Displayvalue.contains("-"))
                     {
                         val splitvalue=Displayvalue.split("-")
                         var first =splitvalue[0]
                         var second = splitvalue[1]

                         if(!prefix.isEmpty())
                         {
                             first=prefix+first
                         }

                         Display.text=(first.toFloat()-second.toFloat()).toString()
                     }
                   else if(Displayvalue.contains("+")){
                         val splitvalue=Displayvalue.split("+")
                         var first =splitvalue[0]
                         var second = splitvalue[1]
                         Display.text=(first.toDouble()+second.toDouble()).toString()
                     }
                   else if(Displayvalue.contains("/"))
                     {
                         val splitvalue=Displayvalue.split("/")
                         var first =splitvalue[0]
                         var second = splitvalue[1]
                         if(second=="0")
                             Display.text="Infinity"
                         else
                         Display.text=(first.toFloat()/second.toFloat()).toString()
                     }
                   else
                     {
                         val splitvalue=Displayvalue.split("*")
                         var first =splitvalue[0]
                         var second = splitvalue[1]
                         Display.text=(first.toDouble()*second.toDouble()).toString()
                     }
               }
               catch(e:ArithmeticException){
                  e.printStackTrace()
               }

           }
    }
     fun onOperator(view:View)
     {
         if(lastNumeric&&!isOperatorAdded(Display.text.toString()))
         {
             Display.append((view as Button).text)
             lastNumeric=false
             lastDot=false
         }
     }

    private fun isOperatorAdded(value:String):Boolean
    {
        return if(value.startsWith("-")){
            false}
        else
        {
            value.contains("/")||value.contains("+")||value.contains("-")||value.contains("*")
        }
    }
}

