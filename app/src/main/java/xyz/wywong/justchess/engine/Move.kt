package xyz.wywong.justchess.engine

/**
 * Contains information recording a single movement of a chess piece
 *
 * @param destination the location the [piece] is being moved to
 * @param piece the [Piece] that is being moved
 *
 */
data class Move(
    val destination: Coordinate,
    val piece: Piece
) {
    /**
     * the original location of the moved [piece]
     */
    val origin: Coordinate = piece.location

    /**
     * returns the location this move is capturing from
     */
    fun captureLocation(board: Board): Coordinate {
        return piece.captureLocation(destination, board)
    }

    /**
     * returns the score change for this move
     */
    fun scoreDelta(board: Board, playerId: Int): Int {
        val removedPiece = board.getPiece(
            captureLocation(board)
        )
        return if (removedPiece != null) {
            val delta = if (removedPiece.playerId == playerId) {
                -removedPiece.score
            } else {
                removedPiece.score
            }
            delta
        } else {
            0
        }
    }
}
