package me.icyy.autoannouncer;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import net.md_5.bungee.api.ChatColor;

public final class Plugin extends JavaPlugin
{
  private static final Logger LOGGER=Logger.getLogger("autoannouncer");

  String message = getConfig().getString("message");
  String prefix = getConfig().getString("prefix");
  int interval = getConfig().getInt("interval");
  String color = getConfig().getString("color");

  final String finalMessage = ChatColor.translateAlternateColorCodes('&', color) + prefix + message;

  private static Plugin instance;

  @Override
  public void onEnable()
  {
    instance = this;
    this.saveDefaultConfig();

    LOGGER.info("autoannouncer enabled!");

    BukkitScheduler scheduler = getServer().getScheduler();
    scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
      @Override
      public void run() {
        Bukkit.broadcastMessage(finalMessage);
      }
    }, 0L, interval * 20L);
  }

  public static Plugin getInstance()
  {
    return instance;
  }

  @Override
  public void onDisable()
  {
    LOGGER.info("autoannouncer disabled!");
  }
}
