package xyz.wywong.justchess

import xyz.wywong.justchess.engine.Coordinate
import xyz.wywong.justchess.engine.Piece

interface GameController {
    /**
     * returns the current player turn
     */
    fun playerTurn(): Int

    /**
     * returns the coordinate of the promotable pawn if it exists
     */
    fun getPromotablePawnCoordinate(): Coordinate?

    /**
     * adds a view model listener to this controller
     */
    fun addViewModelListener(listener: GameEventListener)

    /**
     * select a coordinate on the board
     */
    fun selectCoordinate(coordinate: Coordinate)

    /**
     * return the current view model for the game
     */
    fun getViewModel(): GameViewModel

    /**
     * promotes promotable pawn to provided piece
     */
    fun promotePawn(promotedPiece: Piece)
}