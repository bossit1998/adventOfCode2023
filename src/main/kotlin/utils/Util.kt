package uz.uztelecom.utils

import uz.uztelecom.models.Card
import uz.uztelecom.models.Game
import uz.uztelecom.models.Set
import java.io.File

const val globalPath = "/Users/bossit/IdeaProjects/AdventOfCode2023/src/main/resources/inputs/"

fun getFile(fileName: String): List<String> {
    val file = File(globalPath + fileName)
    val fileLines = mutableListOf<String>()
    file.forEachLine {
        fileLines.add(it)
    }

    return fileLines
}

fun parseDay41(input: List<String>): List<Card> {
    val cardsList = mutableListOf<Card>()
    input.map { cardLine ->
        val split1 = cardLine.split(":")
        val cardId: Int = split1[0].removePrefix("Card ").replace(" ","").toInt()

        val split2 = split1[1].split("|")

        val sets1: MutableList<Int> = mutableListOf()
        val sets2: MutableList<Int> = mutableListOf()

        val set1 = split2[0]
        val newSet1 = set1.removePrefix(" ").removeSuffix(" ")
        val numbers1 = newSet1.split(" ")
        numbers1.map { number ->
            if (number!="") {
                sets1.add(number.toInt())
            }
        }

        val set2 = split2[1]
        val newSet2 = set2.removePrefix(" ").removeSuffix(" ")
        val numbers2 = newSet2.split(" ")
        numbers2.map { number ->
            if (number!="") {
                sets2.add(number.toInt())
            }
        }
        cardsList.add(Card(cardId, sets1, sets2, 0, 0,1))
    }

    return cardsList
}

fun parseDay21(input: List<String>): List<Game> {
    val gamesList = mutableListOf<Game>()
    input.map { gameLine ->
        val split1 = gameLine.split(":")
        val gameId: Int = split1[0].removePrefix("Game ").toInt()

        val split2 = split1[1].split(";")

        val sets: MutableList<Set> = mutableListOf()

        split2.map { set ->
            val colors = set.split(",")
            var red = 0
            var blue = 0
            var green = 0
            colors.map { color ->
                red = if (color.contains("red")) color.getAmount()[0].toInt() else red
                blue = if (color.contains("blue")) color.getAmount()[0].toInt() else blue
                green = if (color.contains("green")) color.getAmount()[0].toInt() else green
            }
            sets.add(Set(red, green, blue))
        }

        gamesList.add(Game(gameId, split2.size, sets, true, 0))
    }

    return gamesList
}

fun String.getAmount(): List<String> {
    return Regex("[0-9]+").findAll(this)
        .map(MatchResult::value)
        .toList()
}
