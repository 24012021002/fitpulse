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

class WaterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_water)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.water_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<ImageView>(R.id.btn_back_arrow).setOnClickListener {
            finish()
        }

        val etWeight = findViewById<EditText>(R.id.et_water_weight)
        val btnCalculate = findViewById<Button>(R.id.btn_calculate_water)
        val tvResult = findViewById<TextView>(R.id.tv_water_result)

        btnCalculate.setOnClickListener {
            val weightStr = etWeight.text.toString()

            if (weightStr.isNotEmpty()) {
                val weight = weightStr.toFloat()
                val waterInLiters = weight * 0.033
                tvResult.text = String.format(Locale.getDefault(), "Daily Water Goal: %.2f Liters", waterInLiters)
            } else {
                Toast.makeText(this, "Please enter your weight", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
