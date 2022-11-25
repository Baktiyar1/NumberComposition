package com.baktiyar11.numbercomposition.domain.usecase

import com.baktiyar11.numbercomposition.domain.entity.Type
import com.baktiyar11.numbercomposition.domain.repsitory.GameRepository

class GenerateQuestionUseCase(private val repository: GameRepository) {
    operator fun invoke(maxSumValue: Int, type: Type) = repository
        .generateQuestion(maxSumValue = maxSumValue, countOfOptions = COUNT_OF_OPTIONS, type = type)

    companion object {
        const val COUNT_OF_OPTIONS = 6
    }
}