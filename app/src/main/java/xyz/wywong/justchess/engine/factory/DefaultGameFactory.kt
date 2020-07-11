package xyz.wywong.justchess.engine.factory

import xyz.wywong.justchess.engine.*
import xyz.wywong.justchess.engine.game.DefaultBoard
import xyz.wywong.justchess.engine.game.DefaultGame

class DefaultGameFactory(
    whitePieceImageProvider: PieceImageProvider,
    blackPieceImageProvider: PieceImageProvider
) : GameFactory {
    override val whitePieceFactory: PieceFactory = DefaultPieceFactory(
        0,
        whitePieceImageProvider
    )
    override val blackPieceFactory: PieceFactory = DefaultPieceFactory(
        1,
        blackPieceImageProvider
    )

    private val blackKingLocation: Coordinate = Coordinate(4, 0)
    private val whiteKingLocation: Coordinate = Coordinate(4, 7)

    override fun createNewGame(): Game {
        val collector: MutableMap<Coordinate, Piece> = mutableMapOf()
        populatePawns(collector)
        populateRooks(collector)
        populateKnights(collector)
        populateBishops(collector)
        populateQueens(collector)
        populateKings(collector)
        return DefaultGame(
            DefaultBoard(
                collector,
                whiteKingLocation,
                blackKingLocation,
                null
            ),
            0
        )
    }

    private fun populatePawns(collector: MutableMap<Coordinate, Piece>) {
        for (c in 0 until 8) {
            val blackCoordinate = Coordinate(c, 1)
            collector[blackCoordinate] = blackPieceFactory.createPawn(blackCoordinate)
            val whiteCoordinate = Coordinate(c, 6)
            collector[whiteCoordinate] = whitePieceFactory.createPawn(whiteCoordinate)
        }
    }

    private fun populateRooks(collector: MutableMap<Coordinate, Piece>) {
        val blackRookLeftLocation = Coordinate(0, 0)
        collector[blackRookLeftLocation] = blackPieceFactory.createRook(blackRookLeftLocation)
        val blackRookRightLocation = Coordinate(7, 0)
        collector[blackRookRightLocation] = blackPieceFactory.createRook(blackRookRightLocation)
        val whiteRookLeftLocation = Coordinate(0, 7)
        collector[whiteRookLeftLocation] = whitePieceFactory.createRook(whiteRookLeftLocation)
        val whiteRookRightLocation = Coordinate(7, 7)
        collector[whiteRookRightLocation] = whitePieceFactory.createRook(whiteRookRightLocation)
    }

    private fun populateKnights(collector: MutableMap<Coordinate, Piece>) {
        val blackKnightLeftLocation = Coordinate(1, 0)
        collector[blackKnightLeftLocation] = blackPieceFactory.createKnight(blackKnightLeftLocation)
        val blackKnightRightLocation = Coordinate(6, 0)
        collector[blackKnightRightLocation] =
            blackPieceFactory.createKnight(blackKnightRightLocation)
        val whiteKnightLeftLocation = Coordinate(1, 7)
        collector[whiteKnightLeftLocation] = whitePieceFactory.createKnight(whiteKnightLeftLocation)
        val whiteKnightRightLocation = Coordinate(6, 7)
        collector[whiteKnightRightLocation] =
            whitePieceFactory.createKnight(whiteKnightRightLocation)
    }


    private fun populateBishops(collector: MutableMap<Coordinate, Piece>) {
        val blackBishopLeftLocation = Coordinate(2, 0)
        collector[blackBishopLeftLocation] = blackPieceFactory.createBishop(blackBishopLeftLocation)
        val blackBishopRightLocation = Coordinate(5, 0)
        collector[blackBishopRightLocation] =
            blackPieceFactory.createBishop(blackBishopRightLocation)
        val whiteBishopLeftLocation = Coordinate(2, 7)
        collector[whiteBishopLeftLocation] = whitePieceFactory.createBishop(whiteBishopLeftLocation)
        val whiteBishopRightLocation = Coordinate(5, 7)
        collector[whiteBishopRightLocation] =
            whitePieceFactory.createBishop(whiteBishopRightLocation)
    }

    private fun populateQueens(collector: MutableMap<Coordinate, Piece>) {
        val blackQueenLocation = Coordinate(3, 0)
        collector[blackQueenLocation] = blackPieceFactory.createQueen(blackQueenLocation)
        val whiteQueenLocation = Coordinate(3, 7)
        collector[whiteQueenLocation] = whitePieceFactory.createQueen(whiteQueenLocation)
    }

    private fun populateKings(collector: MutableMap<Coordinate, Piece>) {
        collector[blackKingLocation] = blackPieceFactory.createKing(blackKingLocation)
        collector[whiteKingLocation] = whitePieceFactory.createKing(whiteKingLocation)
    }
}