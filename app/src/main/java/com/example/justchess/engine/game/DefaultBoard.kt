package com.example.justchess.engine.game

import com.example.justchess.ChessUtil
import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Piece

class DefaultBoard(
    private val pieceMap: Map<Coordinate, Piece>,
    val whiteKingLocation: Coordinate,
    val blackKingLocation: Coordinate
) : Board {
    override fun getPiece(location: Coordinate): Piece? {
        return pieceMap[location]
    }

    override fun getPieces(): Collection<Piece> {
        return pieceMap.values
    }

    override fun movePiece(coordinate: Coordinate, piece: Piece): Board {
        val newPieceMap = pieceMap.toMutableMap()
        newPieceMap.remove(piece.location)
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
        val kingLocation = getPlayerKingLocation(playerId)
        val otherPlayerId = ChessUtil.getOtherPlayerId(playerId)
        pieceMap.values.forEach { piece ->
            if (piece.playerId == otherPlayerId) {
                val destinations = piece.getPossibleDestinations(this)
                if (destinations.contains(kingLocation)) {
                    return true
                }
            }
        }
        return false
    }

    private fun getPlayerKingLocation(playerId: Int): Coordinate {
        return if (playerId == 0) {
            whiteKingLocation
        } else {
            blackKingLocation
        }
    }
}