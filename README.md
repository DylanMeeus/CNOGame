CNOGame
=======

**** IT'S ALIVE! ****
As of today, 29/04/2014, I ran the first optimized build of this game on a low-end device (Nokia Lumia 520).
It ran charmingly smooth, the events launched correctly and I could play the game as it is supposed to be played.
Some minor issues to be fixed though - mostly related to scaling
**** IT'S ALIVE   ****


A repo for my CodenameOne game

This game is written entirely using CodenameOne, and was at first a game written in C# optimized for Windows Phone.
So far, the game is playable under Windows Phone devices, but remains untested under others. 

You are free to use this code for anything you want. Same goes for the images (which can be found in theme.res)
Though, a mention of the original code by me would be nice! :)

Update 18/04/2014:

- Optimized modularity
- Optimized performance
- Improved on MVC 
- Added EndWindow
- Added AboutWindow
- Added support for multiple display sizes.
- Fixed crash issues after finishing game



Update 27/04/2014

- Added JavaDoc
- Added powerups (Shield)
- Added tile icon
- Added tile name
- Improved collision detection
- Improved spawn logic
- Improved general code structure/quality
- Changed naming to be more clear
- Changed metadata

Update 29/04/2014

- Fixed minor scaling issue
- Changed metadata
- Added XAP file (Windows Phone installation)

Update 03/05/2014

- Added JavaDoc
- Minor code cleanup
- Fixed powerup issue

Update 06/05/2014

- added new TIMED gamemode
- added new gamemode window
- added TrialButton to net.itca.components
- changed difficulty
- changed (deprecated) this.setBgImage() to this.getStyle().setBgImage()
- enforced private visibility in game.java
- Game.java now requires GameMode
- improved spawn design (strategy)
- improved general design
- improved javadoc (more clarifying)
- moved spawn logic to net.itca.game.core.spawner
- removed superfluous net.itca.game.tryouts.TrialButton class
- removed unused imports.

Update 13/05/2014

- Added touch suport
- Improved code
- Improved JavaDoc
- Added new .xap file