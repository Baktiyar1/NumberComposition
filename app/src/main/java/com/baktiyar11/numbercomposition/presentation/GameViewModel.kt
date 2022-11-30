package com.baktiyar11.numbercomposition.presentation

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baktiyar11.numbercomposition.data.GameRepositoryImpl
import com.baktiyar11.numbercomposition.domain.entity.*
import com.baktiyar11.numbercomposition.domain.usecase.GenerateQuestionUseCase
import com.baktiyar11.numbercomposition.domain.usecase.GetGameSettingsUseCase
import com.baktiyar11.numbercomposition.entity.GameResult

class GameViewModel : ViewModel() {

    private lateinit var gameSettings: GameSettingsDomain
    private lateinit var level: LevelDomain
    private lateinit var type: TypeDomain
    private var timer: CountDownTimer? = null

    private val repository = GameRepositoryImpl()

    private val generateQuestionUseCase = GenerateQuestionUseCase(repository = repository)
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository = repository)

    private val _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String> get() = _formattedTime

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question> get() = _question

    private val _percentOfRightAnswer = MutableLiveData<Int>()
    val percentOfRightAnswer: LiveData<Int> get() = _percentOfRightAnswer

    private val _progressAnswer = MutableLiveData<List<Int>>()
    val progressAnswer: LiveData<List<Int>> get() = _progressAnswer

    private val _enoughCount = MutableLiveData<Boolean>()
    val enoughCount: LiveData<Boolean> get() = _enoughCount

    private val _enoughPercent = MutableLiveData<Boolean>()
    val enoughPercent: LiveData<Boolean> get() = _enoughPercent

    private val _minPercent = MutableLiveData<Int>()
    val minPercent: LiveData<Int> get() = _minPercent

    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult> get() = _gameResult

    private var countOfRightAnswer = 0
    private var countOfQuestion = 0

    fun startGame(level: LevelDomain, type: TypeDomain) {
        this.level = level
        this.type = type
        getGameSettings()
        startTimer()
        generateQuestion()
        updateProgress()
    }

    fun chooseAnswer(number: Int) {
        checkAnswer(number)
        updateProgress()
        generateQuestion()
    }

    private fun updateProgress() {
        val percent = calculatePercentOfRightAnswer()
        _percentOfRightAnswer.value = percent
        _progressAnswer.value = mutableListOf<Int>().apply {
            add(countOfRightAnswer)
            add(gameSettings.minCountOfRightAnswers)
        }
        _enoughCount.value = countOfRightAnswer >= gameSettings.minCountOfRightAnswers
        _enoughPercent.value = percent >= gameSettings.minPercentOfRightAnswers
    }

    private fun calculatePercentOfRightAnswer() =
        ((countOfRightAnswer / countOfQuestion.toDouble()) * 100).toInt()

    private fun checkAnswer(number: Int) {
        if (number == question.value?.rightAnswer) countOfRightAnswer++
        countOfQuestion++
    }

    private fun getGameSettings() {
        this.gameSettings = getGameSettingsUseCase(level)
        _minPercent.value = gameSettings.minPercentOfRightAnswers
    }

    private fun startTimer() {
        timer = object : CountDownTimer(
            gameSettings.gameTimeInSecond * MILLIS_IN_SECONDS, MILLIS_IN_SECONDS) {
            override fun onTick(millisUntilFinished: Long) {
                _formattedTime.value = formatTime(millisUntilFinished)
            }
            override fun onFinish() {
                finishGame()
            }
        }
        timer?.start()
    }

    private fun generateQuestion() {
        _question.value = generateQuestionUseCase(gameSettings.maxSumValue, type)
    }

    private fun formatTime(millisUntilFinished: Long): String {
        val seconds = millisUntilFinished / MILLIS_IN_SECONDS
        val minutes = seconds / SECONDS_IN_MINUTES
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTES)
        return String.format("%02d:%02d", minutes, leftSeconds)
    }

    private fun finishGame() {
        _gameResult.value = GameResult.mapGameResult(GameResultDomain(enoughCount.value == true &&
                enoughPercent.value == true, countOfRightAnswer, countOfQuestion, gameSettings))
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    companion object {
        private const val MILLIS_IN_SECONDS = 1000L
        private const val SECONDS_IN_MINUTES = 60
    }
}