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
     * @param coordinate the coordinate the piece is being moved to
     * @param board the current board
     * returns the coordinate that this piece captures for the given coordinate
     */
    fun captureLocation(coordinate: Coordinate, board: Board): Coordinate

    /**
     * returns a collection with valid moves for this piece,
     * this is a subset of possible moves and excludes moves
     * that cause the king to be in check
     */
    fun getValidMoves(board: Board): Collection<Collection<Move>>

    /**
     * returns a collection with possible collections of moves to apply,
     * multiple moves are needed to support castling
     */
    fun getPossibleMoves(board: Board): Collection<Collection<Move>>

    /**
     * returns a list of possible destination coordinates for this piece, does not include castling
     */
    fun getPossibleDestinations(board: Board): Collection<Coordinate>
}