# dfs 풀이 >> 시간초과
# move = [(-1, 0), (1, 0), (0, -1), (0, 1)]
# def solution(field):

    # global min_dist, visited


    # def dfs(y, x, dist):
    #     global min_dist, visited

    #     if dist >= min_dist:
    #         return

    #     # 동서남북으로 이동
    #     for dy, dx in move:
    #         ny, nx = y + dy, x + dx
    #         if (0 <= ny < N) and (0 <= nx < N):
    #             if visited[ny][nx] == 0:
    #                 visited[ny][nx] = 1
    #                 # 치킨집 찾으면
    #                 if field[ny][nx] == 2:
    #                     if (dist + 1) < min_dist:
    #                         min_dist = (dist + 1)
    #                 else:
    #                     dfs(ny, nx, dist + 1)
    #                 visited[ny][nx] = 0


    # result = 999999999999999999
    # chicken = []

    # # 치킨집 위치 파악하기 & 치킨집 지우기
    # for y in range(N):
    #     for x in range(N):
    #         if field[y][x] == 2:
    #             field[y][x] = 0
    #             chicken.append((y, x))


    # # 최대 M개에 대해 조합 작성하기
    # for comb in combinations(chicken, M):
    #     chicken_dist = 0
    #     # 치킨집 입력하기
    #     for y, x in comb:
    #         field[y][x] = 2

    #     for y in range(N):
    #         for x in range(N):
                
    #             if chicken_dist > result:
    #                 break

    #             if field[y][x] == 1:
    #                 min_dist = 999999999
    #                 # 방문처리를 위한 배열 선언
    #                 visited = [[0 for _ in range(N)] for _ in range(N)]
    #                 visited[y][x] = 1
    #                 dfs(y, x, 0)
    #                 chicken_dist += min_dist

    #     # 치킨집 지우기
    #     for y, x in comb:
    #         field[y][x] = 0
        
    #     if chicken_dist < result:
    #         result = chicken_dist
    
    # print(result)
    # return result


from itertools import combinations
def solution(field):
    home = []
    chicken = []
    result = 9999999

    # 위치 파악하기
    for y in range(N):
        for x in range(N):
            if field[y][x] == 1:
                home.append((y, x))
            elif field[y][x] == 2:
                chicken.append((y, x))

    for comb in combinations(chicken, M):
        comb_dist = 0

        for y, x in home:
            min_dist = 9999999
            for ty, tx in comb:
                dist = abs(ty - y) + abs(tx - x)
                min_dist = min(dist, min_dist)
            comb_dist += min_dist

        result = min(comb_dist, result)
    
    print(result)
    return result


N, M = map(int, input().split())
field = []
for _ in range(N):
    field.append(list(map(int, input().split())))

solution(field)