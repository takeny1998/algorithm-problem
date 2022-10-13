dyx = [(-1, 0), (0, 1), (1, 0), (0, -1)]

def add():
    min_val = min(dough[0])
    for i in range(len(dough[0])):
        if dough[0][i] == min_val:
            dough[0][i] += 1


def fold():
    global dough
    ptr = 1

    while True:
        div = []

        for x in range(ptr):
            line = []
            for y in range(len(dough) - 1, -1, -1):
                line.append(dough[y][x])
            div.append(line)
        rest = dough[-1][ptr:]
        
        if len(div[0]) > len(rest):
            break

        dough = div + [rest]
        ptr = len(div[0])

    length = max([len(line) for line in dough])

    for i in range(len(dough)):
        for _ in range(length - len(dough[i])):
            dough[i].append(0)


def push():
    global dough

    next_dough = [line[:] for line in dough]

    rows = len(dough)
    for y in range(rows):
        cols = len(dough[y])
        for x in range(cols):
            if dough[y][x] > 0:
                for d in range(4):
                    dy, dx = dyx[d]
                    ny, nx = y + dy, x + dx
                    if (0 <= ny < rows) and (0 <= nx < cols):
                        if dough[ny][nx] > 0:
                            weight = abs(dough[ny][nx] - dough[y][x]) // 5
                            if dough[y][x] > dough[ny][nx]:
                                next_dough[y][x] -= weight
                            else:
                                next_dough[y][x] += weight

    push_dough = []

    for x in range(len(dough[0])):
        for y in range(len(dough) - 1, -1, -1):
            if dough[y][x] > 0:
                push_dough += [next_dough[y][x]]
    dough = [push_dough[:]]


def fold_twice():
    global dough
    length = len(dough[0])
    for _ in range(2):
        half = (length // 2)
        div = []
        rest = []
        for y in range(len(dough) - 1, -1, -1):
            left = dough[y][:half]
            left.reverse()
            div.append(left)
            rest.append(dough[y][half:])

        rest.reverse()
        dough = (div + rest)
        length //= 2


N, K = map(int, input().split())
arr = list(map(int, input().split()))
dough = [arr]

# print(dough)
turn = 0
while True:
    add()
    fold()
    push()
    fold_twice()
    push()

    turn += 1

    max_val, min_val = max(dough[0]), min(dough[0])
    if (max_val - min_val) <= K:
        print(turn)
        break