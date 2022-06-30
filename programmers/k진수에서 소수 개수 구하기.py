from math import sqrt

# 진법변환 하는 함수
# num = 변환할 수
# key = 진법 수
def trans_num(num, key):
    result = ''
    
    while num != 0:
        # 나머지 수 문자열에 붙이기
        result += str(num % key)
        num = num // key
    
    # 뒤집어서 return
    return result[::-1]


def is_prime(num):
    if num == 1:
        return False
    for i in range(2, int(sqrt(num)) + 1):
        if num % i == 0:
            return False
    return True


def solution(n, k):
    answer = 0
    
    # 진법 변환
    transed_num = list(trans_num(n, k))
    curt_num = ''
    
    for ptr in range(len(transed_num)):
        curt_char = transed_num[ptr]
        
        if curt_char == '0':
            if len(curt_num) > 0:
                if is_prime(int(curt_num)):
                    answer += 1
            curt_num = ''
        else:
            curt_num += curt_char
        
        # 끝의 경우
        if ptr == len(transed_num) - 1:
            if len(curt_num) > 0:
                if is_prime(int(curt_num)):
                    answer += 1
            print(curt_num)
    
    return answer