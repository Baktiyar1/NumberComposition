package com.baktiyar11.numbercomposition.domain.entity

data class GameResultDomain(
    val winner: Boolean, val countOfRightAnswer: Int,
    val countOfQuestions: Int, val gameSettings: GameSettingsDomain,
)