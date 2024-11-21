package _06_other_fp_concepts.product_vs_sum_types.chess.product_type_implementation

data class Player(val name: String, val rating: Int)

enum class Status { NotStarted, InProgress, Finished }

// players and winner must be nullable because they might not be present in all game states
// E.g. In a game that has not started, there are no players nor is there a winner.
//      In an in-progress game there are players but no winner.
class Game(val status: Status, val players: Pair<Player, Player>?, val winner: Player?) {

    fun status() {
        when (status) {
            Status.NotStarted -> println("Game not started")

            // If we are not careful in the implementation of our game, the players attribute might be null
            Status.InProgress -> println("Game on: ${players?.first?.name} vs ${players?.second?.name}")

            // If we are not careful in the implementation of our game, the winner attribute might be null
            Status.Finished -> println("Player ${winner?.name} wins!")
        }
    }

}

fun main() {

    val p1 = Player("Alice", 1000)
    val p2 = Player("Bob", 1000)

    // Under normal circumstances, things work ok...
    Game(Status.NotStarted, null, null).status()           // A game that has not started has no players or winner
    Game(Status.InProgress, Pair(p1, p2), null).status()          // An in-progress game SHOULD have two players (but may not)
    Game(Status.Finished, null, p1).status()                      // A finished game SHOULD have a winner (but may not)

    // But if our implementation is not careful, it is possible to create Game objects that are invalid...
    Game(Status.Finished, null, null).status()             // A finished game with no winner
    Game(Status.InProgress, null, null).status()           // An in-progress game with no players
}