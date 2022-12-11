arr = [
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 2, 2, 2, 0, 0, 0, 0, 0, 0],
    [0, 2, 1, 2, 0, 0, 0, 0, 0, 0],
    [0, 2, 1, 2, 0, 0, 0, 0, 0, 0],
    [0, 2, 1, 2, 0, 0, 0, 0, 0, 0],
    [0, 2, 1, 2, 0, 0, 0, 0, 0, 0],
    [0, 2, 1, 2, 0, 0, 0, 0, 0, 0],
    [0, 2, 1, 2, 0, 1, 1, 1, 1, 0],
    [0, 1, 1, 2, 0, 1, 1, 1, 1, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
]

dyx = [(-1, 0), (0, 1), (1, 0), (0, -1)]

def bfs():
    queue = [(0, 4, 0)]
    visited = [[0 for _ in range(10)] for _ in range(10)]
    result = []

    while queue:
        cy, cx, depth = queue.pop(0)
        for d in range(4):
            dy, dx = dyx[d]
            ny, nx = cy + dy, cx + dx

            if (0 <= ny < 10) and (0 <= nx < 10):
                if visited[ny][nx] == 0:
                    if arr[ny][nx] != 2:
                        if arr[ny][nx] == 1:
                            print(ny, nx)
                            result.append((ny, nx, depth + 1))
                        visited[ny][nx] = 1
                        queue.append((ny,nx, depth + 1))
    print(result)
print()
bfs()