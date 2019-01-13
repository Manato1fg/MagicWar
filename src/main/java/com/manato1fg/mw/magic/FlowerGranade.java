/**
 * This project is licensed under MIT
 * @lastUpdated 2019/01/13
 */
package com.manato1fg.mw.magic;


import com.manato1fg.mw.Main;
import com.manato1fg.mw.utils.MagicFunction;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent.DamageCause;
import cn.nukkit.level.Position;
import cn.nukkit.level.Sound;
import cn.nukkit.level.particle.HeartParticle;
import cn.nukkit.level.particle.HugeExplodeSeedParticle;
import cn.nukkit.math.AxisAlignedBB;
import cn.nukkit.math.NukkitMath;
import cn.nukkit.math.SimpleAxisAlignedBB;
import cn.nukkit.math.Vector3;
import cn.nukkit.network.protocol.ExplodePacket;
import cn.nukkit.scheduler.PluginTask;

/**
 * @author Manato
 *
 */
public class FlowerGranade {
	
	private static MagicFunction MF = MagicFunction.getInstance();
	
	//parameter
	private double s = 1/2;
	private double a = 1/2;
	private double k = 5;
	
	private double scale = 3;
	
	private double fa = 0.05d;
	
	private double explosionSize = 4;
	
	private int timeTick = 0;
	
	// r = 0.5*s*a^2+sin(2*k*c)+s*cos^2(kac)-sin(kc)
	
	private double[] rtheta = null;
	
	private Position pos;
	private Player owner;
	private int taskId;
	private Main main;
	
	public FlowerGranade(Position pos, Player owner, Main main) {
		this.owner = owner;
		this.pos = pos;
		this.main = main;
		this.taskId = this.main.getServer().getScheduler().scheduleRepeatingTask(new FlowerGranadeTask(main, this), 1).getTaskId();
	}
	
	public void onRun() {
		this.timeTick ++;
		
		//end
		if(this.timeTick == 60) {
			this.explode();
			this.kill();
			return;
		}
		int l = 120;
		if(this.rtheta == null) {
			//Calc once.
			this.rtheta = new double[l];
			
			for(int i = 0; i < this.rtheta.length; i++) {
				double theta = MF.deg2rad(i * 360 / l);
				double r = 0.5 * s * a * a * MF.sin(2 * k * theta);
				r += s * MF.cos(a * k * theta) * MF.cos(a * k * theta);
				r -= MF.sin(k * theta);
				r *= scale;
				this.rtheta[i] = r;
			}
		}
		
		double x, y, xx, yy;
		Vector3 v;
		double d = (double) (this.timeTick * this.timeTick) * this.fa;
		double cos = MF.cos(MF.deg2rad(d));
		double sin = MF.sin(MF.deg2rad(d));
		for(int i = 0; i < this.rtheta.length; i++) {
			double theta = MF.deg2rad(i * 360 / l);
			double r = this.rtheta[i];
			x = r * MF.cos(theta);
			y = r * MF.sin(theta);
			xx = x * cos - y * sin;
			yy = x * sin + y * cos; 
			v = new Vector3(xx, 0.5, yy).add(this.pos);
			this.pos.level.addParticle(new HeartParticle(v));
		}
		
	}
	
	public void explode() {
		double explosionSize = this.explosionSize * 2d;
        double minX = NukkitMath.floorDouble(this.pos.x - explosionSize - 1);
        double maxX = NukkitMath.ceilDouble(this.pos.x + explosionSize + 1);
        double minY = NukkitMath.floorDouble(this.pos.y - explosionSize - 1);
        double maxY = NukkitMath.ceilDouble(this.pos.y + explosionSize + 1);
        double minZ = NukkitMath.floorDouble(this.pos.z - explosionSize - 1);
        double maxZ = NukkitMath.ceilDouble(this.pos.z + explosionSize + 1);
        
        AxisAlignedBB explosionBB = new SimpleAxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
        Entity[] list = this.pos.level.getNearbyEntities(explosionBB, this.owner);
        
        for (Entity entity : list) {
            double distance = entity.distanceSquared(this.pos);

            if (distance <= explosionSize * explosionSize) {
                Vector3 motion = entity.subtract(this.pos).normalize();
                int exposure = 1;
                double impact = (1 - distance) * exposure;
                int damage = (int) (((impact * impact + impact) / 2) * 8 * explosionSize + 1);
                
                entity.attack(new EntityDamageByEntityEvent((Entity) this.owner, entity, DamageCause.ENTITY_EXPLOSION, damage));

                entity.setMotion(motion.multiply(impact));
            }
        }
        ExplodePacket pk = new ExplodePacket();
        pk.x = (float) this.pos.x;
        pk.y = (float) this.pos.y;
        pk.z = (float) this.pos.z;
        pk.radius = (float) this.explosionSize;
        this.pos.level.addChunkPacket((int) pos.x >> 4, (int) pos.z >> 4, pk);
		this.pos.level.addParticle(new HugeExplodeSeedParticle(pos));
		this.pos.level.addSound(pos, Sound.RANDOM_EXPLODE);
	}
	public void kill() {
		this.main.getServer().getScheduler().cancelTask(this.taskId);
	}
	
	private class FlowerGranadeTask extends PluginTask<Main> {
		
		private FlowerGranade fg;

		public FlowerGranadeTask(Main owner, FlowerGranade fg) {
			super(owner);
			this.fg = fg;
		}

		@Override
		public void onRun(int currentTick) {
			this.fg.onRun();
		}
		
	}
}
