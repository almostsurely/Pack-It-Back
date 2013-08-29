Pack-It-Back
============

Pack-It-Back is designed to simulate randomly assorted booster packs for any collectible card game. It reads from an XML file, and loads the game information into the program.

Files:

Generally, as of v1.0, Pack-It-Back is released with two main files that have to be located in the same directory. The runnable jar file: packitback.jar, and the XML file holding game information: PackItBack.xml.

Java Terminal Arguments:

Pack-It-Back is setup to take one argument from the terminal:

    java -jar packitback.jar (Filepath to XML)

This will allow a user to store the XML in a separate directory from the jar.

XML Layout:

The XML for Pack-It-Back has a specific layout it uses.

<PackItBack> - Root Element
    Required: Yes
    Limited to One: Yes
    Parent: None
    Children: <Game>

    The root element for Pack-It-Back. Everything goes inbetween these two tags.

<Game>
    Required: Yes
    Limited to One: No
    Parent: <PackItBack>
    Children: <Name>, <Set>

    Everything between these tags represents a game.

<Name>
    Required: Yes
    Limited to One: Yes
    Parent: <Game>
    Children: None

    Put the name of the game between these two tags. For example: <Name>Super Fun CCG</Name>.

<Set>
    Required: Yes
    Limited to One: No
    Parent: <Game>
    Children: <Name>, <Build>, <Card>

    Everything between these tags represents a set for the <Game>.

<Name>
    Required: Yes
    Limited to One: Yes
    Parent: <Set>
    Children: None

    Put the name of the set between these two tags.

<Build>
    Required: Yes
    Limited to One: No
    Parent: <Set>
    Children: <Weight>, <Level>

    This element represents a way a booster pack can be laid out. How many Rares, Uncommons, Commons, etc.

<Weight>
    Required: Yes
    Limited to One: Yes
    Parent: <Build>
    Children: None

    This number is used to determine the odds of pulling that build of pack out of a box. For example, if a box has a 1 in 6 chance of pulling a booster pack with a Super Rare, instead of a Rare, there will be two builds. One listed with a Rare with a weight of 5. Another listed with a Super Rare with a weight of 1. Those weights mean for every 6 packs, 1 will have a Super Rare and 5 will have a Rare.

<Level>
    Required: Yes
    Limited to One: No
    Parent: <Build>
    Children: <Name>, <Count>

    
