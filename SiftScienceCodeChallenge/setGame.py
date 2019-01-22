#!/bin/python3
from itertools import combinations as comb
import coreAlgorithm as algo

def parse_cards(raw_lines):
    """
    Input wrapper for parsing text input file into list of card info.
    :params: raw_lines - String of raw text input
    :return: Parsed result for lines of card info
    """
    raw_lines = raw_lines.split('\n')
    lines = [''] * len(raw_lines)
    for i in range(1, len(lines)):
        lines[i] = raw_lines[i].split(' ')
    return lines

def find_all_sets(cards):
    """
    Function that takes in parsed card info and calculate all the possible subsets
    :params: card - parsed lines of card info from raw txt input
    :return: valid_subsets - list of valid subsets
    """
    card_pairs = parse_cards(cards)
    valid_subsets = []
    for subset in comb(card_pairs[1:], 3):
        if algo.check_valid(subset):
            valid_subsets.append(list(subset))
    return valid_subsets

def find_max_disjoint_set(subsets):
    """
    Function to find the largest disjoint set among all possible sets.
    Recursively checking every possible choise. (combine_recurse)
    :params: subsets - list of valid subsets
    :return: max_disjoint_set - max number of disjoint set
    """
    subsets = algo.merge_sets_attributes(subsets)
    max_disjoint_set = []

    for cur_set in algo.recursive_disjoint_set(subsets):
        if len(cur_set) > len(max_disjoint_set):
            max_disjoint_set = cur_set
    return max_disjoint_set

def print_result(subsets, max_disjoint_set):
    """
    Print result. print wrapper.
    :params: subsets - all subsets
    :params: max_disjoint_set - max number of disjoint set
    :return: 
    """
    print(len(subsets))
    print(len(max_disjoint_set))
    print()
    for card in max_disjoint_set:
        for i in range(3):
            print(card[i])
        print()

def set_game(cards):
    """
    Set Game Wrapper. 
    :params: cards - list of string card info
    :return: 
    """ 
    subsets = find_all_sets(cards)
    max_disjoint_set = find_max_disjoint_set(subsets)  
    print_result(subsets, max_disjoint_set)



