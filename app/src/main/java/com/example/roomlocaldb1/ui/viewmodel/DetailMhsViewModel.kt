package com.example.roomlocaldb1.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.roomlocaldb1.repository.RepositoryMhs
import com.example.roomlocaldb1.ui.navigation.DestinasiDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class DetailMhsViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryMhs: RepositoryMhs,
) : ViewModel() {
    private val _nim: String = checkNotNull(savedStateHandle[DestinasiDetail.NIM])

    val detailUiState: StateFlow<DetailUiState> = repositoryMhs.getMhs(_nim)
        .filterNotNull()
        .map {
            DetailUiState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoding = false,
            )
        }
        .onStart {
            emit(DetailUiState(isLoding = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiState(
                    isLoding = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan",
                    ))
        }

}