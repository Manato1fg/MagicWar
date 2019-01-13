/**
 * This project is licensed under MIT
 * @lastUpdated 2019/01/13
 */
package com.manato1fg.mw.utils;

public class StringUtil {
	
	public StringUtil() {
		
	}
	
	public String COLOR_BLACK = "§0";
	public String COLOR_DARK_BLUE = "§1";
	public String COLOR_DARK_GREEN = "§2";
	public String COLOR_DARK_AQUA = "§3";
	public String COLOR_DARK_RED = "§4";
	public String COLOR_DARK_PURPLE = "§5";
	public String COLOR_GOLD = "§6";
	public String COLOR_GRAY = "§7";
	public String COLOR_DARK_GRAY = "§8";
	public String COLOR_BLUE = "§9";
	public String COLOR_GREEN = "§a";
	public String COLOR_AQUA = "§b";
	public String COLOR_RED = "§c";
	public String COLOR_LIGHT_PURPLE = "§d";
	public String COLOR_YELLOW = "§e";
	public String COLOR_WHITE = "§f";
	
	public String color(String message, String color) {
		return color + message;
	}

}
