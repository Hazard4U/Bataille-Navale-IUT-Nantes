La serveur est un serveur REST développé en Node.JS

Cela induit un fonctionnement réseau "limité".


La logique d'échange est la suivante : 


1ere phase : initialisation
___________________________

Des joueurs s'inscrivent sur le serveur via

_Network.suscribeNewPlayer(...)_

On peut lister les joueurs déjà inscrits via 

_Network.listActivePlayers(...)_

Des joueurs initient des parties sur le serveur via 

_Network.initNewGame(...)_

On peut lister les parties initialisées via 

_Network.listInitializedGames(...)_

Un joueur peut rejoindre une partie initialisée

_Network.joinGame(...)_

alors la phase de jeu commence...


2ième phase : dérouler d'une partie
____________________________________
A tour de rôle les deux joueurs de la partie vont pouvoir jouer.


Avant de jouer, il faut vérifier l'état de la partie via

_Network.getInfo(...)_

Puis on peut jouer si c'est à notre tour via 

_Network.playOneTurn(...)_



Les limitations du serveur sont les suivantes :

* le joueur qui a initialisé une partie ne sera pas prévenu qu'un joueur
 a rejoint sa partie ;

* lorsqu'un joueur a joué l'autre joueur n'est pas prévenu que c'est 
à son tour de jouer ;

* lorsqu'un joueur touche (ou coule) un navire adverse, 
l'autre joueur n'est pas prévenu ;

* lorsqu'un joueur gagne la partie, l'autre joueur n'est pas 
prévenu non plus.

NB : il faut utiliser _Network.getInfo(...)_ pour s'informer
 périodiquement de l'état de la partie
