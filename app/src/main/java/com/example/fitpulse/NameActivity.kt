package com.example.fitpulse

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_name)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.name_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etName = findViewById<EditText>(R.id.et_user_name)
        val btnContinue = findViewById<Button>(R.id.btn_continue)

        btnContinue.setOnClickListener {
            val name = etName.text.toString().trim()
            if (name.isNotEmpty()) {
                val sharedPref = getSharedPreferences("UserPrefs", android.content.Context.MODE_PRIVATE)
                sharedPref.edit().putString("NAME", name).apply()

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("USER_NAME", name)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
