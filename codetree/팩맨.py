mdyx = [(-1, 0), (-1, -1), (0, -1), (1, -1), (1, 0), (1, 1), (0, 1), (-1, 1)]
pdyx = [(-1, 0), (0, -1), (1, 0), (0, 1)]

def make_egg():
    global egg
    for y in range(4):
        for x in range(4):
            if len(monster[y][x]):
                egg[y][x] = monster[y][x]


def move_monster():
    global monster

    next_monster = [[[] for _ in range(4)] for _ in range(4)]
    for y in range(4):
        for x in range(4):
            for i in range(len(monster[y][x])):
                md = monster[y][x][i]

                for _ in range(8):
                    dy, dx = mdyx[md]
                    ny, nx = y + dy, x + dx
                    if (0 <= ny < 4) and (0 <= nx < 4):
                        if dead[ny][nx] == 0 and (ny, nx) != (R, C):
                            next_monster[ny][nx] += [md]
                            break
                    md = 0 if (md + 1) == 8 else (md + 1)
    
    monster = [line[:] for line in next_monster]


def move_packman(y, x, eat, route):
    if len(route) == 3:
        print(eat, route)
        return
    for d in range(4):
        dy, dx = pdyx[d]
        ny, nx = y + dy, x + dx
        if (0 <= ny < 4) and (0 <= nx < 4):
            route.append(d)
            move_packman(ny, nx, eat + len(monster[ny][nx]), route)
            route.pop()


def debug(arr):
    for line in arr:
        for elm in line:
            print('%5s' % elm, end=' ')
        print()


M, T = map(int, input().split())
R, C = map(int, input().split())
R, C = R - 1, C - 1
monster = [[[] for _ in range(4)] for _ in range(4)]
for _ in range(M):
    r, c, d = map(int, input().split())
    monster[r - 1][c - 1] += [(d - 1)]


egg = [[[] for _ in range(4)] for _ in range(4)]
dead = [[0 for _ in range(4)] for _ in range(4)]
make_egg()
# debug(monster)
# debug(egg)
move_monster()
move_packman(R, C, 0, [])