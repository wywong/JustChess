package com.example.justchess

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.justchess.engine.Coordinate
import kotlin.math.min

private data class Tile(val rect: Rect, val paint: Paint)

class ChessBoardView(
    context: Context,
    width: Int,
    height: Int
) : SurfaceView(context) {

    private val tileLength: Int = min(width, height) / (ChessUtil.tilesPerSide + 1)
    private val boardLength: Int = ChessUtil.tilesPerSide * tileLength
    private val boardTopX: Int = tileLength / 2
    private val boardTopY: Int = (height - boardLength) / 2
    private val tiles = arrayListOf<Tile>()
    private val coordinateToRect = mutableMapOf<Coordinate, Rect>()

    private var viewModel: GameViewModel? = null

    init {
        preComputeTiles()
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(p0: SurfaceHolder) {
                draw()
            }

            override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
                // TODO
            }

            override fun surfaceDestroyed(p0: SurfaceHolder) {
                // TODO
            }
        })
    }

    fun setViewModel(gameViewModel: GameViewModel) {
        viewModel = gameViewModel
    }

    private fun draw() {
        if (holder.surface.isValid) {
            val canvas = holder.lockCanvas()

            drawBackground(canvas)
            drawBoard(canvas)
            drawPieces(canvas)

            holder.unlockCanvasAndPost(canvas)
        }
    }

    private fun drawBackground(canvas: Canvas) {
        canvas.drawRGB(0, 0, 0)
    }

    private fun drawBoard(canvas: Canvas) {
        for (tile in tiles) {
            canvas.drawRect(tile.rect, tile.paint)
        }
    }

    private fun drawPieces(canvas: Canvas) {
        viewModel?.pieces?.forEach { piece ->
            if (piece.image != null) {
                canvas.drawBitmap(
                    piece.image!!,
                    null,
                    coordinateToRect[piece.location]!!,
                    null
                )
            }
        }
    }

    private fun preComputeTiles() {
        val tileColor1 = Color.rgb(245, 222, 179)
        val paint1 = Paint()
        paint1.color = tileColor1
        val tileColor2 = Color.rgb(222, 184, 135)
        val paint2 = Paint()
        paint2.color = tileColor2
        val tileRange = 0 until ChessUtil.tilesPerSide
        for (row in tileRange) {
            for (col in tileRange) {
                val left = boardTopX + (row * tileLength)
                val top = boardTopY + (col * tileLength)
                val right = left + tileLength
                val bottom = top + tileLength
                val rect = Rect(left, top, right, bottom)
                val paint = if ((row + col) % 2 == 0) {
                    paint1
                } else {
                    paint2
                }
                tiles.add(Tile(rect, paint))
                val coordinate = Coordinate(row, col)
                coordinateToRect[coordinate] = rect
            }
        }
    }
}