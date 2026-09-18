package com.example.fitpulse

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NutritionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nutrition)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.nutrition_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<ImageView>(R.id.btn_back_arrow).setOnClickListener {
            finish()
        }

        calculateNutrition()
    }

    private fun calculateNutrition() {
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val weightStr = sharedPref.getString("WEIGHT", "")
        val heightStr = sharedPref.getString("HEIGHT", "")
        val ageStr = sharedPref.getString("AGE", "")
        val gender = sharedPref.getString("GENDER", "")
        val tvDetails = findViewById<TextView>(R.id.tv_nutrition_details)

        if (weightStr.isNullOrEmpty() || heightStr.isNullOrEmpty() || ageStr.isNullOrEmpty() || gender.isNullOrEmpty()) {
            tvDetails.text = "Please complete your profile to see personalized nutrition goals."
            return
        }

        try {
            val weight = weightStr.toDouble()
            val height = heightStr.toDouble()
            val age = ageStr.toInt()

            // Mifflin-St Jeor Equation for BMR
            val bmr = if (gender == "Male") {
                10 * weight + 6.25 * height - 5 * age + 5
            } else {
                10 * weight + 6.25 * height - 5 * age - 161
            }

            // Maintenance calories (sedentary factor 1.2)
            val calories = (bmr * 1.2).toInt()
            val protein = (weight * 1.8).toInt() // 1.8g per kg
            val fats = (calories * 0.25 / 9).toInt() // 25% from fats
            val carbs = (calories * 0.50 / 4).toInt() // 50% from carbs

            val nutritionText = """
                Daily Nutrition Goals:
                
                • Calories: $calories kcal
                • Protein: ${protein}g
                • Carbohydrates: ${carbs}g
                • Fats: ${fats}g
                
                Note: These are estimates based on a sedentary lifestyle.
            """.trimIndent()

            tvDetails.text = nutritionText

        } catch (e: Exception) {
            tvDetails.text = "Error calculating nutrition. Please check your profile data."
        }
    }
}
