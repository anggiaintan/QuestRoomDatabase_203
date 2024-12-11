package com.example.roomlocaldb1.repository

import com.example.roomlocaldb1.data.entity.Mahasiswa

interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
}