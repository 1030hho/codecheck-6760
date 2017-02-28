
import sys

args = sys.argv

if len(args) == 2:
    sys.stdout.write(args[3])
    sys.exit(0)
if len(args) < 4:
    sys.stdout.write('')
    sys.exit(1)

preWord = args[1]
wordsList = args[2:]

if len(wordsList) == 1:
    print wordsList[0]

def matchWords(preword, words):
    lastL = preword[-1:]
    matchList = []
    for word in words:
        if (word[:1] == lastL):
            matchList.append(word)
    if len(matchList) == 0:
        sys.stdout.write('')
    elif len(matchList) == 1:
        sys.stdout.write(matchList[0])
    else:
        sys.stdout.write(matchList[0])
        ##set value function

matchWords(preWord, wordsList)
