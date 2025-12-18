package com.example.ludosira.gamelibrary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ludosira.BuildConfig
import com.example.ludosira.gamelibrary.data.GameData
import com.example.ludosira.gamelibrary.data.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface GameUiState {
    data object Loading : GameUiState
    data class Success(val games: List<GameData>) : GameUiState
    data class Error(val message: String) : GameUiState
}

class GameViewModel : ViewModel() {

    private val repository = GameRepository()

    private val _uiState = MutableStateFlow<GameUiState>(GameUiState.Loading)

    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    init {
        fetchGames()
    }

    private fun fetchGames() {
        viewModelScope.launch {
            _uiState.value = GameUiState.Loading

            try {
                val apiKey = BuildConfig.API_KEY
                val gameResponse = repository.getGames(apiKey)
                _uiState.value = GameUiState.Success(gameResponse.games)
            } catch (e: Exception) {
                _uiState.value = GameUiState.Error(e.localizedMessage ?: "An unknown error occurred")
            }
        }
    }
}