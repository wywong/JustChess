package com.example.justchess.engine.game

import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Piece

class DefaultBoard(
    private val pieceMap: Map<Coordinate, Piece>,
    private val whiteKingLocation: Coordinate,
    private val blackKingLocation: Coordinate
) : Board {
    override fun getPiece(location: Coordinate): Piece? {
        return pieceMap[location]
    }

    override fun getPieces(): Collection<Piece> {
        return pieceMap.values
    }

    override fun movePiece(coordinate: Coordinate, piece: Piece): Board {
        val newPieceMap = pieceMap.toMutableMap()
        newPieceMap[coordinate] = piece.updateLocation(coordinate)
        val newWhiteKingLocation = if (whiteKingLocation == piece.location) {
            coordinate
        } else {
            whiteKingLocation
        }
        val newBlackKingLocation = if (blackKingLocation == piece.location) {
            coordinate
        } else {
            blackKingLocation
        }
        return DefaultBoard(newPieceMap, newWhiteKingLocation, newBlackKingLocation)
    }

    override fun isKingInCheck(playerId: Int): Boolean {
        TODO("Not yet implemented")
    }

    private fun getOtherPlayerId(playerId: Int): Int {
        return if (playerId == 0) {
            1
        } else {
            0
        }
    }
}