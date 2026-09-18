package com.example.fitpulse

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.profile_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<ImageView>(R.id.btn_back_arrow).setOnClickListener {
            finish()
        }

        val etName = findViewById<EditText>(R.id.et_profile_name)
        val etAge = findViewById<EditText>(R.id.et_profile_age)
        val etHeight = findViewById<EditText>(R.id.et_profile_height)
        val etWeight = findViewById<EditText>(R.id.et_profile_weight)
        val rgGender = findViewById<RadioGroup>(R.id.rg_gender)
        val btnSave = findViewById<Button>(R.id.btn_save_profile)

        // Load saved profile data
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        etName.setText(sharedPref.getString("NAME", ""))
        etAge.setText(sharedPref.getString("AGE", ""))
        etHeight.setText(sharedPref.getString("HEIGHT", ""))
        etWeight.setText(sharedPref.getString("WEIGHT", ""))
        
        val gender = sharedPref.getString("GENDER", "")
        if (gender == "Male") {
            findViewById<RadioButton>(R.id.rb_male).isChecked = true
        } else if (gender == "Female") {
            findViewById<RadioButton>(R.id.rb_female).isChecked = true
        }

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString()
            val height = etHeight.text.toString()
            val weight = etWeight.text.toString()
            
            val selectedGenderId = rgGender.checkedRadioButtonId
            val selectedGender = if (selectedGenderId != -1) {
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else ""

            if (name.isNotEmpty() && age.isNotEmpty() && height.isNotEmpty() && weight.isNotEmpty() && selectedGender.isNotEmpty()) {
                with(sharedPref.edit()) {
                    putString("NAME", name)
                    putString("AGE", age)
                    putString("HEIGHT", height)
                    putString("WEIGHT", weight)
                    putString("GENDER", selectedGender)
                    apply()
                }
                Toast.makeText(this, "Profile Saved Successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
