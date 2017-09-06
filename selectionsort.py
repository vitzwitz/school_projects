"""
Bri Miskovitz
CS I
Homework 5


SOLUTIONS TO QUESTIONS:

1.  A list that's in order, i.e. {5, 10, 15, 20, 25, 30, 35, 40, 50}

2.  Solving for time complexity of selectionSort:

    As stated in the lecture, the function insertion_sort's best scenario has a time
    complexity of O(N) and its worst scenario is O(N^2).  The selectionSort function's
    iterates N-1 x T_findMinFrom(N) times, so the time complexity can be found by
    analyzing findMinFrom.  The function findMinFrom uses a for loop to iterate from
    the index in the iteration within the selectionSort function to the length of the
    list.  So the total number of iterations for findMinFrom function is
    N + N - 1 + N - 2 +...+ 2 + 1 regardless of the list.  Therefore, the time complexity
    for selectionSort is always O(N^2), and it's worst than insertion_sort's function's
    best scenario.

    Short version of solution:

    While the time complexity of insertion_sort for the list is O(N), the
    time complexity of selectionSort, for the same list, is O(N^2).  Therefore,
    insertion_sort performs this test better than selectionSort.

"""


def selectionSort(list):
    """
    selectionSort sorts a list of integers by
    moving them from the sequence they originate in
    into a new sequence, where the smallest numbers
    are placed first, then the largest
    :param list: a list of numbers
    :return: the sorted list
    """

    for mark in range(len(list) - 1):
        swap(list, mark, findMinFrom(list, mark))

    return list


def findMinFrom(lst, mark):
    """
    findMinFrom finds the minimum value in list from an index (mark)
    :param list: a list of numbers
    :param mark: index in the list
    :return: index of the smallest number
    """

    index = mark
    min = lst[mark]

    for num in range(mark, len(lst)):
        if min > lst[num]:
            min = lst[num]
            index = num
    return index


def swap(list, i, j):
    """
    swap swaps elements in a list
    :param i: an index
    :param j: another index
    :param list: a list of numbers
    :return: list with swapped elements
    """
    temp = list[i]
    list[i] = list[j]
    list[j] = temp
    return list


def main():
    """
    main prompts a text file from user,
     opens, reads, and converts file into list of numbers,
     and outputs original list and sorted list,
     where the function PerkSort sorts it

    :return: original and sorted list
    """
    textFile = input("Enter an text file: ")
    list = []
    for line in open(textFile/textFile):
        n = int(line.strip())
        list += [n]
    print("initial list =", list)
    print("sorted list by selection sort =", selectionSort(list))


main()