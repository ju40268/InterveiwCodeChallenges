board = [['a', 'b',  'c'], ['d', 'e', 'f'], ['g, h, i']]

def dfs(dims, i, j, target, K, res, visited):
    if (i < 0 or j < 0 or i >= dims[0] or j >= dims[1] or K < 0 or visited[i][j] == True): return
    K -= 1
    visited[i][j] = True
    dfs(dims)




def count_paths(dims, start, target, K):
    """
    @param dims, a tuple (width, height) of the dimensions of the board
    @param start, a tuple (x, y) of the king's starting coordinate
    @param target, a tuple (x, y) of the king's destination
    @param K, number of steps
    
    @return the number of distinct paths there are for a king in chess (can move one square vertically, horizontally, or diagonally)
    to move from the start to target coordinates on the given board in K moves
    """
    res = 0
    visited = [False * dims[0] for i in range(dims[1])]
    dfs(dims, start[0], start[1], target, K, res, visited)
    return res
 

 
if __name__ == "__main__":
    print("Running tests...")
    assert(count_paths((3, 3), (0, 0), (2, 2), 2) == 1)
    print("Passed test 1")
    assert(count_paths((3, 3), (0, 0), (2, 2), 3) == 6)
    print("Passed test 2")
    assert(count_paths((4, 4), (3, 2), (3, 2), 3) == 12)
    print("Passed test 3")
    assert(count_paths((4, 4), (3, 2), (1, 1), 4) == 84)
    print("Passed test 4")
    assert(count_paths((4, 6), (0, 2), (3, 4), 12) == 122529792)
    print("Passed test 5")