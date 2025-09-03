package ufpr.veiga.flagquiz.controller

import ufpr.veiga.flagquiz.R

data class CountryFlag(
    val name: String,
    val flagResId: Int
)

class QuizController {
    private val countries = listOf(
        CountryFlag("Argentina", R.drawable.flag_argentina),
        CountryFlag("Brasil", R.drawable.flag_brasil),
        CountryFlag("Canadá", R.drawable.flag_canada),
        CountryFlag("China", R.drawable.flag_china),
        CountryFlag("Chipre", R.drawable.flag_chipre),
        CountryFlag("Egito", R.drawable.flag_egito),
        CountryFlag("França", R.drawable.flag_franca),
        CountryFlag("Japão", R.drawable.flag_japao),
        CountryFlag("Madagascar", R.drawable.flag_madagascar),
        CountryFlag("México", R.drawable.flag_mexico),
        CountryFlag("Mongólia", R.drawable.flag_mongolia),
        CountryFlag("Romênia", R.drawable.flag_romenia)
    )

    fun getRandomQuestions(quantity: Int): List<CountryFlag> {
        return countries.shuffled().take(quantity)
    }

    fun answerQuestion(flag: String, answer: String): Boolean {
        return flag == answer
    }

}