+ Leetcode 138
+ top K
+ LRU Cache
+ BST iterator Leetcode 173 (有Begin(), ++BSTiter)
  
我先给了一个可以把整个树遍历一下，记下来，然后直接查下一个是谁，然后再用bst本身去找的方案。不够高效。
第二个就是用栈了，记录一下这个点到root的路径，需要回溯到父节点的时候，直接pop一个出来就好了，也是参考答案。
```
Binary search tree (BST)

Sample BST:
             32
            /  \
          16    48

BSTree bst();
BSTIterator bstIt = bst.begin(); //  iterator represents 16
++bstIt; // iterator represents 32
++bstIt; // iterator represents 48

Define the needed data structures and implement begin() and operator++()


#include <iostream>
using namespace std;

struct TreeNode{
  TreeNode* parent;
  TreeNode* left;
  TreeNode* right;
  int val;
  TreeNode(int _v){
    val = _v;
    left = nullptr;
    right = nullptr;
    parent = nullptr;
  }-baidu 1point3acres
};

struct BSTIterator{
  TreeNode* cur;
  BSTIterator(TreeNode* r){
    cur = r;. From 1point 3acres bbs
  }

  BSTIterator& operator++() {
    TreeNode* temp = cur;
    TreeNode* l = nullptr;
    if(temp->right){
       l = temp->right;
       while(l->left) l=l->left;
    }
    else if(temp->parent){
      l = temp->parent;
      while(l->val < cur->val){
        l = l->parent;
      } 
    }

    this->cur = l;
    return *this;
  }

};

// vecotr<int>::iterator it; from begin to end 

class BSTree{
  public:
    BSTree(){}
    BSTIterator begin(){
      // go to the most left element
      TreeNode* cur = root;
      while(cur->left){
        cur = cur->left;
      }
      return BSTIterator(cur);-baidu 1point3acres
    }

    TreeNode* root;
};


int main(){

  TreeNode* root = new TreeNode(32);
  root->left = new TreeNode(16);
  root->left->parent = root;
  root->right = new TreeNode(48);
  root->right->parent = root;

  root->left->right = new TreeNode(24);
  root->left->right->parent = root->left;

  root->right->left = new TreeNode(40);
  root->right->left->parent = root->right;

  BSTree bst;
  bst.root = root;. check 1point3acres for more.

  BSTIterator bIt = bst.begin();

  cout<<bIt.cur->val<<endl;

  ++bIt;

  cout<<bIt.cur->val<<endl;

  ++bIt;

  cout<<bIt.cur->val<<endl;

  ++bIt;

  cout<<bIt.cur->val<<endl;

  ++bIt;

  cout<<bIt.cur->val<<endl;
  return 0;
}
```
+ [Leetcode 409](https://leetcode.com/problems/longest-palindrome/description/)
+ [Leetcode 200](https://leetcode.com/problems/number-of-islands/)
+ 一个老大哥问了个合并区间的变种。变成找最大的合并区间。秒杀，然后follow up改成接口形式的production code。下一步onsite 
+ unival tree   merge k sorted array 
+ 第一道是 热身题 , 问一个 string , output letter frequency in the order of their appearance in the string. 
这个比较简单, 一个 array[string[i] - 'a ']++就可以解决

+ 第二题是递归. 问给定一个string, no space, 和一个 helper function: isWord 供判断是否是认可的单词. 让你拆分这个 string 成为一个 vector<string>
+ Given a input string and int k, find the longest substring with given k unique char
e.g. input = "aabbcccd" , k = 2
result should be "bbccc"
+ 给一个String，每个char是一个数字，再给一个target int，要求切分String，使得切分后组成的数字的和等于target。
比如：
String 1234，
target：46，
那么切分就是12，34。
切分可以使一次，也可以任意次。
给了DFS，比较满意。
+ 第一题 clone graph hashmap+dfs 搞定

+ 第二题：
Divide a list of numbers (no duplicates) into groups of consecutive numbers
Maintain the original order
Example: 8,2,4,7,1,0,3,6; 
Output: [[2,4,1,0,3], [8,7,6]])
lz用的是lc 128 hashmap的方法。。但每次找到一个range后还得用treemap maintain index order。 。。 follow-up是要o(n) time  想了半天没想出来 [Followup](https://www.careercup.com/question?id=65732)
+ Leetcode 原题 行和列排序的矩阵，找给定的数，问了各种解法和变化
+ C++: Implement a template shared_ptr based on reference counting mechanism. You need to implement constructor, destructor, assignment operator, etc
+ 二维board，中间有wall，给起点和终点，问最少需要几步到达。follow up：board特别大怎么处理，怎么记录路径解法：其实他就想问follow up，就是经典的A*搜索，跟他讲了下用heap还有heuristic function是怎么回事，写下伪代码就完事了。记录路径参考word ladder ii。
+ 第一题，题目就是类似google搜索引擎里面输入时候，关于你输入开头的所有内容在你的搜索记录里的要都显示在下面。例如：你输入u，会显示uber, unique, unbelieve。你输入ub时候只会显示uber了。直接tire tree。然后follow up，一个中国人，半天英语说不清楚，也是我理解能力不行，才听明白他的问题。在这得浪费了15分钟。要不早完事了。唉。难为同胞何苦呢。第二题，find peek element lc原题，没有dups
+ 白人小哥，面的是Leetcode原题。 https://leetcode.com/problems/copy-list-with-random-pointer/description/ 
+ Divide a list of numbers (no duplicates) into groups of consecutive numbers.
Maintain the original order
Example: 8,2,4,7,1,0,3,6;
Output: [[2,4,1,0,3], [8,7,6]]) 第三题不难，先用map记录值，index。 再过一次 找到一个value就向下向上找 找到所有相连的，整一个新的数组，只记录对应index的 groupIndex。再过一次 按顺序把elements 加到最后的返回的list。 是O(N)我应该解释了的。 你用一个数组记录组号。比如：
[0,0, 1, 0, 1, 2, 3].
0,1,2,3 是组别，然后再过一次这个数组，根据组别把它们加到对应的组不就行了，这个算法是O(N)的。
[Solution](https://www.careercup.com/question?id=65732)
+ LRU Cache， Find all the bio-direction pairs in a graph( 其实比较容易，DFS一下图，找到所有的双向节点-即A<-->B 这种节点对），在怎么去重这卡了一下，java里map没有以key值对作为key的。后来他提示是把2个key再排序一下，再hash。
+ 一个找链表是否有环，还有一个找离链表末尾第k个元素。
+ 开头五分钟自我介绍，顺便问了一下有没有distributed经验，我说用过spark & aws。
然后做题，很经典的爬楼梯，有多少种方法可以到第n个台阶，dp可解。
然后follow up是print所有的combinations，dp修改一下就可以了。
+ 如果有一个什么都不干的while(true) loop你觉得这个是不是个problem，我说会浪费cpu资源。他说那如果这个thread已经在跑了，你怎么改进他浪费资源这一点，我说可以提高一下cpu的scheduling policy，可能通过assign priority的方式。然后他说如果我们想keep exactly 3 ec2 instances alive,你现在明白这个while loop是干嘛的了吗。我说明白了，就是用来保持instance在线的
+ 第一轮:一个白人小哥，口齿清晰。简单聊了一下直接做题，利口污贰鲃 随机数那题, follow up是问我能不能只用一个循环来做，答曰可以用类似蓄水池抽样的方法，每读一个数就算一下当前的随机数。

+ 第二轮：
一个印度大姐，口齿还算清晰。也是简单聊了一下直接做题。第一题是李口寺鲕柩。第二题是在第一题的基础上做的。利口斯贰鲃的level order的版本，不太一样。
具体是 给你一个 array，array里每个数字代表了当前节点的子节点的个数，然后让你根据这个array 构造出这个树。用BFS就可以做。follow up是能不能优化空间复杂度。
举例：[3,0,0,2,0,0]对应的树应该是
```
   3
 / | \
 0 0 2
    / \
    0  0
```
+ 考官很重视concurrency跟multi-thread
1. 问了一下工作上是怎么运用concurrency的概念 (Java Callable, Runnable etc)
2. 什么是deadlock
3. 使用Kafka时，怎么样可以提高throughput
4. coding: 
implement trackEvent(type, event), lookupEvent(type) 跟 reset()
type跟event的型別不重要，重点是要可以追踪已经存取的event, 我跟考官说在这个case中, type跟event的型別我分別使用int跟String
逻辑不难，但考官会根据你的code问下去，例如说如果同时很多人call lookupEvent(5) 那会有什么潜在的问题?(我: Not thread safe) 如何改善?(我: synchronize/ mutex) 那如果有100万个user call这个function那不就大家卡死了?任何idea来改善？ (我: 不要lock住整个map, 针对type来lock) ,诸如此类
也会针对其他细节发问，例如为什么使用ArrayList而不是LinkedList, 各有哪些优缺点? 如果lookup一个不存在的type, 该回传null还是直接new一个空的list回去? 如果采用后者,然后有user对某个不存在的type大量lookup, 会有哪些side effect? (我: GC负担很重) 那如果一开始就预备一个空的list的instance来应付look up不存在的type的情况，那又会有什么问题? (我: instance的reference很有可能被很多个user拿在手里，thread-safe issue)等等
+ 只面了一轮on campus，45分钟时间，只有一道design的题，不需要写code。大概是要实现一个处理twitter real-time streaming data的web service，使得每次请求都能返回当前1min，1hr，1day内最被tweet次数最多的hashtag，没有什么constraint，也没有什么假设。
目前在等这家的下一个phone interview。

