dyx = [(-1, 0), (0, 1), (1, 0), (0, -1)]

def bfs(y, x):
    queue = [(y, x)]

    while queue:
        cy, cx = queue.pop(0)

        for d in range(4):
            dy, dx = dyx[d]
            ny, nx = cy + dy, cx + dx

            if (0 <= ny < M) and (0 <= nx < N):
                if field[ny][nx] == 0 and visited[ny][nx] == 0:
                    visited[ny][nx] = 1
                    if ny == (M - 1):
                        return True
                    queue.append((ny, nx))
    return False


def solution():
    global visited
    visited = [[0 for _ in range(N)] for _ in range(M)]

    for x in range(N):
        if field[0][x] == 0:
            visited[0][x] = 1
            if bfs(0, x) is True:
                print("YES")
                return
    print("NO")

M, N = map(int, input().split())
field = []

for _ in range(M):
    field.append([int(n) for n in input()])

solution()