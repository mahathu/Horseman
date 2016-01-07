package io.github.mhoffmann98.horseman;

import org.bukkit.plugin.java.JavaPlugin;

public final class Horseman extends JavaPlugin {
	@Override
	public void onEnable(){
		new HorsemanEventListener(this);
	}
}
