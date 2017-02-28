//	Created By Emi Miyamoto on 2017/02/27
package codecheck;

import java.util.*;

public class App {
	public static void main(String[] args) {

		int status = checkArgs(args);
		if (status != 2) {
			System.exit(status);
		}

		System.out.println(matchWord(args));
		System.exit(0);
	}
	public static int checkArgs(String[] args) {
		if (args.length == 2) {
			System.out.println(args[1]);
			return 0;
		}
		if (args.length <= 1) {
			System.out.println("none");
			return 1;
		}
		return 2;
	}

	public static String matchWord(String[] words) {
		char lastL = words[0].charAt(words[0].length() - 1);
		List<String> list = new ArrayList<String>();
		for (String word: words) {
			if (word.charAt(0) == lastL) {
				list.add(word);
			}
		}
		if (list.size() == 0) {
			return "none";
		} else if (list.size() == 1) {
			return list.get(0);
		} else {
			return feasibleWord(list, words);
		}
	}
	public static String feasibleWord(List<String> wlist, String[] hlist) {
		int maxHits = wlist.size();
		String matchSt = wlist.get(0);
		for (String w1: wlist) {
			char lastL = w1.charAt(w1.length() - 1);
			int count = -1;
			for (String w2: hlist) {
					char startL = w2.charAt(0);
					if (startL == lastL) {
						count += 1;
					}
			}
			if (maxHits > count) {
				matchSt = w1;
				maxHits = count;
			}
		}
		return matchSt;
	}
}
