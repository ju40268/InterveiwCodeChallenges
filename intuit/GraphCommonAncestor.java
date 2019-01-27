import java.util.*;
import org.junit.*;

/****************************************  Problem Statement  *************************************
 *
 * >> boolean collectNodesWith0And1Ancestors(int[][] pairs, int node1, int node2); // (parent, child)
 * >> Rf:
 *     - https://github.com/wenyuanwu/algorithm-practice/blob/
 *       803e4a1a6ff305ad5298b2042375020e806e4573/elements_of_programming/karat.py
 *     - https://github.com/neverlandzzy/leetcode/blob/master/Karat/src/phone/MultiTree.java
 *
 **************************************  End of Problem Statement  *******************************/
public class GraphCommonAncestor {
/***************************************  Solution 1 思路  *****************************************
 *
 *  输入是int[][] input, input[0] 是 input[1] 的 parent,
 *
 *  第一问是只有0个parent 和 只有1个parent的节点,
 *  Write a function that takes this data as input and returns two collections:
 *   - one containing all individuals with 0 known parents, and
 *   - one containing all individuals with exactly 1 known parent
 *
 * 【例子】
 *  输入： (1, 3), (2, 3), (3, 6), (5, 6), (5, 7), (4, 5), (4, 8), (8, 9)
 *
 *    1   2   4
 *     \ /   / \
 *      3   5   8
 *       \ / \   \
 *        6   7   9
 *
 * 【基本题输出】   [1, 2, 4],   // Individuals with zero parents
 *            　 [5, 7, 8, 9]  // Individuals with exactly one parent
 *
 **********************************  End of Solution 1 思路  **************************************/
    public List<List<String>> collectNodesWith0And1Ancestors(String[][] dataset) {  // (parent, child)
        // 1. build graph (单向图, child -> parent)
        Map<String, List<String>> G = new HashMap<>();
        for (String[] pair : dataset) {
            String parent = pair[0], child = pair[1];
            if (!G.containsKey(child))  G.put(child,  new ArrayList<>());
            if (!G.containsKey(parent)) G.put(parent, new ArrayList<>());
            G.get(child).add(parent);
        }

        // 2. loop over graph, check the size of its adj list and add to result accordingly
        List<List<String>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        for (String child : G.keySet()) {
            List<String> parents = G.get(child);
            int size = parents.size();
            if (size == 0) res.get(0).add(child);
            if (size == 1) res.get(1).add(child);
        }

        return res;
    }
/***************************************  Solution 2 思路  *****************************************
 *
 * Suppose we have some input data describing a graph of relationships
 * between parents and children over multiple generations.
 *
 * The data is formatted as a list of (parent, child) pairs,
 * where each individual is assigned a unique integer identifier.
 *
 * For example, in this diagram, 3 is a child of 1 and 2, and 5 is a child of 4:
 *
 *    1   2   4
 *     \ /   / \
 *      3   5   8
 *       \ / \   \
 *        6   7   9
 *
 * Write a function that, for two given individuals in our dataset,
 * returns true if and only if they share at least one ancestor.
 *
 * 找graph中兩個node有無common ancestor (自己不算自己的 ancestor, so input = (2,3), return false)
 *
 * Sample input and output:
 *
 *   [3, 8] => false
 *   [5, 8] => true
 *   [6, 8] => true
 *
 **********************************  End of Solution 2 思路  **************************************/
    public boolean getCommonAncestor(String[] idPair, String[][] dataset) {  // (parent, child)
        // 1. build graph (单向图, child -> parent)
        String node1 = idPair[0], node2 = idPair[1];
        Map<String, List<String>> G = new HashMap<>();
        for (String[] pair : dataset) {
            String parent = pair[0], child = pair[1];
            if (!G.containsKey(child))  G.put(child,  new ArrayList<>());
            if (!G.containsKey(parent)) G.put(parent, new ArrayList<>());
            G.get(child).add(parent);
        }
        // 2. dfs on both nodes, and create a set of ancestors for each (这个 set 同样也是 visited[])
        Set<String> ancestors1 = new HashSet<>();
        Set<String> ancestors2 = new HashSet<>();

        collectAncestors(node1, G, ancestors1);
        collectAncestors(node2, G, ancestors2);

        // 3. 检查两个 set 之间是否有重合
        for (String node : ancestors1) {
            if (ancestors2.contains(node)) return true;
        }
        return false;
    }

    // 没必要另外再开一个 visited set, 直接用 ancestors[] 来检查即可
    private void collectAncestors(String v, Map<String, List<String>> G, Set<String> ancestors) {
        if (!G.containsKey(v)) return;  // 这个不能漏, 否则下面的 .get(v) 会有 NPE
        for (String w : G.get(v)){
            if (!ancestors.contains(w)) {
                ancestors.add(w);
                collectAncestors(w, G, ancestors);
            }
        }
    }
/***************************************  Solution 3 思路  *****************************************
 *
 * Write a function that, for a given individual in our dataset,
 * returns their earliest known ancestor
 * -- the one at the farthest distance from the input individual.
 *
 *  >> If there is more than one ancestor tied for “earliest”, return any one of them.
 *  >> If the input individual has no parents, the function should return null (or -1).
 * ------------------------------------------------------------------------------------
 * find farthest ancestor from node
 *  - return any one of the furthest parents
 *  - return -1 if node has no parent
 * --------------------------------------------------
 * 给一个点，找出最远的root，找一个就行。如果没有parent返回-1
 *
 **********************************  End of Solution 3 思路  **************************************/
    public String getFarthestAncestor(String node, String[][] dataset) {
        // 1. build graph (单向图, child -> parent)
        Map<String, List<String>> G = new HashMap<>();
        for (String[] pair : dataset) {
            String parent = pair[0], child = pair[1];
            if (!G.containsKey(child))  G.put(child,  new ArrayList<>());
            if (!G.containsKey(parent)) G.put(parent, new ArrayList<>());
            G.get(child).add(parent);
        }

        if (G.get(node).size() == 0) return "-1";

        Deque<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        q.add(node);
        visited.add(node);

        while (!q.isEmpty()) {
            String v = q.poll();  // 若有多个同样远的祖先则内部再加个 0..q.sz()-1 遍历
            List<String> parents = G.get(v);
            if (parents.size() == 0) {
                if (q.size() == 0) return v; // Bug1: 只要 build G 时候两边都建立 adj list,
                continue;                    //       那么判断 root 就不能用 adj == null,
            }                                //       而是 adj.size() == 0
            for (String parent : parents) {
                if (!visited.contains(parent)) {
                    visited.add(parent);
                    q.add(parent);
                }
            }
        }

        return null;
    }

// 下面的 DFS 写完之后感觉不对, 因为 BFS 走的才是最短距离, 所以求最远的也应用 BFS 求出来才准确

//    int maxDist = 0; String farthestAns = null;
//    public String getFarthestAncestor(String node, String[][] dataset) {
//        // 1. build graph (单向图, child -> parent)
//        Map<String, List<String>> G = new HashMap<>();
//        for (String[] pair : dataset) {
//            String parent = pair[0], child = pair[1];
//            if (!G.containsKey(child))  G.put(child,  new ArrayList<>());
//            if (!G.containsKey(parent)) G.put(parent, new ArrayList<>());
//            G.get(child).add(parent);
//        }
//
//        // 2. start dfs on the input node
//        dfs(G, node, 0);
//        return farthestAns;
//    }
//
//    // 感觉不能用 visited, 否则若 A->B 中间有多条路径可以走, 那么用 visited 判断之后可能只
//    // 走最近的那条路线, 那么走到最后算得的最短路径可能就有问题了
//    private void dfs(Map<String, List<String>> G, String v, int dist) {
//        List<String> parents = G.get(v);
//        if (parents == null) {  // reaches root
//            if (dist > maxDist) {
//                maxDist = dist;
//                farthestAns = v;
//            }
//            return;
//        }
//        for (String parent : parents) {
//            dfs(G, parent, dist+1);
//        }
//    }

    public static void main(String[] args) {
        String[][] pairs = new String[][] {
                {"1","3"},   {"2","3"},  {"4","5"},  {"4","8"},
                {"3","6"},   {"5","6"},  {"5","7"},  {"8","9"},
                {"6","10"},  {"7","10"}, {"3","10"}
        };

        // Q1
        GraphCommonAncestor obj = new GraphCommonAncestor();
        Assert.assertEquals("[[1, 2, 4], [5, 7, 8, 9]]",
                obj.collectNodesWith0And1Ancestors(pairs).toString());

        // Q2
        Assert.assertTrue(obj.getCommonAncestor(new String[] {"6","9"}, pairs));
        Assert.assertTrue(obj.getCommonAncestor(new String[] {"5","8"}, pairs));
        Assert.assertTrue(obj.getCommonAncestor(new String[] {"6","8"}, pairs));
        Assert.assertFalse(obj.getCommonAncestor(new String[] {"6","2"}, pairs));  // 所以题意是根节点的不能将自己算作祖先?
        Assert.assertFalse(obj.getCommonAncestor(new String[] {"3","8"}, pairs));
        Assert.assertFalse(obj.getCommonAncestor(new String[] {"3","7"}, pairs));
        Assert.assertFalse(obj.getCommonAncestor(new String[] {"1","2"}, pairs));
        Assert.assertFalse(obj.getCommonAncestor(new String[] {"1","5"}, pairs));

        // Q3
        Assert.assertEquals("4", obj.getFarthestAncestor("10", pairs));
        Assert.assertEquals("4", obj.getFarthestAncestor("6", pairs));
        Assert.assertEquals("4", obj.getFarthestAncestor("9", pairs));
        Assert.assertEquals("2", obj.getFarthestAncestor("3", pairs));

        Assert.assertEquals("4", obj.getFarthestAncestor("8", pairs));
        Assert.assertEquals("4", obj.getFarthestAncestor("7", pairs));
        Assert.assertEquals("4", obj.getFarthestAncestor("6", pairs)); //=> {1,2,4} 都算对

        Assert.assertEquals("-1", obj.getFarthestAncestor("1", pairs));
    }
}
