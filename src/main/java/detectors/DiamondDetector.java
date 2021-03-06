package detectors;

import org.bukkit.Material;

public class DiamondDetector extends Detector {

    public DiamondDetector(String nameOfMaterialToDetect, Material materialToDetect, Material craftingMaterial) {
        super("Diamant", nameOfMaterialToDetect, "Spitzhacke", materialToDetect, craftingMaterial, Material.DIAMOND_PICKAXE);
    }

    @Override
    public int getDepth() {
        return 8;
    }

    @Override
    public int getRadius() {
        return 1;
    }
}