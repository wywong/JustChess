package com.example.justchess

import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Piece

/**
 * @param pieces the pieces on the board
 * @param selectedCoordinate the coordinate of the currently selected piece
 * @param validDestinations collection of valid destinations for the currently selected piece
 */
data class GameViewModel(
    val pieces: Collection<Piece>,
    val selectedCoordinate: Coordinate?,
    val validDestinations: Collection<Coordinate>
) {
}