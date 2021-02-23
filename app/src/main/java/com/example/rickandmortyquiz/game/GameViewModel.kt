package com.example.rickandmortyquiz.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyquiz.R

const val TOTALANSWERS = "3"

class GameViewModel : ViewModel() {

    private var questionsAnsweredCorrect = 0
    private var questionsAttempted = 0
    private var currentQuestionIndex = 0

    private lateinit var questionBank: MutableList<Question>

    private var _scoreString = MutableLiveData<String>()
    val scoreString: LiveData<String>
        get() = _scoreString

    private var _currentQuestion = MutableLiveData<Int>()
    val currentQuestion: LiveData<Int>
        get() = _currentQuestion

    private var _answerChecked = MutableLiveData<Boolean>()
    val answerChecked: LiveData<Boolean>
        get() = _answerChecked

    init {
        newGame()
        nextQuestion()
        _answerChecked.value = false;
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun getQuestionBank(): MutableList<Question> {
        return questionBank
    }

    fun getCurrentQuestionIndex(): Int {
        return currentQuestionIndex
    }

    private fun newGame() {
        questionsAnsweredCorrect = 0
        questionsAttempted = 0
        currentQuestionIndex = -1

        updateScoreString()

        resetQuestionBank()
    }

    fun nextQuestion() {
        if (currentQuestionIndex == questionBank.size - 1)
            currentQuestionIndex = 0

        currentQuestionIndex++
        //_answerChecked.value = false;
        _currentQuestion.value = questionBank[currentQuestionIndex].questionID
    }

    fun previousQuestion() {
        if (currentQuestionIndex == 0)
            currentQuestionIndex = questionBank.size - 1

        currentQuestionIndex--
        //_answerChecked.value = false;
        _currentQuestion.value = questionBank[currentQuestionIndex].questionID
    }

    fun checkAnswer(answer: Boolean) {

        questionBank[currentQuestionIndex].attempted = true
        questionsAttempted++

        questionBank[currentQuestionIndex].answered = answer
        if (questionBank[currentQuestionIndex].answer == answer)
            questionsAnsweredCorrect++

        updateScoreString()

        if (_answerChecked.value!!)
            _answerChecked.value = false;
        else
            _answerChecked.value = true;
    }

    private fun resetQuestionBank() {
        questionBank = mutableListOf(
                Question(R.string.question_1, false),
                Question(R.string.question_2, true),
                Question(R.string.question_3, true),
                Question(R.string.question_4, false),
                Question(R.string.question_5, false),
                Question(R.string.question_6, true),
                Question(R.string.question_7, false),
                Question(R.string.question_8, true),
                Question(R.string.question_9, false),
                Question(R.string.question_10, false),
                Question(R.string.question_11, false),
                Question(R.string.question_12, true),
                Question(R.string.question_13, false),
                Question(R.string.question_14, true),
                Question(R.string.question_15, false),
                Question(R.string.question_16, false),
                Question(R.string.question_17, true),
                Question(R.string.question_18, false),
                Question(R.string.question_19, false),
                Question(R.string.question_20, true)
        )
        questionBank.shuffle()
    }

    private fun updateScoreString() {
        _scoreString.value = "Your Score: " + questionsAnsweredCorrect.toString() + "/" + TOTALANSWERS
    }
}