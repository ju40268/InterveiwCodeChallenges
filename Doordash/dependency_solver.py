libraries = [
    'z',
    'a',
    'b',
    'c',
    'd'
]

dependencies = [
    'a->b,d',
    'b->c,f',
    'c->g',
    'd->e',
    'f->g'
]


output = ['z', 'g', 'f', 'c', 'b', 'e', 'd', 'a']


import sys, os
import json, collections

def build_graph(dependencies): 
    graph, degree = {}, {}
    all_libraries = set()
    for l in libraries:
        all_libraries.add(l)
    for d in dependencies:
        cur = (d.split("->"))[0]
        prev = (d.split("->"))[1]
        all_libraries.add(cur)
        for p in prev.split(","):
            all_libraries.add(p)
            graph[p] = graph.get(p, []) + [cur]
            degree[cur] = degree.get(cur, 0) + 1
    
    for l in all_libraries:
        print(l)
        if l not in degree:
            degree[l] = 0
    return graph, degree

def topological_sort(graph, degree):
    queue = collections.deque()
    for lib, indegree in degree.items():
        if indegree == 0: 
            queue.append(lib)
    result = []
    while queue:
        cur = queue.popleft()
        result.append(cur)
        if cur in graph:
            for neighber in graph[cur]:
                degree[neighber] -= 1
                if degree[neighber] == 0:
                    queue.append(neighber)
    for course_name, indegree in degree.items():
        if indegree != 0:
            return []
    return result

def main(): 
    graph, degree = build_graph(dependencies)
    result = topological_sort(graph, degree)
    print(result)

if __name__ == "__main__":
    main()