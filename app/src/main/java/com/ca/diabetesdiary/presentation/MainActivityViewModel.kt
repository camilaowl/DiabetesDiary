package com.ca.diabetesdiary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.diabetesdiary.presentation.state.MainViewState
import com.ca.domain.repository.MainRepository
import com.ca.navigation.nav_graphs.AuthGraph
import com.ca.navigation.nav_graphs.MainGraph
import com.ca.navigation.nav_graphs.TopLevelDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState: StateFlow<MainViewState> = _viewState
        .onStart {
            setTheme()
            loadUserSettings()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            MainViewState()
        )

    fun startDestination(): TopLevelDestination {
        val startDestination = if (!repository.isUserSignedIn) AuthGraph
        else MainGraph.Home
        return startDestination
    }

    private fun setTheme() {
        viewModelScope.launch {
            repository.darkMode().collect { darkMode ->
                _viewState.update { it.copy(darkMode = darkMode) }
            }
        }
    }

    private fun loadUserSettings() {
        viewModelScope.launch {
            repository.fetchRemoteSettings()
        }
    }
}