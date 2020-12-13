import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class MaterialDetector extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();

        getServer().getPluginManager().registerEvents(new PlayerEventListener(), this);

        ItemStack itemStack = new ItemStack(Material.DIAMOND_PICKAXE);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Kohle Detektor");
        itemMeta.setCustomModelData(258);
        itemStack.setItemMeta(itemMeta);

        itemStack.addEnchantment(Enchantment.DURABILITY, 3);
        itemStack.addEnchantment(Enchantment.VANISHING_CURSE, 1);

        NamespacedKey key = new NamespacedKey(this, "coal-detector");
        ShapedRecipe recipe = new ShapedRecipe(key, itemStack);

        recipe.shape(
                "C  ",
                " P ",
                "KKK"
        );

        recipe.setIngredient('C', Material.COMPASS);
        recipe.setIngredient('P', Material.DIAMOND_PICKAXE);
        recipe.setIngredient('K', Material.COAL);

        Bukkit.addRecipe(recipe);

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