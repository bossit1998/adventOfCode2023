package uz.uztelecom

import uz.uztelecom.models.Card
import uz.uztelecom.models.Node
import uz.uztelecom.utils.getFile
import uz.uztelecom.utils.parseDay21
import uz.uztelecom.utils.parseDay41

fun main(args: Array<String>) {
    println("duck")
//    solveDay11()
//    solveDay12()
//    solveDay21()
//    solveDay22()
//    solveDay31()
//    solveDay32()
//    solveDay41()
}

// Everything after this is in red
val yellow = "\u001b[33m"

// Resets previous color codes
val reset = "\u001b[0m"

val dot = '.'

private fun solveDay41() {

    val input = getFile("day4_1.txt")

    val cardsList = parseDay41(input)

    cardsList.map { card ->
        card.winningNumbers.map { winningNumber ->
            if (card.currentNumbers.contains(winningNumber)) {
                card.numberOfWins += 1
                if (card.gamePoints == 0) {
                    card.gamePoints = 1
                } else {
                    card.gamePoints *= 2
                }
            }
        }
    }

    cardsList.mapIndexed { index, card ->
        for (j in 1..card.numberOfRepetitions) {
            for (i in 1..card.numberOfWins) {
                if (index + i < cardsList.size) {
                    cardsList[index + i].numberOfRepetitions += 1
                } else {
                    cardsList[index].numberOfRepetitions += 1
                }
            }
        }
    }

    var sum = 0
    var repSum = 0
    cardsList.map { card ->
        sum += card.gamePoints
        repSum += card.numberOfRepetitions
    }
    println(sum)
    println(repSum)
}

private fun solveDay32() {

    val input = getFile("day3_1.txt")

    var matrix = mutableListOf<List<Node>>()

    input.map { line ->
        matrix.add(line.toCharArray().map { char ->
            if (!char.isLetterOrDigit() && char != dot) {
                Node(char, true, false)
            } else {
                Node(char, false, false)
            }
        })
    }

    matrix = reiterateMatrixForGearRatio(matrix)

    matrix.mapIndexed { nodeIndex, nodes ->
        nodes.mapIndexed { charIndex, node ->
            if (node.isAdjacentToChar) {
                print(yellow + node.value + reset)
            } else {
                print(node.value)
            }
        }
        println()
    }

    // not solved by myself

    /*
    import sys
    import re
    from collections import defaultdict
    input_string = sys.stdin.read()
    print(input_string)
    lines = input_string.split('\n')
    G = [[c for c in line] for line in lines]
    R = len(G)
    C = len(G[0])

    p1 = 0
    nums = defaultdict(list)
    for r in range(len(G)):
      gears = set() # positions of '*' characters next to the current number
      n = 0
      has_part = False
      for c in range(len(G[r])+1):
        if c<C and G[r][c].isdigit():
          n = n*10+int(G[r][c])
          for rr in [-1,0,1]:
            for cc in [-1,0,1]:
              if 0<=r+rr<R and 0<=c+cc<C:
                ch = G[r+rr][c+cc]
                if not ch.isdigit() and ch != '.':
                  has_part = True
                if ch=='*':
                  gears.add((r+rr, c+cc))
        elif n>0:
          for gear in gears:
            nums[gear].append(n)
          if has_part:
            p1 += n
          n = 0
          has_part = False
          gears = set()

    print(p1)
    p2 = 0
    for k,v in nums.items():
      if len(v)==2:
        p2 += v[0]*v[1]
    print(p2)
    */
}

private fun solveDay31() {
    val input = getFile("day3_1.txt")

    var matrix = mutableListOf<List<Node>>()

    input.map { line ->
        matrix.add(line.toCharArray().map { char ->
            if (!char.isLetterOrDigit() && char != dot) {
                Node(char, true, false)
            } else {
                Node(char, false, false)
            }
        })
    }

    matrix = reiterateMatrix(matrix)

//    matrix.mapIndexed { nodeIndex, nodes ->
//        nodes.mapIndexed { charIndex, node ->
//            if (node.isAdjacentToChar) {
//                print(yellow + node.value + reset)
//            } else {
//                print(node.value)
//            }
//        }
//        println()
//    }

    var sum = 0

    matrix.mapIndexed { nodeIndex, nodes ->
        var numberAsString = ""

        nodes.mapIndexed { charIndex, node ->
            if (
                node.value.isDigit() && (node.isAdjacentToChar
                    || (charIndex > 0 && nodes[charIndex - 1].isAdjacentToChar)
                    || (charIndex < nodes.size - 1 && nodes[charIndex + 1].isAdjacentToChar))
            ) {
                numberAsString += node.value.toString()
            } else {
                if (numberAsString != "") {
                    sum += numberAsString.toInt()
                }
                numberAsString = ""
            }
        }
    }


    println("sum: " + sum)
    println()

//    input.map { nodes ->
//        println(nodes.getAmount())
//    }
}

private fun reiterateMatrixForGearRatio(matrix: MutableList<List<Node>>): MutableList<List<Node>> {
    for (i in 1..3) {
        matrix.mapIndexed { nodeIndex, nodes ->
            nodes.mapIndexed { charIndex, node ->
                if (node.isChar && node.value == '*' && node.value != dot) {
                    if (nodes[charIndex - 1].value != dot) nodes[charIndex - 1].isAdjacentToChar = true
                    if (nodes[charIndex + 1].value != dot) nodes[charIndex + 1].isAdjacentToChar = true
                    if (matrix[nodeIndex - 1][charIndex - 1].value != dot) matrix[nodeIndex - 1][charIndex - 1].isAdjacentToChar = true
                    if (matrix[nodeIndex - 1][charIndex].value != dot) matrix[nodeIndex - 1][charIndex].isAdjacentToChar = true
                    if (matrix[nodeIndex - 1][charIndex + 1].value != dot) matrix[nodeIndex - 1][charIndex + 1].isAdjacentToChar = true
                    if (matrix[nodeIndex + 1][charIndex - 1].value != dot) matrix[nodeIndex + 1][charIndex - 1].isAdjacentToChar = true
                    if (matrix[nodeIndex + 1][charIndex].value != dot) matrix[nodeIndex + 1][charIndex].isAdjacentToChar = true
                    if (matrix[nodeIndex + 1][charIndex + 1].value != dot) matrix[nodeIndex + 1][charIndex + 1].isAdjacentToChar = true
                } else if (
                    node.value.isDigit()
                ) {
                    if (charIndex > 0 && charIndex < nodes.lastIndex &&
                        (nodes[charIndex - 1].isAdjacentToChar
                            || nodes[charIndex + 1].isAdjacentToChar
                            )
                    ) {
                        node.isAdjacentToChar = true
                    } else if (charIndex == 0 && nodes[charIndex + 1].isAdjacentToChar) {
                        node.isAdjacentToChar = true
                    } else if (charIndex == nodes.lastIndex && nodes[charIndex - 1].isAdjacentToChar) {
                        node.isAdjacentToChar = true
                    }
                }
            }
        }
    }
    return matrix
}

private fun reiterateMatrix(matrix: MutableList<List<Node>>): MutableList<List<Node>> {
    for (i in 1..3) {
        matrix.mapIndexed { nodeIndex, nodes ->
            nodes.mapIndexed { charIndex, node ->
                if (node.isChar && node.value != dot) {
                    if (nodes[charIndex - 1].value != dot) nodes[charIndex - 1].isAdjacentToChar = true
                    if (nodes[charIndex + 1].value != dot) nodes[charIndex + 1].isAdjacentToChar = true
                    if (matrix[nodeIndex - 1][charIndex - 1].value != dot) matrix[nodeIndex - 1][charIndex - 1].isAdjacentToChar = true
                    if (matrix[nodeIndex - 1][charIndex].value != dot) matrix[nodeIndex - 1][charIndex].isAdjacentToChar = true
                    if (matrix[nodeIndex - 1][charIndex + 1].value != dot) matrix[nodeIndex - 1][charIndex + 1].isAdjacentToChar = true
                    if (matrix[nodeIndex + 1][charIndex - 1].value != dot) matrix[nodeIndex + 1][charIndex - 1].isAdjacentToChar = true
                    if (matrix[nodeIndex + 1][charIndex].value != dot) matrix[nodeIndex + 1][charIndex].isAdjacentToChar = true
                    if (matrix[nodeIndex + 1][charIndex + 1].value != dot) matrix[nodeIndex + 1][charIndex + 1].isAdjacentToChar = true
                } else if (
                    node.value.isDigit()
                ) {
                    if (charIndex > 0 && charIndex < nodes.lastIndex &&
                        (nodes[charIndex - 1].isAdjacentToChar
                            || nodes[charIndex + 1].isAdjacentToChar
                            )
                    ) {
                        node.isAdjacentToChar = true
                    } else if (charIndex == 0 && nodes[charIndex + 1].isAdjacentToChar) {
                        node.isAdjacentToChar = true
                    } else if (charIndex == nodes.lastIndex && nodes[charIndex - 1].isAdjacentToChar) {
                        node.isAdjacentToChar = true
                    }
                }
            }
        }
    }
    return matrix
}

private fun solveDay22() {
    val input = getFile("day2_1.txt")
    val gamesList = parseDay21(input)

    gamesList.map { game ->
        var redCubesMin = Int.MIN_VALUE
        var greenCubesMin = Int.MIN_VALUE
        var blueCubesMin = Int.MIN_VALUE

        game.sets.map { set ->
            if (set.red > redCubesMin && set.red != 0) {
                redCubesMin = set.red
            }
            if (set.green > greenCubesMin && set.green != 0) {
                greenCubesMin = set.green
            }
            if (set.blue > blueCubesMin && set.blue != 0) {
                blueCubesMin = set.blue
            }
        }

        game.power = redCubesMin * greenCubesMin * blueCubesMin
    }

    var power = 0
    gamesList.map { game ->
        power += game.power
    }

    println("power:$power")
}

private fun solveDay21() {
    val input = getFile("day2_1.txt")
    val gamesList = parseDay21(input)

    val redCubesCount = 12
    val greenCubesCount = 13
    val blueCubesCount = 14

    gamesList.map { game ->
        game.sets.map { set ->
            if (set.red > redCubesCount || set.green > greenCubesCount || set.blue > blueCubesCount) {
                game.isPossible = false
            }
        }
    }

    var sum = 0
    gamesList.map { game ->
        if (game.isPossible) {
            sum += game.id
        }
    }

    println(sum)

}

private fun solveDay12() {
    val input = getFile("day1_1.txt")
    val answersList = mutableListOf<Int>()
    val numbersList = mutableListOf<MutableList<Int>>()

    input.map { line ->
        var copyOfLine = line
        copyOfLine = copyOfLine.replace("zero", "zoo")
        copyOfLine = copyOfLine.replace("one", "o1e")
        copyOfLine = copyOfLine.replace("two", "t2o")
        copyOfLine = copyOfLine.replace("three", "t3e")
        copyOfLine = copyOfLine.replace("four", "f4r")
        copyOfLine = copyOfLine.replace("five", "f5e")
        copyOfLine = copyOfLine.replace("six", "s6x")
        copyOfLine = copyOfLine.replace("seven", "s7n")
        copyOfLine = copyOfLine.replace("eight", "e8t")
        copyOfLine = copyOfLine.replace("nine", "n9e")

        val digitsList = mutableListOf<Int>()

        copyOfLine.map { char ->
            if (char.isDigit()) {
                digitsList.add(char.digitToInt())
            }
        }
        numbersList.add(digitsList)
    }

    numbersList.map { list ->
        answersList.add(list[0] * 10 + list[list.lastIndex])
    }

    println(answersList.sumOf { it })
}

private fun solveDay11() {
    val input = getFile("day1_1.txt")
    val answersList = mutableListOf<Int>()
    val numbersList = mutableListOf<MutableList<Int>>()

    input.map { line ->
        val digitsList = mutableListOf<Int>()

        line.map { char ->

            if (char.isDigit()) {
                digitsList.add(char.digitToInt())
            }
        }
        numbersList.add(digitsList)
    }

    numbersList.map { list ->
        answersList.add(list[0] * 10 + list[list.lastIndex])
    }

    println(answersList.sumOf { it })
}