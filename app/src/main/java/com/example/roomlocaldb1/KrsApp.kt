package com.example.roomlocaldb1

import android.app.Application
import com.example.roomlocaldb1.dependeciesinjection.ContainerApp

class KrsApp : Application() {
    lateinit var containerApp: ContainerApp //Fungsinya untuk menyimpan
    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this) //Membuat instance Object
        //instance adalah object yang dibuat dari class
    }
}