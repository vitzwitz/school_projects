"""
Bri Miskovitz
Computer Science I
Homework 9

rainbowTable.py exists as an interface for producing and using rainbow
tables that reads in a list of passwords from a file and generates hashes
for each possible password.  The info is stored into a hash table where the
has is used as a key and the value at that hash is the password

"""
from rit_lib import *
import hashlib


class RainbowTable(struct):
    """
    RainbowTable creates a class with a filename, dictionary, number of hashes found,
    and number of hashes
    """
    _slots = ((object, 'filename'), (dict, 'table'), (int, 'numFound'), (int, 'numHashes'))


def buildTable(filename):
    """
    buildTable builds a rainbow table using the filename. It's opened
    and each line is encoded into hashes, and those hashes and lines are
    added into a dictionary.  Then a rainbow table is returned with the
    original filename, the dictionary, 0, and the length of the table

    buildTable : string -> object

    :param str: string
    :return: rainbow table
    """
    table = {}
    for password in open(filename):

        password = password.strip('\n')
        hash = hashlib.md5(password.encode()).hexdigest()

        table[hash] = password
    return RainbowTable(filename, table, 0, len(table))


def crackHash(rnbwTable, hash):
    """
    crackHash sees if the rainbow table contains the hash given

    crackHash : object * string -> tuple

    :param rnbwTable: rainbow table object
    :param hash: hash
    :return: tuple of boolean and password or Not Found
    """
    if rnbwTable.table.get(hash) != None:

        password = rnbwTable.table.get(hash)
        rnbwTable.numFound += 1
        return (True, password)

    else:
        return (False, "Not Found")

def crackFile(rnbwTable, fileName):
    """
    crackFile creates a dictionary of the hashes found
    inside of the rainbow table and a list of the hashes
    that are not found in the rainbow table

    crackFile : object * string -> tuple

    :param rnbwTable: rainbow table
    :param fileName: filename as a string
    :return: tuple of dictionary and list
    """

    notFoundLst = []
    table = {}
    for line in open(fileName):
        line = line.strip('\n')
        data = crackHash(rnbwTable, line)
        if data[0] == True:
            table[line] = data[1]
        else:
            notFoundLst += [line]
    return (rnbwTable.table, notFoundLst)


def compareTables(rnbwTblOne, rnbwTblTwo):
    """
    compareTable looks through two rainbow tables and counts how
    many hashes it shares

    compareTables : object * object -> int

    :param rnbwTblOne: a rainbow table
    :param rnbwTblTwo: another rainbow table
    :return: number of hashes shared
    """

    numShared = 0
    if rnbwTblOne.numHashes == 0 or rnbwTblTwo.numHashes == 0:
        return numShared


    elif rnbwTblOne.numHashes > rnbwTblTwo.numHashes:
        for key in rnbwTblTwo.table:
            if rnbwTblTwo.table.get(key) != None and rnbwTblOne.table.get(key) == rnbwTblTwo.table.get(key):
                numShared += 1
        return numShared

    else:
        for key in rnbwTblOne.table:
            if rnbwTblOne.table.get(key) != None and rnbwTblOne.table(key) == rnbwTblTwo.table(key):
                numShared += 1
        return numShared
