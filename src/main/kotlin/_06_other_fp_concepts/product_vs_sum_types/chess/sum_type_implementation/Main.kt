package _06_other_fp_concepts.product_vs_sum_types.chess.sum_type_implementation

data class Player(val name: String, val rating: Int)

sealed interface Game {

    // A data object is used here instead of a data class because we don't need to store any attributes
    // beyond the simple fact of the game state, which is represented by the type of the object itself (NotStarted)
    data object NotStarted : Game

    // An in-progress game MUST have two players
    data class InProgress(val player1: Player, val player2: Player) : Game

    // A finished game MUST have a winner
    data class Finished(val winner: Player) : Game

    fun status() {
        when (this) {
            NotStarted -> println("Game not started")
            is InProgress -> println("Game on: ${player1.name} vs ${player2.name}")
            is Finished -> println("Player ${winner.name} wins!")
        }
    }
}

fun main() {

    val p1 = Player("Alice", 1000)
    val p2 = Player("Bob", 1000)

    // Using Sum types, we have created a data structure that can only represent valid game states
    // It is impossible to create an invalid game state
    val game1 = Game.NotStarted               // A game that has not started has no players or winner
    val game2 = Game.InProgress(p1, p2)       // An in-progress game MUST have two players
    val game3 = Game.Finished(p1)             // A finished game MUST have a winner

    game1.status()
    game2.status()
    game3.status()
}