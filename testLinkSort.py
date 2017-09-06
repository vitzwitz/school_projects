"""
Bri Miskovitz
Computer Science I
Homework 8


testLinkSort tests SlList for various different cases
and sets up the main function so the user can create more
test files. Test functions and main creates list objects using
the slList class
"""

import slList as module


def readFile(fileName):
    """
    readFile
    readFile : file -> object

    pre-conditions : filename exists as text file
    post-conditions : list is built as a slList object

    :param fileName: a file
    :return: a list
    """

    lst = module.createList()
    for line in fileName:
        module.append(lst, line)
    return lst

def main():
    """
    pre-conditions:
        1. fileName is a text file integer values, one per line
        2.

    :return:
    """


    fileName = input("Enter a filename: ")
    lst = readFile(fileName)
    print("The unsorted list:", module.toString(lst))
    lst = module.linkSort(lst)
    print("The sorted list:", module.toString(lst))


def empty():
    """
    empty creates a list object where the list is
    empty, tests linkSort from slLink.py, and prints the
     list before and after it's sorted as a string

    pre-conditions : slLink.py is accessible
     post-conditions : slLink.py is accessible

    :return: list before and after sorted as strings
    """
    LST = []

    lst = module.createList()
    for ele in range(len(LST)):
        module.append(lst, LST[ele])

    print("Tests empty list:")
    print("unsorted: ", module.toString(lst))

    module.linkSort(lst)
    print("sorted:", module.toString(lst))

def singleList():
    """
    singleList creates a list object where there's only
    one element, tests linkSort from slLink.py, and prints the
     list before and after it's sorted as a string

     pre-conditions : slLink.py is accessible
     post-conditions : slLink.py is accessible

    :return: list before and after sorted as strings

     """
    LST = [5]

    lst = module.createList()
    for ele in range(len(LST)):
        module.append(lst, LST[ele])

    print("Tests a list that contains only one element:")
    print("unsorted: ", module.toString(lst))

    module.linkSort(lst)
    print("sorted: ", module.toString(lst))

def opposite():
    """
    opposite creates a list object where the elements
     are in opposite order, tests linkSort from
     slLink.py, and prints the list before and
     after it's sorted as a string

     pre-conditions : slLink.py is accessible
     post-conditions : slLink.py is accessible

    :return: list before and after sorted as strings
    """

    LST = [15, 13, 12, 11, 9, 7, 3, 1, 0]

    lst = module.createList()
    for ele in range(len(LST)):
        module.append(lst, LST[ele])

    print("Tests a list that's in opposite order:")
    print("unsorted: ", module.toString(lst))

    module.linkSort(lst)
    print("sorted:", module.toString(lst))

def orderedEven():
    """
    orderedEven creates a ordered list object contains an even number
    of elements, tests linkSort from slLink.py, and prints the
     list before and after it's sorted as a string

     pre-conditions : slLink.py is accessible
     post-conditions : slLink.py is accessible

    :return: list before and after sorted
        """

    LST = [6, 8, 9, 11, 12, 14, 18, 31]

    lst = module.createList()
    for ele in range(len(LST)):
        module.append(lst, LST[ele])

    print("Tests an ordered list with an even number of elements:")
    print("unsorted: ", module.toString(lst))

    module.linkSort(lst)
    print("sorted: ", module.toString(lst))

def orderedOdd():
    """
    orderedOdd creates a ordered list object contains an odd number
     of elements, tests linkSort from slLink.py, and prints the
     list before and after it's sorted as a string

     pre-conditions : slLink.py is accessible
     post-conditions : slLink.py is accessible

    :return: list before and after sorted
    """

    LST = [5, 6, 8, 9, 11, 12, 14, 18, 31]

    lst = module.createList()
    for ele in range(len(LST)):
        module.append(lst, LST[ele])

    print("Tests an ordered list with an odd number of elements:")
    print("unsorted: ", module.toString(lst))

    module.linkSort(lst)
    print("sorted: ", module.toString(lst))

def sameList():
    """
    wack creates a list object contains equal integers,
     tests linkSort from slLink.py, and prints the
     list before and after it's sorted as a string

     pre-conditions : slLink.py is accessible
     post-conditions : slLink.py is accessible

    :return: list before and after sorted
    """
    LST = [5, 5, 5, 5, 5, 5]
    lst = module.createList()

    for ele in range(len(LST)):
        module.append(lst, LST[ele])

    print("Tests a list that contains equal integers:")
    print("unsorted: ", module.toString(lst))
    module.linkSort(lst)
    print("sorted:", module.toString(lst))

def wack():
    """
    wack creates a unordered list object, tests linkSort
     from slLink.py, and prints the list before and after
      it's sorted as a string

     pre-conditions : slLink.py is accessible
     post-conditions : slLink.py is accessible

    :return: list before and after sorted
    """
    LST = [3, 9, 4, 1, 5, 7, 2, 10, 4, 6]
    lst = module.createList()

    for ele in range(len(LST)):
        module.append(lst, LST[ele])

    print("Tests an unordered list:")
    print("unsorted: ", module.toString(lst))
#    print(module.findMinFrom(lst.head))
#    print(module.findMinFrom(lst.head.next))

    module.linkSort(lst)
    print("sorted: ", module.toString(lst))

def TestorMain():
    """
    TestorMain allows user to choose if it wants to run test
    cases or call main and make its own

    pre-condition: test functions and main function exist
    post-conditions : slLink.py is accessible

    :return: list before and after sorted
    """
    print("Would you like to run the test functions already created or run your own?")
    val = int(input("Enter 1 for the first option and 2 for the latter: "))

    if val == 2:
        main()

    else:
        empty()
        singleList()
        opposite()
        orderedEven()
        orderedOdd()
        sameList()
        wack()

TestorMain()
