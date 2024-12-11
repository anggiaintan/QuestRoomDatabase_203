package com.example.roomlocaldb1.dependeciesinjection

import android.content.Context
import com.example.roomlocaldb1.data.database.KrsDatabase
import com.example.roomlocaldb1.repository.LocalRepositoryMhs
import com.example.roomlocaldb1.repository.RepositoryMhs

interface InterfaceContainerApp {
    val repositoryMhs: RepositoryMhs //semua repository dimasukkan ke interface
}

// agar bisa digunakan maka harus ada satu container untuk menampung
class ContainerApp (private val context: Context) : InterfaceContainerApp {
    override val repositoryMhs: RepositoryMhs by lazy {
     LocalRepositoryMhs(KrsDatabase.getDatabase(context).mahasiswaDao())
    }
}