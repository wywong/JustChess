package xyz.wywong.justchess.engine.game

import xyz.wywong.justchess.ChessUtil
import xyz.wywong.justchess.engine.Board
import xyz.wywong.justchess.engine.Coordinate
import xyz.wywong.justchess.engine.Move
import xyz.wywong.justchess.engine.Piece
import xyz.wywong.justchess.engine.movement.DiagonalBehavior
import xyz.wywong.justchess.engine.movement.KnightBehavior
import xyz.wywong.justchess.engine.movement.PlusBehavior

class DefaultBoard(
    private val pieceMap: Map<Coordinate, Piece>,
    val whiteKingLocation: Coordinate,
    val blackKingLocation: Coordinate,
    override val lastMovedPiece: Pair<Piece, Piece>?
) : Board {
    override fun getPiece(location: Coordinate): Piece? {
        return pieceMap[location]
    }

    override fun getPieces(): Collection<Piece> {
        return pieceMap.values
    }

    override fun getPiecesForPlayer(playerId: Int): Collection<Piece> {
        return pieceMap.values.filter { piece -> piece.playerId == playerId }
    }

    override fun applyMoves(moves: Collection<Move>): Board {
        return moves.fold(this as Board) { board, move ->
            board.movePiece(move.destination, move.piece)
        }
    }

    override fun movePiece(coordinate: Coordinate, piece: Piece): Board {
        val newPieceMap = pieceMap.toMutableMap()
        newPieceMap.remove(piece.location)
        newPieceMap.remove(piece.captureLocation(coordinate, this))
        val updatedPiece = piece.updateLocation(coordinate)
        newPieceMap[coordinate] = updatedPiece
        val newWhiteKingLocation = if (whiteKingLocation == piece.location) {
            coordinate
        } else {
            whiteKingLocation
        }
        val newBlackKingLocation = if (blackKingLocation == piece.location) {
            coordinate
        } else {
            blackKingLocation
        }
        return DefaultBoard(
            newPieceMap,
            newWhiteKingLocation,
            newBlackKingLocation,
            Pair(piece, updatedPiece)
        )
    }

    override fun isKingInCheck(playerId: Int): Boolean {
        val kingLocation = getPlayerKingLocation(playerId)
        val otherPlayerId = ChessUtil.getOtherPlayerId(playerId)
        val possibleAttackerOrigins = getPossibleAttackerOrigins(kingLocation)
        getPiecesForPlayer(otherPlayerId).forEach { piece ->
            if (possibleAttackerOrigins.contains(piece.location)) {
                val destinations = piece.getPossibleDestinations(this)
                if (destinations.contains(kingLocation)) {
                    return true
                }
            }
        }
        return false
    }

    private fun getPlayerKingLocation(playerId: Int): Coordinate {
        return if (playerId == 0) {
            whiteKingLocation
        } else {
            blackKingLocation
        }
    }

    /**
     * @param kingLocation the coordinate of the king we are checking
     * returns a set of all possible locations that could potentially attack the king
     */
    private fun getPossibleAttackerOrigins(kingLocation: Coordinate): Set<Coordinate> {
        val possibleAttackerOrigins = mutableSetOf<Coordinate>()
        possibleAttackerOrigins.addAll(
            PlusBehavior(kingLocation).possibleMoves(this)
        )
        possibleAttackerOrigins.addAll(
            DiagonalBehavior(kingLocation).possibleMoves(this)
        )
        possibleAttackerOrigins.addAll(
            KnightBehavior(kingLocation).possibleMoves(this)
        )
        return possibleAttackerOrigins
    }
}