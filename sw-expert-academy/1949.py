dyx = [(-1, 0), (0, 1), (1, 0), (0, -1)]
def solution():
    global max_len
    def dfs(y, x, depth, chance):
        global max_len
        max_len = max(max_len, depth)
        for d in range(4):
            dy, dx = dyx[d]
            ny, nx = y + dy, x + dx
            if (0 <= ny < N) and (0 <= nx < N):
                if visited[ny][nx] == 0:
                    # 높이가 낮을때만
                    if attr[ny][nx] < attr[y][x]:
                        visited[ny][nx] = 1
                        dfs(ny, nx, depth + 1, chance)
                        visited[ny][nx] = 0
                    # 깎을 찬스가 남아있는 경우
                    elif chance and ((attr[ny][nx] - K) < attr[y][x]):
                        visited[ny][nx] = 1
                        chance = False

                        # 공사 할 (현재높이 - 다음높이 + 1) ~ K까지의 깊이
                        for height in range(attr[ny][nx] - attr[y][x] + 1, K + 1):
                            attr[ny][nx] -= height
                            dfs(ny, nx, depth + 1, chance)
                            attr[ny][nx] += height
                        chance = True
                        visited[ny][nx] = 0

    answer = 0
    for y in range(N):
        for x in range(N):
            if attr[y][x] == max_val:
                max_len = 0
                visited = [[0 for _ in range(N)] for _ in range(N)]
                visited[y][x] = 1
                dfs(y, x, 1, True)
                answer = max(answer, max_len)
    
    return answer


T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    # ///////////////////////////////////////////////////////////////////////////////////
    N, K = map(int, input().split())
    attr = []
    max_val = 0
    for _ in range(N):
        line = list(map(int, input().split()))
        max_val = max(max_val, max(line))
        attr.append(line)
    print(f'#{test_case} {solution()}')
    # ///////////////////////////////////////////////////////////////////////////////////