# SMTP Prank-Mail Generator

## Description
This project was made during the Networks course at the HEIG-VD. 
This application is used as follow : the user enter a list of victim mail addresses and a list of messages that will be sent using one of the victim as sender. This allows to send forged emails automatically to small groups of people in the victim list.

## Configuration
In the `config.properties` file under the `config` directory, you need to give the smtp server ip and port with which the application will communicate
The configuration also needs a number of groups of victims to create, and a witness of the prank emails to have in the Cc field.
The `messages.utf8` file contains all the mail bodies that will be chosen and used by the application. Each message ends with a `===` line, and the Subject has to be on the first line, with the prefix `Subject:`. The syntax is classic SMTP (FROM:, RCPT TO:, DATA)
The `victims.utf8` file contains all the victims emails, each on a separated line.

You can execute the program just by running the Main class.

## Implementation
Following the recommandations of [wasadigi](https://github.com/wasadigi), the project has three packages, one for the configuration manager, one containing all the model classes needed and the last one containing the SMTP client.
