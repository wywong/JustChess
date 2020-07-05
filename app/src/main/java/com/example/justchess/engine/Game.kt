package com.example.justchess.engine

interface Game {
    /**
     * returns the current [Board]
     */
    fun getCurrentBoard(): Board

    /**
     * returns the turn history in turn order
     */
    fun getTurnHistory(): Collection<Turn>

    /**
     * updates the game state by applying the supplied moves
     */
    fun applyMoves(moves: Collection<Move>): Turn

    /**
     * returns true if there is at least one turn to undo
     */
    fun canUndo(): Boolean

    /**
     * updates the game state by reversing the latest turn
     */
    fun undo(): Turn

    /**
     * returns 0 if it is white's turn, 1 otherwise
     */
    fun playerTurn(): Int

    /**
     * returns the id of the checkmated player, if no player is in checkmate then null is returned
     */
    fun checkmatedPlayerId(): Int?

    /**
     * returns true if the current player cannot make any more moves and their king is not in check
     */
    fun isStalemate(): Boolean

    /**
     * returns the coordinate of the promotable pawn if it exists
     */
    fun getPromotablePawnCoordinate(): Coordinate?

    /**
     * promotes pawn to promotedPiece
     */
    fun promotePawn(promotedPiece: Piece): Turn
}