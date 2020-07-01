package com.example.justchess.engine.factory

import android.graphics.Bitmap
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.PieceFactory
import com.example.justchess.engine.piece.*

class DefaultPieceFactory(
    private val playerId: Int
) : PieceFactory {
    // TODO populate these
    private val bishopBitmap: Bitmap? = null
    private val kingBitmap: Bitmap? = null
    private val knightBitmap: Bitmap? = null
    private val pawnBitmap: Bitmap? = null
    private val queenBitmap: Bitmap? = null
    private val rookBitmap: Bitmap? = null

    override fun createBishop(location: Coordinate): Bishop {
        return Bishop(location, playerId, bishopBitmap, false)
    }

    override fun createKing(location: Coordinate): King {
        return King(location, playerId, kingBitmap, false)
    }

    override fun createKnight(location: Coordinate): Knight {
        return Knight(location, playerId, knightBitmap, false)
    }

    override fun createPawn(location: Coordinate): Pawn {
        return Pawn(location, playerId, pawnBitmap, false)
    }

    override fun createQueen(location: Coordinate): Queen {
        return Queen(location, playerId, queenBitmap, false)
    }

    override fun createRook(location: Coordinate): Rook {
        return Rook(location, playerId, rookBitmap, false)
    }
}