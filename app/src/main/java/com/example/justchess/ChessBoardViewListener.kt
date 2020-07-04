package com.example.justchess

import com.example.justchess.engine.Coordinate

interface ChessBoardViewListener {
    /**
     * invoked when a chess board coordinate is selected
     */
    fun onCoordinateSelected(coordinate: Coordinate)
}