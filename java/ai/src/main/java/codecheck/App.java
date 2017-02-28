package codecheck;

import java.util.*;

public class App {
	public static void main(String[] args) {
		if (args.length == 2) {
			System.out.println(args[1]);
			System.exit(0);
		}
		if (args.length < 3) {
			System.out.println("none");
			System.exit(1);
		}
		System.out.println(matchWords(args));
		System.exit(0);
	}
	public static String matchWords(String[] words) {
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
			return evaluate(list, words);
		}
	}
	public static String evaluate(List<String> wlist, String[] hlist) {
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
