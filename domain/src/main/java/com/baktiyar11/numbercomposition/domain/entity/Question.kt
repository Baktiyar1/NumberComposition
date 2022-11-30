package com.baktiyar11.numbercomposition.domain.entity

data class Question(
    val sum: Int, val visibleNumber: Int, val rightAnswer: Int, val options: List<Int>,
)