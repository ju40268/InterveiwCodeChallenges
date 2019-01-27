import java.util.*;

public class DomainClickSum {
/***************************************  Solution 1 思路  *****************************************
 *
 * 【第一题】给广告在每个domain上被click的次数， 要求返回domain及其所有sub domain 被click的总次数
 *  输入：[
 *          ["google.com", "60"],
 *          ["yahoo.com", "50"],
 *          ["sports.yahoo.com", "80"]
 *       ]
 *
 *  输出：[
 *          ["com", "190"],             (60+50+80)
 *          ["google.com", "60"],
 *          ["yahoo.com", "130"],       (50+80)
 *          ["sports.yahoo.com", "80"]
 *       ]
 *
 **********************************  End of Solution 1 思路  **************************************/
    public static List<List<String>> clickSum(List<List<String>> input) {
        List<List<String>> res = new ArrayList<>();
        if (input == null || input.size() == 0) return res;

        Map<String, Integer> counts = new HashMap<>();

        // 1. parse info and create counts map
        for (List<String> pair : input) {
            String domain = pair.get(0);
            int count = Integer.valueOf(pair.get(1));

            String[] parts = domain.split("\\.");
            int n = parts.length;
            // 对于每个 domain: 通过 "." 分拆, 然后从后往前一点点将 subdomain 拼接并加入 HashMap 统计次数
            String curr = parts[n-1];
            for (int i = n-1; i >= 0; i--) {
                if (i != n-1) curr = parts[i] + "." + curr;
                counts.put(curr, counts.getOrDefault(curr, 0) + count);
            }
        }

        // 2. construct result
        for (String domain : counts.keySet()) {
            List<String> entry = new ArrayList<>();
            entry.add(domain);
            entry.add(counts.get(domain) + "");
            res.add(entry);
        }

        System.out.println(res);
        return res;
    }
/***************************************  Solution 2 思路  *****************************************
 *
 * 【第二题】给每个user访问历史记录，找出两个user之间longest continuous common history -- 类似 LC718
 *  输入： [
 *             ["3234.html", "xys.html", "7hsaa.html"], // user1
 *             ["3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"] // user2
 *          ], user1 and user2 （指定两个user求intersect）
 *  输出： ["xys.html", "7hsaa.html"]
 *
 * ---------------------------------  二维 DP 思路 --------------------------------------------------
 *
 * 思路: LeetCode 718 直接复制过来的
 *
 * 和 LINT_079_LongestCommonSubstring 完全一样的思路, 具体见 Tushar 的分析
 *
 *   https://www.youtube.com/watch?v=BysNXJHzCEs
 *
 * 建一个 dp(m+1, n+1) 二维数组, 第一位分别留给 "", "" 两个空字符串
 * 然后双层循环中
 *  - 仅当当前 A[i] = B[j] 时才 dp(i, j) = 1 + dp(i-1, j-1);
 *  - 否则需要忽略, 因为我们需要的是 Substring (即 **连续的** subsequence), 一旦断了 dp 就需重新计算
 *
 * 另外每次更新 dp(i, j) 时都顺便更新一下当前已知最大长度, 出循环后直接返回即可
 *
 *        a b c d e
 *      0 0 0 0 0 0
 *    a 0 1 0 0 0 0
 *    c 0 0 0 1 0 0
 *    d 0 0 0 0 2 0
 *    f 0 0 0 0 0 0
 *
 * ---------------------------------  一维 DP 思路 --------------------------------------------------
 *
 * 和上面一样的思路, 有两个改动需要注意
 *  1. 内循环的 j = 1..n 须改成 j = n..1, 因为我们在内循环更新的是 dp[j] = 1 + dp[j-1];
 *     若从左向右 dp[j-1] 会首先被更新 (覆写) 掉, 那么 dp[j] 更新后的结果也就出问题了
 *  2. 还需要加一个 else dp[j] = 0; 因为对于一维数组之前的 dp 记录都会被保留下来,
 *     若当前 A[i] ≠ B[j] 则需把 dp[j] 更新回 0
 *
 **********************************  End of Solution 2 思路  **************************************/
    public static List<String> clickSum2(List<List<String>> input) {
        List<String> commonHistory = new ArrayList<>();
        if (input == null || input.size() < 2) return commonHistory;

        List<String> user1History = input.get(0), user2History = input.get(1);

        int m = user1History.size(), n = user2History.size();
        int[][] dp = new int[m+1][n+1];

        int start = 0, maxLen = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (user1History.get(i-1).equals(user2History.get(j-1))) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        start = j - maxLen;  // 注意不需要再 +1, 因为这个是 dp 中的 j,
                    }                        // 相对 s 中的 j 已经多一位了
                }
            }
        }

        for (int i = start; i < start + maxLen; i++) {
            commonHistory.add(user2History.get(i));   // 因为内循环中 start 定义为 j - maxLen, 所以
        }                                             // 这里必须从 user2 收集数据, 而不是 user1,
                                                      // 否则上面需要改为 start = i - maxLen
        System.out.println(commonHistory);
        return commonHistory;
    }

    public static void main(String[] args) {
        clickSum(new ArrayList<List<String>>() {{
            add(Arrays.asList("google.com", "60"));
            add(Arrays.asList("yahoo.com", "50"));
            add(Arrays.asList("sports.yahoo.com", "80"));
        }});

        clickSum2(new ArrayList<List<String>>() {{
            add(Arrays.asList("3234.html", "xys.html", "7hsaa.html"));
            add(Arrays.asList("3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"));
        }});
    }
}
