package axolotlstudios.commands;

import axolotlstudios.AxolotlKillStreaks;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCMD implements CommandExecutor {

    private AxolotlKillStreaks plugin;

    public ReloadCMD(AxolotlKillStreaks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("axolotlkillstreaks")) {
            if (args[0].equalsIgnoreCase("reload") && (sender.hasPermission("axolotlkillstreaks.reload"))) {
                plugin.reloadConfig();
                plugin.getKillstreakManager().loadKillstreaks();
                sender.sendMessage(AxolotlKillStreaks.c("&d&lAXOLOTL&f&lKILLSTREAKS &8» &aConfiguration file reloaded for killstreak."));
                return true;
            }

        }
        return false;
    }

}

