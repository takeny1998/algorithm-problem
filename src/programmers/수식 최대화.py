from itertools import permutations

# 문자열 연산자로 두 수를 계산하는 함수
def cal_by_str_exp(num1, num2, str_exp):
    if str_exp == '+':
        return num1 + num2
    elif str_exp == '-':
        return num1 - num2
    else:
        return int(num1 * num2)
    

# 주어진 연산자 우선순위대로 계산하는 함수
def cal_by_exp_priority(terms, exp_priorites):
    # 우선순위대로 연산자 지정
    for exp in exp_priorites:
        new_terms = []
        for i in range(len(terms)):
            term_exp, term_num = terms[i]
            if exp == term_exp:
                prev_exp, prev_num = new_terms[-1]
                new_num = cal_by_str_exp(prev_num, term_num, term_exp)
                new_terms[-1] = (prev_exp, new_num)
            else:
                new_terms.append(terms[i])
        terms = new_terms[:]
    return abs(terms[0][1])
        
        
def solution(expression):
    answer = 0
    
    # 단항 계산을 위해 수식을 앞 연산자로 묶어 표현
    terms = []
    curt_num = ''
    curt_exp = ''
    exps = []
    
    for char in expression:
        if char.isdigit():
            curt_num += char
        # 숫자가 아닌경우 >> 연산자
        else:
            exps.append(curt_exp)
            # 현재 연산자와 숫자를 넣고 초기화
            terms.append((curt_exp, int(curt_num)))
            curt_num = ''
            curt_exp = char
    
    exps.append(curt_exp)
    # 마지막 연산자 받기
    terms.append((curt_exp, int(curt_num)))
    
    # 세 연산자로 순열 생성
    for priority in permutations(['*', '+', '-'], 3):
        result = cal_by_exp_priority(terms, priority)
        if result > answer:
            answer = result
        
    return answer