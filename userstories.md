# User stories
## Backlog
### Login and account creation (us-1)
**As a new player, I want to be able to create an account and log in so that I can see what the game is about.**

The user needs to be able to input user information, like username and password, and be able to login with these details. The details are to be saved to a database, which in first iteration will just be a csv file stored locally to the user's computer. The data should be saved for the next time the user opens the application. The user may then input the previously saved user details and these will be checked up against details saved in the database. The application should also prevent users from choosing the same usernames.

##### Important visuals for user
* input fields to log in and sign up
* confirmation that user details input correspond to a user saved in the database
* error message if user details input do not correspond to a user saved in the database
* confirmation that your account has been created with your chosen account details
* error message if you try to sign up with an occupied name
##### Important actions for user
* put in user details in signup
* put in user details in login and log in with said details
### Character information (us-2 and us-3)
**As a player that likes fighting games and knows a bit about them, I want to know what moves the different characters have so that I can choose one that fits my playstyle.**

**As a new player that hasn’t played a lot of fighting games, I want to know the playstyles of the different characters so I can get an idea of what might be interesting for me.**

### Local singleplayer (us-4)
**As a new player I want to be able to try out new characters before taking them to the battlefield so that I know how to play them.**

The player needs to be able to choose a character from the current available characters and start the match when the player is ready.
##### Important visuals for user
* when one chooses character: All the possible characters
* see the character respond after interaction
##### Important actions for user
* choose which character one will play in the match
* start match when the user is ready
* control the character

### Online gameplay (us-5)
**As a gamer I want to test my skill against others around the world to see who’s the best.**

Gamers would appriciate the possibility to play with friends/gamers other places.
##### Important visuals for user
* when one chooses character: All the possible characters
* see the character respond after interaction
* see the account name of who one plays against and which character they play
* enemy character respond to interaction from your character
* your character respond to interaction from enemy character

##### Important actions for user
* choose which character one will play in the match
* start match when the user is ready
* control the character

### Tutorial (us-6)
**As a child who is new to video games I want to get sufficient information and tutorial on how to play so that I can start playing.**
##### Important visuals for user
*
##### Important actions for user
*

### More characters (us-7)
**As a player who has played this game a lot I want to get new characters so that I can get a new experience from this game.**

The player need variance in characters to keep engagement.
##### Important visuals for user
*
##### Important actions for user
*

### Account details (us-8)
**As a player who mistyped my information when creating an account I want to be able to change my account details so that I can log in with my preferred account details.**

The user needs to be able to change user info, i.e username, password. The user should keep other data non related to change. 
##### Important visuals for user
* input fields to change given user details
* error message on change to username allready in use by other account.

##### Important actions for user
* write input to input fields

### Resolution settings (us-9)
**As a player with a small screen I would like to be able to change the resolution so that it can fit my computer.**

The user needs to be able to change the their current resoulution to the resoulution best suited for their screen. This configuration should be saved, such that on reopening of the application would use the new resoulution set by the user.
##### Important visuals for user
* drop down menu for diffrent supported resolutions
##### Important actions for user
* click on the drop down menu to change the resolutions

### Audio settings (us-10)
**As a player who wants to be able to talk to my friends online while playing I want to be able to turn down the game volume so that it does not drawn out other applications.**

Each user will have the opportunity to change the Audio volume either the music or the game sound effects in the settings menu.
##### Important visuals for user
* slider to control the music volume
* slider to control the in game sound effects volume

##### Important actions for user
* adjust the music volume
* adjust the in game sound effects volume
