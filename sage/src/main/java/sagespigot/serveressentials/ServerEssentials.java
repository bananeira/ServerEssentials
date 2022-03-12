package sagespigot.serveressentials;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import sagespigot.serveressentials.listener.JoinListener;
import sagespigot.serveressentials.listener.PlayerMoveListener;
import sagespigot.serveressentials.listener.QuitListener;

public final class ServerEssentials extends JavaPlugin {
    private static ServerEssentials plugin;
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        plugin = this;

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new QuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);

        config.options().copyDefaults(true);
        saveConfig();

        Bukkit.getServer().getConsoleSender().sendMessage("§aEnabled ServerEssentials. §cIf you encounter an error regarding the config file, please delete it and restart the server.");
    }

    public static ServerEssentials getInstance() {
        return plugin;
    }

    public FileConfiguration getConfigFile() {
        return config;
    }
}
