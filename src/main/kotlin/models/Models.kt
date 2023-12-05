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

data class Result(
    val seed: Int,
    val soil: Int,
    val fertilizer: Int,
    val water: Int,
    val light: Int,
    val temperature: Int,
    val humidity: Int,
    val location: Int,
)

data class RangeMaps(
    val destination: Int,
    val source: Int,
    val range: Int
)

data class RangeMaps2(
    val destinationStart: Int,
    val destinationEnd: Int,
    val sourceStart: Int,
    val sourceEnd: Int
)