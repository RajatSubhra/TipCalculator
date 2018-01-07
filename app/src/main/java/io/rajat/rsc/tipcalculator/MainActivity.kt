package io.rajat.rsc.tipcalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener, TextWatcher {

    var lastProgress : Int = 0
    var showPercentage: TextView? = null
    var totalBill: EditText? = null
    var switchButton : Switch? = null
    var resultField : TextView? = null
    var seekBar : SeekBar? = null
    var myBill:Double? = 0.0

    fun evaluate() {
        if(!totalBill?.text.isNullOrBlank()) {
            myBill = totalBill?.text.toString().toDouble()

        }else{
            myBill = 0.0
        }
        //Toast.makeText(this,myBill.toString(),Toast.LENGTH_SHORT).show()
        var result:Double = myBill?.times(lastProgress) as Double
        result = result.div(100.0)
        if(switchButton?.isChecked as Boolean){
            result = result.toInt().toDouble()
        }
        resultField?.text = result.toString()


    }
    override fun afterTextChanged(s: Editable?) {

        evaluate()
    }


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }


    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        showPercentage?.text = lastProgress.toString()+"%"
        evaluate()
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        //Toast.makeText(this,"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();
        lastProgress = progress
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        totalBill = findViewById(R.id.editText)
        showPercentage = findViewById(R.id.textView4)
        seekBar = findViewById(R.id.seekBar)
        switchButton = findViewById(R.id.switch1)
        resultField = findViewById(R.id.textView6)

        // Set the Switch is OFF
        switchButton?.isChecked = false

        switchButton?.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked)
            {
                evaluate()
                //Toast.makeText(this,"Checked",Toast.LENGTH_SHORT).show()
            }
            else
            {
                evaluate()
                //Toast.makeText(this,"UnChecked",Toast.LENGTH_SHORT).show()`
            }

        }


        // seekbar listner
        seekBar?.setOnSeekBarChangeListener(this)
        seekBar?.progress = lastProgress


        // total Bill

        totalBill?.addTextChangedListener(this)
    }



}

