"""
Author: Bri Miskovitz
Computer Science I
Lab 8

myList uses rit_lib to create a Node, Animal, and Lst class and creates an interface
for the game Farmer Eddie. This interface includes:
    - Creating an empty list
    - Adding elements to the list, updating the size, and linking the list appropriately
    - Moves the cursor and updating the count that the cursor lands on lastly
    - Retrieves the value of the item at the cursor

"""


from rit_lib import *


class Animal(struct):
    """
    Animal is a class that has a count as an integer and a name
    as a string
    """
    _slots = ((int, 'count'), (str, 'name'))

class Node(struct):
    """
    Node is class that has data as an object and its link
    as NoneType
    """
    _slots = ( (object, 'data'), ((NoneType, 'Node'), 'next') )

class Lst(struct):
    """
    Lst is a class that has a size as an integer and a cursor as a Node and NoneType
    """
    _slots = ((int, 'size'), ((NoneType, Node), 'cursor'))



def createList():
    """
    createList creates an empty list

    createList : None -> object

    pre-conditions: None
    post-conditions: Node, Count, and Lst classes exist

    createList : None -> object
    :return: list object
    """
    return Lst(0, None)

def addAnimal(animNm, lst):
    """
    adds animal as an node into the list

    addAnimal : int * list -> None

    pre-condition: list received; able to take non-empty & empty list
    post-condition: list contains new items

    :param animNm: string of animal Name
    :param lst: list
    :return: None
    """

    if lst.size == 0:
        animal = Animal(0, animNm)
        lst.cursor = Node(animal, None)
        lst.cursor.next = lst.cursor
        lst.size += 1
    else:
        animal = Animal(0, animNm)
        newNode = Node(animal, lst.cursor.next)
        lst.cursor.next = newNode
        lst.size += 1

def cursorValue(lst):
    """
    cursorValue retrieves value of cursor

    cursorValue : list -> tuple

    pre-condition: lst is non-empty
    post-condition: returns the animal name and count
                    for cursor in the list

    :param lst: list
    :return: tuple
    """

    return lst.cursor.data.name, lst.cursor.data.count

def move(numMoves, lst):
    """
    moves the cursor numMoves times and adds to the counter that
    the cursor lands on the numMoves-th time (if it is an animal
    and not a switch)

    move : int * object -> None

    pre-conditions: numMoves is random # moves that the cursor
                    will move; list is non-empty
    post-conditions: None

    :param numMoves: number times to move cursor
    :param lst: list
    :return: None
    """
    j = 0

    while j <= numMoves:
        [NAME, COUNT] = cursorValue(lst)
        if j < numMoves:
            print(NAME, end=' -> ')
            lst.cursor = lst.cursor.next
            j += 1
        elif j == numMoves:
            print(NAME)
            j += 1
    [NAME, COUNT] = cursorValue(lst)
    if NAME != 'Switch':
        lst.cursor.data.count += 1