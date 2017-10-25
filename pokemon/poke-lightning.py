"""
Author : Bri Miskovitz
Class : Computer Science I
Assignment : Lab 11
"""


from array_heap import *
from rit_lib import *
import random as r

"""
Classes
"""

class Pokemon(struct):
    """
    Pokemon is a pokemon instance that contains a name (as a string)
    and HP and Atk stats (as integers)
    """
    _slots = ((str, 'name'), (int, 'HP'), (int, 'Atk'))

class Player(struct):
    """
    Player is a player instance that contains an ID (as an integer)
    and a deck (as an object)

    pre-condition : deck exists as a valid heap
    """
    _slots = ((int, 'ID'), (object, 'deck'))


"""
Creating Pokedex
"""

def readDex(file):
    """
    readDex read, opens, and converts a file into a list

    readDex : str -> list

    pre-condition : file contains lines each with a name, HP
                    stat, and Atk stat (as integers) that are separated
                    by space (not the lines themselves) and only line
                    that doesn't follow that format can start with
                    the string: 'Pokemon'.

    :param file: file
    :return: list
    """

    pokeDex = []
    for line in open(file):
        line = line.split()

        if line[0] != "Pokemon":
            pokeDex += [Pokemon(line[0], int(line[1]), int(line[2]))]
    return pokeDex


"""
Comparison Functions
"""

def minHP(pokE1, pokE2):
    """
    minHP is comparison function based on minimum HP
    minHP : object*object -> boolean

    pre-condition : pokE1 & pokE2 are pokemon instance with valid HP value
    post-condition : pokE1 & pokE2 used in logic statement are pokemon instance
                    with valid HP value

    :param pokE1: pokemon instance
    :param pokE2: pokemon instance
    :return: boolean
    """
    return pokE1.HP <= pokE2.HP

def maxHP(pokE1, pokE2):
    """
    maxHP is comparison function based on maximum HP
    maxHP : object*object -> boolean

    pre-condition : pokE1 & pokE2 are pokemon instance with valid HP value
    post-condition : pokE1 & pokE2 used in logic statement are pokemon instance
                    with valid HP value
    :param pokE1: pokemon instance
    :param pokE2: pokemon instance
    :return: boolean
    """
    return pokE1.HP >= pokE2.HP

def minATK(pokE1, pokE2):
    """
    minAtk is comparison function based on minimum Atk
    minAtk : object*object -> boolean

    pre-condition : pokE1 & pokE2 are pokemon instance with valid HP value
    post-condition : pokE1 & pokE2 used in logic statement are pokemon instance
                    with valid Atk value

    :param pokE1: pokemon instance
    :param pokE2: pokemon instance
    :return: boolean
    """
    return pokE1.Atk <= pokE2.Atk

def maxAtk(pokE1, pokE2):
    """
    maxAtk is comparison function based on maximum Atk
    maxAtk : object*object -> boolean

    pre-condition : pokE1 & pokE2 are pokemon instance with valid HP value
    post-condition : pokE1 & pokE2 used in logic statement are pokemon instance
                    with valid Atk

    :param pokE1: pokemon instance
    :param pokE2: pokemon instance
    :return: boolean
    """
    return pokE1.Atk >= pokE2.Atk

def minSum(pokE1, pokE2):
    """
    minSum is comparison function based on minimum of sum of HP and Atk
    minSum : object*object -> boolean

    pre-condition : pokE1 & pokE2 are pokemon instance with valid HP value
                    and valid Atk
    post-condition : pokE1 & pokE2 used in logic statement are pokemon instance
                    with valid HP value and valid Atk

    :param pokE1: pokemon instance
    :param pokE2: pokemon instance
    :return: boolean
    """

    return pokE1.HP + pokE1.Atk <= pokE2.HP + pokE1.Atk

def maxSum(pokE1, pokE2):
    """
    maxSum is comparison function based on maximum of sum of HP and Atk
    maxSum : object*object -> boolean

    pre-condition : pokE1 & pokE2 are pokemon instance with valid HP value
    post-condition : pokE1 & pokE2 used in logic statement are pokemon instance
                    with valid HP value and valid Atk

    :param pokE1: pokemon instance
    :param pokE2: pokemon instance
    :return: boolean
    """
    return (pokE1.HP + pokE1.Atk >= pokE2.HP + pokE1.Atk)


"""
Heap related Functions
"""

def createDeck(Seed, pokeDex, strategy, sizeD=15):
    """
    createDeck creates a heap based on a given comparison function,
        given size, and elements choosen randomly from a list using a
        seed

    createDeck : int * list * comparison * int -> heap instance

    pre-condition : strategies function exists, Seed is an integer,
                    pokedex is either empty or contains pokemon instance(s),
                    strategy is a valid comparison function, and size is an
                    integer
    post-condition : returns valid heap instance with given desired size

    :param Seed: integer
    :param pokeDex: list of pokemon instances
    :param strategy: string
    :param sizeD: size of heap
    :return: heap instance
    """
    r.seed(Seed)
    deck = createEmptyHeap(sizeD, strategy)

    if pokeDex == []:
        return deck
    else:

        for _ in range(sizeD):
            pokE = r.randint(0, len(pokeDex)-1)
            pokemon = copyCard(pokeDex[pokE])
            add(deck, pokemon)
        return deck

def copyCard(card):
    """
    copyCard returns a new Pokemon instance with same stats and name

    copyCard : object -> object

    pre-condition : card is a valid pokemon instance (with attack, HP, and name)
    post-condition : returns a valid pokemon instance (with attack, HP, and name)

    :param card: pokemon instance
    :return: pokemon instance
    """
    return Pokemon(card.name, card.HP, card.Atk)


def playGame(player1, player2):
    """
    playGame recursively attempts to destroy two heaps and returns
     the player ID of the player with the last heap standing

    playGame : object * object -> int

    pre-condition : two player instances exist with a player ID and a
                    heap instance (the deck) with pokemon instance(s)
                    with HP and Atk stats and a name
    post-condition : returns player ID of winner (owner of the last
                     non-empty heap left)

    :param player1: player instance
    :param player2: player instance
    :return: player ID

    """

    if player2.deck.size == 0:
        print('Player', player1.ID, 'is the winner!')

    elif player1.deck.size == 0:
        print('Player', player2.ID, 'is the winner!')

    else:

        pokemon1 = removeMin(player1.deck)
        pokemon2 = removeMin(player2.deck)

        print('Player', player1.ID, 'plays', pokemon1.name)
        print('Player', player2.ID, 'plays', pokemon2.name)

        loser, winner = battle(pokemon1, pokemon2)

        if loser == pokemon2:
            pokemon2 = removeMin(player2.deck)
            battle(pokemon2, winner)
            return playGame(player2, player1)

        elif loser == pokemon1:
            pokemon1 = removeMin(player1.deck)
            battle(pokemon1, winner)
            return playGame(player1, player2)


def battle(pokemonATKR, pokemonDFDR):
    """
     battle compares the HP of one pokemon instance to the Atk of
    the other pokemon instance

    battle : object * object - > int

    pre-condition : pokemonATKR is pokemon in play and is a valid pokemon instance
                    pokemonDFDR is pokemon defending pokemonATKR's attack and is
                    a valid pokemon instance

    post-condition : return value is non-negative

    :param pokemonATKR: attacking pokemon instance
    :param pokemonDFDR: defending pokemon instance
    :return: HP of defending pokemon
    """

    print(pokemonATKR.name, 'attacks', pokemonDFDR.name, 'for', pokemonATKR.Atk, 'damage.')
    if pokemonDFDR.HP <= pokemonATKR.Atk:
        print(pokemonDFDR.name, 'fainted.')
        pokemonDFDR.HP = 0
        return pokemonDFDR, pokemonATKR
    else:
        pokemonDFDR.HP = pokemonDFDR.HP - pokemonATKR.Atk
        return battle(pokemonDFDR, pokemonATKR)


def Strategies():
    """
    Strategies creates a dictionary of all strategies
    with letter strings as its keys and comparison functions
    as its values

    Strategies : None -> dictionary

    :return: dictionary of strategies
    """
    strategies = {}

    strategies['A'] = minHP
    strategies['B'] = maxHP
    strategies['C'] = minATK
    strategies['D'] = maxAtk
    strategies['E'] = minSum
    strategies['F'] = minSum
    return strategies


def printDeck(deck):
    """
        printDeck prints the array of the given heap

        printDeck : Heap * NatNum-> NoneType

        pre - condition : heap instance and pokemon instances exist
        post - condition : heap instance and pokemon instances exists

        :param deck: heap instance
        :return: string

    """
    if deck == None:
        return ''
    else:
        x = '\t\t\t'
        count = 0
        for i in range(0, deck.size):

            if count != deck.size - 1:
                x += deck.array[i].name + ', '
                count += 1
            else:
                x += deck.array[i].name
            if count % 5 == 0:
                    x += '\n\t\t\t'
    print(x)

def testDeck():
    """
    testDeck tests the pokedex.txt file, readDex function,
    strategies function, comparison functions, and createDeck
    function, then prints the deck

    ** from in-lab **

    testDeck : None -> string

    post-condition : functions all exist, file exists, array_heap
                    is accessible
    :return: printed heap
    """
    strategies = Strategies()
    pokeDex = readDex('pokedex.txt')
    strat = strategies['B']
    deck = createDeck(1, pokeDex, strat)
    print(deck)

def main():
    """
    main sets up game:
    1. Welcomes players
    2. prompting file for pokemon and creating pokedex list using readDex function
    3. creating dictionary of comparison functions using Strategies function
    4. prompting seed and strategy for player 1
    5. create heap for player 1's deck using createDeck
    6. create player instance for player 1
    7. display player 1's deck
    8. Repeat steps 4 - 7 for player 2
    9. calls playGame and prints winner

    post-conditions : functions exist, valid file and seed are prompted, strategy prompted is
                    either A, B, C, D, E, or F as a string, player, pokemon, and heap classes
                    exist

    :return: info regarding game
    """
    print('Welcome to the Pokemon Lightning Tournament!')
    filename = input('Enter the name of the file containing the Pokemon: ')
    pokedex = readDex(filename)
    strategies = Strategies()

    seed1 = input('Player 1 > Enter a seed to create your deck: ')
    strategy1 = input('Player 1 > Which strategy will your deck use? \n '
                      '\t \t A (min HP), B (Max HP), C (Min ATK), \n '
                      '\t \t D (Max ATK), E (Min BOTH), F (MAX BOTH) \n '
                      '\t \t Enter the order attribute and direction \n  '
                      '\t \t(Ex: A for HP descending): ')
    deck1 = createDeck(seed1, pokedex, strategies[strategy1])
    player1 = Player(1, deck1)
    print("\t\t Your deck contains: ")
    printDeck(deck1)
    seed2 = input('\nPlayer 2 > Enter a seed to create your deck: ')
    strategy2 = input('Player 2 > Which strategy will your deck use? \n '
        '\t \t A (min HP), B (Max HP), C (Min ATK), \n '
        '\t \t D (Max ATK), E (Min BOTH), F (MAX BOTH) \n '
        '\t \t Enter the order attribute and direction \n  '
                      '\t \t(Ex: A for HP descending): ')
    deck2 = createDeck(seed2, pokedex, strategies[strategy2])
    player2 = Player(2, deck2)
    print("Your deck contains: ")
    printDeck(deck2)
    print()
    playGame(player1, player2)


main()
