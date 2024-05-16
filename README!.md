# Team 115 SENG Project

## Farm Resource Game

Documentation:

12/5/24
- so far we have created the set up screen, tower set up screen, home screen with funcionality of the shop.
  - the shop needs the buy functionality
  - need to implement the play rounds and the inventory / sell functionality
- I will try to implement the game round functionality  in an interesting way. Ill also add the inventory screen, which will be similar to the shop screen and functionality.


## Other things to do:

- clean up use case and class diagrams, schedule
- write the report

- clean up the code see tut 4
  - adding packages, practice zipping files
  - naming conventions
  - comments, right formatting conventions

- testing, report test cases as well

- practice running JAR files,
- rename project appropriately

- add to this readme:
  - our names as authors
    The exact command to run your jar
    Instructions on how to build your application
    Instructions on how to import your application into IntelliJ
    Any required attributions for works (i.e. images/music) used in your project

Submission:
- UML use case diagram, class diagram
- Report(PDF)
- README file
- program as a jar ( runs on lab machines)


## working on at the moment:

Inventory

# DONE
- selling items D
  - updating inventory( removing selected item)
  - updating fxml tableview
  - updating cost ( adding after sell)

- When towers are added to inventoyr, set their status to ingame
- 

# DOING
- status of towers 
  - if button clicked, change towerStatus
    - if 3 towers already in game , send msg 'Cannot change Status: 3 towers already in game'
    - otherwise. if in game - change to reserve. if reserve, change to in game

- change update status stuff - bad method in towercontroller
- implement 'Game difficulty' 1 - $20, 2 - $100, 3 - $500  = starting money
- invenotry statsu works - but is referencing only 1 type of tower. If change status of 1 tower, but have 2 of them, changes both status
  - DONE problem: adding same objects to inventory. need to at NEW objects to be individual items