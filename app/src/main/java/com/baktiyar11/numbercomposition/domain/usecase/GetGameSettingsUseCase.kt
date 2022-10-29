package com.baktiyar11.numbercomposition.domain.usecase

import com.baktiyar11.numbercomposition.domain.entity.GameSettings
import com.baktiyar11.numbercomposition.domain.entity.Level
import com.baktiyar11.numbercomposition.domain.repsitory.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {
    operator fun invoke(level: Level): GameSettings = repository.getGameSettings(level = level)
}