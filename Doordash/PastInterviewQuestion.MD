# 1p3acres
+ 第一轮：
此帖的第一题以及第二题follow up 
https://www.1point3acres.com/bbs/thread-458401-1-1.html
要求必须要用iteration去写, 并且要用到n-1的资讯。那位国人兄没有固定输入输出， 比如说 a -> b, b -> c, c->d, d->e, e->c 等等。Leetcode 22 解法3

+ 第二轮：
是connect 4
已经有写好的function了, 但需要实现其中还未完成的
例如update board, check valid move, check last move win or not
写完后就结束了并没有像其他人有说要删除或加入机器人的玩法 [Tutorial Link](https://www.youtube.com/watch?v=UYgyRArKDEs)


+ 第一轮engineering value, 和一位非常senior 的 ABC, 非常友善
有一个问题我没答好，就是问你 你有任何做过的project 有哪里是失败的地方？但你是采取什么途径去解决这问题？
另一个问题：  你有什么哪个project你有非常strong opinion 你认为是对的，但别人不认同。你如何解决？ 
+ 第二轮技术面试问题和一位友善的意大利人，
有关于 batch delivery:
P1, D1, P2, D2 ==> valid,
D1, P1 ==> invalid.
P1, P2, P3, D3, D2, D1 ==> valid,
P1, P2, D2 ===> invalid

+ 第三轮比较友善的国人面试，dependency resolver.
拓展： 如何只 print circular dependency. 

+ 第一轮coding主要是改代码， 一个小游戏叫connect 4. 从input row, col, 改成只需要input col, 自动补全没有填满的row

+ 利口 269, 面试的时候写出来了，
但是最后hr feedback因为这一轮不够strong挂了， hr简略的说了下feedback是edge case没有说完，
可能是面试的时候没有口头上说出来input empty什么的情况？我当时只有口头上说了闭环return none这种，
我感觉可能上一轮connect 4也没有答得特别好？可能两轮都一般就挂了。

+ Merge sort/ Bucket Sort
    + a = [ 4, 64, 200, 42, 56, 22, 1, 64, 0, 161 ]
What you know about this array is
Values are integers
Values are non-negative and less than some number, for example:
0 <= value <= 200 
Q: What sorting algo do you know? What is time complexity?
A : quick sort, merge sort, insertion sort ... bla bla
Q: pros and cons for quick sort and merge sort
Q: Besdies those above sorting algo, what other ways you can sort?
A: (心里有点蒙了，难道要我当场发明一个全新的sorting algo? ) 我"假装"不知道counting sort , 演了一下，
Q: okay implemet it
我觉得interviewer一定知道，很多人都能马上码出quick sort / merge sort
整个问答的目的只是希望能够看你能否相互分析，并且写出你刚刚的分析 
  + 我说用quick sort，面试官说行。写完就是design，就让设计了table和写一个简单的query。面完还有15分钟，本来应该是75分钟。挂了，说是算法不是最优解。面完还有十五分钟呢，面试官也没说不是最优解啊，我写的时候面试官也没说quick sort不行，真是搞不懂了。大家还是用counting sort吧。其实题目里的数组很小，insertion sort什么的应该也行。事后觉得面试官可能是想让sort题目里出现的那个数组，不是一个general算法，大家有机会面到了最好问清楚。

+ Design tweeter
tweets -> followers -> user
and 伪代码读取news feed

+ Count K steps
https://www.1point3acres.com/bbs/thread-464234-1-1.html

```
/*
def count_paths(dims, start, target, K):
"""
@param dims, a tuple (width, height) of the dimensions of the board
@param start, a tuple (x, y) of the king's starting coordinate
@param target, a tuple (x, y) of the king's destination
@param K, number of steps
 
@return the number of distinct paths there are for a king in chess (can move one square vertically, horizontally, or diagonally)
to move from the start to target coordinates on the given board in K moves
"""
raise NotImplementedError
 
a b c
d e f
g h i
 
if __name__ == "__main__":
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
```

+ 利口 429 - 
题目不难,太紧张了想成了序列化和反序列化.写着写着就崩溃啦.
建议大家把各种树的操作就写熟练不要背鸡精. 因为其他树的问题也是从这些基本的操作导出来的.
Binary Tee pre/in/post/level
N-nary Tree pre/post/level
之后问了一个设计题, 问假设爬虫了很多html网页, 如何找出所有电话号码. 很open的题目. 比如如果pre处理,如何extract 电话号码,如何存储结果.

        from collections import deque
        class Solution(object):
            def levelOrder(self, root):
                """
                :type root: Node
                :rtype: List[List[int]]
                """
                if not root: return []
                res = []
                q = [root]
                
                while q:
                    level = []
                    for i in range(len(q)):
                        node = q.pop(0)
                        level.append(node.val)
                        if node and node.children:
                            for n in node.children:
                                q.append(n)
                    res.append(level)
                    
                return res
        

+ 吆泤邻
+ 两小时开会软件背靠背,一个烙印一个韩国小姐姐, 都是工作两年的, 第一个题, connect4, 没有让重写, 而是让优化代码, 着重写了行列处理, 判断是否valid的几个function, 时间比较赶. 韩国小姐姐来了一个lc42 雨水堆积的问题, 和一道follow up, 基本上42会做就能做出来, 这个时间相对宽松,最后十分钟聊了一下组里做什么的, 没有BQ, 一开始简单介绍了自己的背景就直接上代码了
+ Connect4
    + HashMap : key是p, value是d, 出错情况，有p无d，d比p早，p重复出现等
    + 面我的是一个韩国小姐姐，很可爱很和蔼，题目没有之前面经里的复杂，不用简化行和列的输入，主要完成判断输赢的方法，但是会先贴进来一堆已有的代码，然后把空缺的地方进行填补。也没什么诀窍，重在沟通吧。有面经的题目应该不存在完全不会的，所以就看怎么样能和面试官讲的清楚聊的开心。然后也要注重体现逻辑和思考能力，因为是ood的题目，需要分析和探讨的也比较多，要让面试官觉得你是一个有编程逻辑且思考全面的人。
    + 着重写了行列处理, 判断是否valid的几个function, connect4这道题, 我自己也实现了一下, 结果面试的时候发现并没有什么卵用, 可以帮你梳理一下思路, 但是他会一上来给你一个三百行的代码, 写的还特别的乱, 都是strong coupling的代码, 很多class, 让你修改部分代码来完成他要求的某个功能, 比如他一开始的输入是行和列, 然后需要你把它改成只输入列就能工作的. 整个代码都比较乱,用了不少奇怪的变量名和打印方式.  他的代码直接就能运行, 没有让额外写逻辑, 而是就他现有的逻辑来改, 所以快速理清这几百行代码就比较重要了. 
    + 第一个是 把input改成只需要输入列数（因为connect4的棋子是会掉下去的嘛，所以行数可以求出来）
    第二个是 把 checkwinner完成了
    第三个是 增加一个可以跟AI对战的feature，AI随便弄，我就直接弄了个最蠢的随机下子的AI。然后没时间了 
    + 是一个ood design的题，是一个游戏叫做connect 4，google一下游戏规则就出来了。
    题目框架她已经给你写好了，只需要填一些函数。。我感觉难点主要在于，读懂整个框架。。一下子刷刷给你好几个class，要弄懂各个class之间的关系，还是有些心累的。。最难的一个函数就是checkwin，建议大家先事先写一写这个函数。横竖检查的时候都很简单，两个斜线的检查写起来还是略麻烦的。。。总之我写的磕磕绊绊，最后在规定时间内勉强写完，还没来得及debug，就到时间了。。
    + 从input row, col, 改成只需要input col, 自动补全没有填满的row
        + 第1轮coding还不是很传统的算法面试。
        题目是给了一堆已经成型的code，内容是connect4这么个游戏。
        然后让你增加新的feature。做完一个就提一个新的feature加上去。
        我做了3个
        第一个是 把input改成只需要输入列数（因为connect4的棋子是会掉下去的嘛，所以行数可以求出来）. check 1point3acres for more.
        第二个是 把 checkwinner完成了
        第三个是 增加一个可以跟AI对战的feature，AI随便弄，我就直接弄了个最蠢的随机下子的AI。然后没时间了
    + Sample Code
        ```
        def checkOWin(board):
    
        boardHeight = len(board)
        boardWidth = len(board[0])
        tile = 'O'
        # check horizontal spaces
        for y in range(boardHeight):
            for x in range(boardWidth - 3):
                if board[x][y] == tile and board[x+1][y] == tile and board[x+2][y] == tile and board[x+3][y] == tile:
                    return True
    
        # check vertical spaces
        for x in range(boardWidth):
            for y in range(boardHeight - 3):
                if board[x][y] == tile and board[x][y+1] == tile and board[x][y+2] == tile and board[x][y+3] == tile:
                    return True
    
        # check / diagonal spaces
        for x in range(boardWidth - 3):
            for y in range(3, boardHeight):
                if board[x][y] == tile and board[x+1][y-1] == tile and board[x+2][y-2] == tile and board[x+3][y-3] == tile:
                    return True
    
        # check \ diagonal spaces
        for x in range(boardWidth - 3):
            for y in range(boardHeight - 3):
                if board[x][y] == tile and board[x+1][y+1] == tile and board[x+2][y+2] == tile and board[x+3][y+3] == tile:
                    return True
    
        return False
        ``` 

+ Leetcode 69，follow up 是给k 找k decimal points 的解。
```
class Solution(object):
    def mySqrt(self, x):
        """
        :type x: int
        :rtype: int
        """
        l, r = 0, x + 1
        while l < r:
            m = (r - l) // 2 + l
            if m * m > x:
                r = m
            else:
                l = m + 1
        return l - 1
```
With Decimal
```
public double sqrt(double x) {
        double l = 0;
        double r = Math.max(x, 1.0);
        double eps = 1e-12;
        
        while (l + eps < r) {
            double mid = l + (r - l) / 2;
            if (mid * mid < x) {
                l = mid;
            } else {
                r = mid;
            }
        }
        
        return l;
    }
```
+ Dependency Solver
题目是问, 要安装library列表里面的所有library. 然后这些library对应的依赖关系在dependency列表里. 箭头前的依赖箭头后的(可能依赖多个)
这些依赖可能还存在更深的依赖关系. 必须装了对应的依赖才能装对应的library. 无关的依赖的安装顺序没有要求. 但是要按顺序装library里面的.
所以, 以上述输入为例.
一来先装z, 发现z无依赖, 直接安装. 然后安装a, 发现a依赖于b和d, 因此要先安装b和d. 但是发现b和d又各有依赖, 因此去安装对应的依赖
```
dependency resolver

input:
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


(possible) output:
['z', 'g', 'f', 'c', 'b', 'e', 'd', 'a']


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
    for course_name, indegree in degree.items():
        if indegree == 0: 
            queue.append(course_name)
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


```


+ 那个给出数字1,2,3,4,5之类的面筋题, 问一共有多少种合理的pick up 和 deliver的序列的题目, 要求用[迭代]的方式做, 不要递归的解法. 
```
n = 1, only 1 possible, p1 d1
n = 2,  6 possible 
p1 d1 p2 d2
p1 p2 d1 d2
p1 p2 d2 d1
p2 p1 d1 d2
p2 p1 d2 d1
p2 d2 p1 d1
总之就是要对于每一个物品, pick up 一定要在 deliver之前. 具体哪个物体先哪个后无所有.
我记得n = 4有84种, n = 5有2520种

def possile(l, all_possile):
    rest = list(set(all_possile) - set(l))
    tmp = []
    for r in rest:
        if 'p' in r:
            tmp.append(l+[r])
        if 'd' in r:
            idx = r[1]
            pick = 'p' + idx
            if pick not in rest:
                tmp.append(l+[r])
    return tmp


def interview(n):
    all_possile = []
    for i in range(1, n+1):
        all_possile.append("p{}".format(str(i)))
        all_possile.append("d{}".format(str(i)))

    q = []
    for i in range(1, n+1):
        q.append(["p{}".format(str(i))])
    res = []
    while q:
        l = q.pop(0)
        if len(l) == 2*n:
            res.append(l)
        else:
            q.extend(possile(l, all_possile))
    print(res)
    print(len(res))
    
if __name__ == '__main__':
    interview(2)
```

+ 系统设计 聊天系统 要求实现1v1 聊天功能和群聊功能 设计schema 呀 存贮呀 pull/push等讨论

+ 是一个游戏的问题 叫connect4 美国人好像都知道， 可以去搜一下怎么玩。这道题是一个在笔记本上写代码的题，很多已经实现好了 要求实现其中的怎么判断游戏有没有结束的一个function。然后是添加一个robot功能，这样player和robot对局，然后要给这个robot设计strategy，不能让他太傻。感觉重点不是策略的算法 而是整个的设计 怎么设计借口 类 考虑到可以继续扩展的 很有意思的一个题目

+ Connect 4 with edge cases.
+ Pick up and delivery valid and permutation.
```
P1 D1-Valid
P1 P2 D2 D1- Valid
P1 D1 P1 D1- invalid
P1-invalid...等等等等
再就是给你N个id，让你output所有可能permutation，注意了不能用recursion。。。他说完题我leetcode里backtracking就跳出来了，结果不能用。。。。。。。。
然后勉强做完了，又来了个followup，topological sort时间太紧没做完
```

+ 系统设计是设计一个donation系统。
比如要搞个3天的charity活动，user可以进行donation。如何实现charge 钱， email 收据，最后summary 这几个功能。

+ 排序
+ 然后是 设计 推特数据库，设计完顺便叫你搜索一下
当前用户 和 所有 他 粉了的用户的帖子
也是经典题
+ int［］  排序 
就是一个数组  然后给排个排序

+ 第一轮： 
you are given two sets of intervals, one for Doordash, the other for restaurant ,you want to find if they all open intervals


// example：
// D = [(9, 12), (14, 17), (21, 23)]
// R = [(8, 10), (11, 22)]

// = > [(9, 10), (11, 12), (14, 17), (21, 22)]     

允许自己定义interval class 


+ 第二轮： 
写一个写一个browserHistory class，里面有很多个方法需要补全，void goToLink(String s),  boolean Forward(), boolean back(), gotolink代表你要打开输入的link，
forward代表浏览器里面向前，可以向前的话返回true，不可以的话返回false, back()和forward()同理,
就是和你浏览器打开网页，有向前前后操作一样，这题你就把每次gotoLink输入的string存下来就行。需要考虑a-b-c-d如果current网页是b，这时候运行goToLink(e)，就变成了a-b-e。补充一下browser history那道题，还需要实现jumpForward(int n) 和jumpBack(int n)这两个方法，分别是实现向前跳n步，和向后跳n步。


+ Given an array of integers, assuming the integers are within a limit, let's say (0<=n<=200)
Return an array with sorted integers.
我当时给的解答是：直接create一个counter，记录integer出现的次数，按照出现次数，构建新的排序的array，返回
这题我反思了一下，我觉得应该多问一clarification question：
    1. ascending or descending
    2. which one do you like to optimize in terms of complexity? Space OR Time
面试官问了我关于hashmap（dict）复杂度的问题，我当时简单的回答了O(1) for time, O(n) for space。
    这个回答过于简单，反思后我觉得应该能够针对hashtable的不同method去分析avrage case & worst case。

+ 简单设计twitter的database model，focus在于follower, followee, tweet的功能
    - 写出table name，有哪些column，哪一个是primary，foreign key，等等. From 1point 3acres bbs
  最终目的是根据你的table scheme，写出一段sql，返一个对于特定user，他所follow的所有人的tweets

+ 第一轮是abc小哥哥，先是聊了一些以前实习经历。然后给了一些unit test的码，根据那个码implement一些function，大概是浏览器go to a new page, go back和go forward，每次返回网址。可以跑test看过没过。这轮test都过了，聊了一下，小哥哥表示公司气氛很好，比起大厂更喜欢这里。第二轮是韩国小姐姐，是地里之前说的connect 4，有预先写好的代码，有几个function需要根据写好的代码填完，比如说根据最新的move（Move是个它写好的class）判断是否有哪个player赢了。时间挺紧的，刚好写完，跑了个横着的4个的没有问题，但是其他肯定有bug,也没来得及改。这个过程中除了问点问题之外，全忙着写，和小姐姐没什么交流，这样不好不好。。。

+ 跟地里提过的题完全不一样。。。第一轮coding是个国人小哥，给了很多unit test，和一个class的interface（解释了几个function需要干什么），然后去implement一个class，大概是讲一个database，可更新一些key和value的值，可以revert一些更新，然后可以commit，commit之后就不能revert了。我太关注test了，只想着怎么过test，却没有想到test可能是只考虑一个很片面的情况，忽略了整个class应该用什么方式来实现。所以大家一定要好好读各个function到底要求干什么，不要过分依靠给的test。由于理解错了整道题的意思，完全血崩，跟小哥交流也是鸡同鸭讲，小哥应该挺无语的。
第二轮engineering value是个国人姐姐，就是一些很常见的behavior question，每个问题要求有具体的例子。

+ 
1. Best time buy stock I 
2. 设计 Twitter database schema，how to query your home page post. 
3. dependence & connect4
system design 也是简单的payment design 表单，提交transaction 支付 记录user order payment

+ 从起点走到终点给K个steps， 问有多少种distinct paths，可上下左右对角
+ 最大岛屿问题，不是1， 0. 而是任意数 (LC numbers of island but not just 0 and 1. it could be from 0 - 9, 求最大岛屿面积, 
所有的都是岛，相同的数group在一起算一个岛)
+ 第一轮是一个韩国妹子，题目地里之前也有人发过。。。我再说详细一点吧。
是一个ood design的题，是一个游戏叫做connect 4，google一下游戏规则就出来了。
题目框架她已经给你写好了，只需要填一些函数。。我感觉难点主要在于，读懂整个框架。。一下子刷刷给你好几个class，要弄懂各个class之间的关系，还是有些心累的。。
最难的一个函数就是checkwin，建议大家先事先写一写这个函数。横竖检查的时候都很简单，两个斜线的检查写起来还是略麻烦的。。。
总之我写的磕磕绊绊，最后在规定时间内勉强写完，还没来得及debug，就到时间了。。第二轮是一个国人，人挺好的，给的题也很放水。。写了一个unique path，和一个sqrt()，要求precision到0.001。。这一轮我进行的非常顺利。。聊的也挺开心的。

+ 第一轮： 系统设计， 设计airbnb database. 就是设计schema， tables，有哪些column，，，感觉面试官有点严厉。。。（原来以为会让设计 doordash api）第二轮: white board coding, 类似leetcode 的word break， 不同之处是返回max length, 比如 dict [fire, man, fir , em, an], input: fireman, 
有两种可能 [fire, man ]  以及[fir, em, an] 返回3
第三轮： 午饭 
第四轮： sublime coding, spiral matrix

+ 第一轮：
1.给定数组，求longest consecutive subarray
例如：输入｛1，4，5，6，8，10，11｝，输出｛4，5，6｝，这个用dp可解
2.follow up 例子：输入｛1，4，2，5，3，9，10｝，输出｛1，2，3｝
要求o(nlogn)解法，leetcode上有原题
第二轮：
1.给定长m,宽n，的格子，每次只能向下或向右走，问从最左上角走到最右下角，有多少种方法？dp可解
2.实现sqrt，不同的是给定精度和target，返回一个精度范围内的值即可

+ 给一个BST的root，和两个node，求两个node的path (LCA做)
+ ，就是permutation，不过不是数字 是character， abc这样的，输出abc， acb，bac，bca，cab，cba。还有一个也是原题，add binary。一个小时结束了，过了8天左右，才收到HR邮件，说下一轮就是两个video。前天第一轮video，两轮两小时，美国小哥和美国小姐姐，题目也很简单，小姐姐的是spiral matrix，小哥的是找interval 的公共区间

+ 2. sqrt() 变形
返回int， 但是跟leetcode 不同的是要返回平方后大于等于输入值的结果 比如输入15的话要返回4, 而不是3

follow-up:  输入 输出都是double/float 型， 用牛顿法解决


【今天Skype 两轮视频面】

1. 第一轮

```
/**
   * @param matrix, a 2-d array (list of lists) of integers
   * @return the size of the largest contiguous block (horizontally/vertically connected) of numbers with the same value
  
     int expected1 = 2;
    int [][] matrix1 = new int[][]
                  {{1,2,3},
                   {4,1,6},
                   {4,5,1}};
   
    int expected2 = 4;
    int [][] matrix2 = new int[][]
                  {{1,1,1,2,4},
                   {5,1,5,3,1},
                   {3,4,2,1,1}};
   
    int expected3 = 11;
    int [][] matrix3 = new int[][]
                  {{3,3,3,3,3,1},
                   {3,4,4,4,3,4},
                   {2,4,3,3,3,4},
                   {2,4,4,4,4,4}};
   
    int expected4 = 24;
    int [][] matrix4 = new int[][]{
                     {0,0,0,0,0},
                     {0,0,0,0,0},
                     {0,0,1,0,0},
                     {0,0,0,0,0},
                     {0,0,0,0,0}};
. 1point3acres
**/
```

+ 2. 第二轮
    + 1. 类似于find target in rotated array, 不过是找的rotated的index在哪儿
    + 2. unique path I && II
+ 第一面问了Word break变形 输出有效break的可能里最多的break次数  比如词是“abbc”，字典是"a", "b", "bc", "ab", 有两种可能 一种是a, b, bc, 一种ab, bc，要输出3
然后转天就来2面了 第二面聊了很多简历上的事 感觉之前实习做的事还挺冷门的但是面试官表示他以前也有这方面的经验所以很感兴趣…问的超细致 最后时间不多了他说就写个sqrt吧，也是返回int但是跟lc不同的是要返回平方后大于等于输入值的结果 比如输入15的话要返回4，lc我记得是返回3
用二分写 写的倒是挺快的但是就在check mid >= n / mid时忘记了那个除是向下round的…于是有时就off by 1

+ 最长连续序列。 (exactly one larger than the one that came before it)
ex: 1, 2, 5, 6 -> 1, 2
ex: 3, 5, 6, 7, 12 -> 5, 6, 7
follow up 变形：(exactly one larger than some number that came before it)
ex: 1, 6, 2, 7, 3 -> 1, 2, 3
ex: 3, 5, 6, 7, 12 -> 5, 6, 7

+ 第一题找map中连续相同数字最多是多少，我用boolean【】【】判断是否visited，follow up就是看如何缩小这个space，第二题是unique path，follow up也是缩小space complexity
+ 先聊聊resume 然后问了基本的bit manipulations 题 不用写代码 讲讲concept就行 接下来问Leetcode 原题implement sqrt ＋ 一些些小的变化


# Leetcode Tags
+ Stock Buy & Sell
```
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int globalMin = prices[0];
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            globalMin = Math.min(globalMin, prices[i]);
            res = Math.max(res, prices[i] - globalMin);
        }
        return res;
    }
}
```
+ Num of Islands

DFS
```
class Solution {
    public static int numIslands(char[][] grid) {
        int res = 0;
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == '1') {
                    dfs(grid, i, j, m, n);
                    res++;
                }
            }
        }
        return res;
    }

    private static void dfs(char[][] grid, int i, int j, int m, int n) {
        if ( i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0') return;
        grid[i][j] = '0';
        dfs(grid, i+1, j, m, n);
        dfs(grid, i-1, j, m, n);
        dfs(grid, i, j+1, m, n);
        dfs(grid, i, j-1, m, n);
    }
}
```

BFS 
```
class Solution {
    public int numIslands(char[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }
    public void bfs(char[][] grid, int x, int y) {
        grid[x][y]='0';
        int n = grid.length;
        int m = grid[0].length;
        LinkedList<Integer> queue = new LinkedList<Integer>();  
        int code = x * m + y;  
        queue.offer(code);  
        while(!queue.isEmpty()) {  
            code = queue.poll();  
            int i = code / m;  
            int j = code % m;  
            System.out.println("i & j :" + i + ", " + j);
            if(i > 0 && grid[i-1][j]=='1') {  
                queue.offer((i-1)*m+j);  
                grid[i-1][j]='0';  
            }  
            if(i < n-1 && grid[i+1][j]=='1'){  
                queue.offer((i+1)*m+j);  
                grid[i+1][j]='0';  
            }  
            if(j > 0 && grid[i][j-1] == '1') {  
                queue.offer(i*m+j-1);  
                grid[i][j-1]='0';  
            }  
            if(j < m-1 && grid[i][j+1] == '1') {  
                queue.offer(i*m+j+1);  
                grid[i][j+1]='0';  
            } 
        }
    }
}
```
+ Course Schedlue II 
```
public int[] findOrder(int numCourses, int[][] prerequisites) { 
    if (numCourses == 0) return null;
    // Convert graph presentation from edges to indegree of adjacent list.
    int indegree[] = new int[numCourses], order[] = new int[numCourses], index = 0;
    for (int i = 0; i < prerequisites.length; i++) // Indegree - how many prerequisites are needed.
        indegree[prerequisites[i][0]]++;    

    Queue<Integer> queue = new LinkedList<Integer>();
    for (int i = 0; i < numCourses; i++) 
        if (indegree[i] == 0) {
            // Add the course to the order because it has no prerequisites.
            order[index++] = i;
            queue.offer(i);
        }

    // How many courses don't need prerequisites. 
    while (!queue.isEmpty()) {
        int prerequisite = queue.poll(); // Already finished this prerequisite course.
        for (int i = 0; i < prerequisites.length; i++)  {
            if (prerequisites[i][1] == prerequisite) {
                indegree[prerequisites[i][0]]--; 
                if (indegree[prerequisites[i][0]] == 0) {
                    // If indegree is zero, then add the course to the order.
                    order[index++] = prerequisites[i][0];
                    queue.offer(prerequisites[i][0]);
                }
            } 
        }
    }

    return (index == numCourses) ? order : new int[0];
}
```

+ Basic Calculator 
```
class Solution {
       public int calculate(String s) {
        int len;
        if(s==null || (len = s.length())==0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char sign = '+';
        for(int i=0;i<len;i++){
            if(Character.isDigit(s.charAt(i))){
                num = num*10+s.charAt(i)-'0';
            }
            if((!Character.isDigit(s.charAt(i)) &&' '!=s.charAt(i)) || i==len-1){
                if(sign=='-'){
                    stack.push(-num);
                }
                if(sign=='+'){
                    stack.push(num);
                }
                if(sign=='*'){
                    stack.push(stack.pop()*num);
                }
                if(sign=='/'){
                    stack.push(stack.pop()/num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }

        int re = 0;
        for(int i:stack){
            re += i;
        }
        return re;
    }
}
```

+ Max area of island
```
private int[][] directions = {{1, 0},{-1, 0},{0, 1},{0, -1}};
public int maxAreaOfIsland(int[][] grid) {
    if(grid == null || grid.length == 0 || grid[0].length == 0){
        return 0;
    }
    int max = 0;
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    for(int i = 0; i < grid.length; i++){
        for(int j = 0; j < grid[0].length; j++){
            if(grid[i][j] == 1 && !visited[i][j]){
                max = Math.max(max, dfs(grid, i, j, visited));
            } 
        }
    }
    return max;
}
private int dfs(int[][] grid, int x, int y, boolean[][] visited){
    if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || visited[x][y] || grid[x][y] != 1){
        return 0;
    }
    int res = 1;
    visited[x][y] = true;
    for(int[] dir : directions){
        int newX = x + dir[0];
        int newY = y + dir[1];
        res += dfs(grid, newX, newY, visited);
    }
    return res;
}
```
+ Random Pick with Weight
idea : 

Use accumulated freq array to get idx.
w[] = {2,5,3,4} => wsum[] = {2,7,10,14}
then get random val random.nextInt(14)+1, idx is in range [1,14]

idx in [1,2] return 0
idx in [3,7] return 1
idx in [8,10] return 2
idx in [11,14] return 3

```
class Solution {

    Random random;
    int[] wSums;
    
    public Solution(int[] w) {
        this.random = new Random();
        for(int i=1; i<w.length; ++i)
            w[i] += w[i-1];
        this.wSums = w;
    }
    
    public int pickIndex() {
        int len = wSums.length;
        int idx = random.nextInt(wSums[len-1]) + 1;
        int left = 0, right = len - 1;
        // search position 
        while(left < right){
            int mid = left + (right-left)/2;
            if(wSums[mid] == idx)
                return mid;
            else if(wSums[mid] < idx)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}
```

+ Interval List intersections
```
class Solution {
    public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        List<Interval> resList = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.length && j < B.length) { 
            Interval curA = A[i];
            Interval curB = B[j];
            int start = Math.max(curA.start, curB.start);
            int end = Math.min(curA.end, curB.end);
            if (end >= start) { // overlap !
                resList.add(new Interval(start, end));
            }
            if (end == curA.end) i++;
            if (end == curB.end) j++;
        }
        Interval[] res = new Interval[resList.size()];
        for (int k = 0; k < resList.size(); k++) {
            res[k] = resList.get(k);
        }
        return res;
    }
}
```

+ Design Twitter
```
class Twitter {
    class Tweet {
        int time;
        int tweetId;
        public Tweet(int time, int id) {
            this.time = time;
            this.tweetId = id;
        }
    }
    
    Map<Integer, Set<Integer>> userToUser;
    Map<Integer, List<Tweet>> userToTweet;
    int globalTime = 0;
    /** Initialize your data structure here. */
    public Twitter() {
        userToUser = new HashMap<>();
        userToTweet = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!userToTweet.containsKey(userId)) {
            userToTweet.put(userId, new ArrayList<>());
        }
        if (!userToUser.containsKey(userId)) {
            userToUser.put(userId, new HashSet<>());
        }
        Tweet t = new Tweet(globalTime++, tweetId);
        userToTweet.get(userId).add(t);
        userToUser.get(userId).add(userId);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        // // max-heap by tweet post time
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> b.time - a.time);
        Set<Integer> allFollowee;
        if (!userToUser.containsKey(userId)) allFollowee = new HashSet<>();
        else allFollowee = userToUser.get(userId);
        for (Integer e : allFollowee) {
            if (!userToTweet.containsKey(e)) continue;
            for (Tweet t : userToTweet.get(e)) {
                pq.offer(t);
            }
        }        
        int k = 10;
        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty() && (k--) > 0)
            result.add(pq.poll().tweetId);
        return result;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!userToUser.containsKey(followerId)) {
            userToUser.put(followerId, new HashSet<>());
        }
        userToUser.get(followerId).add(followeeId);   
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!userToUser.containsKey(followerId) || followerId == followeeId) return; 
        userToUser.get(followerId).remove(followeeId);
    }
}

```

+ Unique Path
```
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++) dp[i][0] = 1;
        for(int j = 0; j < n; j++) dp[0][j] = 1;
        for(int i = 1;i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
```

+ Unique Path II  - With Obstacles
```
/**
     * obstacleGrid =  0,0,0
     *                 0,1,0
     *                 0,0,0
     *    index of dp 0,1,2,3
     *   first time   0,1,1,1
     *   sec   time   0,1,0,1
     *   third time   0,1,1,2
     *
     *   ******************
     * obstacleGrid =  0,0,0
     *                 0,0,0
     *                 0,0,0
     *    index of dp 0,1,2,3
     *   first time   0,1,1,1
     *   sec   time   0,1,2,3
     *   third time   0,1,3,6
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 0; i < m ; i++) {
            for (int j = 1; j <=n; j++) {
                if(obstacleGrid[i][j-1] == 1){
                    dp[j] = 0;
                }else {
                    dp[j] += dp[j -1];
                }
            }
        }
        return dp[n];
    }
```