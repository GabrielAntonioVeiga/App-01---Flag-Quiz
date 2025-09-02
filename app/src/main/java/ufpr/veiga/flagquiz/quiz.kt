package ufpr.veiga.flagquiz

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ufpr.veiga.flagquiz.controller.QuizController

class quiz : AppCompatActivity() {
    private val quizController = QuizController()
    val pontuacao = 0
    val tentativas = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun tentativa(view: View){
        val texto = findViewById<EditText>(R.id.editTextText).text.toString().lowercase()

    }
}
//flag_brasil.png
//14