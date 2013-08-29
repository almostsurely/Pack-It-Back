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

    Children:
