
dyx = [(-1, 0), (0, 1), (1, 0), (0, -1)]

def bfs():
    global field
    visited = [[0 for _ in range(N)] for _ in range(N)]
    divided = [[-1 for _ in range(N)] for _ in range(N)]
    group_list = []
    index = 0
    
    for y in range(N):
        for x in range(N):
            if visited[y][x] == 0:
                divided[y][x] = index

                queue = [(y, x)]
                area = [(y, x)]

                num = field[y][x]

                while queue:
                    cy, cx = queue.pop(0)
                    for d in range(4):
                        dy, dx = dyx[d]
                        ny, nx = cy + dy, cx + dx

                        if (0 <= ny < N) and (0 <= nx < N):
                            if field[ny][nx] == num and visited[ny][nx] == 0:
                                visited[ny][nx] = 1
                                divided[ny][nx] = index
                                queue.append((ny, nx))
                                area.append((ny, nx))

                group_list.append({'index': index, 'stan': (y, x), 'num': num, 'length': len(list(set(area)))})
                index += 1

    for i in range(len(group_list)):
        y, x = group_list[i]['stan']
        num = group_list[i]['num']
        visited = [[0 for _ in range(N)] for _ in range(N)]
        visited[y][x] = 1
        queue = [(y, x)]
        border = [0 for _ in range(len(group_list))]
        
        while queue:
            y, x = queue.pop(0)
            for d in range(4):
                dy, dx = dyx[d]
                ny, nx = y + dy, x + dx

                if (0 <= ny < N) and (0 <= nx < N):
                    if field[ny][nx] == num and visited[ny][nx] == 0:
                        visited[ny][nx] = 1
                        queue.append((ny, nx))
                    else:
                        idx = divided[ny][nx]
                        border[idx] += 1
        border[i] = 0
        group_list[i]['border'] = border
                                
    return group_list


def combinations(arr, r):
    for i in range(len(arr)):
        if r == 1:
            yield arr[i]
        else:
            for next in combinations(arr[i + 1:], r - 1):
                yield [arr[i]] + [next]


def rotate():
    global field
    
    next_field = [[-1 for _ in range(N)] for _ in range(N)] 
    size = (N // 2)

    ny, nx = 0, 0

    # 십자가 반시계 회전하기
    for x in range(N - 1, -1, -1):
        for y in range(N):
            next_field[ny][nx] = field[y][x]
            nx += 1
            if nx == N:
                nx = 0
                ny += 1

    # 정사각형 회전하기
    for y in range(0, N, (size + 1)):
        for x in range(0, N, (size + 1)):
            yw, xw = 0, 0
            part = []
            for ny in range(y, y + size):
                part += [field[ny][x:x+size]]
            # 회전
            for px in range(size):
                for py in range(size - 1, -1, -1):
                    next_field[y + yw][x + xw] = part[py][px]
                    xw += 1
                    if xw == size:
                        xw = 0
                        yw += 1

    field = [line[:] for line in next_field]


def get_score(a, b):
    return (a['length'] + b['length']) * a['num'] * b['num'] * a['border'][b['index']]


def solution():
    answer = 0

    for _ in range(4):
        score_sum = 0
        group_list = bfs()
        for a, b in combinations(group_list, 2):
            score = get_score(a, b)
            if score > 0:
                score_sum += score

        answer += score_sum
        rotate()

    print(answer)


N = int(input())
field = []
for _ in range(N):
    field.append(list(map(int, input().split())))

solution()
