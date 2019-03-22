import java.util.ArrayList;
import java.util.List;

/*
print "Running tests..."
    assert(count_paths((3, 3), (0, 0), (2, 2), 2) == 1)
    print "Passed test 1"
    assert(count_paths((3, 3), (0, 0), (2, 2), 3) == 6)
    print "Passed test 2"
    assert(count_paths((4, 4), (3, 2), (3, 2), 3) == 12)
    print "Passed test 3"
    assert(count_paths((4, 4), (3, 2), (1, 1), 4) == 84)
    print "Passed test 4"
    assert(count_paths((4, 6), (0, 2), (3, 4), 12) == 122529792)
    print "Passed test 5"
 */


public class CountPathsDoordash {
    static int count;

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static int countPaths(int[][] matrix, int x1, int y1, int x2, int y2, int K) {
        count = 0;
        count(matrix, x1, y1, x2, y2, 0, K);
        return count;
    }

    public static List<List<Node>> printAllPaths(int[][] matrix, int x1, int y1, int x2, int y2, int K) {
        List<List<Node>> answer = new ArrayList<List<Node>>();
        List<Node> path = new ArrayList<Node>();
        getAllPaths(matrix, x1, y1, x2, y2, 0, K, answer, path);
        return answer;
    }

    public static void count(int[][] matrix, int x1, int y1, int x2, int y2, int k, int K) {
        if(k > K) {
            return;
        }

        if(k == K && x1 == x2 && y1 == y2) {
            count++;
            return;
        }

        if(x1<0 || x1 >= matrix.length || y1 < 0 || y1 >= matrix[0].length) {
            return;
        }
        count(matrix, x1 - 1, y1 - 1, x2, y2, k + 1, K);
        count(matrix, x1 - 1, y1, x2, y2, k + 1, K);
        count(matrix, x1 - 1, y1 + 1, x2, y2, k + 1, K);
        count(matrix, x1, y1 - 1, x2, y2, k + 1, K);
        count(matrix, x1, y1 + 1, x2, y2, k + 1, K);
        count(matrix, x1 + 1, y1 - 1, x2, y2, k + 1, K);
        count(matrix, x1 + 1, y1, x2, y2, k + 1, K);
        count(matrix, x1 + 1, y1 + 1, x2, y2, k + 1, K);
    }

    public static void getAllPaths(int[][] matrix, int x1, int y1, int x2, int y2, int k, int K,  List<List<Node>> answer, List<Node> path) {

        if(k == K && x1 == x2 && y1 == y2) {
            count++;
            answer.add(new ArrayList<>(path));
            return;
        }

        if(x1<0 || x1 >= matrix.length || y1 < 0 || y1 >= matrix[0].length) {
            return;
        }
        if(k==K) {
            return;
        }
        path.add(new Node(x1, y1));

        getAllPaths(matrix, x1-1, y1-1, x2, y2, k+1, K,  answer, path);
        getAllPaths(matrix, x1-1, y1, x2, y2, k+1, K,  answer, path);
        getAllPaths(matrix, x1-1, y1+1, x2, y2, k+1, K,  answer, path);
        getAllPaths(matrix, x1, y1-1, x2, y2, k+1, K,  answer, path);
        getAllPaths(matrix, x1, y1+1, x2, y2, k+1, K,  answer, path);
        getAllPaths(matrix, x1+1, y1-1, x2, y2, k+1, K,  answer,path);
        getAllPaths(matrix, x1+1, y1, x2, y2, k+1, K,  answer, path);
        getAllPaths(matrix, x1+1, y1+1, x2, y2, k+1, K,  answer, path);

        path.remove(path.size()-1);
    }

    public static void main(String[] args) {
        System.out.println(countPaths(new int[4][4], 3, 2, 3, 2, 10));
        List<List<Node>> a = printAllPaths(new int[4][4], 3, 2, 2, 2, 3);
        System.out.println(printAllPaths(new int[4][4], 3, 2, 3, 2, 3).size());
    }
}
