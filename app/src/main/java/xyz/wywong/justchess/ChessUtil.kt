package xyz.wywong.justchess

object ChessUtil {
    const val tilesPerSide: Int = 8

    const val bishopId: Int = 0
    const val knightId: Int = 1
    const val queenId: Int = 2
    val promotionIds: Collection<Int> = (0 until 4).toList()

    fun getOtherPlayerId(playerId: Int): Int {
        return if (playerId == 0) {
            1
        } else {
            0
        }
    }
}