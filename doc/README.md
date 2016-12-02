# rensvanderveldt-pset5

## by Rens van der Veldt (10766162)

A list managing app

#### Functions:
This app supports adding lists with items in them, the lists are stored in a database and their items use the list ids as identifiers.
A singleton has been implemented to manage all the list form a "birdseye" view.
Lists support deletion, which deletes all their items as well, by holding the list in its view (long click).
Items are edited by clicking them after selecting which list you want to view. They have the update (edit), delete and complete options.
Complete in its turn makes the item faint green to indicate completion.

Rotation of device is supported and maintains selections and state through state restoration.
