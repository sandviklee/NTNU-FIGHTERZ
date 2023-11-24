# JavaFX Fighting Game: NTNU FIGHTERZ
[![NTNU FIGTHERZ](https://media.discordapp.net/attachments/353907776633700363/1066753378958442506/6ae495074a7c35656342107b2aa2c2af.gif?width=837&height=454)](https://www.youtube.com/watch?v=3NE40v7QaqM)

_Discover the full dynamics of NTNU FIGHTERZ. Click on the GIF to watch the game in action._

## Table of contents
1. [Introduction](#introduction)
3. [Setup](#setup)
5. [Usage](#usage)
6. [Tests](#tests)
7. [Repository Structure](#repository-structure)
8. [Contributors](#contributors)

---
## Introduction

NTNU FIGHTERZ is a scaling fighting game inspired by the likes of Super Smash Bros. In its current avatar, players get to embody the unique character,  _"The Angry Cyclist"_, a nimble and dynamic fighter with a quirky set of moves.
Group 1 IT1901 Fall 2022 course
_Our Project is seperated into four main parts:  **base**, **docs**, **fxui** og **gameplay**_

**Base** contains userinformation. DAO; Database Access Object is used. [Base README](gr2201/base/readme.md)

**Docs** and the folder **release1** contains all the documentation for the project. Here lies all from diagrams to meetings. [Docs README](gr2201/docs/readme.md)

**Fxui** contains most of the graphical and "main" parts of the project. [Fxui README](gr2201/fxui/readme.md)

**Gameplay** contains all the game logic. [Gameplay README](gr2201/gameplay/readme.md)


## Setup
**Prerequisites:**
* Java JDK 17 or higher.
* Maven 3.8.6 or newer.

### Clone the repository
```bash
git clone https://github.com/sandviklee/NTNU-FIGHTERZ.git;
cd NTNU-FIGHTERZ;
cd gr2201;
```

### Install dependencies
To install all the necessary dependencies run the following command.
```bash
mvn clean install -DskipTests
```

## Usage
To run NTNU-FIGHTERZ, there are two systems one needs to start; the server and the game. 
One do not need to run the server to start the game, but then one can not save ones profile or log into an older account.

### Starting the game
Navigate to the directory named "gr2201" in your command line interface and execute the following command:
```bash
mvn clean compile;
cd fxui;
mvn javafx:run
```

### Starting the Server (optional)
To start the server one needs to make sure that the port 8080 is not currently in use. If it is in use you need to kill the application running on that port to make the server be able to use the port.
after that run the following code.
```bash
mvn clean compile;
cd springboot/restserver;
mvn spring-boot:run
```

## Tests
To run the full test suit, run the following command in the folder gr2201:
```bash
mvn test
```

### Genererate test coverage repport
Jacoco can be launched by typing:

```bash
mvn clean jacoco:prepare-agent install jacoco:report
```

## Contributors
<table align="center">
  <tr>
    <td align="center">
        <a href="https://github.com/EliHaugu">
            <img src="https://github.com/EliHaugu.png?size=100" width="100px;" alt="Elin Haugum"/><br />
            <sub><b>Elin Haugum</b></sub>
        </a>
    </td>
    <td align="center">
        <a href="https://github.com/Knolaisen">
            <img src="https://github.com/Knolaisen.png?size=100" width="100px;" alt="Kristoffer Nohr Olaisen"/><br />
            <sub><b>Kristoffer Nohr Olaisen</b></sub>
        </a>
    </td>
    <td align="center">
        <a href="https://github.com/SverreNystad">
            <img src="https://github.com/SverreNystad.png?size=100" width="100px;"/><br />
            <sub><b>Sverre Nystad</b></sub>
        </a>
    </td>
    <td align="center">
        <a href="https://github.com/sandviklee">
            <img src="https://github.com/sandviklee.png?size=100" width="100px;" alt="Simon Sandvik Lee"/><br />
            <sub><b>Simon Sandvik Lee</b></sub>
        </a>
    </td>
  
  </tr>
</table>

