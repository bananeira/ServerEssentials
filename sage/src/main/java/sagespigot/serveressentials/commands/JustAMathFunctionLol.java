package sagespigot.serveressentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created on 15.03.2022, 22:06 in spigot.
 */
public class JustAMathFunctionLol implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player ingameSender = (Player) sender;

            if (args.length <= 1) {
                if (args.length == 0) {
                    ingameSender.sendMessage("");
                } else {
                    try {
                        Integer.parseInt(args[0]);
                    } catch (Exception exception) {
                        ingameSender.sendMessage("/justamathfunctionlol [int x-value]");
                        return false;
                    }

                    double ySolDouble;
                    int xVal = Integer.parseInt(args[0]);

                    if (xVal <= 0 || xVal > 62) {
                        ingameSender.sendMessage("x = [1 - 62]");

                        return false;
                    }

                    Double k = 1024.0;
                    Double l = 0.0257;
                    Double m = 0.0675;
                    Double n = 10.0;
                    Double o = 0.776;

                    ySolDouble = (l * Math.exp(m * xVal) * m * Math.pow(xVal, 3) + (m/-(Math.pow(xVal, 2)) + k)) * Math.exp((n * xVal) - 550) + o;

                    int ySol = (int) Math.ceil(ySolDouble);
                    ingameSender.sendMessage(String.valueOf(ySol));

                    return false;
                }
            } else {
                ingameSender.sendMessage("/justamathfunctionlol [int x-value]");
            }
        } else {
            Bukkit.getServer().getConsoleSender().sendMessage("Sorry, no. Ingame plz.");
        }
        return false;
    }
}
