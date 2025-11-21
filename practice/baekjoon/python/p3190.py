# 북동남서 (+1 오른쪽, -1 왼쪽)
move = [(-1, 0), (0, 1), (1, 0), (0, -1)]

def solution():
    # 뱀 정보
    snake = [(0, 0)]
    # 현재 방향
    direct = 1
    second = 0

    while True:
        # 마지막 위치 보기
        y, x = snake[-1]

        # 뱀 이동
        dy, dx = move[direct]
        ny, nx = y + dy, x + dx

        # end조건 >> 범위이탈
        if not ((0 <= ny < N) and (0 <= nx < N)):
            break
        # 몸 만날때
        if (ny, nx) in snake:
            break
        
        # 이동성공
        snake.append((ny, nx))
        # 만약 사과 없으면 꼬리 줄이기
        if field[ny][nx] == 0:
            snake.pop(0)
        else:
            field[ny][nx] = 0

        second += 1
        
        # 방향회전
        if second in rotate.keys():
            if rotate[second] == 'D':
                direct += 1
                direct = 0 if (direct > 3) else direct
            else:
                direct -= 1
                direct = 3 if (direct < 0) else direct
    print(second + 1)


N = int(input())
field = [[0 for _ in range(N)] for _ in range(N)]

K = int(input())
for _ in range(K):
    y, x = map(int, input().split())
    field[y - 1][x - 1] = 1

L = int(input())
rotate = {}
for _ in range(L):
    X, C = input().split()
    rotate[int(X)] = C

solution()