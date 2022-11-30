package com.baktiyar11.numbercomposition.domain.usecase

import com.baktiyar11.numbercomposition.domain.entity.GameSettingsDomain
import com.baktiyar11.numbercomposition.domain.entity.LevelDomain
import com.baktiyar11.numbercomposition.domain.repsitory.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {
    operator fun invoke(level: LevelDomain): GameSettingsDomain = repository.getGameSettings(level = level)
}