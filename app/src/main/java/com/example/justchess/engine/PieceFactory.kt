package com.example.justchess.engine

import com.example.justchess.engine.piece.*

interface PieceFactory {
    /**
     * returns a new [Bishop] at coordinate
     */
    fun createBishop(location: Coordinate): Bishop

    /**
     * returns a new [King] at coordinate
     */
    fun createKing(location: Coordinate): King

    /**
     * returns a new [Knight] at coordinate
     */
    fun createKnight(location: Coordinate): Knight

    /**
     * returns a new [Pawn] at coordinate
     */
    fun createPawn(location: Coordinate): Pawn

    /**
     * returns a new [Queen] at coordinate
     */
    fun createQueen(location: Coordinate): Queen

    /**
     * returns a new [Rook] at coordinate
     */
    fun createRook(location: Coordinate): Rook
}