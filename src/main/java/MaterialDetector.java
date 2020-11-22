import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MaterialDetector extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equals("sayhello")) {
            Player player = getServer().getPlayer(sender.getName());
            player.sendMessage("Hallo " + sender.getName());
        }
        return super.onCommand(sender, command, label, args);
    }
}