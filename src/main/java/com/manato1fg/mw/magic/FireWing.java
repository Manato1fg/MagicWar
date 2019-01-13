/**
 * This project is licensed under MIT
 * @lastUpdated 2019/01/13
 */
package com.manato1fg.mw.magic;

import com.manato1fg.mw.utils.MagicFunction;

import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.level.particle.AngryVillagerParticle;
import cn.nukkit.math.Vector3;

public class FireWing {
	private static MagicFunction MF = MagicFunction.getInstance();
	
	private static int[][] data = 
		{
				{0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0},
				{0,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,0,0,0,1,1,0,0,0,1,1,1,1},
				{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1}
		};
	private static double scale = 0.3;

	
	public static void draw(Position pos, double angle) {
		Level level = pos.getLevel();
		int l1, l2;
		l1 = data.length;
		
		double sin = MF.sin(MF.deg2rad(angle));
		double cos = MF.cos(MF.deg2rad(angle));
		Vector3 vec;
		for(int y = 0; y < l1; y++) {
			l2 = data[y].length;
			for(int x = 0; x < l2; x++) {
				int flag = data[y][x];
				if(flag == 0) continue;
				double kx, ky;
				kx = x - (int) (l2 / 2);
				ky = y - (int) (l1 / 2);
				ky = y * (-1);
				//(x,y)=(2,2) => (x,y)=(-6, 1)
				double r = scale * kx;
				double px = r * cos, py = scale * ky + 1.7d, pz = r * sin;
				vec = new Vector3(px, py, pz).add(pos);
				level.addParticle(new AngryVillagerParticle(vec));
			}
		}
	}
}
