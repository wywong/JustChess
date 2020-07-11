package xyz.wywong.justchess

import xyz.wywong.justchess.engine.Coordinate

interface ChessBoardViewListener {
    /**
     * invoked when a chess board coordinate is selected
     */
    fun onCoordinateSelected(coordinate: Coordinate)
}