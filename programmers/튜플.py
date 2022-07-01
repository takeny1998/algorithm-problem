def solution(s):
    answer = []
    s = s.lstrip("{{").rstrip("}}")
    set_list = s.split("},{")
    
    set_list = [[int(elm) for elm in line.split(',')] for line in set_list]
    
    set_list.sort(key=len)
    
    set_list = [set(line) for line in set_list]
    
    for set_elm in set_list:
        answer.append(list(set_elm - set(answer))[0])
        
    return answer