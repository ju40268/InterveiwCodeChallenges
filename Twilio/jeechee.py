"""
Hello Jeechee,

Thanks for taking the time to interview with me! 
I had my first round on Grace Hopper Celebration and second round with Charlie, you are actually my third interviewer.
Already a Twilio service user, and I got to know more about the company on GHC :)

As the conversation, I had previous internship experience using Spark to clean and transform the unstructured data, but not including setting up the whole data pipeline architecture.
I think that's the part I am missing and really hope I could have a chance to join and learn how to build up the infrastructure :)!

Here is the problem we discussed that day.
I tried another different approach without using stack and anaylze the time/space complexity as follow.

Thanks and have a good day,
- Chia-Ju(Chrisite)

"""


answer = [True, False, False, True, False, False, True, False, False, True, False, False, True]
testcases = ["(this should pass)",
   "(this should not pass",
   ") This should not pass",
   "((this should pass) )",
   "(((This should not pass))",
   "(((This should not pass))))",       
   "([this should pass])",
   "(<this should not pass)", 
   ") This should not pass<", 
   "(<(this { should } pass)> )",
   "(<[ this should not pass >])", 
   "( < [ > this should not pass] )"            
   "(((<This should not pass>))))",  
   "(1+3) + ((3-4)/5) * This should pass[0] <1>"]


"""
Solution 1 -- Stack 
whenever it 
Time Complexity : O(n)
Space Complexity : O(n) - extra space for stack

"""
def is_balanced_stack(S):
    if not S: return True
    stack = []
    parenthesis = '(){}[]<>'
    mapping = {
        '(' : ')',
        '{' : '}',
        '[' : ']',
        '<' : '>'
    }
    for s in S:
        if s in mapping:
            stack.append(mapping[s])
        else:
            if s not in parenthesis:
                continue
            if stack == [] or stack.pop() != s:
                return False
    return stack == []
"""
Solution 2 -- Without Stack, Counter for each parenthesis pairs

"""
def is_balanced_counter(S):
    counter = [0] * 4
    idx_ptr = {'(':0, '[':0, '{':0, '<':0}
    left_dict = {'(':0, '[':1, '{':2, '<':3}
    right_dict = {')':0, ']':1, '}':2, '>':3}
    mapping = {')':'(', ']':'[', '}':'{', '>':'<'}
    current, m = -1, 0
    for i, s in zip(range(1, len(S)+1), S):
        if s not in left_dict and s not in right_dict:
            continue
        elif s in left_dict:
            current = left_dict[s]
            counter[current] += 1
            idx_ptr[s] += i
            m = i
        else:
            top = right_dict[s]
            if top != current:
                return False
            else:
                counter[current] -= 1
                idx_ptr[mapping[s]] -= m
                m = -1
                for k, v in idx_ptr.items():
                    if v > m:
                        m = v
                        current = left_dict[k]
    for c in counter:
        if c != 0: return False
    return True

if __name__ == "__main__":
    for line, ans in zip(testcases, answer):
        assert is_balanced_counter(line) == ans
        assert is_balanced_stack(line) == ans

