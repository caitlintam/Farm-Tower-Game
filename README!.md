# Team 115 SENG Project

## Farm Resource Game

## Licensing
All images are attributed to Freepik
Following Freepiks license, You are free to use this image:
- For both personal and commercial projects and to modify it.
- In a website or presentation template or application or as part of your design.

You are not allowed to:
- Sub-license, resell or rent it.
- Include it in any online or offline archive or database.
As this project is for personal use, and I have credited Freepik I am following their license

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

# DOING NOW

- levels
  - start at 1
  - tower level number +=1 for upgrades, random events, if upgrade of res amount applied, and goes above level res amount
  - 
- 
- tower level numnbeR: 
- 
- methods in tower class?
  - increase level (random event, level upgrade APPLIED 
  - ) - changes res amt (+6)and reload speed (-4), +1 to level
  - assess level ( used after res upgrade bought).
    - if res amount >(6*current level), increase level(+1) and decrease reload speed (-4)
    - print tower level up
    - 
    - do nothign otherwise
  - decrease level ( random event()) - cannot go below 0, decrease rest amt, inc reload speed
- 
UpGrades:
- level:(+6 to res, -4 off reload speed). call increase level method
- res amnt (+2).
- reload speed (-1). call assess level method.
- 
- 
  - decrease only in random events, get set back to level -1( res amt and stuff)
      - cant go below 0
- upgrades
  - tower resource amnt/reload speed/both -- levels
  - when tower res amount bought, assessTowerLevel()
    - if tower res amnt > tower levelREsAMountBOundary. level + 1
  - if level up bought - increases level to set next level. (expensive)
- tower cost
  - make them more expensive / scale to initial money 
- starting money in game

# Done
- track distance doesnt go belwo 0, related to numgamerounds

implement functionality for:
- only 10 towers in total
- buying a tower. if ingame towers list size <5 and inventory size < 10. add to in games, set to in game status
- otherwise (ingame >=5), add to reserve list, add to inventory, status reserve.
- if invenotry size>10: cant buy tower, inventory full




-- audio
from choose difficulty, on next button clicked




CREDIT AUDO
MOO - License Agreement
You must attribute the work in the manner specified by the author or licensor.
- Author: manofham, Title: Moo 1 - Moo Moo the Cow
- 


void addUpgradesToInventory(Upgrade upgrade)

removeTowerFromInventory(Tower selectedTower)

removeUpgradeFromInventory(Upgrade selectedUpgrade) 


List<Tower> getTowerInventory()
List<Upgrade> getUpgradeInventory()
void startRound()
List<Integer> getRandomEventsRoundList()
void evaluateRoundSuccess()
void runRound(int trackDistance) 