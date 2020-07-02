package com.example.justchess.engine

import com.example.justchess.ChessUtil

/**
 * @param x the row index
 * @param y the column index
 */
data class Coordinate(
    val x: Int,
    val y: Int
) {
    companion object {
        fun inBounds(coordinate: Coordinate): Boolean {
            return (coordinate.x in 0 until ChessUtil.tilesPerSide) &&
                    (coordinate.y in 0 until ChessUtil.tilesPerSide)
        }
    }
}
