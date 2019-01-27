"""
Write a function that, given a string (such as 'AABBCCCCCBABAA') returns
the character with the longest consecutive substring. In this case, it is C, because there are 5 Cs in a row, which trump all other characters.
"""

chunk1 = 'AABBCCCCCBABAA'
chunk2 = 'AABBXXXXXBABAAA'

"""
Fast/Slow pointer Solution.
First do string compression. ex. the give chunk1 = 'AABBCCCCCBABAA' -> A2B2C5BABA2 -> {'A' : 2, 'C' : 5, 'B' : 2}
record the max_consecutive_len = 5 -> loop through the dictionary to get the representing char with max_len. -> get 'C'.

Time Complexity : O(n)
Space Complexity : O(m) m for the size of dictionary

[Modification]
Solved the problem for 
1. duplicated max consective length chars (Occurrence breaks tie). ex. AAAAABBBB -> A5B5 -> return ['A', 'B'].
2. all distinct. 3. empty string

"""


def improve_solution(word_stream):
    chars = list(word_stream)
    slow = i = 0
    max_consecutive_len = float('-inf')
    occurence = {}
    while i < len(chars):
        c, length = chars[i], 1
        while (i + 1) < len(chars) and c == chars[i + 1]:
            length, i = length + 1, i + 1
        chars[slow] = c
        if length > 1:
            len_str = str(length)
            chars[slow + 1:slow + 1 + len(len_str)] = len_str
            slow += len(len_str)
            if c not in occurence:
                occurence[c] = length
                if length >= max_consecutive_len:
                    max_consecutive_len = length
            else:
                occurence[c] = max(length, occurence.get(c, 0))
        slow, i = slow + 1, i + 1
    longest_consecutive_char = [k for k, v in occurence.items() if v == max_consecutive_len]
    return (longest_consecutive_char if longest_consecutive_char else chars[:slow])

def interview_solution(s):
    count = 1
    max_len = 1
    result = ""
    for i in range(len(s)-1):
        if s[i] == s[i+1]:
            count += 1
        else:
            count = 1 
        if (count > max_len):
            max_len = count
            result = s[i]     
    return result

if if __name__ == "__main__":
    assert 'C' == interview_solution(chunk1)
    assert ['A', 'C'] == improve_solution('AABBCCCCCBABAAAAA')
    assert ['A'] == improve_solution('A')
    assert [] == improve_solution('')
    assert ['A', 'B', 'C'] == improve_solution('ABC')
    assert ['A'] == improve_solution('AAAAAAAAAA')


# edge cases:
# - empty string
# - only 2 characters
# - in the case of a tie, first one is returned

# chunk1 = 'AABBCCCCCBABAAAAABBXXXXXBABAA' C 5 A 6
# chunk2 = '' X 5

# how to execute this on a very large string?
# -- maybe map reduce?
# -- machine 1 spits out a letter and a count - e.g., C, 3
# -- search for longest on each machine, merge (check head and tail)

    # count = 2
    # max_len = 5
    # "AABBCCCCCBABAAA" c5 head : a2 tail : a3
    

#  label   machine 1 C # count
#  label   machine 2 B # count
    
"""
c5 head : a2 tail : a3 index: 1
c5 head : a4 tail : a4 index: 2  (           )



c5 head : a2 tail : a3 index: 3  (           )
c5 head : a2 tail : a3 index: 4  (           )
c5 head : a2 tail : a3
c5 head : a2 tail : a3
c5 head : a2 tail : a3
c5 head : a2 tail : a3
c5 head : a2 tail : a3
(A, 100), (C, 500)
"""