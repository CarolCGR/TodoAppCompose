package com.example.todoappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todoappcompose.ui.theme.TodoAppComposeTheme

data class Task(
    val title: String,
    val isCompleted: Boolean = false
)

class TodoAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoAppComposeTheme {
                TodoScreen(onBack = { finish() })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(onBack: () -> Unit) {

    val taskText = rememberSaveable { mutableStateOf("") }
    val taskList = remember { mutableStateListOf<Task>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todo App", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF7B1FA2)
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            Row(modifier = Modifier.fillMaxWidth()) {

                OutlinedTextField(
                    value = taskText.value,
                    onValueChange = { taskText.value = it },
                    label = { Text("Nueva tarea") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (taskText.value.isNotBlank()) {
                            taskList.add(Task(taskText.value))
                            taskText.value = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF9C27B0),
                        contentColor = Color.White
                    )
                ) {
                    Text("+")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            taskList.forEachIndexed { index, task ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row {

                        Checkbox(
                            checked = task.isCompleted,
                            onCheckedChange = { checked ->
                                taskList[index] =
                                    task.copy(isCompleted = checked)
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFF7B1FA2)
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = task.title,
                            style = if (task.isCompleted)
                                MaterialTheme.typography.bodyLarge.copy(
                                    textDecoration = TextDecoration.LineThrough
                                )
                            else
                                MaterialTheme.typography.bodyLarge
                        )
                    }

                    IconButton(
                        onClick = {
                            taskList.removeAt(index)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Eliminar",
                            tint = Color.Red
                        )
                    }
                }
            }
        }
    }
}