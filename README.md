# Coderdojo Workshops: Minecraft Material Detector

In diesen Workshops programmieren wir ein Java Plugin für Minecraft. Dabei starten wir ganz einfach damit, den Spieler 
auf Kommando zu begrüßen und werden in weiterer Folge einen "Material-Detektor" bauen. Dieser soll ähnlich wie ein 
Metalldetektor funktionieren und einen Ton abspielen, wenn ein bestimmter Block (z.B. ein Diamant) in der Nähe ist
. [In diesem Video](https://youtu.be/EDVATLCACEM) siehst du, wie das dann in etwa aussehen soll.

[![Material Detector Demo](https://img.youtube.com/vi/EDVATLCACEM/0.jpg)](https://www.youtube.com/watch?v=EDVATLCACEM)


## Zusammenfassung 1. Termin | 11.20.2020

Bei diesem Workshop haben wir uns darauf konzentriert, dass alle auf ihrem lokalen Minecraft Server spielen können
 und haben danach ein kleines Plugin geschrieben, welches den Spieler auf Kommando begrüßt. Unten findest du eine
  kurze Übersicht der Dinge, die wir im Workshop besprochen haben.
 
## Voraussetzungen
- Eine offizielle Installation von Minecraft (Java Version) mit Version 1.16.4
- [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/) mit Version 2020.2.3
- Java 11

## Anleitunng

1. Erstelle einen neuen Ordner. Ich habe meinen zum Beispiel `minecraft-server` genannt.
2. Lade die Datei `spigot-1.16.4.jar` herunter und speichere sie in deinem neuen Ordner.
3. Mach einen Doppelklick auf die Datei `spigot-1.16.4.jar` um sie auszuführen. Das kann ein paar Sekunden dauern
. Danach sollte es in deinem Ordner so aussehen: 
![spigot-1.16.4.jar nach dem ersten Ausführen](readme/images/spigot-eula.png)
4. Öffne nun die Datei `eula.txt` und ändere die Zeile `eula=false` auf `eula=true` um dem Endebenutzer-Lizenzvertrag
 zuzustimmen.
5. Mach einen Doppelklick auf die Datei `spigot-1.16.4.jar` um sie auszuführen. Das kann wieder ein bisschen dauern
. Danach sollte ein neues Fenster aufgehen, indem der Server ausgeführt wird. Der Server läuft, wenn in der letzten
 Zeile des Fensters `[11:04:02 INFO]: Done (20.386s)! For help, type "help"` steht. Die Zahl in der Klammer kann
  dabei abweichen.
6. Öffnne nun Minecraft und klicke auf "Mehrspieler" -> "Server hinzufügen" und gib "localhost" als Serveradresse ein
. Klicke danach auf "Fertig".
7. Nun kannst du deinen Server in der Liste auswählen und darauf spielen.
8. Öffne nun IntelliJ IDEA und klicke auf "New Project".
9. Wähle links in der Liste "Gradle" mit einem Klick aus. 
10. Setze die Project SDK auf 11 und wähle bei "Additional Libraries and Frameworks" "Java" mit einem Klick aus. Das
 sollte dann in etwa so wie unten im Bild aussehen.
![IntelliJ IDEA Projekt erstellen](readme/images/intellij-new-project.png)
11. Klicke auf "Next".
12. Gib den Namen deines Projektes bei "Name:" ein. Ich habe hier "MaterialDetector" eingegeben. Klicke dann auf "Finish".
13. Öffne die Datei `build.gradle` und füge das Repository hinzu, von dem später die Bukkit Dependency
 heruntergeladen wird. Füge dazu im Block `repositories {...}` das folgende Repository hinzu:
```
maven {
    url "https://hub.spigotmc.org/nexus/content/repositories/public/"
}
```
14. Füge die unten stehende Zeile im Block `dependencies {...}` ein, um die Bukkit Dependency hinzuzufügen.
```
implementation group: 'org.bukkit', name: 'bukkit', version: '1.15.2-R0.1-SNAPSHOT'
```
15. Deine `build.gradle` Datei sollte nun so aussehen:
```
plugins {
    id 'java'
}

group 'org.example'
version '1.0-SNAPSHOT'

repositories {
    mavenCentral()
    maven {
        url "https://hub.spigotmc.org/nexus/content/repositories/public/"
    }
}

dependencies {
    implementation group: 'org.bukkit', name: 'bukkit', version: '1.15.2-R0.1-SNAPSHOT'
    testCompile group: 'junit', name: 'junit', version: '4.12'
}
```
16. Mache einen Rechtsklick auf den Ordner `src/main/java` und klicke auf "New" -> "Java Class" um eine neue Java
 Klasse zu erstellen.
17. Gib den Namen "MaterialDetector" ein und drücke auf Enter.
18. Füge den folgenden Code in die neue Klasse ein. Was es damit auf sich hat, kannst du [in dieser Übungsanleitung](https://linz.coderdojo.net/uebungsanleitungen/programmieren/minecraft/plugins/erstes-plugin/#die-klasse-programmieren) nachlesen.
```java
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
```
19. Mache nun einen Rechtsklick auf den Ordner `src/main/resources` und klicke auf "New" -> "File". Gib den Namen
 "plugin.yml" ein und drücke auf Enter.
20. Füge den folgenden Code in die neue Datei ein. Details dazu kannst du wieder [in dieser Übungsanleitung](https://linz.coderdojo.net/uebungsanleitungen/programmieren/minecraft/plugins/erstes-plugin/#das-plugin-yml-anlegen-das-projekt-bauen-und-testen) nachlesen.
```yml
name: MaterialDetector
main: MaterialDetector
version: 0.1
api-version: 1.16
commands:
  sayhello:
    description: Sagt Hallo
    usage: /sayhello
```
21. Klicke in der rechten Sidebar von IntelliJ IDEA auf "Gradle".
22. Öffne dein Projekt mit einem Doppelklick auf deinen Projektnamen (in meinem Fall "MaterialDetector"). 
23. Klicke nun auf "Tasks" und dann auf "build". Die Sidebar sollte nun so aussehen:
![IntelliJ IDEA Gradle Sidebar](readme/images/intellij-gradle.png)
24. Klicke nun auf "jar" (im Bild oben blau markiert), um eine JAR Datei zu erstellen, die deinen Code beinhaltet.
25. Öffne nun den (neuen) Ordner "build" in der linken Sidebar und öffne darin den Ordner "libs".
26. Kopiere die darin enthaltene .jar Datei.
27. Gehe nun zu dem Ordner, den du zu Beginn für den Server erstellt hast. Öffne den Ordner "plugins".
28. Falls dein Server noch läuft, stoppe ihn. 
29. Füge die kopierte Datei im Ordner "plugins" ein.
30. Starte deinen Server erneut mit einem Doppelklick auf `spigot-1.16.4.jar`. 
31. Wenn du nun Minecraft öffnest und dich zu deinem Server verbindest, kannst du nun das Kommando "/sayhello
" eingeben. Der Server wird dich dann begrüßen :)
