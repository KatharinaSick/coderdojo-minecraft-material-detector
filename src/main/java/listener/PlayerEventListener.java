package listener;

import detectors.Detector;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerEventListener implements Listener {

    private Detector detector;

    public PlayerEventListener(Detector detector) {
        this.detector = detector;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Hallo " + player.getName());

        player.discoverRecipe(detector.getKey());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

        if (!itemInMainHand.hasItemMeta()) {
            return;
        }

        ItemMeta meta = itemInMainHand.getItemMeta();
        if (meta == null || !meta.hasCustomModelData() || meta.getCustomModelData() != detector.getId()) {
            return;
        }

        Block targetBlock = player.getTargetBlock(null, 5);
        Location targetLocation = targetBlock.getLocation();
        if (detector.isMaterialBelowLocation(player.getWorld(), targetLocation)) {
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);
        }
    }
}
