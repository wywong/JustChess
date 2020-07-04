package com.example.justchess

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Game
import com.example.justchess.engine.PieceImageProvider
import com.example.justchess.engine.factory.DefaultGameFactory

abstract class ChessGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val chessView = ChessBoardView(this)

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
        val blackImageProvider = PieceImageProvider(
            BitmapFactory.decodeResource(resources, R.drawable.black_bishop),
            BitmapFactory.decodeResource(resources, R.drawable.black_king),
            BitmapFactory.decodeResource(resources, R.drawable.black_knight),
            BitmapFactory.decodeResource(resources, R.drawable.black_pawn),
            BitmapFactory.decodeResource(resources, R.drawable.black_queen),
            BitmapFactory.decodeResource(resources, R.drawable.black_rook)
        )
        val whiteImageProvider = PieceImageProvider(
            BitmapFactory.decodeResource(resources, R.drawable.white_bishop),
            BitmapFactory.decodeResource(resources, R.drawable.white_king),
            BitmapFactory.decodeResource(resources, R.drawable.white_knight),
            BitmapFactory.decodeResource(resources, R.drawable.white_pawn),
            BitmapFactory.decodeResource(resources, R.drawable.white_queen),
            BitmapFactory.decodeResource(resources, R.drawable.white_rook)
        )
        val factory = DefaultGameFactory(whiteImageProvider, blackImageProvider)
        return buildController(factory.createNewGame())
    }

    protected abstract fun buildController(game: Game): GameController
}