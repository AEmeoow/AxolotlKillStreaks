package axolotlstudios.managers;


import axolotlstudios.AxolotlKillStreaks;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class KillstreakManager {

    private Map<Integer, String> killstreakMessages = new HashMap<>();

    private AxolotlKillStreaks plugin;

    public KillstreakManager(AxolotlKillStreaks plugin) {
        this.plugin = plugin;
        loadKillstreaks();
    }

    public void loadKillstreaks() {
        for (String s : plugin.getConfig().getConfigurationSection("killstreaks.").getKeys(false)) {
            int kills = plugin.getConfig().getInt("killstreaks." + s + ".kills");
            String message = plugin.getConfig().getString("killstreaks." + s + ".message");
            killstreakMessages.put(kills, message);
        }
    }

    public void handleKillstreak(Player player, int kills) {
        System.out.println("Contains: " + killstreakMessages.containsKey(kills));
        if(killstreakMessages.containsKey(kills)){
            String message = killstreakMessages.get(kills).replace("{player}", player.getName());
            Bukkit.broadcastMessage(AxolotlKillStreaks.c(message));
        }
    }

}
