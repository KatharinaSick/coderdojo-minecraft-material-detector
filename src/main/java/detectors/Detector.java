package detectors;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class Detector {

    private final String name;
    private final int id;

    private final Material materialToDetect;
    private final Material craftingMaterial;
    private final Material baseMaterial;

    private NamespacedKey recipeKey;

    public Detector(String namePrefix, String nameOfMaterialToDetect, String nameOfBaseMaterial,
                    Material materialToDetect, Material craftingMaterial, Material baseMaterial) {
        name = namePrefix + " " + nameOfMaterialToDetect + " Detektor " + nameOfBaseMaterial;
        id = name.hashCode();

        this.materialToDetect = materialToDetect;
        this.craftingMaterial = craftingMaterial;
        this.baseMaterial = baseMaterial;
    }

    public NamespacedKey getKey() {
        return recipeKey;
    }

    public int getId() {
        return id;
    }

    public void addCraftingRecipe(Plugin plugin) {
        // Create result
        ItemStack detector = new ItemStack(baseMaterial);

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
                " B ",
                "MMM"
        );

        recipe.setIngredient('C', Material.COMPASS);
        recipe.setIngredient('B', baseMaterial);
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

    public boolean isMaterialInRange(World world, Location location) {
        for(int x = location.getBlockX() - 1; x <= location.getBlockX() + 1; x++) {
            for(int z = location.getBlockZ() - 1; z <= location.getBlockZ() + 1; z++) {
                if (isMaterialBelowLocation(world, new Location(world, x, location.getBlockY(), z))) {
                    return true;
                }
            }
        }
        return false;
    }
}