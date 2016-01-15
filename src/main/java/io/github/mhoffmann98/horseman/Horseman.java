package io.github.mhoffmann98.horseman;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class Horseman extends JavaPlugin {
	private boolean isEnabled = true;

	@Override
	public void onEnable() {
		new HorsemanEventListener(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("horseman")) { // If the player typed
															// /basic then do
															// the following...
			if (args.length != 1)
				return false;
			if (args[0].equals("enable")) {
				if (!isEnabled){
					isEnabled = true;
					Bukkit.broadcastMessage("Horseman has been enabled!");
				}
				return true;
			}
			if (args[0].equals("disable")) {
				if (isEnabled) {
					isEnabled = false;
					Bukkit.broadcastMessage("Horseman has been disabled!");
				}
				return true;
			}
			if (args[0].equals("toggle")) {
				if(isEnabled)
					Bukkit.broadcastMessage("Horseman has been disabled!");
				else
					Bukkit.broadcastMessage("Horseman has been enabled!");
				isEnabled = !isEnabled;
				return true;
			}
			return false;
		}
		return false;
	}

	public boolean horseSpawningEnabled() {
		return isEnabled;
	}
}
