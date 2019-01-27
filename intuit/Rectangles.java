import java.util.*;
import org.junit.*;

public class Rectangles {
/***************************************  Solution 1 思路  *****************************************
 *
 * 【基本题】给一个矩阵,矩阵中有且只有一个由0组成的长方体,其他全是1.
 *         找出这个长方体的左上角左边,长方体的长和高。
 *  input:
 *  [
 * 		[1,1,1,1,1,1],
 * 		[1,0,0,0,1,1],
 * 		[1,0,0,0,1,1],
 * 		[1,1,1,1,1,1],
 * 		[1,1,1,1,1,1]
 *  ]
 *  那么返回{1,1,3,2}  坐标为（1,1）长为3,高为2
 *
 **********************************  End of Solution 1 思路  **************************************/
    public int[] findRectangle(int[][] grid) {
        // 1. find 1st 0
        // 2. start scanning vertically & horizontally to get length and width
        int m = grid.length, n = grid[0].length;
        int[] res = new int[4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    res[0] = i;
                    res[1] = j;

                    int l = 0, w = 0;
                    while (i < m && grid[i][j] == 0) {
                        l++;
                        i++;
                    }
                    i--;  // Bug1: 这里别忘记 i--, 因为上面可能会让 i 走出边界了
                    while (j < n && grid[i][j] == 0) {
                        w++;
                        j++;
                    }
//                  res[2] = l;               // 长
//                  res[3] = w;               // 宽
                    res[2] = res[0] + l - 1;  // 右下角横坐标
                    res[3] = res[1] + w - 1;  // 右下角纵坐标
                    break;
                }
            }
        }

        return res;
    }
/***************************************  Solution 2 思路  *****************************************
 *
 * 【Follow up 1】给一个矩阵,矩阵里的每个元素是1,但是其中分布着一些长方形区域, 这些长方形区域中的元素为0.
 * （前提每两个长方体之间是不会连接的）
 *  要求输出每个长方形的位置（用长方形的左上角元素坐标和右下角元素坐标表示）。
 *
 *  每个长方形都不重叠也不会相连,每个长方形之间都会被1隔开。
 *
 *  input:
 *  [
 *  	[1,1,1,1,1,1],
 *  	[0,0,1,0,1,1],
 *  	[0,0,1,0,1,0],
 *  	[1,1,1,0,1,0],
 *  	[1,0,0,1,1,1]
 *  ]
 *  output:
 *  [
 *  	[1,0,2,1],
 *  	[1,3,3,3],
 *  	[2,5,3,5],
 *  	[4,1,4,2]
 *  ]
 *
 **********************************  End of Solution 2 思路  **************************************/
    public List<int[]> findRectangle2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<int[]> res = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    int x = i, y = j;
                    int[] coord = new int[4];
                    coord[0] = x;
                    coord[1] = y;

                    int l = 0, w = 0;
                    while (x < m && grid[x][y] == 0) {
                        l++;
                        x++;
                    }
                    x--;  // Bug1: 这里别忘记 x--, 因为上面可能会让 x 走出边界了
                    while (y < n && grid[x][y] == 0) {
                        w++;
                        y++;
                    }
                    coord[2] = coord[0] + l - 1;  // 右下角横坐标
                    coord[3] = coord[1] + w - 1;  // 右下角纵坐标

                    for (int row = coord[0]; row <= coord[2]; row++) {
                        for (int col = coord[1]; col <= coord[3]; col++) {
                            visited[row][col] = true;
                        }
                    }

                    res.add(coord);
                }
            }
        }

        return res;
    }
/***************************************  Solution 3 思路  *****************************************
 *
 * 【Follow up 2】返回 list of list, 每一个list是矩阵中相连的元素为0的点的坐标, 类似 LC200 number of island
 *
 **********************************  End of Solution 3 思路  **************************************/
    public List<List<Integer>> findRectangle3(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<List<Integer>> res = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    List<Integer> coords = new ArrayList<>();
                    dfs(grid, coords, visited, i, j);
                    res.add(coords);
                }
            }
        }

        return res;
    }

    private void dfs(int[][] grid, List<Integer> coords, boolean[][] visited, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i == -1 || j == -1 || i == m || j == n || visited[i][j] || grid[i][j] == 1) return;
        visited[i][j] = true;
        coords.add(i);
        coords.add(j);
        dfs(grid, coords, visited, i+1, j);
        dfs(grid, coords, visited, i-1, j);
        dfs(grid, coords, visited, i, j+1);
        dfs(grid, coords, visited, i, j-1);
    }





    /**************  Tests  *************/
    public static void main(String[] args) {
        // Q1
        Rectangles obj = new Rectangles();
        Assert.assertArrayEquals(new int[] {1,1,2,3}, obj.findRectangle(new int[][] {
                {1,1,1,1,1,1},
                {1,0,0,0,1,1},
                {1,0,0,0,1,1},
                {1,1,1,1,1,1},
                {1,1,1,1,1,1},
        }));
        Assert.assertArrayEquals(new int[4], obj.findRectangle(new int[][] {
                {1,1,1,1,1,1},
                {1,1,1,1,1,1},
                {1,1,1,1,1,1},
        }));
        Assert.assertArrayEquals(new int[] {1,1,2,3}, obj.findRectangle(new int[][] {
                {1,1,1,1},
                {1,0,0,0},
                {1,0,0,0},
        }));
        Assert.assertArrayEquals(new int[] {0,0,1,2}, obj.findRectangle(new int[][] {
                {0,0,0},
                {0,0,0},
        }));

        // Q2
        assertListEquals(new ArrayList<int[]>() {{
            add(new int[] {1,0,2,1});
            add(new int[] {1,3,3,3});
            add(new int[] {2,5,3,5});
            add(new int[] {4,1,4,2});
        }}, obj.findRectangle2(new int[][]{
                {1,1,1,1,1,1},
                {0,0,1,0,1,1},
                {0,0,1,0,1,0},
                {1,1,1,0,1,0},
                {1,0,0,1,1,1},
        }));
        assertListEquals(new ArrayList<int[]>() {{
            add(new int[] {0,0,1,1});
            add(new int[] {1,3,3,5});
            add(new int[] {3,1,3,1});
        }}, obj.findRectangle2(new int[][]{
                {0,0,1,1,1,1},
                {0,0,1,0,0,0},
                {1,1,1,0,0,0},
                {1,0,1,0,0,0},
        }));
        assertListEquals(new ArrayList<int[]>() {{
            add(new int[] {1,3,2,5});
            add(new int[] {3,1,4,1});
            add(new int[] {4,3,4,5});
        }}, obj.findRectangle2(new int[][]{
                {1,1,1,1,1,1,1},
                {1,1,1,0,0,0,1},
                {1,1,1,0,0,0,1},
                {1,0,1,1,1,1,1},
                {1,0,1,0,0,0,1},
                {1,1,1,1,1,1,1},
        }));

        // Q3
        Assert.assertEquals("[[0, 0], [0, 3, 0, 4], " +
                            "[1, 1, 2, 1, 2, 2, 2, 3], [1, 5], [3, 4, 4, 4, 4, 3, 4, 2]]",
                obj.findRectangle3(new int[][] {
                        {0,1,1,0,0,1},
                        {1,0,1,1,1,0},
                        {1,0,0,0,1,1},
                        {1,1,1,1,0,1},
                        {1,1,0,0,0,1},
                }).toString());
    }

    private static void assertListEquals(List<int[]> l1, List<int[]> l2) {
        Assert.assertEquals(l1.size(), l2.size());
        for (int i = 0; i < l1.size(); i++) {
            Assert.assertArrayEquals(l1.get(i), l2.get(i));
        }
    }
}
