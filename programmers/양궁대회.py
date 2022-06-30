# 같은 점수일때 더 낮은 점수 많이맞춘 경우 찾기
# out: True > list1, False > list2
def diff_lowest(list1, list2):
    for i in range(10, 0, -1):
        # 둘다 0점이 아님
        if list1[i] > list2[i]:
            return True
        elif list2[i] > list1[i]:
            return False


# 어피치와 라인언의 점수차를 계산
def cal_score(apeach, ryan):
    result = {'apeach': 0, 'ryan': 0}
    
    for i in range(11):
        score = abs(i - 10)
        # 어피치 화살이 같거나 클 때
        if apeach[i] >= ryan[i]:
            if apeach[i] > 0:
                result['apeach'] += score
        else:
            result['ryan'] += score
    return result['ryan'] - result['apeach']


def dfs(ryan, arrow_num, score):
    global apeach, max_diff, max_diff_list
    
    if score == 11:
        # 남은 화살 있으면 0점에 다 쏘기
        if arrow_num > 0:
            ryan[10] = arrow_num
            
        diff = cal_score(apeach, ryan)
        # 만약 차이보다 큰 경우 기존 정답리스트 비워줘야함
        if diff > max_diff:
            max_diff_list = []
        if diff >= max_diff:
            max_diff = diff
            max_diff_list.append(ryan[:])
        return

    # 라이언이 해당 점수에서 이기려고 할때
    # 해당점수에서 어피치 화살이 남아있는 화살보다 적어야함
    if apeach[score] < arrow_num:
        shot_num = apeach[score] + 1
        ryan.append(shot_num)
        dfs(ryan, arrow_num - shot_num, score + 1)
        # 백트래킹, 다시 돌아올떄는 해당 점수에서 화살을 쏜 흔적 제거해야 함
        ryan.pop()
    
    # 해당 점수 안먹을때
    ryan.append(0)
    dfs(ryan, arrow_num, score + 1)
    ryan.pop()

    
def solution(n, info):
    global apeach, max_diff, max_diff_list
    apeach = info
    # 어피치 이길려면 1점차 이상이어야 함
    max_diff = 1
    answer = []
    max_diff_list = []
    
    dfs([], n, 0)
    if len(max_diff_list) == 0:
        return [-1]
    answer = max_diff_list.pop()
    
    while len(max_diff_list):
        curt_elm = max_diff_list.pop()
        if diff_lowest(curt_elm, answer):
            answer = curt_elm
        
    return answer