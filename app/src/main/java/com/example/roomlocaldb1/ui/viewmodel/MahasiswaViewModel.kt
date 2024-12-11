package com.example.roomlocaldb1.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomlocaldb1.data.entity.Mahasiswa
import com.example.roomlocaldb1.repository.RepositoryMhs
import kotlinx.coroutines.launch

class MahasiswaViewModel (private val repositoryMhs: RepositoryMhs) : ViewModel() {
    var uiState by mutableStateOf(MhsUiState())

    //Memperbarui state berdasarkan input pengguna
    fun updateState(mahasiswaEvent: MahasiswaEvent) { //event: suatu kejadian/aksi, state: hasil dari aksi/perubahan
        uiState = uiState.copy(
            mahasiswaEvent = mahasiswaEvent,
        )
    }

    //v Validasi data input pengguna
    private fun validateFilds(): Boolean { //artinya boolean true or false
        val event = uiState.mahasiswaEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isEmpty()) null else "Nim tidak boleh kosong",
            nama = if (event.nama.isEmpty()) null else "Nama tidak boleh kosong",
            jenisKelamin = if (event.jenisKelamin.isEmpty()) null else "Jenis Kelamin tidak boleh kosong",
            alamat = if (event.alamat.isEmpty()) null else "Alamat tidak boleh kosong",
            kelas = if (event.kelas.isEmpty()) null else "Kelas tidak boleh kosong",
            angkatan = if (event.angkatan.isEmpty()) null else "Angkatan tidak boleh kosong",
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    // Menyimpan database ke repository
    fun saveData() { //aksi yg pertama dianggil, karenakita ingin menyimpan data
        val currentEvent = uiState.mahasiswaEvent
        if (validateFilds()) { //jika ini benar
            viewModelScope.launch {
                try {
                    repositoryMhs.insertMhs(currentEvent.toMahasiswaEntity()) //maka kita memanggil repositoryMhs. dari entitas baru dimasukkan ke database
                    uiState = uiState.copy(
                        snackbarMessage = "Data berhasil disimpan",
                        mahasiswaEvent = MahasiswaEvent(), // Reset input form
                        isEntryValid = FormErrorState() // Reset error state
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackbarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(snackbarMessage = "Input tidak valid. Periksa kembali data Anda.")
        }

    }

    // Reset pesan Snackbar setelah ditampilkan
    fun resetSnackbarMessage() {
        uiState = uiState.copy(snackbarMessage = null)
    }
}

// untuk membungkus formerrorstate, mahasiswaevent, snackbarmessage dalam suatu data class
data class MhsUiState(
    val mahasiswaEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackbarMessage: String? = null,
)

// memberikan sebuah validasi apakah textfield sudah sesuai atau belum
data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val jenisKelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null
) {
    fun isValid(): Boolean {
        return nim == null && nama == null && jenisKelamin == null &&
                alamat == null && kelas == null && angkatan == null
    }
}

//data class variabel yang menyimpan data input form
data class MahasiswaEvent( // menyimpan data dari sebuah textfield
    val nim: String = "",
    val nama: String = "",
    val jenisKelamin: String = "",
    val alamat: String = "",
    val kelas: String = "",
    val angkatan: String = ""
)

//menyimpan input form ke dalam entity
fun MahasiswaEvent.toMahasiswaEntity(): Mahasiswa = Mahasiswa(
    nim = nim, //yang kiri adalah entitas yang kanan adalah variabel yang ada di MahasiswaEvent
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan = angkatan
)