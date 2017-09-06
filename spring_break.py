"""
Author: Bri Miskovitz
3/8/2017
Computer Science I
Lab 6

Description: The user inputs a text file. The file is opened, read, and converted in a class called
                Station with the distance of the station from the start and the name of the station.
                All the Station classes are placed in a list. One of the lines in the text file
                contains a single number and assigns it as global variable for the miles_per_tank.
                Selection sort method is used to sort the list by the distances from the start.
                Then using the miles per tank, the number of stops need to be made is determined
                and the number of stops.  The information that will be outputted is:
                    1. The list of stations with the name and distance in the classes in order
                    2. Miles per tank
                    3. The starting station
                    4. The number of stops needed to be made
                    5. The list of stops needed to be made to reach the destination

"""


from rit_lib import *

miles_per_tank = 0

class Station(struct):
    _slots = ((int, 'distance'), (str, 'name'))


def read_trip(file):
    """
    read_trip reads and open a textfile, globalizes the numerical line
    as the miles per tank, creates a class for each line containing info
    about a station stop, and creates a list containing all the stations,
    then returns the list

    pre-conditions: textfile is determined by user in main and
                    consists of a list of gas station stops and
                    their distance from the start
    post-conditions: list consists of gas stations stops in the form
                     of the class, station, with the name and distance

    read_trip : file -> list
    :param file: a textfile
    :return: a list
    """

    lst = []
    for line in open(file):
        line = line.split()

        if line == '':
            return

        elif len(line) == 1:
            global miles_per_tank
            miles_per_tank = int(line[0])

        else:
            distance = int(line[0])
            name = ''
            for element in range(1, len(line)):
                name += line[element]
                name += ' '

            newStation = Station(distance, name)
            lst += [newStation]

    return lst

def sort_choices(lst):
    """
    sort_choices sorts the list using selection sort
    swap : list -> list

    pre-condition: lst contains a list of distances
    post-condition: returns the list sorted with it starting
                    with the stations closest to the start
                    of trip

    :param lst: a list of numbers
    :return: a sorted list
    """
    lst = selectionSort(lst)
    return lst


def plan_trip(lst, mpt):
    """
    plan_trip uses the miles per tank, the number of stops need to be made is determined
                and the number of stops.  It compares each two adjacent stations' distances,
                and determines if the distance is more than the miles per tank.

    plan_trip : list * integer -> list

    pre-condition: the list consists of the station classes in order and mpt
                   is the the miles_per_tank found when opening textfile
    post-condition: the list of stops of stops needed to be made to fill
                    the tank

    :param lst: a list of values
    :param mpt: miles per tank
    :return: the list that meet a condition within function
    """
    limit = mpt
    choices = lst
    stops = []
    last = lst[0].distance

    for sta in range(1, len(choices)):
        pre = sta - 1
        dist = choices[sta].distance - last
        if dist > limit:
            stops.append(choices[pre])
            last = choices[pre].distance

    return stops

def report_trip(lst):
    """
    report_trip prints out information about the plan for the trip:
        1. Miles per tank
        2. Number of stops that need to be taken
        2. The list of stops

    report_trip : list -> string

    pre-conditions: list contains the stops determined from plan_trip that
                    need to be taken
    post-conditions: first print outputs the global variable miles_per_tank
                     and the last print outputs object descriptions from the
                     class Station

    :param lst: a list
    :return: print statements, lists, and values
    """

    print("Miles per tank:", miles_per_tank)

    if len(lst) == 0:
        print("No stops were made!")
    else:
        print("Number of stops made:", len(lst))
        for i in range(len(lst)):
            print("The stop list:", lst[i].name, lst[i].distance)



def selectionSort(lst):
    """
    selectionSort sorts a list of integers by
    moving them from the sequence they originate in
    into a new sequence, where the smallest numbers
    are placed first, then the largest

    selectionSort : list - > list

    pre-condition:  lst contains a list of distances of
                    stations from the start
    post-condition: returns the list sorted of stations
                    by distance from start

    :param lst: a list of numbers
    :return: the sorted list
    """

    for mark in range(0, len(lst)-1):
        lst = swap(lst, mark, findMinFrom(lst, mark))
    return lst


def findMinFrom(lst, mark):
    """
    findMinFrom finds the smallest distance from the start in the station
                objects in list from an index (mark)

    findMinFrom : list * int - > int

    pre-condition: lst contains a list of gas station stops and their distance
                   from the start and mark is an index in lst
    post-condition: returns the index of the station object with the smallest
                    distance from the start

    :param lst: a list of numbers
    :param mark: index in the list
    :return: index of the smallest number
    """

    index = mark
    min = lst[mark].distance

    for num in range(mark, len(lst)):
        if min > lst[num].distance:
            min = lst[num].distance
            index = num
    return index


def swap(lst, i, j):
    """
    swap swaps elements in a list

    swap : list * int * int -> list

    pre-condition: lst contains a list of gas station stops and their
                   distance from the start define as objects
    post-condition: returns the list with elements i and j swapped

    :param i: an index
    :param j: another index
    :param lst: a list of numbers
    :return: list with swapped elements
    """
    temp = lst[i]
    lst[i] = lst[j]
    lst[j] = temp
    return lst



def main():
    """
    main prompts a text file from user, calls read_trip with the text file
    and assigns it as a list, calls sort_choices with that list and updates
    the list, prints out the list of gas station stops, localizes a global
    variable for the miles per tank, prints out the first gas station, calls
    plan_trip with the list and the now local variable and assigns it as a new
    list, and calls report_trip

    pre-condition: None
    post-condition: miles_per_tank is a global variable that is localized then
                    printed and called with plan_trip

    :return: original and sorted list
    """
    textFile = input("Enter an text file: ")

    lst = read_trip(textFile)
    lst = sort_choices(lst)
    if len(lst) == 0:
        print("There are no gas stations during this trip!")
    else:
        print("List of stations:")
        for index in range(len(lst)):
            print(lst[index])

    mpt = miles_per_tank
    print("Start:", lst[0].name, 'with', mpt, 'mile range')
    LST = plan_trip(lst, mpt)
    report_trip(LST)


main()