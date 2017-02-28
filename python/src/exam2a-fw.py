import subprocess
import sys


args = sys.argv

if len(args) < 5:
    sys.exit(1)

wlist = args[4:]
preWord = args[3]
stupAI = args[1]
stupAI = args[2]

def setAiCommand(aiCom, l, words):
    command = aiCom + ' ' + l + ' ' + ' '.join(words)
    return command
def checkInvalid(preword, word, wlist):
    if word in wlist == False:
        return False
    elif preword[-1:] != word[:1]:
        return False
    elif word == '':
        return False
    else:
        return True
def getAiAns(stupAI, preword, wlist):
    proc = subprocess.Popen(setAiCommand(stupAI, preword, wlist),
                            shell=True,
                            stdin=subprocess.PIPE,
                            stdout=subprocess.PIPE,
                            )
    return proc.communicate()[0]
def outProgress(turn, valid, word):
    out = turn + ' (' + valid + '): ' + word
    print  out
def outResult(turn):
    out = 'WIN - ' + turn
    print  out
def checkArg(preword, wlist):
    if len(wlist) > 1000:
        sys.exit(1)
    hasAns = False
    lastL = preword[-1:]
    for word in wlist:
        if len(word) > 10000:
            sys.exit(1)
        if word[0] == lastL:
            hasAns = True
    if hasAns == False:
        sys.exit(1)


def main(stupAI, preWord, wlis):
    while 1:
        ans1 = getAiAns(stupAI, preWord, wlist)
        ans1 = ans1.rstrip("\n")
        if checkInvalid(preWord, ans1, wlist):
            outProgress('FIRST', 'OK', ans1)
            preWord = ans1
            wlist.remove(ans1)
        else:
            outProgress('FIRST', 'NG', ans1)
            outResult('SECOND')
            break
        ans2 = getAiAns(stupAI, preWord, wlist)
        ans2 = ans2.rstrip("\n")
        if checkInvalid(preWord, ans2, wlist):
            outProgress('SECOND', 'OK', ans2)
            preWord = ans2
            wlist.remove(ans2)
        else:
            outProgress('SECOND', 'NG', ans2)
            outResult('FIRST')
            break
    return 0

checkArg(preWord, wlist)
main(stupAI, preWord, wlist)
