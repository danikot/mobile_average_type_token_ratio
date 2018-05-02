Author:
Daniel Stortchilov


---

0.
Angehängt ist:

0.a) Lauffähiges Programm:
- MATTR.jar
Gestartet durch:

java -cp MATTR.jar main.Main *.txt

Anstatt von *.txt eine beliebige durch Leerstellen getrennte Liste von Dateien stehen.
Sie erstellt zu jeder eingegebenen Datei vier neue (alphabetisierte Typesliste, 20 häufigste Types mit Anzahl, 20 häufigste großgeschriebene Types mit Anzahl, MA(TTR)) und liefert letzteres auch dem CLI raus.
0.b) Kommentierter Sourcecode:
- main.java
0.c) Ausgabe: 
- Ausgabe.zip

---

3.Über den Tellerrand
Die verwandte Bibliothek (stanford nlp) ist im unterordner /lib/ zu finden.
Der Sourcecode mit Bibliothek ist Main_lib.java. Um die jar Datei zu starten, schreibe man:

java -cp MATTR_lib.jar main.Main_lib *.txt

---

1.a)
Die alphabetische Liste aller Types ist in Ausgabe.zip beigeliefert.

1.b)
die 20 häufigsten Wortformen sind

 - in den Wahlverwandschaften:
und=2516
die=1702
zu=1541
sie=1414
der=1374
sich=1281
nicht=959
in=944
er=884
das=808
es=700
den=695
mit=669
so=668
auf=586
daß=555
war=555
von=553
dem=552
ich=518

 - in den Räubern:
und=1033
der=751
die=740
ich=709
den=487
du=483
nicht=465
in=451
das=442
zu=413
ist=402
Moor=364
er=353
ein=349
auf=322
so=287
mich=283
mit=281
mir=280
ihr=270

1.c)
Die 20 häufigsten großgeschriebenen Wortformen sind

 - in den Wahlverwandschaften:
Sie=416
Eduard=270
Charlotte=259
Ottilie=215
Der=172
Er=171
Die=160
Ottilien=151
Ich=133
Es=125
Zeit=117
Hauptmann=115
Das=104
Charlotten=100
Man=97
Gesellschaft=88
Weise=79
Art=75
Und=75
Welt=73

 - in den Räubern:
Moor=364
Franz=217
Amalia=203
Ich=203
Er=136
Vater=95
Spiegelberg=93
Schweizer=93
D=92
Daniel=92
Was=90
Sohn=85
Es=85
Sie=84
Du=83
Und=80
Herrmann=80
Hauptmann=75
Das=64
Hand=63

1.d)
Problematische, also falsch eingelese, Wortformen wurden, hoffe ich doch, ausgemerzt. Ob man den Token nicht auch anders definieren könne, als es in dieser Übungsblattbearbeitung geschehen ist, läuft als ein sprachphilosophisches Problem über die Grenzen dieser Übung hinaus.

---

2. a)
- Goethe-Wahlverwandtschaften-clear.txt

Type/Token Ratio: 0.1345846
Moving-Average Type/Token Ratio with Window size 300: 0.6345762

- Schiller-Die-Raeuber-clear.txt:

Type/Token Ratio: 0.19065522
Moving-Average Type/Token Ratio with Window size 300: 0.6274611

2. b)
Die Ergbnisse unterscheiden sich, natürlich, schon.

2. c)
Je 2 Token selben Typs die in einer Textdatei mehr als um 300 Token von einander weg liegen werden bei der Berechnung des MATTR als 2 verschiedene Typen betrachtet.

