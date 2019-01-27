import java.util.*;

public class CourseSchedule {
/***************************************  Solution 1 思路  *****************************************
 *
 * 第一题是给{Student ID, Course}的列表，找出每两名学生之间相同的课, 比如
 *
 * Input:
 * {"58", "A"},
 * {"17", "A"},
 * {"58", "B"},
 * {"94", "C"}.
 * {"94", "B"}
 *
 * Output:
 * [58, 17] : {"A"}
 * [94, 58] : {"B"}
 * [94, 17] : []
 * ------------------------------------------------------------------------------------------------
 *
 * for each student id:
 *   create a set of courses he takes, stored in map<sid, set<course>>
 *
 * loop over the map (n^2)
 *   for each sid pair (set): get their course intersection, and add to map value
 *
 **********************************  End of Solution 1 思路  **************************************/
    public Map<Set<String>, Set<String>> getCommonCourses(String[][] courses) {
        // 1. Create an id->course map from input
        Map<String, Set<String>> idToCourses = new HashMap<>();
        for (String[] pair : courses) {
            String studentId = pair[0], course = pair[1];
            if (!idToCourses.containsKey(studentId)) idToCourses.put(studentId, new HashSet<>());
            idToCourses.get(studentId).add(course);
        }

        // 2. get course intersections for every two student id
        Map<Set<String>, Set<String>> studentPairToCommonCourses = new HashMap<>();
        for (String studentId1 : idToCourses.keySet()) {
            for (String studentId2 : idToCourses.keySet()) {
                if (studentId1.equals(studentId2)) continue;
                Set<String> studentPair = new HashSet<>();
                studentPair.add(studentId1);
                studentPair.add(studentId2);

                if (studentPairToCommonCourses.containsKey(studentPair)) continue;  // {1,2} = {2,1}

                Set<String> commonCourses = getIntersection(idToCourses.get(studentId1),
                                                            idToCourses.get(studentId2));

                studentPairToCommonCourses.put(studentPair, commonCourses);
            }
        }

        return studentPairToCommonCourses;
    }

    private Set<String> getIntersection(Set<String> set1, Set<String> set2) {
        if (set1.size() > set2.size()) return getIntersection(set2, set1);
        Set<String> res = new HashSet<>();
        for (String key1 : set1) {  // iterate over the smaller set
            if (set2.contains(key1)) {
                res.add(key1);
            }
        }
        return res;
    }
/***************************************  Solution 2 思路  *****************************************
 *
 * 第二题：给出一些课程和课程的先修课，每个课程有且只有一门先修课，并且保证学生只有一条path修完所有课程，
 *       求修到一半时的课程名称。例如
 *
 * Input : {{A, B}, {C, D}, {B, C}, {E, F}, {D, E}, {F, G}}
 * Output: D
 * ------------------------------------------------------------------------------------------------
 * 1. build graph & calculate indegree for each node
 * 2. create a q, and add nodes with 0 indegree to q
 * 3. while q not empty:
 *      poll one out & add to res
 *      for each of its neighbors:
 *          decrement its indegree,
 *          add to q if indegree becomes 0
 *
 **********************************  End of Solution 2 思路  **************************************/
    public String getMiddleCourse(String[][] pairs) {
        Map<String, Integer> indegrees = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();
        for (String[] pair : pairs) {
            String course = pair[1], prerequisite = pair[0];
            if (!graph.containsKey(prerequisite)) graph.put(prerequisite, new ArrayList<>());
            graph.get(prerequisite).add(course);

            // Bug1: 下面这个 if 不能漏了, 因为毕竟不是 array 会自动初始化为 0,
            //       若不对 (第一个) 无先修课程的 indegree 初始化为 0 的话, 这个 entry 就不会存在,
            //       从而导致 q 什么也加不进去
            if (!indegrees.containsKey(prerequisite)) indegrees.put(prerequisite, 0);
            indegrees.put(course, indegrees.getOrDefault(course, 0)+1);
        }

        Deque<String> q = new ArrayDeque<>();
        for (String course : indegrees.keySet()) {
            if (indegrees.get(course) == 0) q.add(course);
        }

        String[] res = new String[indegrees.size()];
        int k = 0;
        while (!q.isEmpty()) {
            String course = q.poll();
            res[k++] = course;
            List<String> nextCourses = graph.get(course);
            if (nextCourses == null) continue; // 不加会有 NPE
            for (String nextCourse : nextCourses) {
                int count = indegrees.get(nextCourse) - 1;
                indegrees.put(nextCourse, count);
                if (count == 0) q.add(nextCourse);
            }
        }

        return res[res.length/2];
    }
/***************************************  Solution 3 思路  *****************************************
 *
 * 上一道的扩展，每门课可以依赖好几门也可以被好几门课依赖，输出所有依赖链的中间课程
 * 题都不难，但是我选了C++，本来都写对了，打印函数出了个小问题debug半天
 * ------------------------------------------------------------------------------------------------
 * 给一些<course, course>代表prerequisite，返回每条路径中间的那节课。
 * eg. ("A","B"),("B","D"),("E","B"), ("E","C"),("C","F"),("E","F")
 * 路径为 ABD, EBD, ECF, EF
 * 返回 B,C,E (EF 返回 E 还是 F 和面试官讨论一下)
 *
 **********************************  End of Solution 3 思路  **************************************/
    public Set<String> getMiddleCoursesInAllPaths(String[][] pairs) {
        // 1. build graph
        Map<String, List<String>> graph = new HashMap<>();
        for (String[] pair : pairs) {
            String course = pair[1], prerequisite = pair[0];
            if (!graph.containsKey(prerequisite)) graph.put(prerequisite, new ArrayList<>());
            graph.get(prerequisite).add(course);
        }

        // 2. loop over graph and get all non-root courses
        List<List<String>> paths = new ArrayList<>();
        Set<String> nonRootCourses = new HashSet<>();
        for (String course : graph.keySet()) {
            nonRootCourses.addAll(graph.get(course));
        }

        // 3. dfs on graph and get all paths
        for (String course : graph.keySet()) {
            if (!nonRootCourses.contains(course)) {
                dfs(graph, paths, new ArrayList<>(), course);
            }
        }

        // 4. get all middle courses from all paths
        Set<String> middleCourses = new HashSet<>();
        for (List<String> path : paths) {
            middleCourses.add(path.get(path.size() / 2));
        }

        return middleCourses;
    }

    private void dfs(Map<String, List<String>> graph, List<List<String>> paths,
                     List<String> curPath, String course) {
        curPath.add(course);
        List<String> nextCourses = graph.get(course);
        if (nextCourses == null) {
            paths.add(new ArrayList<>(curPath));  // Bug1: 这里不要直接 return 了, 因为两种情况都需要
        } else {                                  //       在最后 remove 一开始加进来的 course
            for (String nextCourse : nextCourses) {
                dfs(graph, paths, curPath, nextCourse);
            }
        }
        curPath.remove(curPath.size()-1);
    }

    public static void main(String[] args) {
        CourseSchedule obj = new CourseSchedule();

        // Q1
        Map<Set<String>, Set<String>> res = obj.getCommonCourses(new String[][] {
                {"15", "A"},
                {"15", "B"},
                {"15", "E"},
                {"17", "A"},
                {"58", "A"},
                {"58", "B"},
                {"58", "C"},
                {"94", "B"},
                {"94", "C"},
        });
        System.out.println(res);

        // Q2
        String res2 = obj.getMiddleCourse(new String[][] {
                {"A", "B"},
                {"C", "D"},
                {"B", "C"},
                {"E", "F"},
                {"D", "E"},
                {"F", "G"}
        });
        System.out.println(res2);

        // Q3
        Set<String> res3 = obj.getMiddleCoursesInAllPaths(new String[][] {
                {"A","B"},
                {"B","D"},
                {"E","B"},
                {"E","C"},
                {"C","F"},
                {"E","F"},
        });
        System.out.println(res3);
    }
}
