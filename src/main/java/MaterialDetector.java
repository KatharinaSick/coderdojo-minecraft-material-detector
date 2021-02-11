import detectors.Detector;
import listeners.PlayerEventListener;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;


public class MaterialDetector extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();

        Map<Integer, Detector> detectors = new HashMap<>();

        Detector diamondCoalDetector = new Detector("Diamant", "Kohle", "Spitzhacke", Material.COAL_ORE, Material.COAL, Material.DIAMOND_PICKAXE);
        diamondCoalDetector.addCraftingRecipe(this);
        detectors.put(diamondCoalDetector.getId(), diamondCoalDetector);

        Detector woodenCoalDetector = new Detector("Holz", "Kohle", "Spitzhacke", Material.COAL_ORE, Material.COAL, Material.WOODEN_PICKAXE);
        woodenCoalDetector.addCraftingRecipe(this);
        detectors.put(woodenCoalDetector.getId(), woodenCoalDetector);

        getServer().getPluginManager().registerEvents(new PlayerEventListener(detectors), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("sayhello")) {
            sender.sendMessage("Hallo " + sender.getName());
        }
        return super.onCommand(sender, command, label, args);
    }
}