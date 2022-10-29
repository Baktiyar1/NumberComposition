package com.baktiyar11.numbercomposition.domain.usecase

import com.baktiyar11.numbercomposition.domain.entity.Question
import com.baktiyar11.numbercomposition.domain.repsitory.GameRepository

class GenerateQuestionUseCase(private val repository: GameRepository) {
    operator fun invoke(maxSumValue: Int): Question =
        repository.generateQuestion(maxSumValue = maxSumValue, countOfOptions = COUNT_OF_OPTIONS)

    companion object {
        const val COUNT_OF_OPTIONS = 6
    }
}