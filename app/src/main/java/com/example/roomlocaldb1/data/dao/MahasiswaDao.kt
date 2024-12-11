package com.example.roomlocaldb1.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.roomlocaldb1.data.entity.Mahasiswa

@Dao
interface MahasiswaDao {
    @Insert
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa) //menggunakan suspend karena operasinya berat (insert, update, delete)
}