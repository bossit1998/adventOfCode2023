package uz.uztelecom

import uz.uztelecom.models.Card
import uz.uztelecom.models.Node
import uz.uztelecom.models.Tile
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
//    solveDay51()
    solveDay101()
}

// Everything after this is in red
val yellow = "\u001b[33m"

// Resets previous color codes
val reset = "\u001b[0m"

val dot = '.'

private fun solveDay112() {
/*grid = open(0).read().splitlines()

empty_rows = [r for r, row in enumerate(grid) if all(ch == "." for ch in row)]
empty_cols = [c for c, col in enumerate(zip(*grid)) if all(ch == "." for ch in col)]

points = [(r, c) for r, row in enumerate(grid) for c, ch in enumerate(row) if ch == "#"]

total = 0
scale = 1000000

for i, (r1, c1) in enumerate(points):
    for (r2, c2) in points[:i]:
        for r in range(min(r1, r2), max(r1, r2)):
            total += scale if r in empty_rows else 1
        for c in range(min(c1, c2), max(c1, c2)):
            total += scale if c in empty_cols else 1

print(total)*/
}

private fun solveDay111() {
    /*grid = open(0).read().splitlines()

empty_rows = [r for r, row in enumerate(grid) if all(ch == "." for ch in row)]
empty_cols = [c for c, col in enumerate(zip(*grid)) if all(ch == "." for ch in col)]

points = [(r, c) for r, row in enumerate(grid) for c, ch in enumerate(row) if ch == "#"]

total = 0
scale = 2

for i, (r1, c1) in enumerate(points):
    for (r2, c2) in points[:i]:
        for r in range(min(r1, r2), max(r1, r2)):
            total += scale if r in empty_rows else 1
        for c in range(min(c1, c2), max(c1, c2)):
            total += scale if c in empty_cols else 1

print(total)*/
}

private fun solveDay102() {
/*from collections import deque

grid = open(0).read().strip().splitlines()

for r, row in enumerate(grid):
    for c, ch in enumerate(row):
        if ch == "S":
            sr = r
            sc = c
            break
    else:
        continue
    break

loop = {(sr, sc)}
q = deque([(sr, sc)])

maybe_s = {"|", "-", "J", "L", "7", "F"}

while q:
    r, c = q.popleft()
    ch = grid[r][c]

    if r > 0 and ch in "S|JL" and grid[r - 1][c] in "|7F" and (r - 1, c) not in loop:
        loop.add((r - 1, c))
        q.append((r - 1, c))
        if ch == "S":
            maybe_s &= {"|", "J", "L"}

    if r < len(grid) - 1 and ch in "S|7F" and grid[r + 1][c] in "|JL" and (r + 1, c) not in loop:
        loop.add((r + 1, c))
        q.append((r + 1, c))
        if ch == "S":
            maybe_s &= {"|", "7", "F"}

    if c > 0 and ch in "S-J7" and grid[r][c - 1] in "-LF" and (r, c - 1) not in loop:
        loop.add((r, c - 1))
        q.append((r, c - 1))
        if ch == "S":
            maybe_s &= {"-", "J", "7"}

    if c < len(grid[r]) - 1 and ch in "S-LF" and grid[r][c + 1] in "-J7" and (r, c + 1) not in loop:
        loop.add((r, c + 1))
        q.append((r, c + 1))
        if ch == "S":
            maybe_s &= {"-", "L", "F"}

assert len(maybe_s) == 1
(S,) = maybe_s

grid = [row.replace("S", S) for row in grid]
grid = ["".join(ch if (r, c) in loop else "." for c, ch in enumerate(row)) for r, row in enumerate(grid)]

outside = set()

for r, row in enumerate(grid):
    within = False
    up = None
    for c, ch in enumerate(row):
        if ch == "|":
            assert up is None
            within = not within
        elif ch == "-":
            assert up is not None
        elif ch in "LF":
            assert up is None
            up = ch == "L"
        elif ch in "7J":
            assert up is not None
            if ch != ("J" if up else "7"):
                within = not within
            up = None
        elif ch == ".":
            pass
        else:
            raise RuntimeError(f"unexpected character (horizontal): {ch}")
        if not within:
            outside.add((r, c))

print(len(grid) * len(grid[0]) - len(outside | loop))*/
}

private fun solveDay101() {
    val input = getFile("day10_1.txt")

    val matrixOfTiles = mutableListOf(mutableListOf<Tile>())
    var startX = 0
    var startY = 0

    var yLength = 0
    input.map { line ->
        var xLength = 0

        val matrixLine = mutableListOf<Tile>()

        line.map { char ->
            var isStartTile = false
            if (char == 'S') {
                isStartTile = true
                startX = xLength
                startY = yLength
            }

            matrixLine.add(Tile(char, xLength, yLength, 0, isStartTile))

            xLength++
        }
        matrixOfTiles.add(matrixLine)
        yLength++
    }

    println(matrixOfTiles)
    println(startX)
    println(startY)

/*
* from collections import deque

grid = open(0).read().strip().splitlines()

for r, row in enumerate(grid):
    for c, ch in enumerate(row):
        if ch == "S":
            sr = r
            sc = c
            break
    else:
        continue
    break

loop = {(sr, sc)}
q = deque([(sr, sc)])

while q:
    r, c = q.popleft()
    ch = grid[r][c]

    if r > 0 and ch in "S|JL" and grid[r - 1][c] in "|7F" and (r - 1, c) not in loop:
        loop.add((r - 1, c))
        q.append((r - 1, c))

    if r < len(grid) - 1 and ch in "S|7F" and grid[r + 1][c] in "|JL" and (r + 1, c) not in loop:
        loop.add((r + 1, c))
        q.append((r + 1, c))

    if c > 0 and ch in "S-J7" and grid[r][c - 1] in "-LF" and (r, c - 1) not in loop:
        loop.add((r, c - 1))
        q.append((r, c - 1))

    if c < len(grid[r]) - 1 and ch in "S-LF" and grid[r][c + 1] in "-J7" and (r, c + 1) not in loop:
        loop.add((r, c + 1))
        q.append((r, c + 1))

print(len(loop) // 2)*/
}

private fun solvePuzzle(char: Char, xIndex: Int, yIndex: Int, order: Int) {
    var nextX1: Int
    var nextY1: Int
    var nextX2: Int
    var nextY2: Int

    if (char == '|') {
        nextX1 = xIndex - 1
        nextY1 = yIndex
        nextX2 = xIndex + 1
        nextY2 = yIndex
    } else if (char == '-') {
        nextX1 = xIndex
        nextY1 = yIndex - 1
        nextX2 = xIndex
        nextY2 = yIndex + 1
    } else if (char == 'L') {

    } else if (char == 'J') {

    } else if (char == '7') {

    } else if (char == 'F') {

    } else if (char == '.') {

    }

//    | is a vertical pipe connecting north and south.
//    - is a horizontal pipe connecting east and west.
//    L is a 90-degree bend connecting north and east.
//    J is a 90-degree bend connecting north and west.
//    7 is a 90-degree bend connecting south and west.
//    F is a 90-degree bend connecting south and east.
//    . is ground; there is no pipe in this tile.
//    S
}

private fun solveDay91() {
    /*def extrapolate(array):
        if all(x == 0 for x in array):
            return 0

        deltas = [y - x for x, y in zip(array, array[1:])]
        diff = extrapolate(deltas)
        return array[-1] + diff

    total = 0

    for line in open(0):
        nums = list(map(int, line.split()))
        total += extrapolate(nums)

    print(total)*/
}

private fun solveDay82() {
    /*from math import gcd

steps, _, *rest = open(0).read().splitlines()

network = {}

for line in rest:
    pos, targets = line.split(" = ")
    network[pos] = targets[1:-1].split(", ")

positions = [key for key in network if key.endswith("A")]
cycles = []

for current in positions:
    cycle = []

    current_steps = steps
    step_count = 0
    first_z = None

    while True:
        while step_count == 0 or not current.endswith("Z"):
            step_count += 1
            current = network[current][0 if current_steps[0] == "L" else 1]
            current_steps = current_steps[1:] + current_steps[0]

        cycle.append(step_count)

        if first_z is None:
            first_z = current
            step_count = 0
        elif current == first_z:
            break

    cycles.append(cycle)

nums = [cycle[0] for cycle in cycles]

lcm = nums.pop()

for num in nums:
    lcm = lcm * num // gcd(lcm, num)

print(lcm)*/
}

private fun solveDay81() {
    /*steps, _, *rest = open(0).read().splitlines()

network = {}

for line in rest:
    pos, targets = line.split(" = ")
    network[pos] = targets[1:-1].split(", ")

step_count = 0
current = "AAA"

while current != "ZZZ":
    step_count += 1
    current = network[current][0 if steps[0] == "L" else 1]
    steps = steps[1:] + steps[0]

print(step_count)*/
}

private fun solveDay72() {
    /*letter_map = {"T": "A", "J": ".", "Q": "C", "K": "D", "A": "E"}


    def score(hand):
        counts = [hand.count(card) for card in hand]

        if 5 in counts:
            return 6
        if 4 in counts:
            return 5
        if 3 in counts:
            if 2 in counts:
                return 4
            return 3
        if counts.count(2) == 4:
            return 2
        if 2 in counts:
            return 1
        return 0


    def replacements(hand):
        if hand == "":
            return [""]

        return [
            x + y
            for x in ("23456789TQKA" if hand[0] == "J" else hand[0])
            for y in replacements(hand[1:])
        ]


    def classify(hand):
        return max(map(score, replacements(hand)))


    def strength(hand):
        return (classify(hand), [letter_map.get(card, card) for card in hand])


    plays = []

    for line in open(0):
        hand, bid = line.split()
        plays.append((hand, int(bid)))

    plays.sort(key=lambda play: strength(play[0]))

    total = 0

    for rank, (hand, bid) in enumerate(plays, 1):
        total += rank * bid

    print(total)*/
}

private fun solveDay71() {
    /*letter_map = {"T": "A", "J": "B", "Q": "C", "K": "D", "A": "E"}


    def classify(hand):
        counts = [hand.count(card) for card in hand]

        if 5 in counts:
            return 6
        if 4 in counts:
            return 5
        if 3 in counts:
            if 2 in counts:
                return 4
            return 3
        if counts.count(2) == 4:
            return 2
        if 2 in counts:
            return 1
        return 0


    def strength(hand):
        return (classify(hand), [letter_map.get(card, card) for card in hand])


    plays = []

    for line in open(0):
        hand, bid = line.split()
        plays.append((hand, int(bid)))

    plays.sort(key=lambda play: strength(play[0]))

    total = 0

    for rank, (hand, bid) in enumerate(plays, 1):
        total += rank * bid

    print(total)*/
}

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