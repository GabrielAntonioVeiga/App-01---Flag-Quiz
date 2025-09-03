package ufpr.veiga.flagquiz.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ufpr.veiga.flagquiz.R
import ufpr.veiga.flagquiz.constants.AppConstants
import ufpr.veiga.flagquiz.databinding.ActivityResultScreenBinding

class ResultScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        displayResults();
    }

    fun redirectToMain(view: View) {
        val mainScreen = Intent(this, MainActivity::class.java)
        startActivity(mainScreen);
        finish()
    }

    private fun displayResults() {
       displayQuestionResults();
       displayScoreResult();
       displayPlayerName()
    }

    private fun displayQuestionResults() {
        val resultsArray = intent.getBooleanArrayExtra(AppConstants.RESULT_QUESTIONS_KEY)

        if (resultsArray == null) {
            return
        }

        val resultTextViews = listOf(
            binding.q1Result,
            binding.q2Result,
            binding.q3Result,
            binding.q4Result,
            binding.q5Result
        )

        resultsArray.forEachIndexed { index, isCorrect ->
            if (index < resultTextViews.size) {
                val currentTextView = resultTextViews[index]

                if (isCorrect) {
                    currentTextView.text = getString(R.string.label_correct_answer)
                    currentTextView.setTextColor(ContextCompat.getColor(this, R.color.green))
                } else {
                    currentTextView.text = getString(R.string.label_incorrect_answer)
                    currentTextView.setTextColor(ContextCompat.getColor(this, R.color.red))
                }
            }
        }
    }

    private fun displayScoreResult() {
        val score = intent.getDoubleExtra(AppConstants.RESULT_SCORE_KEY, 0.0)

        val formattedScore = String.format("%.2f", score)

        binding.playerScore.text = formattedScore
    }

    private fun displayPlayerName() {
        val playerName = intent.getStringExtra(AppConstants.PLAYER_NAME_KEY)
        binding.playerName.text = "Parabéns, ${playerName}"
    }
}
