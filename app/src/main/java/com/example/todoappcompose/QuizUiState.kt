package com.example.todoappcompose

data class QuizUiState(

    val questions: List<Question> = emptyList(),
    val currentIndex: Int = 0,
    val selectedIndex: Int? = null,
    val score: Int = 0,
    val isFinished: Boolean = false,
    val lives: Int = 3,
    val lastAnswerCorrect: Boolean? = null

) {

    val currentQuestion: Question?
        get() = questions.getOrNull(currentIndex)
}