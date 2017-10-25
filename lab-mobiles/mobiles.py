"""
file: mobiles.py
language: python3
author: ben k steele, bks@cs.rit.edu
author: Bri Miskovitz
description: Build mobiles using a tree data structure.
date: 04/2017
purpose: starter code for the tree mobiles lab
"""

from rit_lib import *
from math import floor

############################################################
# structure definitions
############################################################

class Ball(struct):
    """
    class Ball represents a ball of some weight hanging from a cord.
    field description:
    cord: length of the hanging cord in inches
    weight: weight of the ball in ounces (diameter of ball in a drawing)
    # Add size?
    """

    _slots = ((float, 'cord'), (float, 'weight'))


class Rod(struct):
    """
    class Rod represents a horizontal rod part of a mobile with
    a left-side mobile on the end of a left arm of some length,
    and a right-side mobile on the end of a right arm of some length.
    In the middle between the two arms is a cord of some length
    from which the rod instance hangs.
    field description:
    leftmobile: subordinate mobile is a mobile type.
    leftarm: length of the right arm in inches
    cord: length of the hanging cord in inches
    rightarm: length of the right arm in inches
    rightmobile: subordinate mobile is a mobile type.

    An assembled mobile has valid left and right subordinate mobiles;
    an unassembled mobile does not have valid subordinate mobiles.
    # Add size?
    """

    _slots = ((object, 'leftmobile'), (float, 'leftarm'), (float, 'cord'), \
              (float, 'rightarm'), (object, 'rightmobile'))


#########################################################
# Create mobiles from mobile files
#########################################################

def readMobile(file):
    """
    readMobile : OpenFileObject -> Map( Ball | Rod )

    readMobile reads the open file's content and
    builds a mobile 'parts map' from the specification in the file.
    The parts map returned has components for assembling the mobile.
    If the mobile is a simple mobile, the returned value is
    a parts map containing a Ball instance.

    If the mobile is complex, the returned value is a parts list of
    the Rod instance representing the top-most mobile component and
    the other parts.

    The connection point for each part is a string that identifies
    the key name of the part to be attached at that point.

    If there is an error in the mobile specification, then
    return an empty parts map.

    # an example of the file format. 'B10' is key for the 10 oz ball.
    # blank lines and '#' comment lines are permitted.
    B10 40 10

    top B10 240 66 80 B30
    B30 55 30
    """
    partsMap = {}
    for line in file:
        if line.strip() == '' or line[0] == '#':
            pass
        else:

            line = line.split()

            if line[0][0] == 'B':
                partsMap[line[0]] = Ball(float(line[1]), float(line[2]))

            elif line[0][0] == 'R':
                partsMap[line[0]] = Rod(line[1], float(line[2]), float(line[3]), float(line[4]), line[5])

            elif line[0] == "top":
                if line[1][0] == 'R' or line[1][0] == 'B':

                    partsMap[line[0]] = Rod(str(line[1]), float(line[2]), float(line[3]), float(line[4]), str(line[5]))

                else:
                    partsMap[line[0]] = Ball(float(line[1]), float(line[2]))

            else:
                partsMap = {}
                return partsMap

    return partsMap



def constructMobile(partsmap):
    """
    constructMobile : Map( Rod | Ball ) -> Ball | Rod | NoneType

    constructMobile reads the partsmap to put together the
    mobile's components and return a completed mobile object.
    The constructMobile operation 'patches entries' in the partsmap.

    The parts map has the components for assembling the mobile.
    Each Rod in partsmap has a key name of its left and right
    subordinate mobiles.  constructMobile reads the key to
    get the subordinate part and attach it at the slot where
    the key was located within the component.

    The top mounting point of the mobile has key 'top' in partsmap.

    If the completed mobile object is a simple mobile, then
    the top returned value is a Ball instance.
    If the completed mobile is a complex mobile, then
    the top returned value is a Rod instance.

    If the parts map contains no recognizable mobile specification,
    or there is an error in the mobile specification, then
    return None.
    """
    if len(partsmap) == 0:
        return None
    else:
        if isinstance(partsmap['top'], Ball):
            return partsmap['top']
        else:
            lst = partsmap.keys()
            for part in lst:
                if part[0] == 'R' or part == 'top':
                    tempLEFT = partsmap[part].leftmobile
                    tempRIGHT = partsmap[part].rightmobile
                    if isinstance(tempLEFT, str):
                        partsmap[part].leftmobile = partsmap[tempLEFT]
                    if isinstance(tempRIGHT, str):
                        partsmap[part].rightmobile = partsmap[tempRIGHT]
    return partsmap["top"]



############################################################
# mobile analysis functions
############################################################

def is_balanced(theMobile):
    """
    is_balanced : theMobile -> Boolean

    is_balanced is trivially True if theMobile is a simple ball.

    Otherwise theMobile is balanced if the product of the left side
    arm length and the left side is approximately equal to the
    product of the right side arm length and the right side, AND
    both the right and left subordinate mobiles are also balanced.

    The approximation of balance is measured by checking
    that the absolute value of the difference between
    the two products is less than 1.0.

    If theMobile is not valid, then produce an exception
    with the message 'Error: Not a valid mobile\n\t{mobile}',

    pre-conditions: theMobile is a proper mobile instance.
    """

    if isinstance(theMobile, Ball):
        return True
    
    if isinstance(theMobile.leftmobile, Ball):
        if isinstance(theMobile.rightmobile, Ball):
            return floor(theMobile.leftarm * theMobile.leftmobile.weight) \
                   == floor(theMobile.rightarm * theMobile.rightmobile.weight)

        else:
            print(theMobile.leftarm * theMobile.leftmobile.weight)
            print(theMobile.rightarm * weight(theMobile.rightmobile))

            return is_balanced(theMobile.rightmobile) and \
                   floor(theMobile.leftarm * theMobile.leftmobile.weight) \
                   == floor(theMobile.rightarm * weight(theMobile.rightmobile))

    elif isinstance(theMobile.leftmobile, Rod):
        if isinstance(theMobile.rightmobile, Ball):
            return is_balanced(theMobile.leftmobile) and \
                   floor(theMobile.rightarm * theMobile.rightmobile.weight) \
                   == floor(theMobile.leftarm * weight(theMobile.leftmobile))

        else:
            return is_balanced(theMobile.leftmobile) and \
                   is_balanced(theMobile.rightmobile) and \
                   floor(theMobile.leftarm  * weight(theMobile.leftmobile))\
                   == floor(theMobile.rightarm * weight(theMobile.rightmobile))
    
    else:
         raise Exception("Error: Not a valid mobile\n\t" + str(theMobile))


def weight(theMobile):
    """
    weight : theMobile -> Number
    weight of the theMobile is the total weight of all its Balls.

    If theMobile is not valid, then produce an exception
    with the message 'Error: Not a valid mobile\n\t{mobile}',

    pre-conditions: theMobile is a proper mobile instance.
    """

    if isinstance(theMobile, Ball):
        return theMobile.weight
    elif isinstance(theMobile, Rod):
        if isinstance(theMobile.leftmobile, Ball):
            if isinstance(theMobile.rightmobile, Ball):
                return theMobile.leftmobile.weight + theMobile.rightmobile.weight

            else:
                return theMobile.leftmobile.weight + weight(theMobile.rightmobile)
        else:
            if isinstance(theMobile.rightmobile, Ball):
                return weight(theMobile.leftmobile) + theMobile.rightmobile.weight

            else:
                return weight(theMobile.leftmobile) * weight(theMobile.rightmobile)
    else:
        raise Exception("Error: Not a valid mobile\n\t" + str(theMobile))



def height(theMobile):
    """
    height : theMobile -> Number
    height of the theMobile is the height of all tallest side.

    If theMobile is not valid, then produce an exception
    with the message 'Error: Not a valid mobile\n\t{mobile}',

    pre-conditions: theMobile is a proper mobile instance.
    """

    if isinstance(theMobile, Ball):
        return theMobile.cord

    elif isinstance(theMobile, Rod):
        if isinstance(theMobile.leftmobile, Ball):
            if isinstance(theMobile.rightmobile, Ball):
                if theMobile.leftmobile.cord < theMobile.rightmobile.cord:
                    return theMobile.cord + theMobile.rightmobile.cord + theMobile.rightmobile.weight
                else:
                    return theMobile.cord + theMobile.leftmobile.cord + theMobile.leftmobile.weight

            else:
                if theMobile.leftmobile.cord < height(theMobile.rightmobile):
                    return height(theMobile.rightmobile)  + theMobile.cord
                else:
                    return theMobile.leftmobile.cord + theMobile.leftmobile.weight
        else:
            if isinstance(theMobile.rightmobile, Ball):
                if theMobile.rightmobile.cord < height(theMobile.leftmobile):
                    return height(theMobile.leftmobile) + theMobile.cord
                else:
                    return theMobile.rightmobile.cord + theMobile.rightmobile.weight

            else:
                if height(theMobile.leftmobile) < height(theMobile.rightmobile):
                    return height(theMobile.rightmobile) + theMobile.cord
                else:
                    return height(theMobile.leftmobile)  + theMobile.cord
    else:
        raise Exception("Error: Not a valid mobile\n\t" + str(theMobile))




