/**
 * This project is licensed under MIT
 * @lastUpdated 2019/01/13
 */
package com.manato1fg.mw.magic;

import com.manato1fg.mw.Main;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.scheduler.PluginTask;

/**
 * @author Manato
 *
 */
public class MagicManager{
	
	private Main main;
	
	public MagicManager(Main main) {
		this.main = main;
		this.main.getServer().getScheduler().scheduleRepeatingTask(new ConstantMagicTask(this.main), 4);
	}
	
	public void onFlowerGranade(Position pos, Player player) {
		new FlowerGranade(pos, player, this.main);
	}
	
	private class ConstantMagicTask extends PluginTask<Main>{
		
		public ConstantMagicTask(Main main) {
			super(main);
		}

		@Override
		public void onRun(int currentTick) {
			for(Level level: this.getOwner().getServer().getLevels().values()) {
				for(Player player: level.getPlayers().values()) {
					
					int itemId = player.getInventory().getItemInHand().getId();
					
					switch(itemId) {
					case Item.APPLE:
						FireWing.draw(player.getPosition(), player.yaw);
					}
				}
			}
		}
	}
	
}
