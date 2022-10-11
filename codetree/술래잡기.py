# 0:위, 1:오른쪽, 2:아래, 3:왼쪽

dyx = [(-1, 0), (0, 1), (1, 0), (0, -1)]

# 술래가 움직이는 달팽이 궤도 만들기
def make_route():
    route = []
    n = N
    y, x = -1, 0
    move = 1

    while n > 0:
        for _ in range(n):
            y += move
            route += [(y, x)]

        n -= 1

        for _ in range(n):
            x += move
            route += [(y, x)]

        move *= -1

    route.reverse()
    return route
    

# 도망자를 이동하는 함수
def move_runner(runner):
    global iy, ix
    ry, rx, rd = runner
    dy, dx = dyx[rd]
    ny, nx = ry + dy, rx + dx

    # 범위검사
    if (0 <= ny < N) and (0 <= nx < N):
        # 술래 없는경우 이동
        if (ny, nx) != (iy, ix):
            return (ny, nx, rd)
        return (ry, rx, rd)
    else:
        # 방향 반대로
        nd = rd - 2 if rd + 2 > 3 else rd + 2
        dy, dx = dyx[nd]
        ny, nx = ry + dy, rx + dx
        # 술래 없는경우 이동
        if (ny, nx) != (iy, ix):
            return (ny, nx, nd)
        return (ry, rx, nd)



# 술래와 도망자의 거리를 구하는 함수
def find_dist(y, x):
    global iy, ix
    return abs(y - iy) + abs(x - ix)


# 술래를 이동하는 함수
def move_it():
    global ptr, is_reversed
    # 정방향일 경우
    if not is_reversed:
        ptr += 1
        # 꼭지점 방향 틀어주기
        if ptr == len(route):
            is_reversed = True
            ptr -= 2
            return route[ptr][0], route[ptr][1], 2
        
        y, x = route[ptr][0], route[ptr][1]

        if ptr == (len(route) - 1):
            # 맨 마지막인 경우
            return y, x, 2

        ny, nx = route[ptr + 1]
        dy, dx = (ny - y), (nx - x)
        d = dyx.index((dy, dx))
        return y, x, d
        
    
    # 역방향일 경우
    ptr -= 1
    # 꼭지점 방향 틀어주기
    if ptr == -1:
        is_reversed = False
        ptr = 1
        return route[ptr][0], route[ptr][1], 1
        
    # 방향 반대로 뒤집기
    # 맨 마지막인 경우 아래
    y, x = route[ptr][0], route[ptr][1]
    if ptr == 0:
        return y, x, 0
    
    ny, nx = route[ptr - 1]
    dy, dx = (ny - y), (nx - x)
    d = dyx.index((dy, dx))
    return y, x, d
    
    
def solution():
    global ptr, is_reversed, iy, ix, runners
    is_reversed = False
    ptr = 0
    iy, ix = route[ptr]
    id = 0
    score = 0

    for t in range(1, K + 1):
        # 도망자 움직이기
        for i in range(len(runners)):
            # 술래와의 거리가 3 이하일때
            if find_dist(runners[i][0], runners[i][1]) <= 3:
                runners[i] = move_runner(runners[i])

        # 술래 움직이기
        iy, ix, id = move_it()


        # 감시 영역 산출하기
        watch = []
        for w in range(3):
            dy, dx = dyx[id]
            ny, nx = iy + (dy * w), ix + (dx * w)
            if (0 <= ny < N) and (0 <= nx < N):
                if tree_field[ny][nx] == 0:
                    watch.append((ny, nx))

        # 도망자 검사
        next_runners = []
        catch = 0
        while runners:
            ry, rx, rd = runners.pop(0)
            if (ry, rx) in watch:
                catch += 1
            else: next_runners.append((ry, rx, rd))
        
        runners = next_runners[:]

        field = [[[] for _ in range(N)] for _ in range(N)]
        for ry, rx, rd in runners:
            field[ry][rx] += [rd]
        
        field[iy][ix] += ['C', id]

        score += (t * catch)
        

    print(score)


# 크기, 도망자 수, 나무 수, 턴 수
N, M, H, K = map(int, input().split())

# 도망자 입력받기
runners = []
for _ in range(M):
    y, x, d = map(int, input().split())
    # 좌우일때
    if d == 1:
        nd = 1
    else:
        nd = 2
    runners.append((y - 1, x - 1, nd))

# 나무 입력받기
tree_field = [[0 for _ in range(N)] for _ in range(N)]
for _ in range(H):
    y, x = map(int, input().split())
    tree_field[y - 1][x - 1] = 1

route = make_route()
solution()
