package ufpr.veiga.flagquiz.controller

import ufpr.veiga.flagquiz.R

data class CountryFlag(
    val name: String,
    val flagResId: Int
)

class QuizController {
    private val countries = listOf(
        CountryFlag("Albânia", R.drawable.flag_albania),
        CountryFlag("Alemanha", R.drawable.flag_alemanha),
        CountryFlag("Arábia Saudita", R.drawable.flag_arabia_saudita),
        CountryFlag("Argentina", R.drawable.flag_argentina),
        CountryFlag("Austrália", R.drawable.flag_australia),
        CountryFlag("Bangladesh", R.drawable.flag_bangladesh),
        CountryFlag("Barbados", R.drawable.flag_barbados),
        CountryFlag("Bolívia", R.drawable.flag_bolivia),
        CountryFlag("Brasil", R.drawable.flag_brasil),
        CountryFlag("Canadá", R.drawable.flag_canada),
        CountryFlag("Chile", R.drawable.flag_chile),
        CountryFlag("China", R.drawable.flag_china),
        CountryFlag("Coreia do Sul", R.drawable.flag_coreia_do_sul),
        CountryFlag("Egito", R.drawable.flag_egito),
        CountryFlag("Espanha", R.drawable.flag_espanha),
        CountryFlag("França", R.drawable.flag_franca),
        CountryFlag("Índia", R.drawable.flag_india),
        CountryFlag("Inglaterra", R.drawable.flag_inglaterra),
        CountryFlag("Japão", R.drawable.flag_japao),
        CountryFlag("Palau", R.drawable.flag_palau),
        CountryFlag("Rússia", R.drawable.flag_russia),
        CountryFlag("Uganda", R.drawable.flag_uganda)
    )


    fun getRandomQuestions(quantity: Int): List<CountryFlag> {
        return countries.shuffled().take(quantity)
    }

    fun answerQuestion(flag: String, answer: String): Boolean {
        return flag == answer
    }

}