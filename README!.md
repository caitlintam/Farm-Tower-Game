# Team 115 SENG Project

## Farm Resource Game
# TO DO

- add more prints to show what happening when buttons are clicked ( shop/ buy )
  - add error label when buy label clicked but nothing selected
  - not enough money error label
  - item bought pop up?
- inventory - overlapping error labels. 


- if selected two of same, status is resevr for one n 


# DOING

### setup of game
- implement 'Game difficulty' 1 - $20, 2 - $100, 3 - $500  = starting money

## inventory
### upgrades
- implement upgrades. edit inventory so they can be applied
- 'main group must always have one tower'
- applying selected upgrades to selected tower. changing level

### Rounds
## round set up screen
- dont set up screen if not enough towers in game
- create fxml
-

### Carts

### Random Events

# DONE
- selling items D
  - updating inventory( removing selected item)
  - updating fxml tableview
  - updating cost ( adding after sell)

- When towers are added to inventoyr, set their status to ingame
- invenotry statsu works - but is referencing only 1 type of tower. If change status of 1 tower, but have 2 of them, changes both status
  - DONE problem: adding same objects to inventory. need to at NEW objects to be individual items
- - status of towers
- if button clicked, change towerStatus
  - if 3 towers already in game , send msg 'Cannot change Status: 3 towers already in game'
  - otherwise. if in game - change to reserve. if reserve, change to in game



# Other Notes

- MAYBE: shopitem /shopmanager
- --- need to reveiw responsibilities of controllers vs managers
- 
### Models: Cart, Round, RoundTask 
- Round - represents single round in game. Contains parameters like round num, diff, no. carts, track dist, attributes
- Cart - represents cart objec t, stores attributes - size, res type, speed, filled status
- Event - random events that could occur, info on event type, efects, associated conditions.

### Managers: Cart, Round, Game, Event
- Game Manager - overall flow, starting/ending game, handling events, interactiosn between compoennts
- RoundManager - creation, progression completion of rounds,. initliasiing parameters, advancing to next, round specific logic. Also runs the roundTask thread 
- CartManager - manages carts to be filled duirng rounds. Creating carts with attributes, movement, filling with resources, determing completion
- EventManager - random event ( tower upgrades/breakages/ other)
- 


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
