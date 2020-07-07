package com.example.justchess.engine.mocks

import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Move
import com.example.justchess.engine.Piece

class FakeBoard(
    private val kingChecked: Boolean,
    private val piece: Piece? = null,
    override val lastMovedPiece: Pair<Piece, Piece>? = null
) : Board {
    override fun getPiece(location: Coordinate): Piece? {
        return if (this.piece?.location == location) {
            this.piece
        } else {
            null
        }
    }

    override fun getPieces(): Collection<Piece> {
        return ArrayList()
    }

    override fun getPiecesForPlayer(playerId: Int): Collection<Piece> {
        return ArrayList()
    }

    override fun applyMoves(moves: Collection<Move>): Board {
        return this
    }

    override fun movePiece(coordinate: Coordinate, piece: Piece): Board {
        return this
    }

    override fun isKingInCheck(playerId: Int): Boolean {
        return kingChecked
    }
}

