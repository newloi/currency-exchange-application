package com.example.currencyexchange

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val input = findViewById<EditText>(R.id.input)
        val output = findViewById<TextView>(R.id.output)

        val currencies = arrayOf("Viet Nam - Dong", "Korea - Won", "United States - Dollar", "Thailand - Bath", "India - Rupee")
        val currencyAdpater = ArrayAdapter<String>(
            this, android.R.layout.simple_dropdown_item_1line,
            currencies
        )
        var currencyInput: String = "Viet Nam - Dong"
        var currencyOutput: String = "Viet Nam - Dong"
        var tmp: Double = 0.0


        findViewById<Spinner>(R.id.currencyInput).run {
            adapter = currencyAdpater
            onItemSelectedListener = object : OnItemSelectedListener,
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
                    currencyInput = p0.getItemAtPosition(p2).toString()
                    when(currencyInput) {
                        "Viet Nam - Dong" -> tmp = input.text.toString().toDoubleOrNull() ?: 0.0
                        "Korea - Won" -> tmp = (input.text.toString().toDoubleOrNull() ?: 0.0) *  17.43
                        "United States - Dollar" -> tmp = (input.text.toString().toDoubleOrNull() ?: 0.0) * 25575
                        "Thailand - Bath" -> tmp = (input.text.toString().toDoubleOrNull() ?: 0.0) * 752.81
                        "India - Rupee" -> tmp = (input.text.toString().toDoubleOrNull() ?: 0.0) * 298.91
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
//                    tmp = input.text.toString().toDouble()
                }

                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    return false
                }
            }
        }
        findViewById<Spinner>(R.id.currencyOutput).run {
            adapter = currencyAdpater
            onItemSelectedListener = object : OnItemSelectedListener,
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
                    currencyOutput = p0.getItemAtPosition(p2).toString()
                    when(currencyOutput) {
                        "Viet Nam - Dong" -> output.text = "₫ " + String.format(Locale.getDefault(), "%.2f", tmp)
                        "Korea - Won" -> output.text = "₩ " + String.format(Locale.getDefault(), "%.2f", tmp * 0.057)
                        "United States - Dollar" -> output.text = "$ "+ String.format(Locale.getDefault(), "%.2f", tmp * 0.000039)
                        "Thailand - Bath" -> output.text = "฿ " + String.format(Locale.getDefault(), "%.2f", tmp * 0.0013)
                        "India - Rupee" -> output.text = "₹ " + String.format(Locale.getDefault(), "%.2f", tmp * 0.0033)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    output.text =  input.text
                }

                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    return false
                }
            }
        }

        input.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                when(currencyInput) {
                    "Viet Nam - Dong" -> tmp = s.toString().toDoubleOrNull() ?: 0.0
                    "Korea - Won" -> tmp = (s.toString().toDoubleOrNull() ?: 0.0) *  17.43
                    "United States - Dollar" -> tmp = (s.toString().toDoubleOrNull() ?: 0.0) * 25575
                    "Thailand - Bath" -> tmp = (s.toString().toDoubleOrNull() ?: 0.0) * 752.81
                    "India - Rupee" -> tmp = (s.toString().toDoubleOrNull() ?: 0.0) * 298.91
                }
                when(currencyOutput) {
                    "Viet Nam - Dong" -> output.text = "₫ " + String.format(Locale.getDefault(), "%.2f", tmp)
                    "Korea - Won" -> output.text = "₩ " + String.format(Locale.getDefault(), "%.2f", tmp * 0.057)
                    "United States - Dollar" -> output.text = "$ "+ String.format(Locale.getDefault(), "%.2f", tmp * 0.000039)
                    "Thailand - Bath" -> output.text = "฿ " + String.format(Locale.getDefault(), "%.2f", tmp * 0.0013)
                    "India - Rupee" -> output.text = "₹ " + String.format(Locale.getDefault(), "%.2f", tmp * 0.0033)
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }
}