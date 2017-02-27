import subprocess
import sys


args = sys.argv

#print args
wlist = args[4:]
preWord = args[3]
stupAI = args[1]


def setAiCommand(aiCom, l, words):
    command = aiCom + ' ' + l + ' ' + ' '.join(words)
    return command
def checkInvalid(preword, word, wlist):
    if word in wlist == False:
        return False
    elif preword[-1:] != word[:1]:
        return False
    elif word == "nan":
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
    out = turn + '˽(' + valid + '):˽' + word
    print  out
def outResult(turn):
    out = 'WIN˽-˽' + turn
    print  out

def main(stupAI, preWord, wlis):
    while 1:
        ans1 = getAiAns(stupAI, preWord, wlist)
        if checkInvalid(preWord, ans1, wlist):
            outProgress('FIRST', 'OK', ans1)
            preWord = ans1
            wlist.remove(ans1)
        else:
            outProgress('FIRST', 'NG', ans1)
            outResult('SECOND')
            break
        ans2 = getAiAns(stupAI, preWord, wlist)
        if checkInvalid(preWord, ans2, wlist):
            outProgress('SECOND', 'OK', ans2)
            preWord = ans2
            wlist.remove(ans2)
        else:
            outProgress('SECOND', 'NG', ans2)
            outResult('FIRST')
            break
    return 0
main(stupAI, preWord, wlist)
