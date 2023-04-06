def solution(price: list, schedule: list):
    global answer
    answer = 9999

    def dfs(month: int, fee: int):
        global answer

        if month == 12:
            if fee < answer:
                answer = fee
            return

        count = schedule[month]
        if count > 0:
            # 1일권을 쓰는 경우
            dfs(month + 1, fee + (price[0] * count))
            # 1달권을 쓰는 경우
            dfs(month + 1, fee + price[1])
            # 3달권을 쓰는 경우
            dfs(12 if (month + 3 > 11) else month + 3, fee + price[2])
        else:
            # 그냥 넘어가기
            dfs(month + 1, fee)
    dfs(0, 0)
    return min(price[3], answer)

# import sys
# sys.stdin = open("input.txt", "r")

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    # ///////////////////////////////////////////////////////////////////////////////////
    price: list = [int(elm) for elm in input().split()]
    schedule: list = [int(elm) for elm in input().split()]
    print(f'#{test_case} {solution(price, schedule)}')
    # ///////////////////////////////////////////////////////////////////////////////////
