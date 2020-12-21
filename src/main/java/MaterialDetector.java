import detectors.Detector;
import listeners.PlayerEventListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class MaterialDetector extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();

        Detector detector = new Detector();
        detector.addCraftingRecipe(this);

        getServer().getPluginManager().registerEvents(new PlayerEventListener(detector), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("sayhello")) {
            sender.sendMessage("Hallo " + sender.getName());
        }
        return super.onCommand(sender, command, label, args);
    }
}