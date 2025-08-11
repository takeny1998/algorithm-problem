m = [(-1, 0), (1, 0), (0, -1), (0, 1)]

# 0:위, 1:아래, 2:왼쪽, 3:오른쪽
def move(field, direct):
    visited = [[0 for _ in range(N)] for _ in range(N)]
    next_field = [line[:] for line in field]

    # 아래, 오른쪽인 경우 반대로 loop를 돌아야됨
    if direct in [1, 3]:
        move_list = [n for n in range(N - 1, -1, -1)]
    else:
        move_list = [n for n in range(N)]

    for y in move_list:
        for x in move_list:
            if field[y][x] > 0:
                cy, cx = y, x
                # 블록 있는데까지 이동
                while True:
                    dy, dx = m[direct]
                    ny, nx = cy + dy, cx + dx
                    # 범위 벗어나는 경우
                    if not ((0 <= ny < N) and (0 <= nx < N)):
                        break
                    # 블럭 있는 경우
                    if next_field[ny][nx] > 0:
                        break

                    cy, cx = ny, nx

                # 블록 이동시키기
                next_field[y][x] = 0
                next_field[cy][cx] = field[y][x]
                
                # 한번 더 이동해 검사
                ny, nx = cy + dy, cx + dx
                if (0 <= ny < N) and (0 <= nx < N):
                    
                    if (next_field[ny][nx] == next_field[cy][cx]) and (visited[ny][nx] == 0):
                        visited[ny][nx] = 1
                        next_field[ny][nx] *= 2
                        next_field[cy][cx] = 0

                # for line in next_field:
                #     print(line)
                # print('---')

    return next_field, max(map(max, next_field))


def dfs(field, biggest, depth):
    global result

    if depth == 5:
        result = max(result, biggest)
        return
    for d in range(4):
        # 이동
        temp = [line[:] for line in field]
        temp2 = biggest
        field, biggest = move(field, d)
        dfs(field, biggest, depth + 1)
        field = temp
        biggest = temp2


result = 0
N = int(input())
field = []
for _ in range(N):
    field.append(list(map(int, input().split())))

dfs(field, 0, 0)
print(result)