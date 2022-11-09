# Architectural Decision Records

## **1. Use inheritance in controller change methods**
### Context and Problem Statement
The project will contain many different pages and controllers. As such a lot of code will be similar between these controllers, in particular switching between different controllers.

### Considered Options
#### **Option 1: Inheritance**
Create a SceneController class that has the method for switching between controllers. All controllers will inherit this method.
|Pros | Cons|
|---- | ----|
| Share fields that all controllers have across controllers, such as User. | More complex to iplement, but will pay off in the long run. |
| Makes sure only controllers can use these methods, making the code more robust snd better encapsulation. |
<br />

#### **Option 2: Static method**
Can create a class with static methods that the controllers can use.
|Pros | Cons|
|---- | ----|
| Easy to implement, already have a class that can do this. | Worse encapsulation that the previous suggestion.
| | Does not provide an easy way share fields between controllers, need to make methods for this.
<br />

#### **Option 3: Containment**
SceneController in each controller.
|Pros | Cons|
|---- | ----|
| Easily tailor these methods to each controller, though most likely there will not be a lot of changes between different controllers. If needed, these methods can anyway be added later by overriding or adding static methods. | Lots of repetitive code and unnecessary work. |
<br />

### Decision Outcome
**Chosen option:** Inheritance. It might seem like unnecessary complexity if we only have a few controllers and methods, but will pay off in the long run as the app keeps expanding. It will encapsulate these methods and only allow access for the relevant Controller classes. This will also allow us to share fields between controllers, such as users.

## **2. Save character information in JSON file**
### Context and Problem Statement
The game will in the first iteration only contain 4 characters, but these characters come with a lot of different information about their attributes and actions. As such, the app needs a clever way to store this information.

### Considered Options
#### **Option 1: Classes for each character**
Create a new Java class for each character, and when instantiating a character, just initialize an object of that character's specific class.
|Pros | Cons|
|---- | ----|
| Easy to implement features that are specific for one particular character. | Implementing new characters will require a whole new class, and as the app expands and more characters are added, the amount of classes will too. This might be solved by defining a format for these classes. |
| Easy to change implementation for certain methods for each character. | If the characters are not universally defined, the interactions between their different methods and actions might be harder to predict. |
| | A lot of repetitive code for similar methods. |
<br />

#### **Option 2: JSON file format for characters**
A JSON file will contain all information relevant to each character and their moveset, and any character can be initialized through following this JSON format.
|Pros | Cons|
|---- | ----|
| Easy to implement new characters, there is a "recipe"/format for creating each character. | Might require adding many fields that are unnecessary for some characters as well as unnecessary complexity to implement character specific details. |
| Interactions between different characters' moves are predictable as they follow the same format and behaviour between them is defined. | May prove difficult to define certain methods or moves in text format and translating them to code.  |
| Less "magic numbers" saved in each class, these will be put in the JSON file. | |
<br />

#### **Option 3: Combine JSON and classes for each character**
This option combines suggestion of having a JSON file and classes for different characters. A JSON file will contain the general information for each character with methods and fields that are shared by all characters. A superclass is created from this information, and character specific classes are then created which inherit these general attributes and methods, but also add new character specific methods.
|Pros | Cons|
|---- | ----|
| Removes the need to generalize all moves to the same pattern, which may prove difficult for certain characters as well as "bloat" them with unnecessary fields/methods. | Still requires many classes, as each character needs its own class. Despite this it will not take as much work to implement a new character, as this class will only contain outlier, character-specific methods and/or overrides. |
| Much easier and less complex to implement character specific details. | Two places to implement new characters instead of just one. |
| Combines the pros of both the previously discussed options. | |
| Moves that differ too much from the standard pattern can be easily overrided. | |

#### **Option 4: Use JSON format for characters and do character specific traits through "Effects" class**
This option suggests using the JSON file format for saving character information, and doing character specific traits through effects placed on characters. For example, if a character boosts their attack damage after using a certain move, the character is applied an "Attack Boost" effect that provides these specific traits. This can be done through an Effect class or a set of static methods.
|Pros | Cons|
|---- | ----|
| Much easier to implement and access. | A lot of unrelated methods in one class. |
| Does not add bloat to character. | If static methods, allows access to classes that should maybe not have access to them. |
| Does not create many unnecessary classes. |  |

### Decision Outcome
**Chosen option:** Option 3, combining JSON for general stats and individual classes for character specific traits. The current "Player/Character" object will now be a superclass for specific classes with character names. The characters 

## 3. Where to throw exceptions
### Context and Problem Statement
Where to throw exceptions and where to catch them should be defined to ensure that exceptions are properly handled. 
### Considered Options
#### **Option 1: Throw in model, catch in controller**
Here the logic layer will not handle any of the exceptions or errors that may occur, this will all be dealt with in the controllers and then properly represented in the GUI.
|Pros | Cons|
|---- | ----|
| Avoid handling exceptions numerous times or not handling them at all, as where to handle them and where to throw them is clearly defined. | The controller should not contain too much logic. This will however most likely not be a problem as the only thing it adds to the controller, is a "try catch" instead of an "if else". |
| Makes it easier to test the logic classes, as we know they will always throw exceptions and not catch them, and as such only need to check for thrown exceptions. | Adds a "middleman" to handling exceptions. This might be better though, as it separates the two parts of handling exceptions: throwing and catching. |
| What the controller takes in is predictable, and any errors that may occur will be dealt with with catch statements. Without this it would be case specific how the errors will be handled in the controller. | |
#### **Option 2: Throw and handle in model**
In this option all handling of exceptions will happen in the model classes, and the controller classes will not have to do any "decisions" on what to output/show in the GUI.
|Pros | Cons|
|---- | ----|
| All exception handling and logic is done inside the model. | Ambigious what the controller will take in to show the different errors: will it take in a string and output it? What if the app completely crashes due to this? Will it consist of many if else statements, or will more catch statements be required in the controller as well to prevent the app from crashing? |
| Handle all exceptions as they happen without any middlemen. | |
### Decision Outcome
**Chosen option:** Throw exceptions in model, catch exceptions in controller.

## 4. Module VS package VS class
### Context and Problem Statement
What defines a module, a package and a class? What are the criteria for these? When creating a new feature or set of features, how do we decide how to split up the application?
### Considered Options
There were no specific "options" considered during this design choice, but rather a series of criteria discussed for what defines these different parts of the project. A module should be its own contained part of the application, such as a logic layer, a REST API or a UI module. A package should contain classes that depend on each other and implement similar features to the app, and packages that depend on each other should be in the same module. In addition, we also discussed case specific modules, packages and classes, and decided on the ones described below.
### Decision Outcome
* Module "fxui": The GUI, visuals and controllers in the app. No mdoels, only related to what is shown on screen. Consists currently only of the "ui" package.
* Module "base": Includes packages related to "users", "dbAccess"/database and JSON reading. Consists of core logic and functionality of the app that is related to data handling and not gameplay.
* Module "gameplay": Contains all gameplay logic classes.
* Module "rest": Consists of the REST API and server.

## 5. Document metaphor
### Context and Problem Statement
Persistency, storing and fetching data can be represented by two main models: desktop style, where the user has to explicitly save files, and app style, where the data is saved implicitly and automatically. How will our video game store its data?
### Considered Options
#### **Option 1: Desktop style**
In desktop style the user has to explicitly save their data. In the signup screen of the application, the user inputs a username and password and then requests this to be saved to the database. This would be an example of how the app uses the desktop metaphot, but this is only suitable for account details of the application. For the game itself, the desktop style would not be suitable. For example, if game statistics were to be saved, this would allow a user to only save specific games, such as only wins and removing all losses. In addition, it is also unintuitive and extra work to have to save all the statistics and states that are saved in the game manually. In a singleplayer roleplaying game this might be more intuitive, as you often save progress at specific points or manually save your progress in your own adventure. For a multiplayer fighting game on the other hand, the saving should not have to be done explicitly, as it might create mismatch between data shared by multiple users as well as manipulate the users' game data.
#### **Option 2: App style**
In desktop style the user's data is implicitly and automatically saved. This does not fit for the account details of the application, as the user has to input their details for them to be saved. For the game however this model works well. As aforementioned, manually saving your game data is unnatural for a game of this genre, especially as multiplayer is intended. Therefore the game itself will use app style saving, where the user doesn't have to think about saving their progress in this multiplayer game, but where the game rather saves all important data and statistics behind the scenes.
### Decision Outcome
**Chosen option:** As the application is quite big and contains a lot of features, some parts of the application pertain to the desktop style while other features fit the app style more. Due to the nature of the game, things such as user details will follow the desktop style, while the game itself will follow the app style.


## 6. Presistens The use of DAO pattern
### Context and Problem Statement
### Considered Options
#### **Option 1: Use DAO pattern**
Description.
|Pros | Cons|
|---- | ----|
| Abstraction for actual database access implementation separates the data access strategy from the user business logic and the UI. Creates a clear access point between persistent level and business logic. | Creates more boilerplate. |
| Makes the business logic program for the interface rather than the implementation. Witch make it possible to treat the implementation as a black box and changes to implementation does not affect the rest of the program.| The DAO does hide database access calls. These are often very expensive, and it could be cheaper if one makes specific SQL queries.|
| Easier for others to understand and maintain as the DAO design pattern is well known. | Restricts us to this design |

#### **Option 2: Use DAO and Repository pattern**
Description.
|Pros | Cons|
|---- | ----|
| Only one access point, the will abstract more and is useful for several datastorages.  | This will increase the complexity of the program. This option will also add the most boilerplate of all the options, as well as making it harder to read. |

#### **Option 3: Only implementation**
Description.
|Pros | Cons|
|---- | ----|
| Only one access point, no exstra boilerplate code, | On changes need to change how the rest of the program us the implementation. Cant treat it as well as an black box |

### Decision Outcome Option 1
This project where going to change the format of the storede data, as in first assignment the project were going to use CSV files, afterward it would be changed to JSON files. This will make the DAO pattern very helpful as the rest of the application can be programed for the interface and treat the implementation as an black box.


<!---
## 5. Issue
### Context and Problem Statement
### Considered Options
#### **Option 1: Option**
Description.
|Pros | Cons|
|---- | ----|
| | |
### Decision Outcome
-->
