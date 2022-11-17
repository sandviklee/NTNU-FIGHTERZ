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
**Chosen option:** Option 1, Inheritance. It might seem like unnecessary complexity if we only have a few controllers and methods, but will pay off in the long run as the app keeps expanding. It will encapsulate these methods and only allow access for the relevant Controller classes. This will also allow us to share fields between controllers, such as users.

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
**Chosen option:** Option 2, only JSON files. Originally option 3 was chosen, but after further work on the JSON format, it was deemed that combining JSON for general stats and individual classes for character specific traits would not be necessary. In the original plan, the current "Player/Character" object would be a superclass for specific classes with character names. After creating the first character however, we saw that the extra classes were not needed and decided to go with option 2, where all data is saved in JSON.

## **3. Where to throw exceptions**
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
**Chosen option:** Option 1, throw exceptions in model, catch exceptions in controller. This will make it easier to have all exceptions in one layer.

## 4. Module VS package VS class
### Context and Problem Statement
What defines a module, a package and a class? What are the criteria for these? When creating a new feature or set of features, how do we decide how to split up the application?
### Considered Options
There were no specific "options" considered during this design choice, but rather a series of criteria discussed for what defines these different parts of the project. A module should be its own contained part of the application, such as a logic layer, a REST API or a UI module. A package should contain classes that depend on each other and implement similar features to the app, and packages that depend on each other should be in the same module. In addition, we also discussed case specific modules, packages and classes, and decided on the ones described below.
### Decision Outcome
* Module "fxui": The GUI, visuals and controllers in the app. No mdoels, only related to what is shown on screen. Consists currently only of the "ui" package.
* Module "base": Includes packages related to "users", "dbAccess"/database and JSON reading. Consists of core logic and functionality of the app that is related to data handling and not gameplay.
* Module "gameplay": Contains all gameplay logic classes.
* Module "springboot": Consists of the REST API and server.

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


## 6. Persistence and how to access it
### Context and Problem Statement
How shall the program access data from persistence layer? There are several different ways to accomplish this and it is important to separate the different layers.
### Considered Options
#### **Option 1: Use DAO pattern**
Description.
|Pros | Cons|
|---- | ----|
| Abstraction for actual database access implementation separates the data access strategy from the user business logic and the UI. Creates a clear access point between the persistent level and business logic. | Creates more boilerplate. |
| Makes the business logic program for the interface rather than the implementation. This makes it possible to treat the implementation as a black box and any changes to implementation does not affect the rest of the program. | The DAO hides database access calls. These are often very expensive, and it could be cheaper if one makes specific SQL queries. |
| Easier for others to understand and maintain as the DAO design pattern is well known. |  |

#### **Option 2: Use DAO and Repository pattern**
Description.
|Pros | Cons|
|---- | ----|
| Only one access point, which will abstract away more of the implementation details that are not important to see and is useful when dealing with several data storages. | This will increase the complexity of the program.
| | This option will also add the most boilerplate of all the options, as well as making it harder to read. |

#### **Option 3: Only implementation**
Description.
|Pros | Cons|
|---- | ----|
| Only one access point with no extra boilerplate code. | If new changes are implemented, will need to change how the rest of the program uses the implementation.
| | Can't treat it as a well-defined black box where you only need to care about the input/output. |

### Decision Outcome
**Chosen option:** Option 1, using the DAO pattern. In this project, we're going to change the format of the stored data and might have to make new changes along the way. This happened in the first assignment, where the project originally used CSV files, but afterwards changed it to JSON files. This will make the DAO pattern very helpful as the rest of the application can be programed for the interface and treat the implementation as an black box.


## 7. Framework to use for rest server API
### Context and Problem Statement
### Considered Options
#### **Option 1: Spring MVC**
Description.
|Pros | Cons|
|---- | ----|
| More freedom. | Meant for lower level servers. |

#### **Option 2: Spring boot**
Description.
|Pros | Cons|
|---- | ----|
| Easier to setup| Less freedom |

### Decision Outcome
**Chosen option:** Option 2, spring boot. Spring boot does much of the heavy lifting and stops us from reinventing the wheel when we already have an amazing framework to use, and we were recommended to use this framework.

## 8. How to implement users
### Context and Problem Statement
How is the program going to represent a user? The User shall contain at least a username and password, however future iterations might include achievements and player statistics, such as amount of wins, losses and Matchmaking Rating (MMR). As such it is nice to have an easily changeable user object that allows easy implementation of new features and fields. What should identify different users is also a part of the discussion, and as of the current implementation, it is planned that the username is going to be the unique primary key of each user.
### Considered Options
#### **Option 1: User class containg all data**
Description.
|Pros | Cons|
|---- | ----|
| All information about the user in one class increases the leanness of the project and removes duplicate code. | Changes can make maintenace harder. |
| All in one place and high fan-out, which is beneficial for high level classes. | Reusability is lower as if the user id or user data is different, one must change most of the User class. |
| | Changes to this class will affect all classes using this or parts of the user class. |
| | Harder to test, as the User class will contain many different parts, none of which are separated, making it harder to track where the error is coming from. |

#### **Option 2: User class being made from UserId and UserData**
Description.
|Pros | Cons|
|---- | ----|
| ID fields and data fields are often subject to change. Having a separate class that is responsible for handling what identifies users, will make changes easier later on as new changes/fields are implemented. | If there are no changes to what identifies a user then this can create more unnecessary code. However, for future iterations, new fields and changes to identification has been discussed. |
| Higher fan-out as other classes can choose to use only the specific parts of the User class that they need, and not have to wrestle with all checks for the entire class. I.e. if one is going to search for a specific user in the database, then one can use only the UserId and check for that instead of using the entire User class with non relevant details. | Creates more code to maintain, such as the serializers and deserializers for the UserId and UserData classes. These are however easier to maintain as they split up the different parts of the user, and changes to certain parts will only affect certain deserializers and serializers, instead of all of them. |
| Easier to make changes to UserData and UserId as they will not affect each other. | |


### Decision Outcome

## 9. Issue what framework to use for rest server API
### Context and Problem Statement
### Considered Options
#### **Option 1: Jersey**
Description.
|Pros | Cons|
|---- | ----|
| | |


#### **Option 2: Jetty**
Description.
|Pros | Cons|
|---- | ----|
| Can act as an HTTP server. | |

### Decision Outcome
**Chosen option:* Option 2, Jetty. Jetty is often used alongside spring boot, and can act as an HTTP server, and as such, we were recommended to use Jetty for this project.

<!---
## 11. Issue
### Context and Problem Statement
### Considered Options
#### **Option 1: Option**
Description.
|Pros | Cons|
|---- | ----|
| | |
### Decision Outcome
-->
