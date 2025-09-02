package ufpr.veiga.flagquiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
    }

    fun comecar(view: View) {
        val intent = Intent(this, quiz::class.java)

        val editText = findViewById<EditText>(R.id.nomeTextView)

        if (editText.text.isEmpty()) {
            Toast.makeText(this, "Por favor, insira seu nome", Toast.LENGTH_SHORT).show()
            return
        }

        intent.putExtra("userName", editText.text.toString())

        startActivity(intent)
    }
}