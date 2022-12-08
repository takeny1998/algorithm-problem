def bfs(y, x):
    global visited
    queue = [(y, x)]

    while queue:
        cy, cx = queue.pop(0)
        for d in range(4):
            dy, dx = dyx[d]
            ny, nx = cy + dy, cx + dx

            if (0 <= ny < N) and (0 <= nx < M):
                if (field[ny][nx] == 1) and (visited[ny][nx] == 0):
                    visited[ny][nx] = 1
                    queue.append((ny, nx))


import sys
sys.setrecursionlimit(100000)
dyx = [(-1, 0), (0, 1), (1, 0), (0, -1)]

def dfs(cy, cx):
    global visited
    for d in range(4):
        dy, dx = dyx[d]
        ny, nx = cy + dy, cx + dx

        if (0 <= ny < N) and (0 <= nx < M):
            if (visited[ny][nx] is False) and (field[ny][nx] == 1):
                visited[ny][nx] = True
                dfs(ny, nx)


T = int(input())
answers = []


for _ in range(T):
    M, N, K = map(int, input().split())

    field = [[0 for _ in range(M)] for _ in range(N)]
    visited = [[False for _ in range(M)] for _ in range(N)]
    warm = 0

    for _ in range(K):
        X, Y = map(int, input().split())
        field[Y][X] = 1

    for y in range(N):
        for x in range(M):
            if (visited[y][x] is False) and (field[y][x] == 1):
                warm += 1
                visited[y][x] = True
                dfs(y, x)

    answers.append(warm)


for answer in answers:
    print(answer)