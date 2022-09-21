def check():
    for x in range(width):
        num = 0
        for y in range(height - 1):
            if field[y][x] == field[y + 1][x]:
                num += 1
            else:
                num = 0            
            if num == 2:
                break
        if num < 2:
            return False                
    return True


def dfs(y, count):
    print(y, count)
    if check():
        print(count)
        return

    for val in range(2):
        # 해당 행을 A, B로 바꿈
        for x in range(width):
            field[y][x] = val
        
        for i in range(y + 1, height):
            dfs(i, count + 1)
        
        

#import sys
#sys.stdin = open("input.txt", "r")

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    height, width, standard = map(int, input().split())
    field = []

    for _ in range(height):
        line = list(map(int, input().split()))
        field.append(line)
    
    # ///////////////////////////////////////////////////////////////////////////////////
    dfs(0, 0)
    # ///////////////////////////////////////////////////////////////////////////////////