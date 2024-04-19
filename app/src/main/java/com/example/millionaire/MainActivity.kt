package com.example.millionaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var currentRound = 0
    private val rounds = mutableListOf<Round>()
    private lateinit var tvQuestion: TextView
    private lateinit var tvValue: TextView
    private lateinit var button: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvQuestion = findViewById(R.id.tvQuestion)
        tvQuestion.text = "Тут будет первый вопрос"
        tvValue = findViewById(R.id.tvValue)
        button = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        fillRounds()
        updateUI()
        button.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
    }

    private fun fillRounds() {
        rounds.run {
            add(Round("Что такое луна?", "Звезда", "Планета", "Спутник", "Круг сыра", 3, 100))
            add(
                Round(
                    "В каком году запущен первый спутник?",
                    "1957",
                    "1961",
                    "1965",
                    "1969",
                    1,
                    1_000
                )
            )
            add(Round("Сколько спутников у Марса?", "0", "1", "2", "4 сыра", 3, 10_000))
            add(
                Round(
                    "Как называется спутник Плутона?",
                    "Нет спутников",
                    "Харон",
                    "Энцелад",
                    "Ио",
                    2,
                    100_000
                )
            )
            add(
                Round(
                    "Какой крупнейший спутник у Юпитера?",
                    "Европа",
                    "Каллисто",
                    "Титан",
                    "Ганимед",
                    4,
                    1_000_000
                )
            )
        }
    }

    private fun updateUI() {
        tvQuestion.text = rounds[currentRound].question
        tvValue.text = rounds[currentRound].value.toString()
        button.text = rounds[currentRound].answer1
        button2.text = rounds[currentRound].answer2
        button3.text = rounds[currentRound].answer3
        button4.text = rounds[currentRound].answer4
    }

    private fun checkAnswer(givenId: Int) =
        (givenId == rounds[currentRound].rightId)

    private fun goNextRound(): Boolean {
        if (currentRound >= rounds.size - 1) return false
        currentRound++
        updateUI()
        return true
    }

    private fun processRound(givenId: Int) {
        if (checkAnswer(givenId)) {
            if (!goNextRound()) {
                Toast.makeText(this, getString(R.string.wintext), Toast.LENGTH_SHORT).show()
                finish()
            }
        } else {
            Toast.makeText(this, getString(R.string.loosetext), Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onClick(v: View?) {
        v?.let {
            when(it.id) {
                R.id.button -> processRound(1)
                R.id.button2 -> processRound(2)
                R.id.button3 -> processRound(3)
                R.id.button4 -> processRound(4)
                else -> return
            }
        }
    }
    fun buttonClick(view: View) {
        try {
            val id = view.tag.toString().toInt()
            processRound(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}