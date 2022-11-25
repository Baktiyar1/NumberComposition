package com.baktiyar11.numbercomposition.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Type : Parcelable {
    PLUS, MINUS, MULTI, DIVISION
}