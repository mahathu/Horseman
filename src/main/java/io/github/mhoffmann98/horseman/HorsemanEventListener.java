package io.github.mhoffmann98.horseman;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.spigotmc.event.entity.EntityDismountEvent;

public class HorsemanEventListener implements Listener{
	Horseman plugin;
	public HorsemanEventListener(Horseman plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerEatApple(PlayerItemConsumeEvent e) {
		Player sender = e.getPlayer();
		ItemStack item = e.getItem();
		
		if(sender.getVehicle() instanceof Horse){
			e.setCancelled(true);
			return;
		}
		
		if (item.getType() == Material.GOLDEN_APPLE && item.getDurability()==(short)0) { //normal, unenchanted golden apple (durability for enchanted apple is 1)
			Horse h = (Horse) sender.getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.HORSE);
	        h.setTamed(true);
	        h.setOwner(e.getPlayer());
	        h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
	        h.setPassenger(sender);
	        h.setCustomName(ChatColor.GOLD + "Conjured Horse");
	        //h.setColor(Color.GRAY);
	        //h.setStyle(Horse.Style.WHITE_DOTS);
	        h.setCustomNameVisible(true);
	        h.setVariant(Variant.SKELETON_HORSE);
	        h.setAdult();
	        h.setCarryingChest(false);
	        h.setJumpStrength(0.8);
	        h.setHealth(10);
	        h.setMaxHealth(10);
	        h.setMetadata("conjured", new FixedMetadataValue(plugin, true));
		}
    }
	
	@EventHandler
	public void onPlayerLeaveHorse(VehicleExitEvent e){
		Entity entity = e.getVehicle();
		if(entity.hasMetadata("conjured")){ //conjured horse
			entity.remove();
		}
	}
}
