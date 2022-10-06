def solution():
    answer = []

    # dfs 순회
    def dfs(idx, opers, oper_num):
        if idx == (N - 1):
            answer.append(cal(opers))
            return opers
        
        # 연산자별로 끼우기
        for o in range(4):
            if oper_num[o] > 0:
                oper_num[o] -= 1
                opers[idx] = o
                dfs(idx + 1, opers[:], oper_num[:])
                oper_num[o] += 1
                opers[idx] = -1


    # 항 계산하기
    def cal(opers):
        result = nums[0]
        idx = 1
        for oper in opers:
            if oper == 0: # 덧셈
                result += nums[idx]
            elif oper == 1: # 뺄셈
                result -= nums[idx]
            elif oper == 2: # 곱셈
                result *= nums[idx]
            elif oper == 3: # 나눗셈
                result /= nums[idx]
                result = int(result)

            idx += 1

        return result


    dfs(0, [-1 for _ in range(N - 1)], oper_num)

    print(max(answer))
    print(min(answer))
    return


N = int(input())
nums = list(map(int, input().split()))
oper_num = list(map(int, input().split()))
solution()