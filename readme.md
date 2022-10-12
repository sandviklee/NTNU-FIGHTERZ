# Java Fighting Game: NTNU FIGHTERZ Project
## Group 1 IT1901 Fall 2022 course

[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2201/gr2201.git)

---

### Hvordan åpne prosjektet

Prosjektet vårt kan åpnes ved å skrive inn:

 `mvn javafx:run` i terminalen din.
 
---

### Hvordan kjøre Jacoco og få rapporten

Jacoco kan åpnes ved å skrive inn:

`mvn clean jacoco:prepare-agent install jacoco:report` i terminalen din.


---

### Om prosjektet

_Prosjektet vårt er delt opp i tre hovedmapper i mappen gr2201.
Her har vi **base**, **docs**, **fxui** og **gameplay**._ 

**Base** inneholder alt som omhandler brukerinformasjon. Det er kode som feks. DAO, hvor Database Access Object partern blir fulgt. [Base README](gr2201/base/readme.md)

**Docs** og undermappen **release1** inneholder all dokumentasjon om prosjektet fram til nå. Her ligger alt fra diagrammer, figma og møtereferat. [Docs README](gr2201/docs/readme.md)

**Fxui** inneholder hovedkoden vår, alle fxml filer, bilder, og kontrollere. Her er det mesteparten av _kodingsprosjektet_. [Fxui README](gr2201/fxui/readme.md)

**Gameplay** har alt som har med spillogikken å gjøre. Her er alle klassene som får hovedspillet til å kunne kjøre. [Gameplay README](gr2201/gameplay/readme.md)

---


