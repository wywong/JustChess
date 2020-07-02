package com.example.justchess

object ChessUtil {
    const val tilesPerSide: Int = 8

    fun getOtherPlayerId(playerId: Int): Int {
        return if (playerId == 0) {
            1
        } else {
            0
        }
    }
}