## WORKING MEMORY GAME


This project is a Digit Span Memory Game, developed with Java, Hibernate ORM, JSP, and Servlets, designed to test users' memory span by recalling sequences of digits displayed on the screen. The game includes multiple modes such as Forwards, Backwards, Sequencing, and Letter-Number Sequencing. It is essentially a direct digital clone of the WAIS-IV Digit Span and Letter-Number Sequencing subtests, replicating these well-known cognitive assessments. This project offers users an authentic experience that mirrors the memory tasks used in professional IQ testing to measure working memory.


## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup](#setup)
- [How to Play](#how-to-play)
- [Game Modes](#game-modes)
- [License](#license)

## Features

Display of random digit sequences to memorize.

Multiple game modes:

Forwards (standard digit span)

Backwards (recall digits in reverse order)

Sequencing (rearrange digits in numerical order)

Letter-Number Sequencing (mixed digits and letters)

Input validation and result checking.

Session management to keep track of the current game state.

Responsive design for an improved user experience.


## Technologies Used


Java: Core application logic.

Hibernate ORM: Object-relational mapping for data management.

JSP (JavaServer Pages): For the frontend interface.

Servlets: Backend handling for form submissions and game logic.

JSTL (JavaServer Pages Standard Tag Library): Used for dynamic content rendering in JSP.

CSS: For styling the UI.

Jackson Databind: Used to handle JSON operations in the backend.

## Setup

**Prerequisites**

Java Development Kit (JDK) 14 or higher

Apache Maven

A web server (e.g., Apache Tomcat)

**Dependencies**

Hibernate Core: Version 6.5.0

Jackson Databind: Version 2.14.0

Servlet API: Version 3.1.0

Commons Collections: Version 4.4

JSTL: Version 1.2

Hibernate Commons Annotations: Version 6.0.6

Taglibs Standard: Version 1.1.2

**Installation**

1) Clone the repository:

git clone https://github.com/yourusername/working-memory-game.git

2) Navigate to the project directory:
   
cd working-memory-game

3) Build the project using Maven:

mvn clean install

Deploy the generated WAR file to your web server.

4) Deploy the generated WAR file to your web server.


## How to Play

Choose a mode from the dropdown menu.

Press the Start button to display a sequence of numbers.

After the sequence is displayed, enter the digits in the correct order based on the mode you've selected.

Press Check to validate your input.


## Game Modes

Forwards: Recall the sequence in the same order as displayed.

Backwards: Recall the sequence in reverse order.

Sequencing: Rearrange the digits in ascending numerical order.

Letter-Number Sequencing: Rearrange the sequence with numbers first in ascending order, followed by letters in alphabetical order.

## Project Structure ##

src/main/java/org/digit_span/: Contains Java source files

      digit_span_util/: Utility classes for game logic

      servlet/: Servlet classes for handling HTTP requests

  src/main/webapp/: Web resources

      WEB-INF/: Configuration files
      
      *.jsp: JavaServer Pages for the user interface


pom.xml: Maven project configuration file

