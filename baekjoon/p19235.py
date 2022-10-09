blue_field = [[0 for _ in range(4)] for _ in range(6)]
green_field = [line[:] for line in blue_field]

def turn(red_blocks):
    global green_field, blue_field
    score_sum = 0

    # 초록 필드
    # 행, 열 최솟값 찾기
    row_min = 9999
    col_min = 9999
    for y, x in red_blocks:
        row_min = min(row_min, y)
        col_min = min(col_min, x)

    # 초록 필드 블록 구하기
    green_blocks = []
    for ry, rx in red_blocks:
        green_blocks += [(ry - row_min, rx)]

    # 초록 필드 블록 움직이기
    green_field = move_block(green_field, green_blocks)
    green_field, score = examine(green_field)
    score_sum += score

    # 파란 필드 블록 구하기
    blue_blocks = []
    for ry, rx in red_blocks:
        blue_blocks += [(rx - col_min, ry)]

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
            if field[y] == [1, 1, 1, 1]:
                score += 1
                field[y] = [0, 0, 0, 0]

        # 점수 획득시 빈 블록 밑으로 내리기
        if score > 0:
            for x in range(4):
                for y in range(5, -1, -1):
                    if field[y][x] == 1:
                        field[y][x] = 0
                        field = move_block(field, [(y, x)])
        score_sum += score
        if score == 0:
            break

    # 연한 곳 검사
    overflow = 0
    for y in range(2):
        for elm in field[y]:
            if elm == 1:
                overflow += 1
                break
    
    # 넘친 만큼 밑으로 밀기
    if overflow > 0:
        for y in range((5 - overflow), -1, -1):
            field[y + overflow] = field[y][:]

    return field, score_sum
    


def move_block(field, blocks):
    end = False
    while True:
        for y, x in blocks:
            # 범위초과거나 해당위치에 먼저 블록이 있으면 end
            if (y == 6) or (field[y][x] == 1):
                end = True 

        # 그 위칸에 블록을 저장 후 end
        if end:
            for y, x in blocks:
                field[y - 1][x] = 1
            break

        blocks = [(y + 1, x) for y, x in blocks]

    return field


def solution():
    score = 0
    block_num = 0

    for block in block_list:
        score += turn(block)
    
        # for i in range(6):
        #     print(green_field[i], ' | ', blue_field[i])
        # print()
    
    for y in range(6):
        for x in range(4):
            if green_field[y][x] == 1:
                block_num += 1
            if blue_field[y][x] == 1:
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
        block_list.append([(y, x, 2), (y, x + 1, 1)])
    elif t == 3:
        block_list.append([(y, x, 1), (y + 1, x, 3)])
solution()