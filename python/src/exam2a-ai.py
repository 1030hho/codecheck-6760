
import sys
import six


args = sys.argv

if len(args) == 3:
    six.print_(args[2])
    sys.exit(0)
if len(args) < 3:
    six.print_('none')
    sys.exit(1)

preWord = args[1]
wordsList = args[2:]

if len(wordsList) == 1:
    six.print_(wordsList[0])

def matchWords(preword, words):
    lastL = preword[-1:]
    matchList = []
    for word in words:
        if (word[:1] == lastL):
            matchList.append(word)
    if len(matchList) == 0:
        six.print_('none')
    elif len(matchList) == 1:
        six.print_(matchList[0])
    else:
        six.print_(evaluate(matchList, words))

def evaluate(wlist, hlist):
    maxHits = len(wlist)
    matchSt = wlist[0]
    for w1 in wlist:
        lastL = w1[-1:]
        count = -1
        for w2 in hlist:
            if w2[0] == lastL:
                count += 1
        if maxHits > count:
            matchSt = w1
            maxHits = count
    return matchSt


matchWords(preWord, wordsList)
