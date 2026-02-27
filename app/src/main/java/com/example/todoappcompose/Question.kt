package com.example.todoappcompose

data class Question(
    val id: Int,
    val title: String,
    val options: List<String>,
    val correctIndex: Int
)