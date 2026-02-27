package com.example.todoappcompose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QuizViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        QuizUiState(
            questions = seedQuestions(),
            lives = 3
        )
    )

    val uiState: StateFlow<QuizUiState> = _uiState


    fun selectOption(index: Int) {

        _uiState.value = _uiState.value.copy(
            selectedIndex = index
        )
    }


    fun confirmAnswer() {

        val current = _uiState.value

        val question = current.currentQuestion ?: return

        val correct =
            current.selectedIndex == question.correctIndex

        var newScore = current.score
        var newLives = current.lives

        if (correct)
            newScore++
        else
            newLives--

        if (newLives <= 0) {

            _uiState.value = current.copy(
                score = newScore,
                lives = 0,
                lastAnswerCorrect = correct,
                isFinished = true
            )

            return
        }

        _uiState.value = current.copy(
            score = newScore,
            lives = newLives,
            lastAnswerCorrect = correct
        )
    }


    fun nextQuestion() {

        val current = _uiState.value

        if (current.currentIndex <
            current.questions.size - 1) {

            _uiState.value = current.copy(
                currentIndex = current.currentIndex + 1,
                selectedIndex = null
            )

        } else {

            _uiState.value = current.copy(
                isFinished = true
            )
        }
    }


    fun restartQuiz() {

        _uiState.value = QuizUiState(
            questions = seedQuestions(),
            lives = 3
        )
    }


    fun seedQuestions(): List<Question> {

        return listOf(

            Question(
                1,
                "¿Qué palabra es inmutable en Kotlin?",
                listOf("var","val","let","const"),
                1
            ),

            Question(
                2,
                "¿Qué función inicia una Activity?",
                listOf("onStart","onCreate","onPause","onStop"),
                1
            ),

            Question(
                3,
                "¿Qué anotación se usa en Compose?",
                listOf("@Composable","@Android","@Layout","@UI"),
                0
            ),

            Question(
                4,
                "¿Qué tipo es texto en Kotlin?",
                listOf("Int","String","Float","Boolean"),
                1
            ),

            Question(
                5,
                "¿Para qué sirve RadioButton?",
                listOf(
                    "Seleccionar una opción",
                    "Mostrar imagen",
                    "Cambiar pantalla",
                    "Guardar datos"
                ),
                0
            ),

            Question(
                6,
                "¿Qué archivo describe la app?",
                listOf(
                    "MainActivity.kt",
                    "AndroidManifest.xml",
                    "styles.xml",
                    "gradle.properties"
                ),
                1
            ),

            Question(
                7,
                "¿Qué lenguaje usa Android moderno?",
                listOf("JavaScript","Kotlin","Python","Swift"),
                1
            ),

            Question(
                8,
                "¿Qué es Compose?",
                listOf(
                    "Base de datos",
                    "Framework UI",
                    "Sistema operativo",
                    "Servidor"
                ),
                1
            ),

            Question(
                9,
                "¿Qué significa UI?",
                listOf(
                    "User Interface",
                    "Universal Internet",
                    "User Input",
                    "Ultra Image"
                ),
                0
            ),

            Question(
                10,
                "¿Qué layout organiza en columna?",
                listOf(
                    "Row",
                    "Column",
                    "Box",
                    "Stack"
                ),
                1
            ),

            Question(
                11,
                "¿Qué layout organiza en fila?",
                listOf(
                    "Row",
                    "Column",
                    "Box",
                    "Grid"
                ),
                0
            ),

            Question(
                12,
                "¿Qué hace remember en Compose?",
                listOf(
                    "Guarda estado",
                    "Borra datos",
                    "Cierra app",
                    "Crea Activity"
                ),
                0
            ),

            Question(
                13,
                "¿Qué es ViewModel?",
                listOf(
                    "Pantalla",
                    "Controlador de estado",
                    "Botón",
                    "Layout"
                ),
                1
            ),

            Question(
                14,
                "¿Qué hace MutableStateFlow?",
                listOf(
                    "Guardar estado reactivo",
                    "Mostrar imágenes",
                    "Cerrar app",
                    "Crear botones"
                ),
                0
            ),

            Question(
                15,
                "¿Qué hace Scaffold?",
                listOf(
                    "Diseño base de pantalla",
                    "Guardar archivos",
                    "Crear base de datos",
                    "Ejecutar app"
                ),
                0
            )

        )
    }

}