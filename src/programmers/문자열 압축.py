def zip_str(string, zip_num):
    result = ''
    slice_str = []
    
    while len(string):
        # zip_num만큼 string 앞 자르기
        curt_str = string[:zip_num]
        string = string[zip_num:]
        
        # 마지막 원소 찾기
        if len(slice_str):
            prev_str = slice_str[-1]
            # 마지막 원소랑 같으면
            if curt_str == prev_str[0]:
                # 마지막원소 count +1 해주고 넘김
                slice_str[-1][1] += 1
                continue
        slice_str.append([curt_str, 1])
    
    for word, count in slice_str:
        if count > 1:
            result += str(count)
        result += word
        
    return result
    
    
def solution(s):
    answer = len(s)
    
    for i in range(1, (len(s) // 2) + 1):
        zipped_len = len(zip_str(s, i))
        if zipped_len < answer:
            answer = zipped_len
        
    return answer