package com.example.roomlocaldb1.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roomlocaldb1.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

@Dao
interface MahasiswaDao {
    @Insert
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa) //menggunakan suspend karena operasinya berat (insert, update, delete)

    //getALllMahasiswa
    @Query("SELECT * FROM mahasiswa ORDER BY nama ASC")
    fun getAllMahasiswa() : Flow<List<Mahasiswa>>

    //getMahasiswa
    @Query("SELECT * FROM mahasiswa WHERE nim = :nim")
    fun getMahasiswa(nim: String) : Flow<Mahasiswa>
}