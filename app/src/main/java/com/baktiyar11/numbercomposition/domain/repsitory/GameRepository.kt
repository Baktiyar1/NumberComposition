package com.baktiyar11.numbercomposition.domain.repsitory

import com.baktiyar11.numbercomposition.domain.entity.GameSettings
import com.baktiyar11.numbercomposition.domain.entity.Level
import com.baktiyar11.numbercomposition.domain.entity.Question
import com.baktiyar11.numbercomposition.domain.entity.Type

interface GameRepository {
    fun generateQuestion(maxSumValue: Int, countOfOptions: Int, type: Type): Question
    fun getGameSettings(level: Level): GameSettings
}