package com.example.roomlocaldb1.repository

import com.example.roomlocaldb1.data.dao.MahasiswaDao
import com.example.roomlocaldb1.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMhs (
    private val mahasiswaDao: MahasiswaDao
) : RepositoryMhs { //menandakan implementasi interface yang mana
    override suspend fun insertMhs(mahasiswa: Mahasiswa) {
        mahasiswaDao.insertMahasiswa(mahasiswa)
    }

    //getAllMhs
    override fun getAllMhs(): Flow<List<Mahasiswa>> {
        return mahasiswaDao.getAllMahasiswa()
    }

}