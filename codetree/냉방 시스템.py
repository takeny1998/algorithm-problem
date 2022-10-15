# sdyx = {
#     0: [[(0, -1), (-1, 0)], [(-1, 0)], [(0, 1), (-1, 0)]],
#     1: [[(-1, 0), (0, 1)], [(0, 1)], [(1, 0), (0, 1)]],
#     2: [[(0, -1), (1, 0)], [(1, 0)], [(0, 1), (1, 0)]],
#     3: [[(-1, 0), (0, -1)], [(0, -1)], [(1, 0), (0, -1)]]
# }


dyx = [(-1, 0), (0, 1), (1, 0), (0, -1)]
sdyx = {
    0: [[3, 0], [0], [1, 0]],
    1: [[0, 1], [1], [2, 1]],
    2: [[3, 2], [2], [1, 2]],
    3: [[0, 3], [3], [2, 3]]
}


def spread(y, x, d):
    global cold
    dy, dx = dyx[d]
    sy, sx = y + dy, x + dx
    if (0 <= sy < N) and (0 <= sx < N):
        queue = [(sy, sx, 5)]
        coords = []

        while queue:
            cy, cx, depth = queue.pop(0)
            coords.append((cy, cx, depth))
            if depth > 1:
                for dlist in sdyx[d]:
                    ty, tx = cy, cx
                    blocked = False
                    for cd in dlist:
                        if cd in wall[ty][tx]:
                            blocked = True
                            break
                        dy, dx = dyx[cd]
                        ny, nx = ty + dy, tx + dx
                        if not ((0 <= ny < N) and (0 <= nx < N)):
                            blocked = True
                            break
                        ty, tx = ny, nx
                    
                    if blocked == False:
                        queue.append((ny, nx, depth - 1))
        
        coords = list(set(coords))

        for cy, cx, val in coords:
            cold[cy][cx] += val


def mix():
    global cold

    next_cold = [line[:] for line in cold]
    for y in range(N):
        for x in range(N):
            for d in range(4):
                if d not in wall[y][x]:
                    dy, dx = dyx[d]
                    ny, nx = y + dy, x + dx
                    if (0 <= ny < N) and (0 <= nx < N):
                        diff = cold[ny][nx] - cold[y][x]
                        if diff > 0:
                            next_cold[y][x] += (diff // 4)
                        else:
                            next_cold[y][x] -= (abs(diff) // 4)

    cold = [line[:] for line in next_cold]


def hot_border():
    global cold

    y, x = 0, -1
    move = 1
    size = N
    for _ in range(2):
        for _ in range(size):
            x += move
            if cold[y][x] > 0:
                cold[y][x] -= 1
        
        size -= 1

        for _ in range(size):
            y += move
            if cold[y][x] > 0:
                cold[y][x] -= 1
        
        move *= -1
        

def get_temp():
    temps = []
    for oy, ox in office:
        temps.append(cold[oy][ox])
    
    return min(temps) >= K


def solution():
    global cold
    minute = 1
    cold = [[0 for _ in range(N)] for _ in range(N)]
    while True:
        if minute == 101:
            print(-1)
            break
        for ay, ax, ad in aircon:
            spread(ay, ax, ad)
        mix()
        hot_border()
        if get_temp():
            print(minute)
            break
        minute += 1
    


N, M, K = map(int, input().split())

field = []
wall = [[[] for _ in range(N)] for _ in range(N)]

for _ in range(N):
    field.append(list(map(int, input().split())))

for _ in range(M):
    Y, X, S = map(int, input().split())
    # 위인경우
    if S == 0:
        wall[Y - 1][X - 1] += [0]
        if (0 <= (Y - 2) < N) and (0 <= (X - 1) < N):
            wall[Y - 2][X - 1] += [2]
    else: # 왼쪽인 경우
        wall[Y - 1][X - 1] += [3]
        if (0 <= (Y - 1) < N) and (0 <= (X - 2) < N):
            wall[Y - 1][X - 2] += [1]


aircon = []
office = []

for y in range(N):
    for x in range(N):
        if field[y][x] == 1:
            office.append((y, x))
        elif field[y][x] == 2:
            aircon.append((y, x, 3))
        elif field[y][x] == 3:
            aircon.append((y, x, 0))
        elif field[y][x] == 4:
            aircon.append((y, x, 1))
        elif field[y][x] == 5:
            aircon.append((y, x, 2))

solution()