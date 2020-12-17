package detectors;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class Detector {

    private String name;
    private int id;

    private Material materialToDetect;
    private Material craftingMaterial;

    private NamespacedKey recipeKey;

    public Detector() {
        name = "Diamant Kohle Detektor Spitzhacke";
        id = name.hashCode();

        materialToDetect = Material.COAL_ORE;
        craftingMaterial = Material.COAL;
    }

    public NamespacedKey getKey() {
        return recipeKey;
    }

    public int getId() {
        return id;
    }

    public void addCraftingRecipe(Plugin plugin) {
        // Create result
        ItemStack detector = new ItemStack(Material.DIAMOND_PICKAXE);

        ItemMeta detectorMeta = detector.getItemMeta();
        detectorMeta.setDisplayName(name);
        detectorMeta.setCustomModelData(id);
        detector.setItemMeta(detectorMeta);

        detector.addEnchantment(Enchantment.DURABILITY, 3);
        detector.addEnchantment(Enchantment.VANISHING_CURSE, 1);

        // Create recipe
        recipeKey = new NamespacedKey(plugin, name.toLowerCase().replaceAll("\\s", "-"));
        ShapedRecipe recipe = new ShapedRecipe(recipeKey, detector);

        recipe.shape(
                "C  ",
                " P ",
                "MMM"
        );

        recipe.setIngredient('C', Material.COMPASS);
        recipe.setIngredient('P', Material.DIAMOND_PICKAXE);
        recipe.setIngredient('M', craftingMaterial);

        Bukkit.addRecipe(recipe);
    }

    public boolean isMaterialBelowLocation(World world, Location location) {
        for(int y = 0; y < 10; y++) {
            Block targetBlock = world.getBlockAt(location.getBlockX(), location.getBlockY() - y, location.getBlockZ());
            if (targetBlock.getType() == materialToDetect) {
                return true;
            }
        }
        return false;
    }
}