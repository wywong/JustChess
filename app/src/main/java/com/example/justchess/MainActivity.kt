package com.example.justchess

import android.graphics.Point
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var chessView: ChessBoardView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val point = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            display?.getRealSize(point)
        } else {
            windowManager.defaultDisplay.getRealSize(point)
        }

        chessView = ChessBoardView(
            this,
            point.x,
            point.y
        )
        setContentView(chessView)
    }

    override fun onResume() {
        super.onResume()

        chessView?.resume()
    }

    override fun onPause() {
        super.onPause()
        chessView?.pause()
    }
}