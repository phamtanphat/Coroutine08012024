package com.example.coroutine08012024

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

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

//        CoroutineScope(Dispatchers.IO).launch {
//            val a = 5
//            val b = 10
//
//            // a + b - 2
//            val deferredTotal = CoroutineScope(Dispatchers.IO).async {
//                throw Exception()
//                plusNumber(a, b)
//            }
//
//            val total = try {
//                deferredTotal.await()
//            } catch (e: Exception) {
//                0
//            }
//
//            val deferredResult = CoroutineScope(Dispatchers.IO).async {
//                minusNumber(total, 2)
//            }
//
//            var result = deferredResult.await()
//
//            Log.d("phat", result.toString())
//        }

        CoroutineScope(Dispatchers.IO).launch {
            val job = launch {
                try {
                    for (i in 1..10) {
                        Log.d("phat", "Working on task $i...")
                        delay(1000L) // Mô phỏng tác vụ mất thời gian
                    }
                    Log.d("phat", "Task completed successfully.")
                } catch (e: CancellationException) {
                    Log.d("phat","Coroutine was cancelled: ${e.message}")
                } finally {
                    Log.d("phat","Cleaning up resources...")
                }
            }

            // Chờ một lúc rồi hủy coroutine
            delay(3000L)  // Giả sử người dùng muốn hủy tác vụ sau 3 giây
            Log.d("phat","Cancelling the job...")
            job.cancelAndJoin()  // Hủy và chờ coroutine kết thúc
            Log.d("phat","Job is cancelled.")
        }
    }

//    suspend fun plusNumber(a: Int, b: Int): Int {
//        delay(500)
//        return a + b
//    }
//
//    suspend fun minusNumber(a: Int, b: Int): Int {
//        delay(100)
//        return a - b
//    }
}