package detectors;

import org.bukkit.Material;

public class WoodenDetector extends Detector {

    public WoodenDetector(String nameOfMaterialToDetect, Material materialToDetect, Material craftingMaterial) {
        super("Holz", nameOfMaterialToDetect, "Spitzhacke", materialToDetect, craftingMaterial, Material.WOODEN_PICKAXE);
    }

    @Override
    public int getDepth() {
        return 2;
    }

    @Override
    public int getRadius() {
        return 0;
    }
}