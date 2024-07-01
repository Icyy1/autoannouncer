package me.icyy.autoannouncer;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class Plugin extends JavaPlugin
{
  private static final Logger LOGGER=Logger.getLogger("autoannouncer");

  private File configFile = new File(getDataFolder(), "config.yml");

  @Override
  public void onEnable()
  {
    LOGGER.info("autoannouncer enabled!");
    
    if(!configFile.exists())
    {
      saveDefaultConfig();
      LOGGER.info("config.yml created!");
    }

    final String message = getConfig().getString("message");
    final int interval = getConfig().getInt("interval");

    BukkitScheduler scheduler = getServer().getScheduler();
    scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
      @Override
      public void run() {
        Bukkit.broadcastMessage(message);
      }
    }, 0L, interval * 20L);
  }

  @Override
  public void onDisable()
  {
    LOGGER.info("autoannouncer disabled!");
  }
}
