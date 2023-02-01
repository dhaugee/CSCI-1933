import math

# 2. most common character:
def common():
    astring = input('type a sentence:')
    mcc = ["a", 0]
    charlist = []
    for i in astring:
        if i != " ":
            charlist.append(i)
            for char in charlist:
                if charlist.count(char) > mcc[1]:
                    mcc[1] = charlist.count(char)
                    mcc[0] = char
                elif charlist.count(char) <= mcc[1]:
                    mcc[1] = mcc[1]
                    mcc[0] = mcc [0]
    print(mcc)

# 3. palindrome:
def pal():
    stri = input("enter a word:")
    revdrome = []
    drome = []
    for i in stri:
        if i != " ":
            revdrome.append(i)
    revdrome.reverse()
    for i in stri:
        if i != " ":
            drome.append(i)
    if drome == revdrome:
        print("yes it is")
    else:
        print("nope")

# with recursion:
def pali(s):
    if len(s) == 1 or len(s) == 0:
        return True
    elif s[0] != s[-1]:
        return False
    else:
        return pali(s[1:-1])

# 4. circle class:
class Circle:

    def __init__(self, radius):
        self.radius = radius

    def getRadius(self):
        return self.radius

    def setRadius(self, radius2):
        self.radius = radius2

    def getArea(self):
        return math.pi * (self.radius ** 2)

    def getDiameter(self):
        return self.radius * 2

    def getCircumference(self):
        return math.pi * self.radius * 2

    def __eq__(self, radii):
        if self.radius == radii:
            return True
        else:
            return False

circle1 = Circle(8)
print(circle1.getRadius())
circle1.setRadius(2.5)
print(circle1.getRadius())
print(circle1.getArea())
print(circle1.getDiameter())
print(circle1.getCircumference())
print(circle1.__eq__(2.5))
print(circle1.__eq__(2))
