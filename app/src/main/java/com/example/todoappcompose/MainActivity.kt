package com.example.todoappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoappcompose.ui.theme.TodoAppComposeTheme
import androidx.compose.ui.text.style.TextDecoration

data class Task(
    val title: String,
    val isCompleted: Boolean = false
)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoAppComposeTheme {

                var currentScreen by remember { mutableStateOf("menu") }

                when (currentScreen) {

                    "menu" -> MainMenuScreen(
                        onTodoClick = { currentScreen = "todo" },
                        onCounterClick = { currentScreen = "counter" }
                    )

                    "todo" -> TodoScreen(
                        onBack = { currentScreen = "menu" }
                    )

                    "counter" -> CounterScreen(
                        onBack = { currentScreen = "menu" }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen(
    onTodoClick: () -> Unit,
    onCounterClick: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Menú Principal ✧˖°. ⋆｡˚✿｡･:*˚:✧｡ ",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF9C27B0)
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = onCounterClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFBA68C8)
                )
            ) {
                Text("Counter App ≽^•⩊•^≼ ", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onTodoClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFBA68C8)
                )
            ) {
                Text("Todo App ૮˶• ﻌ •˶ა ", fontSize = 18.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(onBack: () -> Unit) {

    var taskText by remember { mutableStateOf("") }
    val taskList = remember { mutableStateListOf<Task>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Todo App",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF9C27B0)
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                OutlinedTextField(
                    value = taskText,
                    onValueChange = { taskText = it },
                    label = { Text("Nueva tarea") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (taskText.isNotBlank()) {
                            taskList.add(Task(taskText))
                            taskText = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFBA68C8)
                    )
                ) {
                    Text("+")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            taskList.forEachIndexed { index, task ->

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Checkbox(
                        checked = task.isCompleted,
                        onCheckedChange = { checked ->
                            taskList[index] =
                                task.copy(isCompleted = checked)
                        }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = task.title,
                        fontSize = 18.sp,
                        textDecoration =
                            if (task.isCompleted)
                                TextDecoration.LineThrough
                            else
                                TextDecoration.None
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFBA68C8)
                )
            ) {
                Text("Volver")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterScreen(onBack: () -> Unit) {

    var count by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Counter App",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF9C27B0)
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = count.toString(),
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7B1FA2)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row {

                Button(
                    onClick = { count-- },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFBA68C8)
                    )
                ) {
                    Text("-", fontSize = 22.sp)
                }

                Spacer(modifier = Modifier.width(20.dp))

                Button(
                    onClick = { count++ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFBA68C8)
                    )
                ) {
                    Text("+", fontSize = 22.sp)
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFBA68C8)
                )
            ) {
                Text("Volver")
            }
        }
    }
}