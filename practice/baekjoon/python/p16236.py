from unittest import result


dyx = [(-1, 0), (0, 1), (1, 0), (0, -1)]

def solution():
    global sy, sx
    def bfs(sy, sx):
        queue = [(sy, sx, 0)]
        visited = [[0 for _ in range(N)] for _ in range(N)]
        visited[sy][sx] = 1
        min_dist = 99999999999
        ry, rx = 999999, 999999
        while queue:
            y, x, dist = queue.pop(0)
            if dist <= min_dist:
                # end 조건
                if 0 < field[y][x] < size:
                    if dist < min_dist:
                        min_dist = dist
                        ry, rx = y, x
                    elif dist == min_dist:
                        if y < ry:
                            ry, rx = y, x
                        elif y == ry:
                            if x < rx:
                                ry, rx = y, x
                else:
                    # 이동하기
                    for d in range(4):
                        dy, dx = dyx[d]
                        ny, nx = y + dy, x + dx
                        # 범위검사
                        if (0 <= ny < N) and (0 <= nx < N):
                            if visited[ny][nx] == 0:
                                if field[ny][nx] <= size:
                                    visited[ny][nx] = 1
                                    queue.append((ny, nx, dist + 1))
        return min_dist, ry, rx
        

    # 아기 상어의 크기 >> 2
    size = 2
    eat = 0
    sec = 0
    
    while True:
        # 다음 목적지 정하기
        min_dist, ny, nx = bfs(sy, sx)
        if min_dist == 99999999999:
            break
        

        field[sy][sx] = 0
        sy, sx = ny, nx
        field[ny][nx] = 9999
        eat += 1
        
        if eat >= size:
            eat = 0
            size += 1
        sec += min_dist

    print(sec)
    return


N = int(input())

field = []
for _ in range(N):
    field.append(list(map(int, input().split())))

sy, sx = 0, 0
for y in range(N):
    for x in range(N):
        if field[y][x] == 9:
            field[y][x] = 9999
            sy, sx = y, x

solution()