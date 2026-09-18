package com.example.fitpulse

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class BmiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bmi)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.bmi_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<ImageView>(R.id.btn_back_arrow).setOnClickListener {
            finish()
        }

        val etWeight = findViewById<EditText>(R.id.et_bmi_weight)
        val etHeight = findViewById<EditText>(R.id.et_bmi_height)
        val btnCalculate = findViewById<Button>(R.id.btn_calculate_bmi)
        val tvResult = findViewById<TextView>(R.id.tv_bmi_result)

        btnCalculate.setOnClickListener {
            val weightStr = etWeight.text.toString()
            val heightStr = etHeight.text.toString()

            if (weightStr.isNotEmpty() && heightStr.isNotEmpty()) {
                val weight = weightStr.toFloat()
                val heightCm = heightStr.toFloat()
                val heightM = heightCm / 100

                if (heightM > 0) {
                    val bmi = weight / (heightM * heightM)
                    val category = when {
                        bmi < 18.5 -> "Underweight"
                        bmi < 25 -> "Normal"
                        bmi < 30 -> "Overweight"
                        else -> "Obese"
                    }
                    tvResult.text = String.format(Locale.getDefault(), "BMI: %.2f\nCategory: %s", bmi, category)
                }
            } else {
                Toast.makeText(this, "Please enter weight and height", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
