import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {
    /*
     * Complete the eval function below.
     */
    /* global index for recording string */
    static int i = 0;
    static int nextNum(String s) {
        int num = 0;
        int sign = 1;
        /* passing the whitespace */
        while (i < s.length() && s.charAt(i) == ' ')
            i++;
        /* if currently its negative number */
        if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        }
        /* calculate actual number */
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            num = num * 10 + (s.charAt(i++) - '0');
        }
        /* passing the whitespace */
        while (i < s.length() && s.charAt(i) == ' ')
            i++;
        return sign * num;
    }
    static int eval(String s) {
        /*
         * Write your code here.
         */
        /**
        * Using stack to record all the numbers, and nextNum to parse the negative number
        **/
        Stack<Integer> stack = new Stack<>();
        /* corner case for first number */
        stack.push(nextNum(s));

        while (i < s.length()) {
            char c = s.charAt(i++);
            if (c == '+')
                stack.push(nextNum(s));
            else if (c == '-')
                stack.push(-nextNum(s));
            else if (c == '*')
                stack.push(stack.pop() * nextNum(s));
            else if (c == '/')
                stack.push(stack.pop() / nextNum(s));
        }

        int expression = 0;
        while (!stack.isEmpty())
            expression += stack.pop();
        return expression;
    }

    private static final Scanner scanner = new Scanner(System.in);