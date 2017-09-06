"""
Bri Miskovitz
Computer Science
Homework 10

expTree creates, computes, and prints expressions using binary expression trees
"""

from rit_lib import *

# ***********************************#
#               Classes              #
# ***********************************#

class Num(struct):
    """
    Num is a number tree class with just an integer
    """
    _slots = (int, 'number')
class Add(struct):
    """
    Add is a class with an empty tuple
    """
    _slots = ()
class Mult(struct):
    """
    Mult is a class with an empty tuple
    """
    _slots = ()


class BinaryOperationTree(struct):
    """
    BinaryOperationTree is tree with two sub-expression trees as trees
        and an operation as classes Add or Mult
    """
    _slots = (((Num, 'BinaryOperationTree'), "left"), \
             ((Add, Mult), "operation"), \
             ((Num, 'BinaryOperationTree'), "right"))




# ***********************************#
#              Functions             #
# ***********************************#

def createSum(varA, varB):
    """
    createProd creates a binary expression tree class with sum as its operator
    :param varA: number tree, Binary expression tree class
    :param varB: number tree, Binary expression tree class
    :return: Binary expression tree class
    """
    return BinaryOperationTree(varA, Add(), varB)

def createProd(varA, varB):
    """
    createProd creates a binary expression tree class with multiplication as its operator
    :param varA: number tree, Binary expression tree class
    :param varB: number tree, Binary expression tree class
    :return: Binary expression tree class
    """
    return BinaryOperationTree(varA, Mult(), varB)


def interp(expTree):
    """
    interp recursively computes the expression tree's value

    pre-c: expTree is a binary expression tree class with two of the following options:
            1. A number tree, where the number is an integer type
            2. Another binary expression tree

    :param expTree: binary expression tree
    :return: solution to expression
    """
    if isinstance(expTree.left, Num):
        if isinstance(expTree.right, Num):
            if isinstance(expTree.operation, Add):
                #print(str(expTree.left.number), "+", str(expTree.right.number))
                return expTree.left.number + expTree.right.number
            else:
                #print(str(expTree.left.number) , "*" , str(expTree.right.number))
                return expTree.left.number * expTree.right.number

        else:
            if isinstance(expTree.operation, Add):
                #print(str(expTree.left.number) ,"+", str(interp(expTree.right)))
                return expTree.left.number + interp(expTree.right)
            else:
                #print(str(expTree.left.number), "*", str(interp(expTree.right)))
                return expTree.left.number * interp(expTree.right)
    else:
        if isinstance(expTree.right, Num):
            if isinstance(expTree.operation, Add):
               # print(str(interp(expTree.left)) , "+", str(expTree.right.number))
                return interp(expTree.left) + expTree.right.number
            else:
                #print(str(interp(expTree.left)), "*", str(expTree.right.number))
                return interp(expTree.left) * expTree.right.number

        else:
            if isinstance(expTree.operation, Add):
                #print(str(interp(expTree.left)), "+", str(interp(expTree.right)))
                return interp(expTree.left) + interp(expTree.right)
            else:
                #print(str(interp(expTree.left)), "*", str(interp(expTree.right)))
                return interp(expTree.left) * interp(expTree.right)




def expToString(expTree):
    """
    expToString recursively formulates the tree as a legible expression

    pre-c: expTree is a binary expression tree class with two of the following options:
            1. A number tree, where the number is an integer type
            2. Another binary expression tree

    :param expTree: binary expression tree
    :return: expression as a string
    """
    if isinstance(expTree.left, Num):
        if isinstance(expTree.right, Num):
            if isinstance(expTree.operation, Add):
                return '(' + str(expTree.left.number) + '+' + str(expTree.right.number) + ')'
            else:
                return '(' + str(expTree.left.number) + '*' + str(expTree.right.number) + ')'

        else:
            if isinstance(expTree.operation, Add):
                return str(expTree.left.number) + '+' + expToString(expTree.right)
            else:
                return str(expTree.left.number) + '*' + expToString(expTree.right)
    else:
        if isinstance(expTree.right, Num):
            if isinstance(expTree.operation, Add):
                return expToString(expTree.left) + '+' + str(expTree.right.number)
            else:
                return expToString(expTree.left) + '*' + str(expTree.right.number)

        else:
            if isinstance(expTree.operation, Add):
                return expToString(expTree.left) + '+' + expToString(expTree.right)
            else:
                return expToString(expTree.left) + '*' + expToString(expTree.right)



            # ***********************************#
#                Tests               #
# ***********************************#

def test_interp():
    """
    test_interp tests interp
    :return: test cases and info regarding
    """

    print('TESTS INTERP')
    print('')
    print('Tests negative numbers in addition expression:')
    tree = createSum(Num(2), Num(-3))
    print('Is supposed to be:', -1)
    print(interp(tree))

    print('')
    print('Tests negative numbers muliplication expression:')
    tree = createProd(Num(-2), Num(3))
    print('Is supposed to be:', -6)
    print(interp(tree))


    print('')
    print('Tests zero in addition expression:')
    tree = createSum(Num(2), Num(0))
    print('Is supposed to be:', 2)
    print(interp(tree))

    print('')
    print('Tests zero in muliplication expression:')
    tree = createProd(Num(0), Num(3))
    print('Is supposed to be:', 0)
    print(interp(tree))

    print('')
    print("Tests when first sub-expression is an expression tree:")
    tree = createProd(createProd(Num(5), Num(7)), Num(2))
    print('Is supposed to be:', 70)
    print(interp(tree))

    print('')
    print("Tests when both sub-expressions are expression trees:")
    tree = createProd(createProd(Num(5), Num(7)), createSum(Num(3), Num(6)))
    print('Is supposed to be:', 315)
    print(interp(tree))

    print('')
    print('Test something crazy on the left:')
    tree = createProd(createProd(Num(9), createSum(Num(3), Num(4))), Num(7))
    print('Is supposed to be:', 441)
    print(interp(tree))

    print('')
    print('Test something crazy on the right:')
    tree = createProd(Num(7), createProd(Num(9), createSum(Num(3), Num(4))))
    print('Is supposed to be:', 441)
    print(interp(tree))

    print('')
    print('Test something NUTS')
    tree = createProd(createSum(createProd(Num(5), Num(4)), createProd(Num(1), Num(3))), createSum(Num(5), createSum(Num(8), Num(9))))
    print('Is supposed to be:', 506)
    print(interp(tree))
    print('')



def test_expToString():
    """
    test_expToString tests expToString
    :return: Test cases and info regarding
    """
    print('TESTS ExpToString')
    print('Tests negative numbers in addition expression:')
    tree = createSum(Num(2), Num(-3))
    print(expToString(tree))

    print('')
    print('Tests negative numbers muliplication expression:')
    tree = createProd(Num(-2), Num(3))
    print(expToString(tree))

    print('')
    print('Tests zero in addition expression:')
    tree = createSum(Num(2), Num(0))
    print(expToString(tree))

    print('')
    print('Tests zero in muliplication expression:')
    tree = createProd(Num(0), Num(3))
    print(expToString(tree))

    print('')
    print("Tests when first sub-expression is an expression tree:")
    tree = createProd( createProd(Num(5), Num(7)), Num(2))
    print(expToString(tree))

    print('')
    print("Tests when both sub-expressions are expression trees:")
    tree = createProd( createProd(Num(5), Num(7)), createSum(Num(3), Num(6)) )
    print(expToString(tree))

    print('')
    print('Test something crazy on the left:')
    tree = createProd(createProd(Num(9), createSum(Num(3), Num(4))), Num(7))
    print(expToString(tree))

    print('')
    print('Test something crazy on the right:')
    tree = createProd(Num(7), createProd(Num(9), createSum(Num(3), Num(4))))
    print(expToString(tree))

    print('')
    print('Test something NUTS')
    tree = createProd(createProd(createSum(Num(5), Num(4)), createProd(Num(1), Num(3)) ), createSum(Num(5), createSum(Num(8), Num(9))))
    print(expToString(tree))

# ***********************************#
#                 Main               #
# ***********************************#

def main():
    """
    main runs test cases for interp and expToString
    :return:
    """
    test_interp()
    print('')
    test_expToString()



if __name__ == "__main__" :
    main()
    # put your test function calls here in this if block