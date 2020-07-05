package com.example.justchess

object ChessUtil {
    const val tilesPerSide: Int = 8

    val bishopId: Int = 0
    val knightId: Int = 1
    val queenId: Int = 2
    val promotionIds: Collection<Int> = (0 until 4).toList()

    fun getOtherPlayerId(playerId: Int): Int {
        return if (playerId == 0) {
            1
        } else {
            0
        }
    }
}