package ufpr.veiga.flagquiz.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ufpr.veiga.flagquiz.R
import ufpr.veiga.flagquiz.constants.AppConstants
import ufpr.veiga.flagquiz.controller.CountryFlag
import ufpr.veiga.flagquiz.controller.QuizController
import ufpr.veiga.flagquiz.databinding.ActivityQuizBinding
import ufpr.veiga.flagquiz.databinding.ActivityResultScreenBinding

class QuizActivity : AppCompatActivity(), View.OnClickListener {
    private val quizController = QuizController()
    private var pontuacao: Double = 0.0;
    var tentativas = 0
    var perguntas = listOf<CountryFlag>()
    var resultado = mutableListOf<Boolean>()

    private lateinit var binding: ActivityQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        clearResults();
        perguntas = quizController.getRandomQuestions(5)
        binding.buttonAttempt.setOnClickListener(this)
        renderizar()
    }
    fun handleAttempt(){

        val resultScreen = Intent(this, ResultScreenActivity::class.java)

        val userAttemptInput = binding.editTextText.text.toString().lowercase().trim()
        if(userAttemptInput.isEmpty()){
            Toast.makeText(this, "Digite uma resposta", Toast.LENGTH_SHORT).show()
            return;
        }
        val respostaCorreta =
            quizController.answerQuestion(perguntas[tentativas].name.lowercase(), userAttemptInput)
        if (respostaCorreta) {
            Toast.makeText(this, "Resposta correta", Toast.LENGTH_SHORT).show()
            pontuacao+=20
        } else {
            Toast.makeText(this, "Resposta incorreta", Toast.LENGTH_SHORT).show()
        }
        resultado.add(respostaCorreta)
        val isTheLastAttempt = tentativas == 4;
        if (isTheLastAttempt) {
            setResultsInTheNextScreen(resultScreen);
            startActivity(resultScreen)
            return finish()
        }
        tentativas++
        renderizar()

    }

    fun clearResults() {
        tentativas = 0;
        pontuacao = 0.0;
        resultado.clear();
        perguntas = listOf();
    }

    fun setResultsInTheNextScreen(resultScreen: Intent) {
        resultScreen.putExtra(AppConstants.Companion.RESULT_SCORE_KEY, pontuacao)
        resultScreen.putExtra(AppConstants.Companion.RESULT_QUESTIONS_KEY, resultado.toBooleanArray())
        resultScreen.putExtra(
            AppConstants.PLAYER_NAME_KEY,
            intent.getStringExtra(
                AppConstants.PLAYER_NAME_KEY)
        )
    }
    fun renderizar(){
        val imageView = binding.imageView;
        Log.d("QUIZ", "Tamanho da lista: ${perguntas.size}, índice atual: $tentativas")

        imageView.setImageResource(perguntas[tentativas].flagResId)
        binding.textPageIndex.text = "${(tentativas + 1)} / 5";
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_attempt) {
            handleAttempt()
        }
    }

}