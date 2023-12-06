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
    solveDay51()
}

// Everything after this is in red
val yellow = "\u001b[33m"

// Resets previous color codes
val reset = "\u001b[0m"

val dot = '.'

private fun solveDay61() {
    /*times, distances = [list(map(int, line.split(":")[1].split())) for line in open(0)]

    n = 1

    for time, distance in zip(times, distances):
    margin = 0
    for hold in range(time):
    if hold * (time - hold) > distance:
    margin += 1
    n *= margin

    print(n)*/



    /*import sys
import re
from collections import defaultdict
name = sys.stdin.read()

D = open(sys.argv[0]).read().strip()
L = name.split('\n')
ans = 0

t,d = L
times = [int(x) for x in t.split(':')[1].split()]
dist = [int(x) for x in d.split(':')[1].split()]

T = ''
for t in times:
  T += str(t)
T = int(T)
D = ''
for d in dist:
  D += str(d)
D = int(D)

def f(t, d):
  # runs in O(log(t))
  # let g(x) = x*(t-x) is maximized at t//2
  # we want to know: what is the lowest value s.t. g(x) >= d
  # we want to know: what is the highest value s.t. g(x) >= d
  def g(x):
    return x*(t-x)
  lo = 0
  hi = t//2
  if hi*(t-hi) < d:
    return 0
  assert g(lo)<d and g(hi)>=d
  while lo+1<hi:
    m = (lo+hi)//2
    if g(m)>=d:
      hi = m
    else:
      lo = m
  assert lo+1 == hi
  assert g(lo)<d and g(hi)>=d
  first = hi
  assert g(first)>=d and g(first-1)<d

  # g(x) == g(t-x), so there's symmetry about the midpoint t/2
  last = int((t/2) + (t/2-first))
  assert g(last)>=d and g(last+1)<d, f'last={last} g(last)={g(last)} {g(last+1)} d={d}'
  return last-first+1

ans = 1
for i in range(len(times)):
  ans *= f(times[i], dist[i])
print(ans)
print(f(T,D))*/
}
private fun solveDay51() {
    val input = getFile("day5_1.txt")

    var oneBigString = ""

    input.mapIndexed { index, line ->
        oneBigString = "$oneBigString $line"
    }

    println(oneBigString)

    val list = oneBigString.split("  ")

    println(list)

    list.map { list2 ->

    }


    /*not solved by myself
     import sys
import re
from collections import defaultdict
name = sys.stdin.read()
# D = open(sys.argv[1]).read().strip()
L = name.split('\n')

parts = name.split('\n\n')
seed, *others = parts
seed = [int(x) for x in seed.split(':')[1].split()]

class Function:
  def __init__(self, S):
    lines = S.split('\n')[1:] # throw away name
    # dst src sz
    self.tuples: list[tuple[int,int,int]] = [[int(x) for x in line.split()] for line in lines]
    #print(self.tuples)
  def apply_one(self, x: int) -> int:
    for (dst, src, sz) in self.tuples:
      if src<=x<src+sz:
        return x+dst-src
    return x

  # list of [start, end) ranges
  def apply_range(self, R):
    A = []
    for (dest, src, sz) in self.tuples:
      src_end = src+sz
      NR = []
      while R:
        # [st                                     ed)
        #          [src       src_end]
        # [BEFORE ][INTER            ][AFTER        )
        (st,ed) = R.pop()
        # (src,sz) might cut (st,ed)
        before = (st,min(ed,src))
        inter = (max(st, src), min(src_end, ed))
        after = (max(src_end, st), ed)
        if before[1]>before[0]:
          NR.append(before)
        if inter[1]>inter[0]:
          A.append((inter[0]-src+dest, inter[1]-src+dest))
        if after[1]>after[0]:
          NR.append(after)
      R = NR
    return A+R

Fs = [Function(s) for s in others]

def f(R, o):
  A = []
  for line in o:
    dest,src,sz = [int(x) for x in line.split()]
    src_end = src+sz

P1 = []
for x in seed:
  for f in Fs:
    x = f.apply_one(x)
  P1.append(x)
print(min(P1))

P2 = []
pairs = list(zip(seed[::2], seed[1::2]))
for st, sz in pairs:
  # inclusive on the left, exclusive on the right
  # e.g. [1,3) = [1,2]
  # length of [a,b) = b-a
  # [a,b) + [b,c) = [a,c)
  R = [(st, st+sz)]
  for f in Fs:
    R = f.apply_range(R)
  #print(len(R))
  P2.append(min(R)[0])
print(min(P2))*/
}

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