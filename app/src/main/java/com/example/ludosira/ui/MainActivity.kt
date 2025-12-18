package com.example.ludosira.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.ludosira.BuildConfig
import com.example.ludosira.gamelibrary.data.GameDto
import com.example.ludosira.gamelibrary.presentation.GameList
import com.example.ludosira.core.network.RetrofitClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameScreen()
                }
            }
        }
    }
}

@Composable
fun GameScreen() {
    // 1. State to hold the list of games
    var games by remember { mutableStateOf<List<GameDto>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // 2. Get the API key from BuildConfig
    val apiKey = BuildConfig.API_KEY

    // 3. Trigger API call when the screen loads
    LaunchedEffect(Unit) {
        try {
            val response = RetrofitClient.service.getGames(apiKey)
            games = response.games
            isLoading = false
        } catch (e: Exception) {
            Log.e("MainActivity", "Error fetching data", e)
            errorMessage = e.localizedMessage
            isLoading = false
        }
    }

    // 4. UI Logic
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (errorMessage != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Error: $errorMessage")
        }
    } else {
        // Render the list from GameLibraryCards.kt
        GameList(games = games)
    }
}