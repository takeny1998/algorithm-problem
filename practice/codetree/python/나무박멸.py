tdyx = [(-1, 0), (0, 1), (1, 0), (0, -1)]
kdyx = [(-1, 1), (1, 1), (1, -1), (-1, -1)]

def grow(field):
    for y in range(N):
        for x in range(N):
            if field[y][x] > 0:
                near_tree = 0

                for d in range(4):
                    dy, dx = tdyx[d]
                    ny, nx = y + dy, x + dx

                    if (0 <= ny < N) and (0 <= nx < N):
                        if field[ny][nx] > 0:
                            near_tree += 1
                
                field[y][x] += near_tree

    return field


def spread(field):
    next_field = [line[:] for line in field]

    for y in range(N):
        for x in range(N):
            if field[y][x] > 0:
                spreadable = []

                for d in range(4):
                    dy, dx = tdyx[d]
                    ny, nx = y + dy, x + dx

                    if (0 <= ny < N) and (0 <= nx < N):
                        if field[ny][nx] == 0 and killer[ny][nx] == 0:
                            spreadable.append((ny, nx))
                
                for sy, sx in spreadable:
                    next_field[sy][sx] += (field[y][x] // len(spreadable))
    
    return next_field


def kill():
    global field, killer
    ky, kx = 0, 0
    kill_max = 0
    kill_coord = []

    for y in range(N):
        for x in range(N):
            if field[y][x] > 0:
                killed = field[y][x]
                coord = [(y, x)]

                for d in range(4):
                    cy, cx = y, x
                    dy, dx = kdyx[d]
                    for _ in range(K):
                        ny, nx = cy + dy, cx + dx
                        
                        if field[cy][cx] <= 0:
                            break

                        if (0 <= ny < N) and (0 <= nx < N):
                            if field[ny][nx] == -1:
                                killed += 1
                            killed += field[ny][nx]
                            cy, cx = ny, nx
                            coord.append((ny, nx))
                                
                if killed > kill_max:
                    ky, kx = y, x
                    kill_max = killed
                    kill_coord = coord
                elif killed == kill_max:
                    if y < ky:
                        ky, kx = y, x
                        kill_max = killed
                        kill_coord = coord
                    elif y == ky:
                        if x < kx:
                            ky, kx = y, x
                            kill_max = killed
                            kill_coord = coord

    for ky, kx in kill_coord:
        killer[ky][kx] = C + 1
        if field[ky][kx] > 0:
            field[ky][kx] = 0
            

    return kill_max


def solution():
    global field

    answer = 0

    for _ in range(M):
        field = grow(field)
        field = spread(field)
        answer += kill()
        
        # 제초제 없애기
        for y in range(N):
            for x in range(N):
                if killer[y][x] > 0:
                    killer[y][x] -= 1


    print(answer)


N, M, K, C = map(int, input().split())
field = []
killer = [[0 for _ in range(N)] for _ in range(N)]

for _ in range(N):
    field.append(list(map(int, input().split())))
solution()

