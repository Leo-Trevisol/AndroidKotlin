package com.project.primeiroprojetokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import org.json.JSONObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        result = findViewById(R.id.text_result)

        val buttonConvert = findViewById<Button>(R.id.bt_converter)

        buttonConvert.setOnClickListener(View.OnClickListener {
            converter()
        })
    }

    fun converter() {
        val radioGroup = findViewById<RadioGroup>(R.id.radio_group)

        val radioSelected = radioGroup.checkedRadioButtonId

        val currency = when (radioSelected) {
            R.id.radio_usd -> "USD"
            R.id.radio_eur -> "EUR"
            else -> return
        }

        val editText = findViewById<EditText>(R.id.edit_quanto_e)

        val value = editText.text.toString()

        if (value.isEmpty()) {
            return
        }

        Thread {

            val url =
                URL("https://atway.tiagoaguiar.co/free/api/currency/convert?q=${currency}_BRL")

            val conn = url.openConnection() as HttpsURLConnection

            try {

                val data = conn.inputStream.bufferedReader().readText()

                val obj = JSONObject(data)

                runOnUiThread {
                    val res = obj.getDouble("${currency}_BRL")

                    result.text = "R$ ${"%.4f".format(value.toDouble() * res)}"

                    result.visibility = View.VISIBLE

                }

            } finally {
                conn.disconnect()
            }
        }.start()

    }
}
