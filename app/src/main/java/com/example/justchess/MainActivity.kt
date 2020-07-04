package com.example.justchess

import android.graphics.BitmapFactory
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.justchess.controller.TwoPersonGameController
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.PieceImageProvider
import com.example.justchess.engine.factory.DefaultGameFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val point = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            display?.getRealSize(point)
        } else {
            windowManager.defaultDisplay.getRealSize(point)
        }

        val chessView = ChessBoardView(
            this,
            point.x,
            point.y
        )
        val controller = createController()
        chessView.addViewListener(object : ChessBoardViewListener {
            override fun onCoordinateSelected(coordinate: Coordinate) {
                controller.selectCoordinate(coordinate)
                chessView.setViewModel(
                    controller.getViewModel()
                )
            }
        })
        chessView.setViewModel(controller.getViewModel())
        setContentView(chessView)
    }

    private fun createController(): GameController {
        val blackImageProvider: PieceImageProvider = PieceImageProvider(
            BitmapFactory.decodeResource(resources, R.drawable.black_bishop),
            BitmapFactory.decodeResource(resources, R.drawable.black_king),
            BitmapFactory.decodeResource(resources, R.drawable.black_knight),
            BitmapFactory.decodeResource(resources, R.drawable.black_pawn),
            BitmapFactory.decodeResource(resources, R.drawable.black_queen),
            BitmapFactory.decodeResource(resources, R.drawable.black_rook)
        )
        val whiteImageProvider: PieceImageProvider = PieceImageProvider(
            BitmapFactory.decodeResource(resources, R.drawable.white_bishop),
            BitmapFactory.decodeResource(resources, R.drawable.white_king),
            BitmapFactory.decodeResource(resources, R.drawable.white_knight),
            BitmapFactory.decodeResource(resources, R.drawable.white_pawn),
            BitmapFactory.decodeResource(resources, R.drawable.white_queen),
            BitmapFactory.decodeResource(resources, R.drawable.white_rook)
        )
        val factory = DefaultGameFactory(whiteImageProvider, blackImageProvider)
        return TwoPersonGameController(factory.createNewGame())
    }
}