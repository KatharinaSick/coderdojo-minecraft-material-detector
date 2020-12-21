## Zusammenfassung 2. Termin Anfänger | 10.12.2020

Bei diesem Workshop haben wir einen sogenannten `EventListener` hinzugefügt, um den Spieler nicht nur auf Kommando zu begrüßen, sondern sofort wenn er auf den Server kommt. Als nächstes haben wir ein eigenes Rezept für eine Diamant Spitzhhacke, die später Kohle detektieren soll, hinzugefügt.
 
## Voraussetzungen
- Den Code vom [1. Termin des Workshops](https://github.com/KatharinaSick/coderdojo-minecraft-material-detector/tree/2020-11-20_first-workshop) ausführen können.

## Anleitung
1. Mache einen Rechtsklick auf den Ordner `src/main/java` und klicke auf "New" -> "Java Class" um eine neue Java
 Klasse zu erstellen.
2. Gib den Namen "PlayerEventListener" ein und drücke auf Enter.
3. Füge den folgenden Code ein. Die jeweiligen Zeilen sind in den Kommentaren erklärt.
```java
// Hier importieren wir die Klassen, die wir unten im Code verwenden. Würden wir das nicht machen, müsste man statt z.B. "Player" immer "org.bukkit.entity.Player" schreiben, damit unsere Klasse weiss, welcher Player gemeint ist
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

// Die Definition der Klasse:
// - Die Klasse ist "public" -> Für alle anderen Klassen sichtbar
// - Ihr Name ist "PlayerEventListener"
// - Die Klasse implementiert das Interface Listener. Vereinfacht gesagt, bestimmen wir so, dass unsere Klasse auch ein "Listener" ist. Falls das Interface Methoden enthalten würde, müssten wir diese überschreiben.
public class PlayerEventListener implements Listener {

    // Eine sogenannte "Annotation", die die unten stehende Methode als EventHandler (mit der Priorität NORMAL) markiert.
    @EventHandler
    // Die Definition der Methode
    // - Sie ist "public" -> für alle sichtbar
    // - Ihr Name ist "onPlayerJoin"
    // - Sie hat ein Argument vom Typ PlayerJoinEvent namens event
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Hier speichern wir den Spieler, durch den das Event ausgelöst wurde in der Variable player
        Player player = event.getPlayer();
        // Hier schicken wir die Begrüßungsnachricht an den Spieler
        player.sendMessage("Hallo " + player.getName());
    }
}
```
4. Nun müssen wir unseren neuen `EventListener` noch registrieren, damit Bukkit weiß, dass es ihn gibt. Wechsle dazu wieder zu deiner `MaterialDetector` Klasse und füge die folgende Zeile in die `onEnable()` Methode ein.
```java
getServer().getPluginManager().registerEvents(new PlayerEventListener(), this);
```
5. Wenn du das Plugin jetzt baust, zu deinem Server hinzufügst und den Server neu lädst oder neu startest (siehe [Anleitung des 1. Workshops](#zusammenfassung-1-termin--20112020), wirst du als Spieler begrüßt, wenn du dich zum Server verbindest.
6. Nun ist es an der Zeit, ein neues Rezept für unseren Kohle Detektor hinzuzufügen. Füge dazu den folgenden Code (Erklärungen wieder in den Kommentaren) in die `onEnable()` Methode ein.
```java
// Ab hier definieren wir, wie das Ergebnis aussehen soll -> eine verzauberte Diamant Spitzhacke mit dem Namen "Kohle Detektor" und der ID 258. Diese ID könnte jede Zahl sein, du musst sie dir nur merken.
ItemStack itemStack = new ItemStack(Material.DIAMOND_PICKAXE);

ItemMeta itemMeta = itemStack.getItemMeta();
itemMeta.setDisplayName("Kohle Detektor");
itemMeta.setCustomModelData(258);
itemStack.setItemMeta(itemMeta);

itemStack.addEnchantment(Enchantment.DURABILITY, 3);
itemStack.addEnchantment(Enchantment.VANISHING_CURSE, 1);

// Ab hier definieren wir das Rezept selbst
NamespacedKey key = new NamespacedKey(this, "coal-detector");
ShapedRecipe recipe = new ShapedRecipe(key, itemStack);

// Die Form des Rezeptes -> wie man die Zutaten auf der Werkbank anordnen muss. In diesem Fall: links oben ein "C", in der Mitte ein "P" und in der unteren Reihe 3 "K"
recipe.shape(
        "C  ",
        " P ",
        "KKK"
);

// Hier definieren wir, dass "C", "P" und "K" in unserem Rezept bedeuten. Also Kompass, Spitzhacke und Kohle.
recipe.setIngredient('C', Material.COMPASS);
recipe.setIngredient('P', Material.DIAMOND_PICKAXE);
recipe.setIngredient('K', Material.COAL);

// Als letzten Schritt fügen wir das Rezept zu Bukkit hinzu.
Bukkit.addRecipe(recipe);
```
7. Das wars auch schon. Die komplette `onEnable()` Methode sollte nun so aussehen:
```java
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
```
8. Nun kannst du dir einen Kohle Detektor auf der Werkbank bauen. Allerdings, siehst du ihn noch nicht im Rezeptbuch, das kommt im nächsten Workshop.
