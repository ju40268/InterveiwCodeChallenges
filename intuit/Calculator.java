import java.util.*;
import org.junit.*;

public class Calculator {
/***************************************  Solution 1 思路  *****************************************
 *
 * 【基本题】basic calculator，没有括号只有加减的。 給你一個string例如"2+3-999"回傳計算結果int。
 *
 **********************************  End of Solution 1 思路  **************************************/
    public static int calculate1(String s) {
        int n = s.length();
        int sign = 1, res = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                int num = ch - '0';
                while (i+1 < n && Character.isDigit(s.charAt(i+1))) {
                    num = num * 10 + s.charAt(i+1) - '0';
                    i++;
                }
                res += num * sign;
            } else {
                sign = ch == '+' ? 1 : -1;
            }
        }
        return res;
    }
/***************************************  Solution 2 思路  *****************************************
 *
 * 【Follow up 1】加上括号，例如"2+((8+2)+(3-999))"一樣回傳計算結果
 *  类似LC224 不過input的字串不會有空白或是特殊符號, 可以省一些處理
 * -----------------------------------------------------------
 *  - "+/-": update sign
 *  - num: add to res with sign considered
 *  - (  : add res & sign to stack, and reinit them
 *  - )  : res = s.pop + s.pop*res
 *
 *  1+2-(1-2)-(1+2)
 *
 **********************************  End of Solution 2 思路  **************************************/
    public static int calculate2(String s) {
        int n = s.length(), sign = 1, res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                int num = ch - '0';
                while (i+1 < n && Character.isDigit(s.charAt(i+1))) {
                    num = num * 10 + s.charAt(i+1) - '0';
                    i++;
                }
                res += sign * num;
            } else if (ch == '(') {
                stack.push(sign);
                stack.push(res);
                res = 0;
                sign = 1;
            } else if (ch == ')') {
                res = stack.pop() + stack.pop() * res;
            } else {
                sign = ch == '+' ? 1 : -1;
            }
        }

        return res;
    }
/***************************************  Solution 3 思路  *****************************************
 *
 * 【Follow up 2】在【Follow up 1】基础上加了变量名。给你一个map比如{'a':1, 'b':2, 'c':3}，
 * 假设输入为"a+b+c+1"输出要是7，如果有未定义的变量，比如"a+b+c+1+d"输出就是7+d
 * ------------------------------------------------------------------
 *  - "+/-": update sign
 *  - num: add to res with sign considered
 *  - (  : add res & sign to stack, and reinit them
 *  - )  : res = s.pop + s.pop*res
 *  - var: get var & add to varSB with sign
 *
 * 一个字符串的符号由两个因素决定，一个是它前面的operator，另一个情况是如果在括号里，还和括号前的符号有关
 * 我是维护一个叫mark的变量，每次遇到括号就mark *= op（加号是1，减号是-1），那么字符串前面的符号就是mark*op
 *
 * Ref:
 *  - http://www.1point3acres.com/bbs/thread-445171-1-1.html
 *  - https://leetcode.com/problems/basic-calculator/discuss/62430/Java-solutionStack
 *
 **********************************  End of Solution 3 思路  **************************************/
    public static String calculate3(String s, Map<String, Integer> map) {
        int n = s.length(), sign = 1, res = 0;

        StringBuilder variables = new StringBuilder();

        Stack<Integer> stack = new Stack<>();
        stack.push(sign);

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == ' ') continue;
            if (Character.isDigit(ch)) {
                int num = ch - '0';
                while (i+1 < n && Character.isDigit(s.charAt(i+1))) {
                    num = num * 10 + s.charAt(i+1) - '0';
                    i++;
                }
                res += sign * num;
            } else if (ch == '(') {
                stack.push(sign);
            } else if (ch == ')') {
                stack.pop();
            } else if (ch == '+' || ch == '-') {
                sign = ch == '+' ? stack.peek() : stack.peek() * -1;
            } else { // ch is letter
                int start = i;
                while (i+1 < n && Character.isLetter(s.charAt(i+1))) i++;
                String variable = s.substring(start, i+1);

                if (map.containsKey(variable)) {
                    res += map.get(variable) * sign;
                } else {
                    variables.append(sign == 1 ? "+" : "-");
                    variables.append(variable);
                }
            }
        }

        return res + variables.toString();
    }

    public static void main(String[] args) {
        // Q1
        Assert.assertEquals(2, calculate1("1+1"));
        Assert.assertEquals(35, calculate1("12+23"));
        Assert.assertEquals(11, calculate1("-12+23"));
        Assert.assertEquals(-22, calculate1("-10-12"));
        Assert.assertEquals(-98, calculate1("0-1+2-3+4-100"));
        // Q2
        Assert.assertEquals(9, calculate2("1+2-((2-3)-(1-2)-2-4)"));
        Assert.assertEquals(-48, calculate2("13+2-((2-31)-(13-212)-103-4)"));
        //Q3
        Assert.assertEquals("7-d", calculate3("a+b+c+1-d", new HashMap<String, Integer>() {{
            put("a", 1);
            put("b", 2);
            put("c", 3);
        }}));

        Map<String, Integer> e = new HashMap<String, Integer>() {{
            put("e", 1);
            put("temperature", 12);
        }};

        Assert.assertEquals("5-pressure", calculate3("e - 8 + temperature - pressure", e));
        Assert.assertEquals("-19+pressure", calculate3("e - 8 - (temperature - pressure)", e));
        Assert.assertEquals("-19-pressure", calculate3("e - (8 + pressure) - temperature ", e));
        Assert.assertEquals("5", calculate3("-( 1+ ( 4  + 5+2)- 3) + ( 6+8)", e));
        Assert.assertEquals("-11-d+pressure", calculate3("e - d - (temperature - pressure)", e));


        Assert.assertEquals("-6+a-b+d-c-e-f", calculate3("-(1-a-(-b-1+d-(3+c)-2-e)+f)-g+2", new HashMap<String, Integer>() {{
            put("g", 1);
        }}));
    }
}
