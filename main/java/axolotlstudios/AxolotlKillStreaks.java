package axolotlstudios;

import axolotlstudios.commands.ReloadCMD;
import axolotlstudios.listeners.KillstreakListener;
import axolotlstudios.managers.KillstreakManager;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AxolotlKillStreaks extends JavaPlugin {

    private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    @Getter
    private static AxolotlKillStreaks instance;

    private KillstreakManager killstreakManager;

    @Override
    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        killstreakManager = new KillstreakManager(this);
        getServer().getPluginManager().registerEvents(new KillstreakListener(killstreakManager), this);
        getCommand("axolotlkillstreaks").setExecutor(new ReloadCMD(this));
    }

    public static String getHex(String msg) {
        Matcher matcher = pattern.matcher(msg);
        while (matcher.find()) {
            String color = msg.substring(matcher.start(), matcher.end());
            msg = msg.replace(color, net.md_5.bungee.api.ChatColor.of(color) + "");
            matcher = pattern.matcher(msg);
        }
        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String c(String s){
        return ChatColor.translateAlternateColorCodes('&', getHex(s));
    }

    public KillstreakManager getKillstreakManager() {
        return killstreakManager;
    }

}

