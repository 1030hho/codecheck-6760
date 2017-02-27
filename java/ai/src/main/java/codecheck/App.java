package codecheck;

import java.util.*;

public class App {
	public static void main(String[] args) {
		for (int i = 0, l = args.length; i < l; i++) {
			//	System.out.println(args[i]);
		}
		System.out.println(matchWords(args));
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
			return list.get(0);
		}
	}
}
