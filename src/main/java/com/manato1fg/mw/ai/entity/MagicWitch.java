/**
 * This project is licensed under MIT
 * @lastUpdated 2019/01/14
 */
package com.manato1fg.mw.ai.entity;

import cn.nukkit.Player;
import cn.nukkit.entity.EntityHuman;
import cn.nukkit.level.Level;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

/**
 * @author Manato
 *
 */
public class MagicWitch extends EntityHuman {
		
	public static final int NETWORK_ID = -1;

	@Override
	public int getNetworkId() {
	    return -1;
	}

	/**
	 * @param chunk
	 * @param nbt
	 */
	public MagicWitch(FullChunk chunk, CompoundTag nbt) {
		super(chunk, nbt);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    protected void initEntity() {
        super.initEntity();
        this.setMaxHealth(26);
    }
    @Override
    public String getName() {
        return "Witch";
    }
    
    @Override
    public void spawnTo(Player player) {
    	
    	if (!this.hasSpawned.containsKey(player.getLoaderId()) && player.usedChunks.containsKey(Level.chunkHash(this.chunk.getX(), this.chunk.getZ()))) {
            this.hasSpawned.put(player.getLoaderId(), player);
        }
    }

}
