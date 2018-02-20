# digimon
Java RPG CLI Game


# Getting started

* You need java 8 and maven >3.2 to run this project. The example instructions therefore assume you have them on your classpath
* Clone this project to your prefered directory
* Make sure the current user has permission to write and read files from the directory


# Example
```
$ cd ~/path/to/my/directory
$ git clone https://github.com/norrey/digimon.git
$ mvn clean install
$ java -jar  target/digimon-0.0.1-jar-with-dependencies.jar
```



# TODO

* Game ends at level one - I need to create configuration and generate sample date for more levels
* No logging whatsoever has been added - I need to add logging using either log4j or logback to ensure we can track game actions
* One user - The current game assumes one user per device. Once you create your character, the character will be loaded everytime you start the game
* Dependecy Injection - The DI used is too basic and we do not manage the unloading or lazy loading of classes at the time of injection. This calls for the use of a standard DI framework
