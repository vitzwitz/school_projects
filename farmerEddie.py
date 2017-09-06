"""
Author: Bri Miskovitz
Computer Science I
Lab 8

farmerEddie implements functions and classes from myList in
  order to play the farmer eddie game.  Main prompts the user
  for a file name and uses its content to create two wheels for
  the game. It prompts the user for a random seed value and plays
  the game.

  To play the game, we spin the first wheel (until it
  hits the switch, then the second wheel spins) and as the wheel
  spins, the needle hits the animals it goes through in the linked
  list. The random seed module is used as a random number
  generator for the spinning of the wheel since what animal the needle
  lands on at the end of the spin is supposed to be by chance. Once an
  animal is landed on three times, the game is over and that animal is
  the winner.  Note: Switch cannot be a winner because it is not an
  animal; it only switches which wheels are being used to play.

  Additionally, if the file contains no animals, the user is informed
  that the game cannot be played.

"""

from rit_lib import *
from myList import *
import random as r


def readFile(fileName):
    """
    readFile reads, opens, and converts a file into two list objects

    pre-conditions: file name contains various animal names as strings
                    with one name per line and each name only exists once,
                    a singular blank line exists before at least two
                    lines (one that contains an animal name and one that
                    contains switch), switch appears once before the blank line
                    and once after, and switches does not appear first or right
                    after the blank line

    post-conditions: Lists each contain unique animal names
                    (No animal names are in the first list if they are
                    in the second and vise versa), and each list contains a switch

    :param fileName: a file name
    :return: tuple of lists
    """

    L1 = createList()
    L2 = createList()

    flag = True

    for line in open(fileName):

        if len(line.strip()) == 0:
            flag = False
        elif flag == True:
            addAnimal(line.strip(), L1)
        else:
            addAnimal(line.strip(), L2)
    return L1, L2


def Game(fileName, intSeed):
    """
    Game plays the Farmer Eddie game. It builds the appropriate lists,
    informs user that the game has begun, and randomly chooses a number of movements,
    moves the cursor that many times,  switches to other list if
    the cursor lands on switch at the end of the Spin, and prints the Spin (movement
    of the cursor during Spin) until an animal appears at the tail of a Spin 3 times.
    Then it prints the name of the animal and that it won!
    It also informs user if the wheels are empty and no possible winner.

    Game : file * int -> string

    pre-condition: file name contains various animal names as strings
                    with one name per line and each name only exists once;
                    intSeed is an integer
    post-condition: If the lists are empty, the user is informed that the
                    wheels are empty! If not, animal that wins has a name to
                    call.

    :param fileName: filename
    :param intSeed: seed
    :return: string regarding the game
    """
    r.seed(intSeed)
    print('')
    print("The game has begun! Wheel is now spinning:")
    print('')
    [L1, L2] = readFile(fileName)

    if L1.size == 0 and L2.size == 0:
        print("These wheels are blank! No one could win! Therefore you can't play.")

    else:
        i = 0
        flag = 1
        while True:

            if flag == 1:
                i += 1
                numMoves = r.randint(2, 2 * L1.size)
                print('Spin', i, ': ', end='')
                move(numMoves, L1)
                data1 = cursorValue(L1)

                if data1[0] == 'Switch':
                    print('----Switching wheels----')
                    flag = 2

                if data1[1] == 3:
                    print('')
                    print(data1[0], 'won!')
                    break

            if flag == 2:
                i += 1
                numMoves = r.randint(2, 2 * L2.size)
                print('Spin', i, ': ', end='')
                move(numMoves, L2)
                data2 = cursorValue(L2)

                if data2[0] == 'Switch':
                    print('----Switching wheels----')
                    flag = 1

                if data2[1] == 3:
                    print('')
                    print(data2[0], 'won!')
                    break

def main():
    """
    main prompts the user for a file name and a seed, calls the random seed module,
    and plays the game by calling the Game function

    main : None -> string

    pre-conditions : Game runs properly
    post-conditions : Game runs properly

    :return: string regarding the game from function Game
    """

    fileName = input("Enter a file name of animals for the wheels: ")
    intSeed = int(input("Enter a seed for the random number generator: "))
    Game(fileName, intSeed)

main()