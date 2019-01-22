#!/bin/python3
"""
Define the mapping relation for symbols & Shades.
For symbol : a - A - @ as in a same group. Same for h, s.
For Shades :  lower, upper or symbol case
"""
symbol_mapping = [{'a', 'A', '@'}, {'h', 'H', '#'}, {'s', 'S', '$'}]
shades_mapping = [{'a', 'h', 's'}, {'A', 'H', 'S'}, {'@', '#', '$'}]

def check_length(attributes):
    """ 
    Check if the given list of element consists only 2 unique keys
    :params: attribues - list of string
    :return: True/False - whether exactly 2 unique key included
    """
    cur_set = set()
    for attribute in attributes:
        cur_set.add(attribute)
    return len(cur_set) == 2

def match_feature(pairs, feature_mapping):
    """ 
    Match the pairs of cards on it's group_id. Based on symbol_mapping or shades_mapping.
    ex. match 'a' -> [{'a', 'A', '@'}, {'h', 'H', '#'}, {'s', 'S', '$'}]
    should get 'a' in the first sets. so the group_id should be '0'.
    :params: pairs - list of cards
    :params: feature_mapping - Either symbol_mapping or shades_mapping.
    :return: match_result - list of int (group_id)
    """
    match_result = []
    for color, feature in pairs:
        for index, group in enumerate(feature_mapping):
            if feature[0] in group:
                match_result.append(index)
                break
    return match_result

def check_valid(card_pairs):
    """ 
    Check if the given pairs of cards can form a valid subset.
    :params: card_pairs - pair of three cards. ex. ['blue', '$$'], ['blue', 'aa'], ['green', 'sss']
    :return: True/False - whether three card can form a valid subset
    """
    aggreg_attributes = list(zip(*card_pairs))

    """
    Extracts attribe from the three cards and reorder.
    ex. code_pairs = ['blue', '$$'], ['blue', 'aa'], ['green', 'sss']
    -> colors = ['blue','blue','green']
    -> symbols = ['$$', 'aa', 'sss']
    -> symbol_lengths = ['2', '2', '3']
    The occurrences should either be all same (len(set) = 1) or all different. (len(set) = 3)
    """
    colors = aggreg_attributes[0]
    symbols = aggreg_attributes[1]
    symbol_lengths = list(map(lambda x: len(x), symbols))

    if check_length(colors): return False
    if check_length(symbol_lengths): return False

    """
    Trys to match the symbol, shade into group_id (index serve as the group_id). 
    Check the occurrences based on the same logic above.
    """

    result = match_feature(card_pairs, shades_mapping)
    if check_length(result): return False
    
    result = match_feature(card_pairs, symbol_mapping)
    if check_length(result): return False

    return True

def recursive_disjoint_set(sets, cur_list = None, list_set = set()):
    """ 
    Recursively combines the subsets. 
    Visits the current set of cards and sees if it can add it to the current set of disjoint sets.
    If there contains no overlap, it will explore the next card (sets[index + 1:]) and assume that the current set of cards is part of the new disjoint set.
    Meanwhile need to update the overall disjoint set of sets at each step.

    :params: sets - the total set of sets of cards
    :params: cur_list - the current disjoint list of sets
    :params: list_set - the disjoint set of sets
    :return: card - generater object on each recursion call
    """
    if cur_list is None:
        cur_list = []
    if cur_list:
        yield cur_list
    for index, cur_set in enumerate(sets):
        union = list_set.intersection(cur_set)
        if not union:
            merge_set = cur_list + [cur_set]
            union_set = list_set | set(cur_set)
            for card in recursive_disjoint_set(sets[index + 1:], merge_set, union_set):
                yield card

def merge_sets_attributes(subsets):
    """ 
    Functions for generating list of card info for recursive_disjoint_set
    :params: subsets - the total set of sets of cards
    :return: merged_sets - list of formated strings for card info
    """
    merged_sets = [''] * len(subsets)
    for i in range(len(subsets)):
        cur = []
        for j in range(3):
            cur.append(' '.join(subsets[i][j][0:2]))
        merged_sets[i] = cur
    return merged_sets