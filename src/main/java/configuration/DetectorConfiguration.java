package configuration;

import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

public class DetectorConfiguration implements ConfigurationSerializable {

    private String name;
    private Material materialToDetect;
    private Material craftingMaterial;

    public DetectorConfiguration(Map<String, Object> configuration) {
        this.name = (String) configuration.get("name");
        this.materialToDetect = Material.valueOf((String) configuration.get("material_to_detect"));
        this.craftingMaterial = Material.valueOf((String) configuration.get("crafting_material"));
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("name", name);
        resultMap.put("material_to_detect", materialToDetect.name());
        resultMap.put("crafting_material", craftingMaterial.name());
        return resultMap;
    }
}
