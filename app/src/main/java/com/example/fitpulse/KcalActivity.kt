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

class KcalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kcal)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.kcal_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<ImageView>(R.id.btn_back_arrow).setOnClickListener {
            finish()
        }

        val etWeight = findViewById<EditText>(R.id.et_kcal_weight)
        val etHeight = findViewById<EditText>(R.id.et_kcal_height)
        val etAge = findViewById<EditText>(R.id.et_kcal_age)
        val btnCalculate = findViewById<Button>(R.id.btn_calculate_kcal)
        val tvResult = findViewById<TextView>(R.id.tv_kcal_result)

        btnCalculate.setOnClickListener {
            val weightStr = etWeight.text.toString()
            val heightStr = etHeight.text.toString()
            val ageStr = etAge.text.toString()

            if (weightStr.isNotEmpty() && heightStr.isNotEmpty() && ageStr.isNotEmpty()) {
                val weight = weightStr.toFloat()
                val height = heightStr.toFloat()
                val age = ageStr.toInt()

                val kcal = (10 * weight) + (6.25 * height) - (5 * age) - 78
                tvResult.text = String.format(Locale.getDefault(), "Maintenance Kcal: %.0f kcal/day", kcal)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
