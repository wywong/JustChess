package com.example.justchess.engine.mocks

import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.GameEventListener
import com.example.justchess.engine.Piece

class FakeGameEventListener(
    private val promotedPiece: Piece
) : GameEventListener {
    override fun handlePawnPromotion(coordinate: Coordinate): Piece {
        return promotedPiece
    }

    override fun handleCheckmate(playerId: Int) {
    }

    override fun handleStalemate() {
    }
}