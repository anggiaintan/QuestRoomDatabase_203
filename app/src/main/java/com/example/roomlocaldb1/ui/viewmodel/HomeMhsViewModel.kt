package com.example.roomlocaldb1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roomlocaldb1.repository.RepositoryMhs
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class HomeMhsViewModel (
    private val repositoryMhs: RepositoryMhs
) : ViewModel() {
    val homeUiState: StateFlow<HomeUiState> = repositoryMhs.getAllMhs()
        .filterNotNull()
        .map { HomeUiState (
            listMhs = it.toList(),
            isLoading = false,
        ) }
        .onStart {
            emit(HomeUiState(isLoading = true))
            delay(900)
        }
}