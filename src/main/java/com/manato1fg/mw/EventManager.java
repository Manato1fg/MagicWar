/**
 * This project is licensed under MIT
 * @lastUpdated 2019/01/13
 */
package com.manato1fg.mw;

import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.ProjectileHitEvent;

import com.manato1fg.mw.utils.StringUtil;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.server.ServerCommandEvent;
import cn.nukkit.plugin.PluginLogger;

import cn.nukkit.Player;
import cn.nukkit.entity.projectile.EntityProjectile;
import cn.nukkit.entity.projectile.EntitySnowball;

/**
 * @author Manato
 *
 */
public class EventManager implements Listener{
	
	//alias
	private StringUtil S = new StringUtil();
	private PluginLogger L = null;
	
	private Main main = null;
	
	public EventManager(Main main) {
		this.main = main;
		L = this.main.getLogger();
	}
	
	@EventHandler()
	public void onPlayerJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		String message1 = S.color("Welcome to Magic War Server!", S.COLOR_YELLOW);
		String message2 = S.color("If you need a help, please type ", S.COLOR_DARK_GREEN) + S.color("</mwhelp>", S.COLOR_GREEN);
		
		player.sendMessage(message1);
		player.sendMessage(message2);
	}
	
	@EventHandler()
	public void onProjectileHit(ProjectileHitEvent e) {
		EntityProjectile ep = (EntityProjectile) e.getEntity();
		if(ep.getNetworkId() == EntitySnowball.NETWORK_ID) {
			Player player = (Player) ep.shootingEntity;
			this.main.getMagicManager().onFlowerGranade(ep.getPosition(), player);
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false) //DON'T FORGET THE ANNOTATION @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        L.info("ServerCommandEvent is called!");
        //you can do more here!
    }

}
