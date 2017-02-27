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
			return list.get(0);
		}
	}
	public static void evaluate(List<String> words) {

	}
}
