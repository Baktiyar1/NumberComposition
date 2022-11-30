package com.baktiyar11.numbercomposition.domain.repsitory

import com.baktiyar11.numbercomposition.domain.entity.GameSettingsDomain
import com.baktiyar11.numbercomposition.domain.entity.LevelDomain
import com.baktiyar11.numbercomposition.domain.entity.Question
import com.baktiyar11.numbercomposition.domain.entity.TypeDomain

interface GameRepository {
    fun generateQuestion(maxSumValue: Int, countOfOptions: Int, type: TypeDomain): Question
    fun getGameSettings(level: LevelDomain): GameSettingsDomain
}