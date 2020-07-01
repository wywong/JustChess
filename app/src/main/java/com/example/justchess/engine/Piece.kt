package com.example.justchess.engine

import android.graphics.Bitmap

interface Piece {
    /**
     * the current location of the chess piece
     */
    val location: Coordinate

    /**
     * returns the id of the player that this piece belongs to, 0 if white, 1 if black
     */
    val playerId: Int

    /**
     * a bitmap that represents this chess piece
     */
    val image: Bitmap?

    /**
     * returns true if the piece has moved from it's initial location
     */
    fun hasMoved(): Boolean

    /**
     * returns the piece with an updated location
     */
    fun updateLocation(coordinate: Coordinate): Piece

    /**
     * returns a collection with valid coordinates to move to
     */
    fun getValidDestinations(board: Board): Collection<Coordinate>
}