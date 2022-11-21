package com.baktiyar11.numbercomposition.domain.repsitory

import com.baktiyar11.numbercomposition.domain.entity.GameSettings
import com.baktiyar11.numbercomposition.domain.entity.Level
import com.baktiyar11.numbercomposition.domain.entity.Question

interface GameRepository {
    fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question
    fun getGameSettings(level: Level): GameSettings
}