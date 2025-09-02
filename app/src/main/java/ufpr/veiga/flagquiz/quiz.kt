package ufpr.veiga.flagquiz

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ufpr.veiga.flagquiz.constants.AppConstants
import ufpr.veiga.flagquiz.controller.CountryFlag
import ufpr.veiga.flagquiz.controller.QuizController

class quiz : AppCompatActivity() {
    private val quizController = QuizController()
    var pontuacao = 0
    var tentativas = 0
    var perguntas = listOf<CountryFlag>()
    var resultado = mutableListOf<Boolean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        perguntas = quizController.getRandomQuestions(5)
        renderizar()
    }
    fun tentativa(view: View){
        val texto = findViewById<EditText>(R.id.editTextText).text.toString().lowercase().trim()
        if(texto.isEmpty()){
            Toast.makeText(this, "Digite uma resposta", Toast.LENGTH_SHORT).show()
        }else {
            val resposta =
                quizController.answerQuestion(perguntas[tentativas].name.lowercase(), texto)
            if (resposta) {
                Toast.makeText(this, "Resposta correta", Toast.LENGTH_SHORT).show()
                pontuacao+=20
            } else {
                Toast.makeText(this, "Resposta incorreta", Toast.LENGTH_SHORT).show()
            }
            tentativas++
            resultado.add(resposta)
            if (tentativas == 5) {
                intent.putExtra(AppConstants.RESULT_SCORE_KEY, pontuacao)
                intent.putExtra(AppConstants.RESULT_QUESTIONS_KEY, resultado.toBooleanArray())
                intent.getStringExtra(AppConstants.RESULT_QUESTIONS_KEY)
                startActivity(intent)
                finish()
            }
            renderizar()
        }
    }
    fun renderizar(){
        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(perguntas[tentativas].flagResId)
    }
}
//flag_brasil.png
//14