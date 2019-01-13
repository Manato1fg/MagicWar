/**
 * This project is licensed under MIT
 * @lastUpdated 2019/01/13
 */
package com.manato1fg.mw.utils;

import java.util.Random;

/**
 * @author Manato
 *
 */
public class ColorUtil {
	
	
	public static int[][] COLOR = {
			{89, 152, 255},//blue
			{78, 252, 101},//green
			{252, 169, 85},//orange
			{252, 85, 85},//wine red
			{255, 79, 208},//pink
			{183, 36, 242}//purple
	};
	
	public static int[] getRandomColor() {
		Random r = new Random();
		int n = r.nextInt(COLOR.length - 1);
		return COLOR[n];
	}

}
