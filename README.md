## Zusammenfassung 3. Termin | 18.12.2020

Bei diesem Workshop haben wir unseren Kohledetektor so angepasst, dass nicht nur Blöcke unter dem aktuellen Block sondern auch die umliegenden Blöcke überprüft werden.

## Voraussetzungen
- Den Code vom [2. Termin des Workshops (Fortgeschrittene)](https://github.com/KatharinaSick/coderdojo-minecraft-material-detector/tree/2020-12-15_second-workshop-advanced) ausführen können.

## Anleitung
1. Öffne die Klasse `Detector` und füge die unten stehenden Methode hinzu. Diese überprüft nicht nur den Block, auf den der Spieler aktuell zeigt, sondern auch die umliegenden Blöcke. Wenn sich unter einem der Blöcke das gesuchte Material (Kohle) befindet, gibt sie "true" zurück, sonst "false".
```java
public boolean isMaterialInRange(World world, Location location) {
    for(int x = location.getBlockX() - 1; x <= location.getBlockX() + 1; x++) {
        for(int z = location.getBlockZ() - 1; z <= location.getBlockZ() + 1; z++) {
            if (isMaterialBelowLocation(world, new Location(world, x, location.getBlockY(), location.getBlockZ()))) {
                return true;
            }
        }
    }
    return false;
}
```
2. Öffne nun den `PlayerEventListener` und ersetze das letze `if(..){..}` mit dem folgenden Code. Dieser überprüft, ob sich das gesuchte Material (Kohle) direkt unter der `targetLocation` befindet und spielt dann den Ton mit volume 1 und pitch 2 ab. Wenn sich das Material nicht unter dem Block, auf den der Spieler zeigt, befindet, wird überprüft ob es sich unter den umliegenden Blöcken befindet. Wenn ja, wird der gleiche Ton mit volume 0.5 und pitch 1 abgespielt. 
```java
if (detector.isMaterialBelowLocation(player.getWorld(), targetLocation)) {
    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 2);
} else if (detector.isMaterialInRange(player.getWorld(), targetLocation)) {
    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 0.5F, 1);
}
```
