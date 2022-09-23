def check():
    for x in range(width):
        num = 0
        for y in range(height - 1):
            if field[y][x] == field[y + 1][x]:
                num += 1
            else:
                num = 0            
            if num == (standard - 1):
                break
        if num < (standard - 1):
            return False                
    return True


def dfs(y, count, inject):
    global answer
    if count > answer:
        return

    if check():
        answer = min(answer, count)
    
    if y < height:
        for val in range(2):
            temp = field[y][:]
            # 해당 행을 A, B로 바꿈
            field[y] = [val for _ in range(width)]
            inject.append((y, val))
            for i in range(y + 1, height + 1):
                dfs(i, count + 1,  inject)
            inject.pop()
            field[y] = temp[:]


T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    height, width, standard = map(int, input().split())
    field = []
    answer = 9999999999

    for _ in range(height):
        line = list(map(int, input().split()))
        field.append(line)
    
    # ///////////////////////////////////////////////////////////////////////////////////
    for i in range(height):
        dfs(i, 0, [])
    print(f'#{test_case} {answer}')
    # ///////////////////////////////////////////////////////////////////////////////////