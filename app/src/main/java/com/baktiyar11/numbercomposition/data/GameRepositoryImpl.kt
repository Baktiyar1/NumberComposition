package com.baktiyar11.numbercomposition.data

import com.baktiyar11.numbercomposition.domain.entity.GameSettings
import com.baktiyar11.numbercomposition.domain.entity.Level
import com.baktiyar11.numbercomposition.domain.entity.Question
import com.baktiyar11.numbercomposition.domain.repsitory.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max(rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswer + countOfOptions)
        when (options.size < countOfOptions) {
            options.add(Random.nextInt()) -> {}
            else -> {}
        }
        return Question(sum, visibleNumber, options.toList())
    }

    override fun getGameSettings(level: Level): GameSettings = when (level) {
        Level.TEST -> GameSettings(maxSumValue = 10,
            minCountOfRightAnswers = 3, minPercentOfRightAnswers = 50, gameTimeInSecond = 8)
        Level.EASY -> GameSettings(maxSumValue = 10,
            minCountOfRightAnswers = 10, minPercentOfRightAnswers = 70, gameTimeInSecond = 60)
        Level.NORMAL -> GameSettings(maxSumValue = 20,
            minCountOfRightAnswers = 20, minPercentOfRightAnswers = 80, gameTimeInSecond = 50)
        Level.HARD -> GameSettings(maxSumValue = 30,
            minCountOfRightAnswers = 30, minPercentOfRightAnswers = 90, gameTimeInSecond = 40)

    }

}