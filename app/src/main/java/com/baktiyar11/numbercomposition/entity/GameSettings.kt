package com.baktiyar11.numbercomposition.entity

import android.os.Parcelable
import com.baktiyar11.numbercomposition.domain.entity.GameSettingsDomain
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings(
    val maxSumValue: Int, val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int, val gameTimeInSecond: Int,
) : Parcelable {
    companion object {
        fun mapGameSettings(gameSettingsDomain: GameSettingsDomain) = GameSettings(
            maxSumValue = gameSettingsDomain.maxSumValue,
            minCountOfRightAnswers = gameSettingsDomain.minCountOfRightAnswers,
            minPercentOfRightAnswers = gameSettingsDomain.minPercentOfRightAnswers,
            gameTimeInSecond = gameSettingsDomain.gameTimeInSecond
        )
    }
}