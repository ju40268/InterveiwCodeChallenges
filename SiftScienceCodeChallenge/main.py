import os, sys
from setGame import set_game

"""
Set Game Implementation 
* Displays the maximum number of sets formed from the input
* Displays the max size of disjoint set
* Displays the each possible set

@ Author : Christie Chen
"""

def main():   
    if os.isatty(0):
        print("*** [Error] : False Command Line Input. ***")
        sys.exit("*** Valid Input Format: cat yourInputFile.txt | python main.py ***\n")
    else:
        data = sys.stdin.read()
        set_game(data)
if __name__ == "__main__":
    main()