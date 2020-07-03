package com.example.justchess

import com.example.justchess.engine.Coordinate

interface GameController {
    /**
     * select a coordinate on the board
     */
    fun selectCoordinate(coordinate: Coordinate)

    /**
     * return the current view model for the game
     */
    fun getViewModel(): GameViewModel
}