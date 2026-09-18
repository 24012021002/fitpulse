package com.example.fitpulse

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // If user is already logged in, navigate straight to MainActivity
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        if (sharedPref.getBoolean("IS_LOGGED_IN", false)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        dbHelper = DatabaseHelper(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etEmail = findViewById<EditText>(R.id.et_username)
        val etPassword = findViewById<EditText>(R.id.et_password)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val tvForgotPassword = findViewById<TextView>(R.id.tv_forgot_password)
        val tvRegister = findViewById<TextView>(R.id.tv_register)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Please enter a valid email address"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            val user = dbHelper.authenticateUser(email, password)
            if (user != null) {
                // Save session in UserPrefs
                with(sharedPref.edit()) {
                    putString("NAME", user.name)
                    putString("EMAIL", user.email)
                    putBoolean("IS_LOGGED_IN", true)
                    apply()
                }

                Toast.makeText(this, "Welcome back, ${user.name}!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                if (dbHelper.checkEmailExists(email)) {
                    Toast.makeText(this, "Incorrect password. Please try again.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "No account found with this email. Please register first.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        tvForgotPassword.setOnClickListener {
            showForgotPasswordDialog()
        }

        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showForgotPasswordDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Reset Password")

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 40, 50, 10)
        }

        val inputEmail = EditText(this).apply {
            hint = "Enter your registered email"
            inputType = android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }
        layout.addView(inputEmail)

        val inputNewPassword = EditText(this).apply {
            hint = "Enter new password (min 6 chars)"
            inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        layout.addView(inputNewPassword)

        builder.setView(layout)

        builder.setPositiveButton("Reset") { dialog, _ ->
            val email = inputEmail.text.toString().trim()
            val newPass = inputNewPassword.text.toString()

            if (email.isEmpty() || newPass.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            if (newPass.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            if (!dbHelper.checkEmailExists(email)) {
                Toast.makeText(this, "No account found with this email", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            val updated = dbHelper.updatePassword(email, newPass)
            if (updated) {
                Toast.makeText(this, "Password reset successfully! Please login with your new password.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Failed to update password", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }
}

