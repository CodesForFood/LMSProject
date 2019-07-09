package com.smoothstack.main;

import java.util.Scanner;

public class UI {

	public Scanner scan;
	private static UI singleUI;
	
	private UI() {
		scan = new Scanner(System.in);
	}
	
	public static UI getInstance() {
		return singleUI == null ? singleUI = new UI() : singleUI;		
	}	
	
	public String readLine() {
		return scan.nextLine(); 
	}
	
	public int readInt() {
		String input = scan.nextLine();		
		return tryParseInt(input) ? Integer.parseInt(input) : 0;			
	}	
	
	public void closeScanner() {
		scan.close();
	}
	
	public static boolean tryParseInt(String input) {
		try {
			Integer.parseInt(input);
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	
	public static void lineBreak() {
		say("=============================================================");
	}
	
	
	public static void badInput() {
		say("Invalid option");
	}
	
	public static void say(String text) {
		System.out.println(text);
	}
	
	
	
	
	
}
