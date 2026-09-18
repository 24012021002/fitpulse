package com.example.fitpulse

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        dbHelper = DatabaseHelper(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etName = findViewById<EditText>(R.id.et_register_name)
        val etEmail = findViewById<EditText>(R.id.et_register_email)
        val etPassword = findViewById<EditText>(R.id.et_register_password)
        val btnRegister = findViewById<Button>(R.id.btn_register)
        val tvBackToLogin = findViewById<TextView>(R.id.tv_back_to_login)

        btnRegister.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Please enter a valid email address"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                etPassword.error = "Password must be at least 6 characters"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            if (dbHelper.checkEmailExists(email)) {
                Toast.makeText(this, "An account with this email already exists", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val success = dbHelper.registerUser(name, email, password)
            if (success) {
                // Save session in UserPrefs
                val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString("NAME", name)
                    putString("EMAIL", email)
                    putBoolean("IS_LOGGED_IN", true)
                    apply()
                }

                Toast.makeText(this, "Account created successfully! Welcome, $name", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finishAffinity()
            } else {
                Toast.makeText(this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }

        tvBackToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

