import java.io.*;
import java.util.*;
public class largestSet {

    /*
     * To execute Java, please define "static void main" on a class
     * named Solution.
     *
     * If you need more classes, simply define them inline.
     */

//   matrix1 = {{1, 1, 3},
//              {4, 1, 6},
//              {4, 5, 2}};
// // 3

//   matrix2 = {{1, 1, 1, 2, 4},
//              {5, 1, 5, 3, 1},
//              {3, 4, 2, 1, 1}};

// // 4

//   matrix3 = {{3, 3, 3, 3, 3, 1},
//              {3, 4, 4, 4, 3, 4},
//              {2, 4, 3, 3, 3, 4},
//              {2, 4, 4, 4, 4, 4}};

// // 11

//   matrix4 = {{1, 1, 1, 1, 1},
//              {1, 1, 1, 1, 1},
//              {1, 1, 0, 1, 1},
//              {1, 1, 1, 1, 1},
//              {1, 1, 1, 1, 1}};

// 21

        static int tempCounts = 0;
        static int rows;
        static int cols;
        public static void main(String[] args) {

            System.out.println(largestSet(new int[][]{{3, 3, 3, 3, 3, 1},
                    {3, 4, 4, 4, 3, 4},
                    {2, 4, 3, 3, 3, 4},
                    {2, 4, 4, 4, 4, 4}}));

        }

        public static int largestSet(int[][] matrix) {
            int[][] checkMatrix = new int[matrix.length][matrix[0].length];

            int maxCount = Integer.MIN_VALUE;
            int count = 0;
            rows = matrix.length;
            cols = matrix[0].length;
            for(int i= 0; i<matrix.length; i++) {
                for(int j= 0; j<matrix[i].length; j++) {
                    if(checkMatrix[i][j] == 0) {
                        tempCounts = 1;
                        checkMatrix[i][j] = -1;
                        findSizeOfConnectedSet(matrix, checkMatrix, i, j, matrix[i][j]);

                        if(maxCount < tempCounts) {
                            maxCount = tempCounts;
                        }
                    }
                }
            }
            return maxCount;
        }

        public static void findSizeOfConnectedSet(int[][] matrix, int[][] checkMatrix,
                                                  int i, int j, int value) {

            if(i<0 || j <0 || i > matrix.length || j > matrix[0].length ) {
                return;
            }
            // diagonal down
            if(i+1 < rows && j+1 < cols && checkMatrix[i+1][j+1] == 0 && matrix[i+1][j+1] == value) {
                tempCounts++;
                checkMatrix[i+1][j+1] = -1;
                findSizeOfConnectedSet(matrix, checkMatrix, i+1, j+1, value);

            }

            // right
            if(j+1 < cols && checkMatrix[i][j+1] == 0 && matrix[i][j+1] == value) {
                tempCounts++;
                checkMatrix[i][j+1] = -1;
                findSizeOfConnectedSet(matrix, checkMatrix, i, j+1, value);

            }

            // bottom
            if(i+1 < rows && checkMatrix[i+1][j] == 0 && matrix[i+1][j] == value) {
                tempCounts++;
                checkMatrix[i+1][j] = -1;
                findSizeOfConnectedSet(matrix, checkMatrix, i+1, j, value);

            }
            // bottom left
            if(i+1 < rows && j-1 > 0 && checkMatrix[i+1][j-1] == 0 && matrix[i+1][j-1] == value) {
                tempCounts++;
                checkMatrix[i+1][j-1] = -1;
                findSizeOfConnectedSet(matrix, checkMatrix, i+1, j-1, value);

            }

            //left
            if(i-1 > 0 && checkMatrix[i-1][j] == 0 && matrix[i-1][j] == value) {
                tempCounts++;
                checkMatrix[i-1][j] = -1;
                findSizeOfConnectedSet(matrix, checkMatrix, i-1, j, value);

            }

            // upper left
            if(i-1 > 0 && j-1 > 0 && checkMatrix[i-1][j-1] == 0 && matrix[i-1][j-1] == value)     {
                tempCounts++;
                checkMatrix[i-1][j-1] = -1;
                findSizeOfConnectedSet(matrix, checkMatrix, i-1, j-1, value);

            }

            // up
            if(i-1 > 0 && checkMatrix[i-1][j] == 0 && matrix[i-1][j] == value) {
                tempCounts++;
                checkMatrix[i-1][j] = -1;
                findSizeOfConnectedSet(matrix, checkMatrix, i-1, j, value);

            }

            // up right
            if(i-1 > 0 && j+1 < cols &&checkMatrix[i-1][j+1] == 0 && matrix[i-1][j+1] ==           value) {
                tempCounts++;
                checkMatrix[i-1][j+1] = -1;
                findSizeOfConnectedSet(matrix, checkMatrix, i-1, j+1, value);

            }
        }
    }


