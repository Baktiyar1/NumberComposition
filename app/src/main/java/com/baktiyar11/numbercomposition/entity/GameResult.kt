package com.baktiyar11.numbercomposition.entity

import android.os.Parcelable
import com.baktiyar11.numbercomposition.domain.entity.GameResultDomain
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    val winner: Boolean, val countOfRightAnswer: Int,
    val countOfQuestions: Int, val gameSettings: GameSettings,
) : Parcelable {
    companion object {
        fun mapGameResult(gameResultDomain: GameResultDomain) = GameResult(
            winner = gameResultDomain.winner,
            countOfRightAnswer = gameResultDomain.countOfRightAnswer,
            countOfQuestions = gameResultDomain.countOfQuestions,
            gameSettings = GameSettings.mapGameSettings(gameSettingsDomain = gameResultDomain.gameSettings),
        )
    }
}