package com.example.coroutine08012024

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Scope: Quan ly phạm vi của coroutine
        // CoroutineContext: Cung cấp các element để tạo ra được 1 coroutine
        // Job: Quản lý về life cycle của Coroutine
        // Coroutine name: Tên Coroutine
        // Dispatcher: Quản lý luồng chạy của Coroutine
        // CoroutineExceptionHandler: Quản lý các exception xảy ra trong coroutine


        CoroutineScope(Dispatchers.IO).launch {
            var a = 0
            for (i in 0..1000) {
                a += i
                delay(1)
            }

            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity, a.toString(), Toast.LENGTH_SHORT).show()
            }

        }
    }
}