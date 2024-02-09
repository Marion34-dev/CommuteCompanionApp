# Welcome to Your Commute Companion App!

After completing the Fundamentals of Sustainability and Technology course from IBM, I decided to create 
a program that promotes the use of public transport. This Java program makes an API call to the 
Transport For London (TFL) website.

```

                    .-------------------------------------------------------------.
                    '------..-------------..----------..----------..----------..--.|
                    |       \\            ||          ||          ||          ||  ||
                    |        \\           ||          ||          ||          ||  ||
                    |    ..   ||  _    _  ||    _   _ || _    _   ||    _    _||  ||
                    |    ||   || //   //  ||   //  // ||//   //   ||   //   //|| /||
                    |_.------"''----------''----------''----------''----------''--'|
                    |)|      |       |       |       |    |         |      ||==|  |
                    | |      |  _-_  |       |       |    |  .-.    |      ||==| C|
                    | |  __  |.'.-.' |   _   |   _   |    |.'.-.'.  |  __  | "__=='
                    '---------'|( )|'----------------------'|( )|'----------""
                    '-'                          '-'

```

## How to use
The user will be asked to select a (bus or tube) station from the dropdown menu. Upon selection,
tthe server will make the API call and return the 'latest information' about
this station and provide information such as which lines are expected, their destinations as well as their expected arrival time. 
The list is sorted in ascending order, which means that the line which is expected to arrive first appears at the top.

## Behind the scenes
This Java program uses Springboot and a Tomcat server. 
The TFL website requires the Naptan code of a station in order to return the relevant information.
To do this, the program reads the input from the user (the desired station), retrieves the corresponding 
Naptan code from the CSV file and uses it in its API call.

## Special note
Please wait at least 10 seconds before selecting a second station.
For protective measures against DDoS, the TFL website will not process the second request 
if sent too soon after the first one. Thank you for your understanding and patience.

### Happy Commuting! 😃
