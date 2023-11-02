def mymax(iterable, key=lambda x: x):
    max_x = max_key = None

    for x in iterable:
        key_x = key(x)

        if max_x == None or key_x > max_key:
            max_key, max_x = key_x, x

    return max_x


maxint = mymax([1, 3, 5, 7, 4, 6, 9, 2, 0])
maxchar = mymax("Suncana strana ulice")
maxstring = mymax([
    "Gle", "malu", "vocku", "poslije", "kise",
    "Puna", "je", "kapi", "pa", "ih", "njise"])

longestString = mymax([
    "Gle", "malu", "vocku", "poslije", "kise",
    "Puna", "je", "kapi", "pa", "ih", "njise"], lambda x: len(x))

print("Max int from array is: {0}".format(maxint))
print("Max char from array is: {0}".format(maxchar))
print("Max string from array is: {0}".format(maxstring))
print("Longest string from array is: {}".format(longestString))

D = {"burek": 8, "buhtla": 5}
maxdict = mymax(D, lambda x: D.get(x))
print("Max value from dictionary is: {0}".format(maxdict))

col = [("Ante", "Antic"), ("Šime", "Šimić"), ("Ivo", "Ivić")]
maxcol = mymax(col)
print(maxcol)
