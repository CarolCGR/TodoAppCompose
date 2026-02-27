package com.example.todoappcompose

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(onBack: () -> Unit) {

    val viewModel = QuizViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Trivia App") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF9C27B0),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (!uiState.isFinished) {

                val question = uiState.currentQuestion

                Text(
                    text = "Pregunta ${uiState.currentIndex + 1} / ${uiState.questions.size}",
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = question?.title ?: "",
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                question?.options?.forEachIndexed { index, option ->

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        RadioButton(
                            selected = uiState.selectedIndex == index,
                            onClick = {
                                viewModel.selectOption(index)
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xFF7B1FA2),   // Morado
                                unselectedColor = Color(0xFFCE93D8)  // Lila
                            )
                        )

                        Text(
                            text = option,
                            fontSize = 20.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(

                    enabled = uiState.selectedIndex != null,

                    onClick = {

                        viewModel.confirmAnswer()

                        if (!uiState.isFinished)
                            viewModel.nextQuestion()
                    },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF7B1FA2),
                        contentColor = Color.White
                    ),

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)

                ) {

                    Text(

                        if (uiState.currentIndex ==
                            uiState.questions.size - 1)

                            "Ver resultados"

                        else

                            "Confirmar",

                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // VIDAS
                Text(
                    text = "Vidas: ${uiState.lives}/3 ❤️",
                    fontSize = 18.sp,
                    color = Color.Red
                )

                Spacer(modifier = Modifier.height(10.dp))

                val progress =
                    ((uiState.currentIndex + 1) * 100) /
                            uiState.questions.size

                Text(
                    "Porcentaje avance: $progress%",
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                uiState.lastAnswerCorrect?.let {

                    if (it) {
                        Text(
                            "Pregunta anterior: ✅ Correcta",
                            fontSize = 18.sp,
                            color = Color(0xFF2E7D32)
                        )
                    } else {
                        Text(
                            "Pregunta anterior: ❌ Incorrecta",
                            fontSize = 18.sp,
                            color = Color.Red
                        )
                    }
                }

            } else {


                Text(
                    text = "Resultado: ${uiState.score} / ${uiState.questions.size}",
                    fontSize = 26.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Vidas restantes: ${uiState.lives}",
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFCE93D8),
                        contentColor = Color.White
                    )
                ) {

                    Text("Volver")
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        viewModel.restartQuiz()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF7B1FA2),
                        contentColor = Color.White
                    )
                ) {

                    Text("Reintentar 🔄")
                }

            }
        }
    }
}