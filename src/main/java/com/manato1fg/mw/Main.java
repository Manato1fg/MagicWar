/**
 * This project is licensed under MIT
 */
package com.manato1fg.mw;

import com.manato1fg.mw.magic.MagicManager;
import com.manato1fg.mw.utils.StringUtil;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginLogger;

/**
 * @author Manato1fg
 * @lastUpdated 2019/01/12
 */
public class Main extends PluginBase {
	
	//alias
	private StringUtil S = new StringUtil();
	private PluginLogger L = null;
	
	//Magic Manager
	private MagicManager MM;
	
	@Override
	public void onEnable() {
		L = this.getLogger();
		L.info(S.color("Initializing Magic War Plugin...", S.COLOR_AQUA));
		L.info(S.color("§3Just a minute please...", S.COLOR_DARK_GREEN));
		
		this.registerEvents();
		this.bootMagicManager();
	}
	
	public void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new EventManager(this), this);
	}
	
	public void bootMagicManager() {
		this.MM = new MagicManager(this);
	}
	
	
	//getter
	public MagicManager getMagicManager() {
		return this.MM;
	}
	
}
