package axolotlstudios.listeners;

import axolotlstudios.AxolotlKillStreaks;
import axolotlstudios.managers.KillstreakManager;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.persistence.PersistentDataType;

public class KillstreakListener implements Listener {

    private KillstreakManager killstreakManager;

    public KillstreakListener(KillstreakManager killstreakManager) {
        
        this.killstreakManager = killstreakManager;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        player.getPersistentDataContainer().set(new NamespacedKey(AxolotlKillStreaks.getInstance(), "killstreak"), PersistentDataType.INTEGER, 0);

        Player killer = player.getKiller();
        System.out.println("Killer: " + killer);
        if(killer != null) {
            int currentStreak = killer.getPersistentDataContainer().getOrDefault(new NamespacedKey(AxolotlKillStreaks.getInstance(), "killstreak"), PersistentDataType.INTEGER, 0);
            killer.getPersistentDataContainer().set(new NamespacedKey(AxolotlKillStreaks.getInstance(), "killstreak"), PersistentDataType.INTEGER, currentStreak + 1);
            System.out.println("Current Streak: " + currentStreak);
            killstreakManager.handleKillstreak(killer, currentStreak);
        }
    }
}

