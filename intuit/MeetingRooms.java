import java.util.*;
import org.junit.*;

public class MeetingRooms {
/***************************************  Solution 1 思路  *****************************************
 *
 * 【基本题】类似LC252 meeting rooms，输入是一个int[][] meetings, int start, int end,
 * 每个数都是时间，13：00 -> 1300， 9：30 -> 930，
 *
 * 看新的meeting 能不能安排到meetings.初始给的intervals可能互相重叠
 * eg: {[1300, 1500], [930, 1200],[830, 845]},
 * 新的meeting[820, 830], return true; [1450, 1500] return false;
 * ------------------------------------------------------------------------------------------------
 * 一开始想复杂了, 其实并没有必要在两个 meetings 之间进行前后对比, 直接一个一个检查是否有重合即可
 * 对于 inputMeeting 和 curMeeting, 判断重合的条件不太好写 (折腾很久都没写对), 不妨写出不重合的条件再取反
 *
 * meeting1   input  meeting2
 * [1,2]      [5,6]  [8,9]
 *
 * 不重合条件: meeting.end < input.start || input.end < meeting.start
 * 所以取反为: meeting.end > input.start && input.end > meeting.start
 *
 **********************************  End of Solution 1 思路  **************************************/
    // [1,3], [5,6], [11,13]
    // [4,5]
    public static boolean canAttendMeetings(int[][] meetings, int[] time) {
        int start = time[0], end = time[1];
        for (int[] meeting : meetings) {
            if (meeting[1] > start && end > meeting[0]) return false;
        }
        return true;
    }
/***************************************  Solution 2_1 思路  ***************************************
 *
 * 【Follow up 1】类似merge interval，唯一的区别是输出，输出空闲的时间段，merge完后，再把两两个之间的空的输出就好，注意要加上0 - 第一个的start time
 *
 **********************************  End of Solution 2_1 思路  ************************************/
    public static List<int[]> freeTimes(int[][] meetings) {
        List<int[]> freeTimes = new LinkedList<>();
        if (meetings == null || meetings.length == 0) return freeTimes;
        Arrays.sort(meetings, (m1, m2) -> m1[0] - m2[0]);

        // 1. merge meeting times and add to list
        int n = meetings.length;
        int start = meetings[0][0], end = meetings[0][1];
        List<int[]> occupiedTimes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] meeting = meetings[i];
            if (end >= meeting[0]) {  // can merge
                end = Math.max(end, meeting[1]); // 因为按照 start 排序, 所以没必要再更新 start 的最小值了
            } else {  // cannot merge, then add the current new big interval to list
                occupiedTimes.add(new int[] {start, end});
                start = meeting[0];
                end = meeting[1];
            }
        }

        occupiedTimes.add(new int[] {start, end});

        // 2. extract free times from meeting time list
        for (int i = 1; i < occupiedTimes.size(); i++) {
            int[] preTime = occupiedTimes.get(i-1);
            int[] curTime = occupiedTimes.get(i);
            freeTimes.add(new int[] {preTime[1], curTime[0]});
        }


        // add [0, x] to list head
        if (freeTimes.get(0)[0] > 0) {
            freeTimes.add(0, new int[] {0, occupiedTimes.get(0)[0]});
        }

        // add [x, 2400] to list tail
        if (freeTimes.get(freeTimes.size()-1)[1] < 2400) {
            freeTimes.add(new int[] {occupiedTimes.get(occupiedTimes.size()-1)[1], 2400});
        }

        return freeTimes;
    }
/***************************************  Solution 2_2 思路  ***************************************
 *
 * 很简单, 每次更新 end 的时候同时计算当前 interval 的 len, 对应更新 maxLen 和 s, e 即可
 *
 **********************************  End of Solution 2_2 思路  ************************************/
    public static int[] longestRange(int[][] meetings) {
        int[] res = new int[2];
        if (meetings == null || meetings.length == 0) return res;
        Arrays.sort(meetings, (m1, m2) -> m1[0] - m2[0]);

        // 1. merge meeting times and add to list
        int n = meetings.length;
        int maxLen = 0;
        int start = meetings[0][0], end = meetings[0][1];
        for (int i = 0; i < n; i++) {
            int[] meeting = meetings[i];
            if (end >= meeting[0]) {  // can merge
                end = Math.max(end, meeting[1]); // 因为按照 start 排序, 所以没必要再更新 start 的最小值了
                int len = end - start;
                if (len > maxLen) {
                    maxLen = len;
                    res[0] = start;
                    res[1] = end;
                }
            } else {  // cannot merge, then re-initialize start and end
                start = meeting[0];
                end = meeting[1];
            }
        }

        return res;
    }

/***************************************  Solution 3 思路  *****************************************
 *
 * 就是 Meeting Rooms II 的扫描线算法. 每次到 timeOverlap 为当前最大的时候就更新一下 result overlap 数组即可
 *
 **********************************  End of Solution 3 思路  **************************************/
    public static int[] longestOverlap(int[][] meetings) {
        int n = meetings.length;
        int[] startTimes = new int[n], endTimes = new int[n];

        for (int i = 0; i < n; i++) {
            startTimes[i] = meetings[i][0];
            endTimes[i]   = meetings[i][1];
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int[] longestOverlap = new int[2];
        int maxMeetings = 0;
        int nMeetings = 0;
        int i = 0, j = 0;
        while (i < n) {
            if (startTimes[i] < endTimes[j]) {
                nMeetings++;
                if (nMeetings > maxMeetings) {
                    maxMeetings = nMeetings;
                    longestOverlap[0] = startTimes[i];
                    longestOverlap[1] = endTimes[j];
                }
                i++;
            } else if (startTimes[i] > endTimes[j]) {
                nMeetings--;
                j++;
            } else {
                i++;
                j++;
            }
        }

        return longestOverlap;
    }
/***************************************  Solution 4 思路  *****************************************
 *
 * TODO: 给会议室分配房间, http://www.1point3acres.com/bbs/thread-444738-1-1.html
 *
 **********************************  End of Solution 4 思路  **************************************/


    public static void main(String[] args) {
        // Q1
        int[][] meetings1 = new int[][] {
                {1,3},
                {5,7},
                {9,11},
                {14,15},
        };
        int[][] meetings2 = new int[][] {
                {1,2},
                {2,4},
                {3,6},
                {5,7},
                {9,11},
                {12,15},
                {13,18},
                {18,19},
        };
        Assert.assertTrue(canAttendMeetings(meetings1, new int[] {3,5}));
        Assert.assertTrue(canAttendMeetings(meetings1, new int[] {8,8}));
        Assert.assertTrue(canAttendMeetings(meetings1, new int[] {12,13}));
        Assert.assertFalse(canAttendMeetings(meetings1, new int[] {2,4}));
        Assert.assertFalse(canAttendMeetings(meetings1, new int[] {2,5}));
        Assert.assertFalse(canAttendMeetings(meetings1, new int[] {2,6}));
        Assert.assertFalse(canAttendMeetings(meetings1, new int[] {10,13}));
        Assert.assertFalse(canAttendMeetings(meetings1, new int[] {13,16}));
        // Q2.1
        for (int[] ft : freeTimes(meetings1)) {
            System.out.println(Arrays.toString(ft));
        }
        System.out.println();
        for (int[] ft : freeTimes(meetings2)) {
            System.out.println(Arrays.toString(ft));
        }
        // Q2.2
        Assert.assertArrayEquals(new int[] {12,19}, longestRange(meetings2));
        System.out.println();
        // Q3
        Assert.assertArrayEquals(new int[] {3,4}, longestOverlap(meetings2));
        Assert.assertArrayEquals(new int[] {2,3}, longestOverlap(new int[][] {
                {1,3},
                {2,4},
                {5,6},
        }));
        Assert.assertArrayEquals(new int[] {6,7}, longestOverlap(new int[][] {
                {1,3},
                {2,4},
                {5,8},
                {6,8},
                {5,7},
        }));
    }
}
