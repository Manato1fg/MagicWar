/**
 * This project is licensed under MIT
 * @lastUpdated 2019/01/13
 */
package com.manato1fg.mw.utils;

import java.util.ArrayList;
import java.util.Collection;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.math.Vector3;

/**
 * @author Manato
 * This Class contains functions which we make magic more confortable.
 */
public class MagicFunction {
	
	private static MagicFunction instance = null;

	
	private MagicFunction() {
	}
	
	public static MagicFunction getInstance() {
		if(instance == null) {
			instance = new MagicFunction();
		}
		
		return instance;
	}
	
	public Player getNearestPlayer(double x, double y, double z, Level level) {
		Collection<Player> levelPlayers = level.getPlayers().values();
		
		Vector3 vec = new Vector3(x, y, z).multiply(-1);
		
		Player nearest = null;
		double distanse = Double.MAX_VALUE;
		
		for(Player p : levelPlayers) {
			
			Position pp = p.getPosition().add(vec);
			double d = this.distanseFromOriginal(pp);
			
			if(d < distanse) {
				nearest = p;
			}
			
		}
		return nearest;	
	}
	
	public Player getNearestPlayer(Player player) {
		Collection<Player> levelPlayers = player.level.getPlayers().values();
		
		Vector3 vec = player.multiply(-1);
		
		Player nearest = null;
		double distanse = Double.MAX_VALUE;
		
		for(Player p : levelPlayers) {
			
			if(p == player) continue;
			
			Position pp = p.add(vec);
			double d = this.distanseFromOriginal(pp);
			
			if(d < distanse) {
				nearest = p;
			}
			
		}
		return nearest;	
	}
	
	public ArrayList<Player> getPlayersInRadius(double x, double y, double z, double radius, Level level) {
		Collection<Player> levelPlayers = level.getPlayers().values();
		Vector3 vec = new Vector3(x, y, z).multiply(-1);
		
		ArrayList<Player> list = new ArrayList<Player>();
		
		double r = radius * radius;
		
		for(Player p : levelPlayers) {
			
			Position pp = p.getPosition().add(vec);
			double d = this.distanseFromOriginal(pp);
			
			if(d <= r) {
				list.add(p);
			}
			
		}
		return list;	
	}
	
	public ArrayList<Player> getPlayersInRadius(Player player, double radius) {
		Collection<Player> levelPlayers = player.level.getPlayers().values();
		Vector3 vec = player.multiply(-1);
		
		ArrayList<Player> list = new ArrayList<Player>();
		
		double r = radius * radius;
		
		for(Player p : levelPlayers) {
			
			if(p == player) continue;
			
			Position pp = p.getPosition().add(vec);
			double d = this.distanseFromOriginal(pp);
			
			if(d <= r) {
				list.add(p);
			}
			
		}
		return list;	
	}
	
	public double distanseFromOriginal(Position p) {
		return p.x * p.x + p.y * p.y + p.z * p.z;
	}
	
	public double deg2rad(double degree) {
		return Math.toRadians(degree);
	}
	
	public double rad2deg(double radian) {
		return Math.toDegrees(radian);
	}
	
	public double sin(double rad) {
		return Math.sin(rad);
	}
	
	public double cos(double rad) {
		return Math.cos(rad);
	}
	
	public double tan(double rad) {
		return Math.tan(rad);
	}
	
	public double sqrt(double squared) {
		return Math.sqrt(squared);
	}

}
