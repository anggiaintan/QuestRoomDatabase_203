package com.example.roomlocaldb1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roomlocaldb1.repository.RepositoryMhs
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class HomeMhsViewModel (
    private val repositoryMhs: RepositoryMhs
) : ViewModel() {
    val homeUiState: StateFlow<HomeUiState> = repositoryMhs.getAllMhs()
        .filterNotNull()
        .map { HomeUiState (
            listMhs = it.toList(),
            isLoading = false,
        ) }
}