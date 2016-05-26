# Crush The Doges!
## Alpha Release

Victoria Kay <vkay@mines.edu>

Taylan Dillion <tdillion@mines.edu>

Kevin Day <kday@mines.edu>

Andre Pacheco <apacheco@mines.edu>

### HAS THIS EVER HAPPENED TO YOU???
> "I love Angry Birds, but I want to rek my friends playing it!"
Look no further! Crush The Doges is a pass-and-play implementation of Angry Birds / Crush The Castle where your friends can get rekt! By you!

When the game screen launches, you will see a pretty basic image and some doges. We recommend that you just start grunting and poking at the screen until it does what you want, but we are mindful of our more technical audience:

- There are 2 players. They are named Player 1 (left side of the screen) and Player 2 (right side of the screen).
- When the game launches, Player 1 (P1) is to build a castle by tapping on the screen. Each tap places a block.
- The button on the very left of the screen will allow Player 2 (P2) to build its castle.
- Once P2 is done placing the castle, P2 should press the button to the top right of the screen to return back to P1
- Pressing the button to the FAR LEFT of the screen will start the game. The button to the right of that is a reset button. Do not press it.
- A doge should have appeared. Drag, starting at the doge, and end in the direction that you want the doge to attack.
- If you did it right, the doge will hit P2's castle. Get rekt, P2.

### Notes

- This was built using AndEngine <http://www.andengine.org/>
- I'm really getting sick of Kalfadar being Halfadar.

### Bugs

- Yes, there are a lot of bugs. If it helps, think of this as Goat Simulator.
- The camera stuff is nice, but a bit clunky. An end-of-turn is not detected until the doge stops moving.
- The doge can be flung multiple times during a turn, resulting in maximum rekkage.
- Pressing the buttons that switch between P2 / P1 in construction mode is odd.
- There is currently no way to win or lose. Here's how this will work: each player will be required to have placed a "King" before concluding their turn. If, at any point during the game, the doge touches a king, the player who owns that king loses.
