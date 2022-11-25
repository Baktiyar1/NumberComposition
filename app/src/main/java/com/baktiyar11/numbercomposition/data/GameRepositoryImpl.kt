package com.baktiyar11.numbercomposition.data

import com.baktiyar11.numbercomposition.domain.entity.GameSettings
import com.baktiyar11.numbercomposition.domain.entity.Level
import com.baktiyar11.numbercomposition.domain.entity.Question
import com.baktiyar11.numbercomposition.domain.entity.Type
import com.baktiyar11.numbercomposition.domain.repsitory.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class GameRepositoryImpl : GameRepository {

    private var sum = 1
    private var visibleNumber = 1

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int, type: Type): Question {
        sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue+1)
        val rightAnswer = when (type) {
            Type.PLUS -> generatePlusRightAnswer()
            Type.MINUS -> generateMinusRightAnswer(maxSumValue)
            Type.MULTI -> generateMultiRightAnswer()
            Type.DIVISION -> generateDivisionRightAnswer(maxSumValue)
        }
        val options = HashSet<Int>()
        options.add(rightAnswer)
        val from = max(rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswer + countOfOptions)
        while (options.size < countOfOptions) options.add(Random.nextInt(from, to))
        return Question(sum, visibleNumber, rightAnswer, options.toList())
    }

    private fun generatePlusRightAnswer(): Int {
        visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        return (sum - visibleNumber)
    }

    private fun generateMinusRightAnswer(maxSumValue: Int): Int {
        visibleNumber = Random.nextInt(sum + 1, maxSumValue)
        return (visibleNumber - sum)
    }

    private fun generateMultiRightAnswer(): Int {
        visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        if ((sum / visibleNumber.toDouble()) % 1 != 0.0) generateMultiRightAnswer()
        return sum / visibleNumber
    }

    private fun generateDivisionRightAnswer(maxSumValue: Int): Int {
        sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue / 2)
        visibleNumber = Random.nextInt(sum, maxSumValue+1)
        if ((visibleNumber / sum.toDouble()) % 1 != 0.0) generateDivisionRightAnswer(maxSumValue)
        return (visibleNumber / sum)
    }

    override fun getGameSettings(level: Level): GameSettings = when (level) {
        Level.TEST -> GameSettings(maxSumValue = 10,
            minCountOfRightAnswers = 3, minPercentOfRightAnswers = 70, gameTimeInSecond = 8)
        Level.EASY -> GameSettings(maxSumValue = 10,
            minCountOfRightAnswers = 10, minPercentOfRightAnswers = 70, gameTimeInSecond = 30)
        Level.NORMAL -> GameSettings(maxSumValue = 20,
            minCountOfRightAnswers = 20, minPercentOfRightAnswers = 80, gameTimeInSecond = 45)
        Level.HARD -> GameSettings(maxSumValue = 30,
            minCountOfRightAnswers = 30, minPercentOfRightAnswers = 90, gameTimeInSecond = 60)
    }

    private companion object {
        private const val MIN_SUM_VALUE = 2
        private const val MIN_ANSWER_VALUE = 1
    }
}