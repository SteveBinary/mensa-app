# Mensa App

Das Mensa-Essen des Studierendenwerks an der DHBW Heidenheim muss vorbestellt werden.
Es existiert eine nicht sonderlich benutzerfreundliche Webseite, auf der das Essen der nächsten Tage angesehen und bestellt werden kann.

## Mindestanforderungen der App

- Essen der nächsten Tage strukturiert anzeigen
    - Name des Essens
    - Beschreibung des Essens
    - Preise
    - Uhrzeiten
    - Anzahl der Bestellung
- Ein oder mehrere Essen auswählen und bestellen
    - auch unterschiedliche Essen an verschiedenen Tagen und verschiedenen Uhrzeiten; das geht nicht mit der Webseite
- Einstellungen, um Daten zu speichern, um sie für zukünftige Bestellungen wiederzuverwenden (Sollen ohne App-Neustart in der gesamten App übernommen und gleichzeitig persistiert werden)
    - Vorname, Name
    - valide Email
    - Zustimmung der Nutzungsbedingungen des Anbieters
    - weitere Präferenzen

## Features für die weiteren Anforderungen der Aufgabenstellung

- Jetpack Compose
- Auswählbare Essen in einem Tab einsehbar und Menge und Uhrzeit jeweils spezifizierbar
- Caching der Essen auch über Neustart der App (Room database)
- Gesten: Swipe to refresh (zur Aktualisierung der Übersicht der bestellbaren Essen)
- Bestellhistorie kann in eigenem Tab eingesehen werden (Room database) und vergangende Bestellungen können ausgeblendet, sodass nur zukünftige Essen angezeigt werden
- Änderungen, wie das Bestellen von Essen, in einem Tab wirken sich direkt auf den andere Tabs aus
- Alles in der App ist so persistiert, dass eine Rotation des Geräts oder ein vorübergehendes Schließen in den App-Switcher keinen Reset verursachen

- Animationen für verbesserte User Experience
- speziell angepassste Light und Dark Themes, Erstellung von Mockups als Konzept für UI Design
- Material 3 Design Umsetzung
- Übersetzungen an allen möglichen Stellen (alles was nicht von der API kommt)
- Homescreen-Widget, das anzeigt, wie viele Essen für den heutigen Tag bestellt wurden (bis eine Stunde nach dem Zeitslot)

- Error handling
   - Wenn das Gerät gar nicht mit dem WLAN oder Mobilen Daten verbunden ist, wird eine Netzwerkanfrage gar nicht erst gestartet. Dann wird ein Toast angezeigt, wo steht, dass man nicht verbunden ist.
   - Geht eine Request schief, wird das abgefangen und eine entsprechende Meldung angezeigt.
   - User Inputs werden validiert

## Mögliche weitere Features

- Bestellung bei anderen Mensen des Anbieters (Dies ist prinzipiell bereits möglich und wurde mit der Mensa in Aalen (mensaId=5) erfolgreich getestet. Jedoch soll sich die Bestellung ersteinmal auf Heidenheim (mensaId=15) beschränken, weshalb es in der UI keine Einstellung dafür gibt.)
- Für jede Bestellung in Bestellhistorie mit Hilfe des zu hinterlegenden Buchungscodes einen QR-Code für Abholung generieren (Die API gibt den Buchungscode nämlich nicht in der Response zurück)
- Bestellungen in der Bestellhistorie manuell löschen
- Notification für bestellte Essen
- Erweitertes UI Testing

## Styleguide

Im Styleguide werden die Farben in Hex für jedes Element der UI definiert.
Im Finalen Design werden aus funktionalen Gründen nicht alle Aspekte des Mockups eins zu eins umgesetzt werden, allerderings wird besonders auf die Umsetzung der farblichen Akzente geachtet.

![homescreen_dark_mockup](/docs/mocks/Home_Dark.png)

Die restlichen Mockups sind unter docs/mocks zu finden.

### Light Theme

- Buttons/Warenkorb auf Karten/Umrandung Preis aktive Karte/abbrechen/Settings Headline/Settings Fields/ Non-Toggle_inner/aktive toggle point 	
   - #866c00
	
- Background Body 
   - #fffaf7
- Cards:	
   - #ffffff
- Schrift/Card Outlines/Bottom Bar Icons nicht aktiv	
   - #000000
- Header/Bestellen Button/aktive Toggle inner	
   - #ffdf5b
- Non active toggle outer/ non akctive toggle point	
   - #e4dec6


### Dark Theme

- Header/schrift auf aktivem Tab in bottom bar/ Warenkorb icon in header/ schrift default  
   - #000000
- Background	
   - #222a35
- Cards/Bottom Bar/ Warenkorb Ansicht Card	
   - #333f50
- Food Icon Top/ Background Cart Icon Top/ Buttons/ Cart on Crads/ active Card Price Outline/ Active Time Outline/ Abbrechen Button Schrift/ Bestellen Button Background / Settings Input Field outline / input field name / active Toggle inner	
   - #ffdf5b
- Non active toggle outer/ non akctive toggle point	
   - #e4dec6
- Active toggle point / non active toggle inner	
   - #866c00

