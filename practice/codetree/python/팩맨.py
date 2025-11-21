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
                moved = False

                for _ in range(8):
                    dy, dx = mdyx[md]
                    ny, nx = y + dy, x + dx
                    if (0 <= ny < 4) and (0 <= nx < 4):
                        if dead[ny][nx] == 0 and (ny, nx) != (R, C):
                            next_monster[ny][nx] += [md]
                            moved = True
                            break
                    md = 0 if (md + 1) == 8 else (md + 1)

                if moved is False:
                    next_monster[y][x] += [monster[y][x][i]]
    monster = [line[:] for line in next_monster]


def find_packman_route(y, x, eat, route):
    global max_eat, packman_route

    if len(route) == 3:
        if eat > max_eat:
            max_eat = eat
            # print(max_eat, route)
            packman_route = route[:]
        return
    for d in range(4):
        dy, dx = pdyx[d]
        ny, nx = y + dy, x + dx
        if (0 <= ny < 4) and (0 <= nx < 4):
            temp = monster[ny][nx][:]
            monster[ny][nx] = []
            route.append(d)
            find_packman_route(ny, nx, eat + len(temp), route)
            monster[ny][nx] = temp[:]
            route.pop()


def move_packman(route):
    global R, C, monster, dead
    y, x = R, C

    for d in route:
        dy, dx = pdyx[d]
        ny, nx = y + dy, x + dx
        if monster[ny][nx]:
            monster[ny][nx] = []
            dead[ny][nx] = 3
        y, x = ny, nx
    
    R, C = y, x


def born():
    global monster

    for y in range(4):
        for x in range(4):
            if len(egg[y][x]):
                monster[y][x] += egg[y][x][:]


def solution():
    global monster, egg, max_eat, packman_route, dead
    dead = [[0 for _ in range(4)] for _ in range(4)]


    for _ in range(T):
        egg = [[[] for _ in range(4)] for _ in range(4)]

        make_egg()
        move_monster()
        max_eat = -1
        packman_route = []
        find_packman_route(R, C, 0, [])
        
        move_packman(packman_route)
        for y in range(4):
            for x in range(4):
                if dead[y][x] > 0:
                    dead[y][x] -= 1
        born()

    answer = 0
    for y in range(4):
        for x in range(4):
            answer += len(monster[y][x])

    print(answer)


M, T = map(int, input().split())
R, C = map(int, input().split())
R, C = R - 1, C - 1

monster = [[[] for _ in range(4)] for _ in range(4)]
for _ in range(M):
    r, c, d = map(int, input().split())
    monster[r - 1][c - 1] += [(d - 1)]

solution()
