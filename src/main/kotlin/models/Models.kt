package uz.uztelecom.models

data class Game(
    val id: Int,
    val numberOfSets: Int,
    val sets: List<Set>,
    var isPossible: Boolean,
    var power: Int,
)

data class Set(
    val red: Int,
    val green: Int,
    val blue: Int
)

data class Node(
    val value: Char,
    val isChar: Boolean,
    var isAdjacentToChar: Boolean
)

data class Card(
    val number: Int,
    val winningNumbers: List<Int>,
    val currentNumbers: List<Int>,
    var gamePoints: Int,
    var numberOfWins: Int,
    var numberOfRepetitions: Int
)