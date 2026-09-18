package com.example.fitpulse

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        updateGreeting()

        val sdf = SimpleDateFormat("MMM, dd yyyy", Locale.getDefault())
        val currentDate = sdf.format(Calendar.getInstance().time)
        findViewById<TextView>(R.id.tv_date).text = currentDate

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<MaterialCardView>(R.id.card_bmi).setOnClickListener {
            startActivity(Intent(this, BmiActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.card_kcal).setOnClickListener {
            startActivity(Intent(this, KcalActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.card_water).setOnClickListener {
            startActivity(Intent(this, WaterActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.card_nutrition).setOnClickListener {
            startActivity(Intent(this, NutritionActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.card_muscle_builder).setOnClickListener {
            startActivity(Intent(this, MuscleBuildingActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.card_weight_loss).setOnClickListener {
            startActivity(Intent(this, WeightLossActivity::class.java))
        }

        findViewById<ImageView>(R.id.iv_profile_icon).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        updateGreeting()
    }

    private fun updateGreeting() {
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val userName = sharedPref.getString("NAME", "User")
        findViewById<TextView>(R.id.tv_greeting).text = "Hello, $userName"
    }
}
