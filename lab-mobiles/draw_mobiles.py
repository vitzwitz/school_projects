"""
file: draw_mobiles.py
description: main program to draw mobiles using turtle
language: python3
author: ben k steele, bks@cs.rit.edu
date: 10/2015
purpose: supplied code for a solution to tree mobiles lab
"""

import tkinter as tk  # to get the screen width and height

import turtle as tt

import mobiles as mob  # this is the student's module

def getScreenInfo():
    """
    getScreenInfo : Void -> Tuple( width, height )
    return the screen dimensions
    """
    root = tk.Tk()
    screen_width = root.winfo_screenwidth()
    screen_height = root.winfo_screenheight()
    return screen_width, screen_height 

#########################################################
# display a mobile
#########################################################

def initCanvas( wide, high ):
    """
    initCanvas : Natural Natural -> NoneType
    initCanvas creates a square canvas with a coordinate system 
    that has the origin at the top-center of the canvas.
    This is suitable for 'hanging' a mobile.

    pre-conditions: wide and high values should be less than screen size.
    post-conditions: size 2 pen is down at origin, facing South.
    """

    margin = 10
    tt.setup( wide, high )
    tt.clear()
    tt.reset()
    tt.setworldcoordinates( -wide / 2, -high, wide / 2, margin )
    tt.right( 90 )
    tt.pensize( 2 )

def drawCord( cordlen ):
    """
    drawCord : Natural -> NoneType
    drawCord draws a cord as a line with the length written
    half-way down the line.

    pre-conditions: turtle pen is down to draw.
    pre-conditions: turtle start position is the 'cord hang point'
                    and orientation heading is South on the canvas.
    post-conditions: turtle end position is the bottom end of the cord.
    """
    tt.forward( cordlen / 2 )
    tt.write( format( cordlen, " .2f" ), False \
            , 'left', ('Arial', 12, 'bold' ) )
    tt.forward( cordlen / 2 )

def drawBall( cordlen, weight ):
    """
    drawBall : Natural Natural -> NoneType
    drawBall draws a Ball as a circle with the weight inside it
    hanging from a cord of cordlen size.

    pre-conditions: turtle pen is down to draw starting with the cord.
    pre-conditions: turtle start position is the 'cord hang point'
                    and orientation heading is South on the canvas.
    post-conditions: turtle end position, orientation match the start.
    """

    drawCord( cordlen )
    tt.write( format( weight, " .2f" ), False \
            , 'left', ('Arial', 12, 'bold' ) )
    tt.right( 90 )
    # weight is represented by the diameter (i.e. half the radius)
    tt.circle( weight / 2 )
    tt.left( 90 )
    tt.forward( - cordlen )


def drawRod( leftmobile, leftarm, cordlen, rightarm, rightmobile ):
    """
    drawRod : Mobile Natural Natural Natural Mobile -> NoneType

    pre-conditions: turtle pen is down to draw starting with the cord.
    pre-conditions: turtle start position is the 'cord hang point'
                    and orientation heading is South on the canvas.
    post-conditions: turtle end position, orientation match the start.
    """

    drawCord( cordlen )
    tt.right( 90 )
    # left side
    tt.forward( leftarm )
    tt.write( format( leftarm, ".2f" ), False \
            , 'center', ('Arial', 12, 'bold' ) )
    tt.left( 90 )
    drawMobile( leftmobile )  # recurse indirectly
    tt.left( 90 )

    # then right side
    tt.forward( leftarm + rightarm )
    tt.write( format( rightarm, ".2f" ), False \
            , 'center', ('Arial', 12, 'bold' ) )
    tt.right( 90 )
    drawMobile( rightmobile )  # recurse indirectly
    tt.left( 90 )
    tt.forward( - rightarm )
    # then back up to the cord hang point of this Rod
    tt.right( 90 )
    tt.forward( - cordlen )


def drawMobile( theMobile ):
    """
    drawMobile : theMobile -> NoneType
    drawMobile draws the mobile structure.
    If any of the components of theMobile is not
    either a Ball or a Rod object, then raise an
    exception with the message 'not a valid mobile {mobile}',
    where {mobile} is the string representation of theMobile.

    pre-conditions: turtle pen is down to draw starting with the cord.
    pre-conditions: turtle start position is the 'cord hang point'
                    and orientation heading is South on the canvas.
    post-conditions: turtle end position, orientation match the start.
    """

    if isinstance( theMobile, mob.Ball ):
        drawBall( theMobile.cord, theMobile.weight )

    elif isinstance( theMobile, mob.Rod ):
        drawRod( theMobile.leftmobile    \
               , theMobile.leftarm       \
               , theMobile.cord          \
               , theMobile.rightarm      \
               , theMobile.rightmobile )

    else:
        raise Exception( "Error: Not a valid mobile\n\t" + \
                         str( theMobile ) )
    return


def width( theMobile ) :
    """
    width : theMobile -> Number
    return the width of the theMobile.
    If the mobile is a simple Ball, then the width is the diameter.
    Remember that the ball's weight also represents its diameter.

    The width of its widest point must take into account 
    the rotation of complex mobiles.
    If the mobile is a Rod with submobiles, then
    the width has special issues because the mobile may
    rotate. The possible rotation may cause the widest side
    to shift from one side of the hang point to another.
    To account for this spinning, this function needs to
    identify the width of the widest side and use that as
    the width of both sides of the mobile.
    """

    return 2 * width_aux( theMobile )
 
def width_aux( theMobile ) :
    """
    width_aux : theMobile -> Number
    return the width of the widest side of theMobile.
    If the mobile is a simple Ball, then the width is the diameter.
    Remember that the ball's weight also represents its diameter.

    If the mobile is a Rod with submobiles, then
    identify the width of the widest side 
    and return the value of the widest.

    If theMobile is not valid, then raise an exception
    with the message 'not a valid mobile {mobile}',

    pre-conditions: theMobile is a proper mobile instance.
    """

    if isinstance( theMobile, mob.Ball ):
        return theMobile.weight

    elif isinstance( theMobile, mob.Rod ):

        lwide = theMobile.leftarm + width_aux( theMobile.leftmobile )
        rwide = theMobile.rightarm + width_aux( theMobile.rightmobile )
        return max( rwide, lwide ) 

    # else unconditionally raise exception for any other case.
    raise Exception( "Error: Not a valid mobile\n\t" + str( theMobile ) )


def processMobileFile( screen_width, screen_height, fname ):
    """
    processMobileFile : Natural Natural String -> NoneType
    processMobileFile processes the mobile found in fname by
    constructing it, and displaying it within the limits of
    a canvas size less than or equal to the screen's width and height.
    Procedure:
        - read the mobile file, which builds the mobile.
        - print whether or not the mobile is balanced.
        - print the weight of the mobile.
        - print the width and height of the mobile.
        - set up the canvas. 
        - display the mobile.
    """

    parts = mob.readMobile( open( fname ) )

    theMobile = mob.constructMobile( parts )

    if isinstance( theMobile, mob.Ball ) \
    or isinstance( theMobile, mob.Rod ):

        print( "file:", fname )
        print( "is balanced? :", mob.is_balanced( theMobile ) ) 
        print( "weight:", mob.weight( theMobile ) ) 
        high = mob.height( theMobile )
        wide = width( theMobile )
        print( 'width X height:', wide, 'X', high )
        # set the canvas size so most of the mobile is visible.
        # Note that mobiles with one very long side arm
        # might not fully display without canvas enlargement.
        cwidth, cheight = ( wide * 1.2, high * 1.2 )
        print( 'canvas size (w x h):', cwidth, 'X', cheight )
        initCanvas( min( cwidth, screen_width ) \
                  , min( cheight, screen_height ) )
        drawMobile( theMobile ) 

    else:
        print( fname, "did not contain a valid mobile" ) 

 
############################################################
# main function
############################################################

def main():
    """
    main : Void -> NoneType
    The main program:
        - prompts for name of file containing one mobile description.
        -- if the name is the empty string, the program ends.
        - processes the mobile file in processMobileFile.
    """

    width, height = getScreenInfo()

    while True:
        fname = input( "Enter a Mobile file name(hit enter to quit): " )

        if fname == "":
            tt.bye()
            break
        processMobileFile( width, height, fname )
        # continue to prompt for a mobiles file
    return

if __name__ == "__main__":
    main()
