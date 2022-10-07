dyx = [(-1, 0), (0, 1), (1, 0), (0, -1)]
from itertools import product

def solution():
    global field, answer
    answer = 999999999999
    
    # bfs로 감시 영역을 찾는 함수
    def cal(y, x, type, d):
        global field

        queue = [(y, x, d)]
        changed = []

        if type == 2: # 2번 경우 반대쪽도
            # 반대편으로 꺾기
            nd = (d - 2) if (d + 2) > 3 else (d + 2)
            dy, dx = dyx[nd]
            queue.append((y, x, nd))
        elif type == 3: # 3번은 90도 왼쪽
            # 90도 왼쪽 회전
            nd = 3 if (d - 1) < 0 else (d - 1)
            queue.append((y, x, nd))
        elif type == 4:
            # 반대편으로 꺾기
            nd = (d - 2) if (d + 2) > 3 else (d + 2)
            dy, dx = dyx[nd]
            queue.append((y, x, nd))
            # 90도 왼쪽 회전
            nd = 3 if (d - 1) < 0 else (d - 1)
            queue.append((y, x, nd))
        elif type == 5:
            # 반대편으로 꺾기
            nd = (d - 2) if (d + 2) > 3 else (d + 2)
            dy, dx = dyx[nd]
            queue.append((y, x, nd))
            # 90도 왼쪽 회전
            nd = 3 if (d - 1) < 0 else (d - 1)
            queue.append((y, x, nd))
            # 90도 오른쪽 회전
            nd = 0 if (d + 1) > 3 else (d + 1)
            queue.append((y, x, nd))

        
        while queue:
            cy, cx, d = queue.pop()
            dy, dx = dyx[d]
            ny, nx = cy + dy, cx + dx

            status = move(ny, nx)
            if status:
                if status == 2:
                    changed.append((ny, nx))
                queue.append((ny, nx, d))
        
        return changed

            
    def move(ny, nx):
        global field
        if (0 <= ny < N) and (0 <= nx < M):
            if field[ny][nx] != 6:
                if field[ny][nx] == 0:
                    field[ny][nx] = '#'
                    return 2
                return 1
        return 0
    
    
    def find_space():
        space = 0
        for y in range(N):
            for x in range(M):
                if field[y][x] == 0:
                    space += 1
        return space


    cctv_list = []

    for y in range(N):
        for x in range(M):
            if 1 <= field[y][x] < 6:
                cctv_list.append((y, x, field[y][x]))


    def dfs(idx):
        global field, answer
        if idx == len(cctv_list):
            # print(dlist)
            answer = min(answer, find_space())
            return
        
        for d in range(4):
            changed = cal(cctv_list[idx][0], cctv_list[idx][1], cctv_list[idx][2], d)
            dfs(idx + 1)
            for y, x in changed:
                field[y][x] = 0
            

    dfs(0)
    print(answer)
    return


field = []
N, M = map(int, input().split())
for _ in range(N):
    field.append(list(map(int, input().split())))
solution()