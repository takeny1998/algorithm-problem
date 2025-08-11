
dyx = [(-1, 0), (0, 1), (1, 0), (0, -1)]


def bfs():
    visited = [[0 for _ in range(N)] for _ in range(N)]
    result = []

    for y in range(N):
        for x in range(N):
            if field[y][x] == 1:
                visited[y][x] = 1
                queue = [(y, x, field[y][x])]
                route = [(y, x)]
                tail = 0

                while queue:
                    cy, cx, stan = queue.pop(0)
                        
                    for d in range(4):
                        dy, dx = dyx[d]
                        ny, nx = cy + dy, cx + dx
                        if (0 <= ny < N) and (0 <= nx < N):
                            if visited[ny][nx] == 0:
                                # if (y, x) == (9, 11):
                                #     print(ny, nx, stan, field[ny][nx])
                                if field[ny][nx] == (stan + 1):
                                    visited[ny][nx] = 1
                                    tail = (len(route) - 1)
                                    route.append((ny, nx))
                                    queue.append((ny, nx, stan + 1))
                                elif field[ny][nx] == stan:
                                    visited[ny][nx] = 1
                                    route.append((ny, nx))
                                    queue.append((ny, nx, stan))
                
                link = True
                for ry, rx in route:
                    if field[ry][rx] == 4:
                        link = False
                        break
                
                if link:
                    tail = len(route) - 1


                result.append({'route': route, 'head': 0, 'tail': tail, 'reversed': False})

    return result
                

def move(group):
    head, tail, length = group['head'], group['tail'], len(group['route'])
    
    if group['reversed']:
        head = 0 if (head + 1 == length) else head + 1
        tail = 0 if (tail + 1 == length) else tail + 1
    else:
        head = length - 1 if (head - 1 == -1) else head - 1
        tail = length - 1 if (tail - 1 == -1) else tail - 1
    
    group['head'], group['tail'] = head, tail
    return group


def make_gift_route():
    route = []
    for y in range(N):
        route.append((y, 0, 1))

    for x in range(N):
        route.append((N - 1, x, 0))
    
    for y in range(N - 1, -1, -1):
        route.append((y, N - 1, 3))

    for x in range(N - 1, -1, -1):
        route.append((0, x, 2))
    
    return route


def reverse(group):
    temp = group['head']
    group['head'] = group['tail']
    group['tail'] = temp
    if group['reversed']:
        group['reversed'] = False
    else:
        group['reversed'] = True
    return group



def examine(group_list):
    mask_list = []
    score = 0

    for group in group_list:
        mask = []
        head, tail, route, reversed = group['head'], group['tail'], group['route'], group['reversed']
        length = len(route)
        
        ptr = head
        while True:
            mask.append(route[ptr])
            if reversed:
                ptr =  length - 1 if (ptr - 1 == -1) else ptr - 1
            else:
                ptr = 0 if (ptr + 1 == length) else ptr + 1
            if ptr == tail:
                mask.append(route[ptr])
                break
        mask_list.append(mask)


    gptr = turn

    if gptr >= len(gift_route):
        gptr %= len(gift_route)

    gy, gx, gd = gift_route[gptr]
    dy, dx = dyx[gd]
    end = False

    while True:
        # 검사
        for i in range(len(mask_list)):
            if (gy, gx) in mask_list[i]:
                end = True
                idx = mask_list[i].index((gy, gx))
                group_list[i] = reverse(group_list[i])
                # print(gy, gx, idx)
                score += (idx + 1) ** 2

        if end:
            break
        ny, nx = gy + dy, gx + dx
        if (0 <= ny < N) and (0 <= nx < N):
            gy, gx = ny, nx
        else:
            break

    return score, group_list


N, M, K = map(int, input().split())
field = []
for _ in range(N):
    field.append(list(map(int, input().split())))

group_list = bfs()
# print(group_list)
gift_route = make_gift_route()

answer = 0
gift_ptr = 0

for turn in range(K):
    for i in range(len(group_list)):
        group_list[i] = move(group_list[i])
    score, group_list = examine(group_list)
    answer += score
print(answer)