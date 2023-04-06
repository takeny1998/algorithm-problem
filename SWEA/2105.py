def dfs(y, x, d, visited):
    global iy, ix, answer
    # end조건
    if (y, x) == (iy, ix) and len(visited) > 1 and d == 3:
        answer = max(answer, len(visited))
        return
    
    if (0 <= y < size) and (0 <= x < size) and (field[y][x] not in visited):
        visited.append(field[y][x])

        # 직진할 때
        ny, nx = y + move[d][0], x + move[d][1]
        dfs(ny, nx, d, visited[:])

        # 회전할 때
        if d < 3:
            ny, nx = y + move[d + 1][0], x + move[d + 1][1]
            dfs(ny, nx, d + 1, visited[:])



T = int(input())

# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    move = [(1, 1), (1, -1), (-1, -1), (-1, 1)]
    answer = -1

    size = int(input())
    field = []
    
    for _ in range(size):
        field.append(list(map(int, input().split())))

    for iy in range(size):
        for ix in range(size):
            dfs(iy, ix, 0, list())

    print(f'#{test_case} {answer}')