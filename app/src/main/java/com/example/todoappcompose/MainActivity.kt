package com.example.todoappcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todoappcompose.ui.theme.TodoAppComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppComposeTheme {
                MainScreen {
                    startActivity(Intent(this, TodoAppActivity::class.java))
                }
            }
        }
    }
}

@Composable
fun MainScreen(onNavigate: () -> Unit) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = onNavigate,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7B1FA2), // Morado elegante
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Entrar a TodoApp",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}