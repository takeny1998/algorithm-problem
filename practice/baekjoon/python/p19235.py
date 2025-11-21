blue_field = [[0 for _ in range(4)] for _ in range(6)]
green_field = [line[:] for line in blue_field]

def turn(red_blocks):
    global green_field, blue_field
    score_sum = 0

    # 초록 필드
    # 행, 열 최솟값 찾기
    row_min = 9999
    col_min = 9999
    for y, x, _ in red_blocks:
        row_min = min(row_min, y)
        col_min = min(col_min, x)

    # 초록 필드 블록 구하기
    green_blocks = []
    for ry, rx, type in red_blocks:
        green_blocks += [(ry - row_min, rx, type)]

    # 초록 필드 블록 움직이기
    green_field = move_block(green_field, green_blocks)
    green_field, score = examine(green_field)
    score_sum += score

    # 파란 필드 블록 구하기
    blue_blocks = []
    for ry, rx, t in red_blocks:
        if t > 1:
            t = 3 if t == 2 else 2
        blue_blocks += [(rx - col_min, ry, t)]

    blue_field = move_block(blue_field, blue_blocks)
    blue_field, score = examine(blue_field)
    score_sum += score

    return score_sum


def examine(field):
    score_sum = 0
    
    while True:
        score = 0
        # 꽉찬 곳 검사
        for y in range(5, -1, -1):
            # 만약 1이 채워지면
            count = 0
            for elm in field[y]:
                if elm != 0:
                    count += 1
            if count == 4:
                score += 1
                field[y] = [0, 0, 0, 0]

        # 블록 검사하기 3 ~ 1 종류별로

        for y in range(5, 0, -1):
            block_list = []
            for x in range(3, -1, -1):
                # 세로 블럭 검사하기
                if field[y][x] == 3:
                    # 위에 1이 없는경우 중심 옮기기
                    if field[y - 1][x] != 1:
                        field[y][x] = 1
                    else:
                        field[y][x] = 0
                        field[y - 1][x] = 0
                        # 블록 이동하기
                        block_list.append([(y, x, 3), (y - 1, x, 1)])
                # 가로 블럭 검사하기
                if field[y][x] == 2:
                    field[y][x] = 0
                    field[y][x - 1] = 0
                    block_list.append([(y, x, 2), (y, x - 1, 1)])
                # 홀블럭 검사하기
                if field[y][x] == 1:
                    field[y][x] = 0
                    block_list.append([(y, x, 1)])
            
            for blocks in block_list:
                field = move_block(field, blocks)
        score_sum += score
        
        if score == 0:
            break
    # 연한 곳 검사
    overflow = 0
    for y in range(2):
        for elm in field[y]:
            if elm != 0:
                overflow += 1
                break
    
    # 넘친 만큼 밑으로 밀기
    if overflow > 0:
        for y in range((5 - overflow), -1, -1):
            field[y + overflow] = field[y][:]

    for y in range(2):
        field[y] = [0, 0, 0, 0]
    return field, score_sum
    


def move_block(field, blocks):
    end = False
    while True:
        for y, x, _ in blocks:
            # 범위초과거나 해당위치에 먼저 블록이 있으면 end
            if (y == 6) or (field[y][x] != 0):
                end = True 

        # 그 위칸에 블록을 저장 후 end
        if end:
            for y, x, t in blocks:
                field[y - 1][x] = t
            break

        blocks = [(y + 1, x, t) for y, x, t in blocks]

    return field


def solution():
    score = 0
    block_num = 0
    t = 0
    for block in block_list:
        score += turn(block)
        t += 1
        
        # print(t)
        # for i in range(6):
        #     print(green_field[i], ' | ', blue_field[i])
        # print()
    
    for y in range(6):
        for x in range(4):
            if green_field[y][x] != 0:
                block_num += 1
            if blue_field[y][x] != 0:
                block_num += 1

    print(score)
    print(block_num)
    


N = int(input())
block_list = []
# 블록 입력받기
for _ in range(N):
    t, y, x = map(int, input().split())
    if t == 1:
        block_list.append([(y, x, 1)])
    elif t == 2:
        block_list.append([(y, x, 1), (y, x + 1, 2)])
    elif t == 3:
        block_list.append([(y, x, 1), (y + 1, x, 3)])
solution()