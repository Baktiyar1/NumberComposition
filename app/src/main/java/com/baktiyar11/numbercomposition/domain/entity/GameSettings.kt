package com.baktiyar11.numbercomposition.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings(
    val maxSumValue: Int, val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int, val gameTimeInSecond: Int,
) : Parcelable

// Но это все не важно. Потому-что можно просто в plugins добавить "id 'kotlin-parcelize',
// после этого добавить анатацию Parcelize в классе и всё мать вашу

//{
//
//    // С помощью Parcel мы записываем данные.
//    // Записывает в соответствии с конструктором.
//    constructor(parcel: Parcel) : this(
//        parcel.readInt(),
//        parcel.readInt(),
//        parcel.readInt(),
//        parcel.readInt()) {
//    }
//
//    // Мы сами записываем все параметры.
//    // Пишем в замивимости в каком порядке все параметры записаны в конструкторе.
//    // Тип записанных данных мы пишем в ручную.
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeInt(maxSumValue)
//        parcel.writeInt(minCountOfRightAnswers)
//        parcel.writeInt(minPercentOfRightAnswers)
//        parcel.writeInt(gameTimeInSecond)
//    }
//
//    override fun describeContents(): Int = 0
//
//    companion object CREATOR : Parcelable.Creator<GameSettings> {
//        override fun createFromParcel(parcel: Parcel): GameSettings {
//            return GameSettings(parcel)
//        }
//
//        override fun newArray(size: Int): Array<GameSettings?> {
//            return arrayOfNulls(size)
//        }
//    }
//}