def dfs(sheep, wolf, next_list, visited):
    global INFO, EDGES, max_sheep
    
    if sheep <= wolf:
        return
    else:
        if sheep > max_sheep:
            max_sheep = sheep
        for next_node in next_list:
            visited.append(next_node)
            next_list.remove(next_node)
            # 다음 노드의 자식노드 추가
            children = find_children(next_node)
            for child in children:
                next_list.append(child)

            # 노드의 동물 추가
            if INFO[next_node]:
                dfs(sheep, wolf + 1, next_list[:], visited[:])
            else:
                dfs(sheep + 1, wolf, next_list[:], visited[:])

            # 백트래킹 - 원래대로 돌리기
            for child in children:
                next_list.remove(child)

            next_list.insert(0, next_node)
            visited.pop()
    
    
def find_children(node):
    global INFO, EDGES
    result = []
    
    for parent_node, edge_node in EDGES:
        if parent_node == node:
            result.append(edge_node)
            
    return result
    
    
def solution(info, edges):
    global INFO, EDGES, max_sheep
    INFO = info
    EDGES = edges
    max_sheep = 0
    dfs(1, 0, find_children(0), [0])
    return max_sheep