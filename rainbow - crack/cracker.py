from rainbowTable import *


def main():
    passwords = ['password', 'nikolas', 'ld0jzx2869', 'letmein', 'kawaiisensei@1984', 'flarb1234@', 'nimda',
                 '1ab23cd45ef', 'xkcdquestionablecontent', 'HelloWorld']
    results = ['both', 'rt2', 'rt1', 'both', 'rt1', 'none', 'none', 'none', 'none', 'none']
    rt1 = buildTable("hak5.txt")
    rt2 = buildTable("elitehacker.txt")

    print("Test 1 [compareTables]: " + str(compareTables(rt1, rt2) == 33))
    rt1found, rt1notfound = crackFile(rt1, "hashlst.txt")
    rt2found, rt2notfound = crackFile(rt2, "hashlst.txt")

    for i in range(0, 10):

        hash = hashlib.md5(passwords[i].encode()).hexdigest()
        result = False

        if (results[i]) == 'both' and (hash in rt1found) and (hash in rt2found) and (not hash in rt1notfound) and (
        not hash in rt2notfound):
            result = True
        elif (results[i]) == 'rt1' and (hash in rt1found) and (not hash in rt2found) and (not hash in rt1notfound) and (
            hash in rt2notfound):
            result = True
        elif (results[i]) == 'rt2' and (not hash in rt1found) and (hash in rt2found) and (hash in rt1notfound) and (
        not hash in rt2notfound):
            result = True
        elif (results[i]) == 'none' and (not hash in rt1found) and (not hash in rt2found) and (
            hash in rt1notfound) and (hash in rt2notfound):
            result = True


        print("Test " + str(i + 2) + " [" + passwords[i] + "]: " + str(result))
    print("Test 12 [numFound]: " + str(rt1.numFound == 4 and rt2.numFound == 3))


main()