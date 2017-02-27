//	Created By Emi Miyamoto on 2017/02/27

package codecheck;

import java.util.*;
import java.io.*;

public class App {
	static String ai1;// start up command of first AI
	static String ai2;//	start up command of After AI
	static String preword;
	static String answer;
	static String[] wlist;
	public static void main(String[] args) {
		if (args.length < 4) {
			System.exit(1);
		}

		ai1 = args[0];
		ai2 = args[1];
		preword = args[2];
		wlist = Arrays.copyOfRange(args, 3, args.length);

		checkArg();

		startShiritori();

		System.exit(0);
	}

	public static void outProgress(String turn, String valid) {
		System.out.println(turn + "˽(" + valid + "):˽" + answer);
	}
	public static void outResult(String turn) {
		System.out.println("WIN˽-˽" + turn);
	}

	public static String getAns(String[] arg) {
		ProcessBuilder pb = new ProcessBuilder(arg);
		try {
		  Process p = pb.start();
			//	wait
			p.waitFor();

		  try (BufferedReader br = new BufferedReader(
		          new InputStreamReader(p.getInputStream()))) {
				String out = br.readLine();
				if (out == null) {
					return "none";
				} else {
					return out;
				}
		  }
		} catch (IOException | InterruptedException e) {
		  //
			return "none";
		}
	}

	public static String[] setAIarg(String aiCommand) {
		List<String> list = new ArrayList<String>();
		list.add(aiCommand);
		list.add(preword);
		for (String val: wlist) {
			list.add(val);
		}
		String[] array=(String[])list.toArray(new String[0]);
		return array;
	}

	public static boolean checkAns() {
		char lastL = preword.charAt(preword.length() - 1);
		if(!Arrays.asList(wlist).contains(answer)) {
			return false;
		} else if (lastL != answer.charAt(0)) {
			return false;
		} else if (answer == "none") {
			return false;
		} else {
			return true;
		}
	}

	public static void updateList() {
		List<String> list= new LinkedList(Arrays.asList(wlist));
		int index = list.indexOf(answer);
		list.remove(index);
		wlist = (String[])list.toArray(new String[list.size()]);
		preword = answer;
	}

	public static void checkArg() {
		if (wlist.length > 1000) {
			System.exit(1);
		}
		boolean hasAns = false;
		char lastL = preword.charAt(preword.length() - 1);
		for (String val: wlist) {
			if (val.length() >= 10000) {
				System.exit(1);
			}
			if (val.charAt(0) == lastL) {
				hasAns = true;
			}
		}
		if (!hasAns) {
			System.exit(1);
		}
	}

	public static void startShiritori() {
		while(true) {
			answer = getAns(setAIarg(ai1));
			if (checkAns()) {
				outProgress("FIRST", "OK");
				updateList();
			} else {
				outProgress("FIRST", "NG");
				outResult("SECOND");
				break;
			}
			answer = getAns(setAIarg(ai2));
			if (checkAns()) {
				outProgress("SECOND", "OK");
				updateList();
			} else {
				outProgress("SECOND", "NG");
				outResult("FIRST");
				break;
			}
		}
	}
}
