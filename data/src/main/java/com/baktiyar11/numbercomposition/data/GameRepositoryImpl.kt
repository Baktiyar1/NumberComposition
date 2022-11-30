package com.baktiyar11.numbercomposition.data

import com.baktiyar11.numbercomposition.domain.entity.GameSettingsDomain
import com.baktiyar11.numbercomposition.domain.entity.LevelDomain
import com.baktiyar11.numbercomposition.domain.entity.Question
import com.baktiyar11.numbercomposition.domain.entity.TypeDomain
import com.baktiyar11.numbercomposition.domain.repsitory.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class GameRepositoryImpl : GameRepository {

    private var sum = 1
    private var visibleNumber = 1

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int, type: TypeDomain): Question {
        sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val rightAnswer = when (type) {
            TypeDomain.PLUS -> generatePlusRightAnswer()
            TypeDomain.MINUS -> generateMinusRightAnswer(maxSumValue)
            TypeDomain.MULTI -> generateMultiRightAnswer()
            TypeDomain.DIVISION -> generateDivisionRightAnswer(maxSumValue)
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
        sum = Random.nextInt(MIN_ANSWER_VALUE, maxSumValue / 2)
        visibleNumber = Random.nextInt(sum, maxSumValue + 1)
        if ((visibleNumber / sum.toDouble()) % 1 != 0.0) generateDivisionRightAnswer(maxSumValue)
        return (visibleNumber / sum)
    }

    override fun getGameSettings(level: LevelDomain): GameSettingsDomain = when (level) {
        LevelDomain.TEST -> GameSettingsDomain(maxSumValue = 10,
            minCountOfRightAnswers = 3, minPercentOfRightAnswers = 70, gameTimeInSecond = 8)
        LevelDomain.EASY -> GameSettingsDomain(maxSumValue = 10,
            minCountOfRightAnswers = 10, minPercentOfRightAnswers = 70, gameTimeInSecond = 30)
        LevelDomain.NORMAL -> GameSettingsDomain(maxSumValue = 20,
            minCountOfRightAnswers = 20, minPercentOfRightAnswers = 80, gameTimeInSecond = 45)
        LevelDomain.HARD -> GameSettingsDomain(maxSumValue = 30,
            minCountOfRightAnswers = 30, minPercentOfRightAnswers = 90, gameTimeInSecond = 60)
    }

    private companion object {
        private const val MIN_SUM_VALUE = 2
        private const val MIN_ANSWER_VALUE = 1
    }
}