# User stories
## Backlog

### Login and account creation (us-1)
**As a new player, I want to be able to create an account and log in so that I can see what the game is about.**

The user needs to be able to input user information, like username and password, and be able to login with these details. The details are to be saved to a database, which in first iteration will just be a csv file stored locally to the user's computer. The data should be saved for the next time the user opens the application. The user may then input the previously saved user details and these will be checked up against details saved in the database. The application should also prevent users from choosing the same usernames.


The user should also be able to see what options they have and the content of the game after logging in. This will be done by the main menu screen which will include buttosn to the various features of the game. This includes multiplayer online mode, singleplayer practice mode, character information, settings and exit game button.

##### Important to see
* input fields to log in and sign up
* confirmation that user details input correspond to a user saved in the database
* error message if user details input do not correspond to a user saved in the database
* confirmation that your account has been created with your chosen account details
* error message if you try to sign up with an occupied name
* buttons containing main elements of game
##### Important actions
* put in user details in signup
* put in user details in login and log in with said details
* press buttons on main menu screen after login to try out various parts of the game

### Character information (us-2 and us-3)
**As a player that likes fighting games and knows a bit about them, I want to know what moves the different characters have so that I can choose one that fits my playstyle.**

**As a new player that hasn’t played a lot of fighting games, I want to know the playstyles of the different characters so I can get an idea of what might be interesting for me.**

The user needs to easily be able to find information about the game's characters within the game. The game should provide information such as moveset, speed, power, knockback and other attributes that will play an impact in whether or not the player would enjoy this character/the character fits the player's playstyle. The character information will be saved in text format so that new characters and expansions can easily be integrated into the game by following this pattern. The user will be provided with long descriptions but also summaries so that players who just want to get the gist of it and get right into the game can do that, while those who like knowing a bit more about the game can read the detailed descriptions.

##### Important to see
* easily accessible information about character attributes and moves with detailed descriptions
* easily understand the gist of a character with more beginner friendly information, keywords instead of long descriptions
* visuals supplementing text to easier see how a move works
* information about how to unlock this character (might be kept secret or with hints)
##### Important actions
* click characters to expand details about them
* click character moves to see animation of them

### Local singleplayer (us-4)
**As a new player I want to be able to try out new characters before taking them to the battlefield so that I know how to play them.**

The user needs to be able to try out characters in a singleplayer mode with a training dummy before trying them out against real players. The dummy will in first iteration only be a stationary character with hitbox and hurtbox, but might in later iterations involve some sort of AI fighter (though most likely the project span does not give sufficient time for that). Here the player can use the different moves of the character and see how they work, as well as do secret combos to unlock new characters (maybe?).
##### Important to see
* a selection screen with all possible characters the player can pick from
* feedback on which character has been pressed and chosen
* a button to confirm chosen character
* training grounds and dummy that player can try out moves on
* pop-up menu with settings (can use same as on main menu) and go back button
##### Important actions
* choose which character one will play in the match
* start match when the user is ready
* control the character
* go back when player is done practicing

### Online gameplay (us-5)
**As a gamer I want to test my skill against others around the world to see who’s the best.**

The user needs the possbility to play with others who play the game through internet. This will make it easier to be able to play with friends that might live far away, or play with new people from around the world. The player needs to be able to find their friends' users and invite them to a game. In first iteration it will only be able to play against people you choose and know, but future iterations might include a matchmaking system. In addition a friends list and system to save people you want to play with would be preferable.
##### Important to see
* a selection screen with all possible characters the player can pick from
* confirmation button to start game
* character responding after interaction (input from keyboard)
* account name of who one plays against and which character they play
* enemy character respond to interaction from your character
* your character respond to interaction from enemy character

##### Important actions
* choose which character one will play in the match
* confirm character to start the match
* control the character

### Tutorial (us-6)
**As a child who is new to video games I want to get sufficient information and tutorial on how to play so that I can start playing.**
##### Important to see
* 
##### Important actions
*

### More characters (us-7)
**As a player who has played this game a lot I want to get new characters so that I can get a new experience from this game.**

The player need variance in characters to keep engagement. The characters will be unlocked gradually through different achievements to 
##### Important to see
*
##### Important actions
*

### Account details (us-8)
**As a player who mistyped my information when creating an account I want to be able to change my account details so that I can log in with my preferred account details.**

The user needs to be able to change user info, i.e username, password. The user should keep other data non related to change. 
##### Important to see
* input fields to change given user details
* error message on change to username allready in use by other account.

##### Important actions
* write input to input fields

### Resolution settings (us-9)
**As a player with a small screen I would like to be able to change the resolution so that it can fit my computer.**

The user needs to be able to change the their current resoulution to the resoulution best suited for their screen. This configuration should be saved, such that on reopening of the application would use the new resoulution set by the user.
##### Important to see
* drop down menu for diffrent supported resolutions
##### Important actions
* click on the drop down menu to change the resolutions

### Audio settings (us-10)
**As a player who wants to be able to talk to my friends online while playing I want to be able to turn down the game volume so that it does not drawn out other applications.**

Each user will have the opportunity to change the Audio volume either the music or the game sound effects in the settings menu.
##### Important to see
* slider to control the music volume
* slider to control the in game sound effects volume

##### Important actions
* adjust the music volume
* adjust the in game sound effects volume

### Achievements (us-11)
** As a player that does not like playing online, I would still like to be able to enjoy the game and be rewarded. **

** As a player that likes playing online, but not competitively, I would still like to be able to get rewards regardless of my skills compared to other players. **
##### Important to see
##### Important actions

### Skip tutorial (us-12)
** As a player that is already experienced with this game or similar ones, I would like to be able to skip the explanation of the game and tutorial. **
##### Important to see
##### Important actions
