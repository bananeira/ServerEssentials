package sagespigot.serveressentials;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import sagespigot.serveressentials.commands.AdminEssentialsCommand;
import sagespigot.serveressentials.listener.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ServerEssentials extends JavaPlugin {
    private static ServerEssentials plugin;
    FileConfiguration config = getConfig();

    public List<Player> performingDoubleJump = new ArrayList<>();
    public List<Player> recentlyPerformedDoubleJump = new ArrayList<>();

    public World lobbyWorld = getServer().getWorld((String) Objects.requireNonNull(config.get("lobby-world")));

    @Override
    public void onEnable() {
        plugin = this;

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new QuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
        Bukkit.getPluginManager().registerEvents(new ToggleFlightListener(), this);

        getCommand("serveressentials").setExecutor(new AdminEssentialsCommand());

        config.options().copyDefaults(true);

        Bukkit.getServer().getConsoleSender().sendMessage("§aEnabled ServerEssentials. §cIf you encounter an error regarding the config file, please delete it and restart the server.");
    }

    public static ServerEssentials getInstance() {
        return plugin;
    }

    public FileConfiguration getConfigFile() {
        return config;
    }

    public void triggerDoubleJumpDelay(Player player, double delay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                ServerEssentials.getInstance().recentlyPerformedDoubleJump.remove(player);
            }
        }.runTaskLater(this, (long) ( delay * 20 ));
    }
}