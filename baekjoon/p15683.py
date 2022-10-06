dyx = [(-1, 0), (0, 1), (1, 0), (0, -1)]

def solution():
    def cal(type, y, x, d):
        global field

        queue = [(y, x)]
        dy, dx = dyx[d]

        while queue:
            cy, cx = queue.pop(0)
            ny, nx = cy + dy, cx + dx
            
            # 보는 방향으로 일직선으로
            if move(ny, nx):
                queue.append((ny, nx))
            
            if type == 2: # 2번 경우 반대쪽도
                nd = (d - 2) if (d + 2) > 3 else (d + 2)
                dy, dx = dyx[nd]
                ny, nx = cy + dy, cx + dx
                if move(ny, nx):
                    queue.append((ny, nx))
                    
            

        for line in field:
            for elm in line:
                print('%2s' % elm, end=' ')
            print()


    def move(ny, nx):
        global field
        if (0 <= ny < N) and (0 <= nx < M):
            if field[ny][nx] != 6:
                if field[ny][nx] == 0:
                    field[ny][nx] = '#'
                return True
        return False
        
    cal(2, 2, 2, 1)
    return



field = []
N, M = map(int, input().split())
for _ in range(N):
    field.append(list(map(int, input().split())))

solution()