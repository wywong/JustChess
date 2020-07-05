package com.example.justchess

import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Game
import com.example.justchess.engine.Piece

interface GameController {
    /**
     * returns the current game instance
     */
    val game: Game

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