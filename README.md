# Projet Bataille Navale

## Description succinte du projet 

Il s'agit de réaliser des développements afin d'obtenir
 un programme client java permettant de jouer à la bataille navale,
  via un serveur (NB : le serveur est fourni).
  
Certains développements seront fortement guidés/imposés.

Les groupes d'étudiants sont constitués par nos soins 
(voir affichage)

Chaque groupe aura un enseignant-référent qui passera 
vous voir régulièrement 
afin de vous assister mais aussi évaluer votre travail.


## Ce qu'il y a à faire 


1. initialiser votre projet

    * créer un git spécifique à votre groupe sur le gitlab de l'université : 
    https://gitlab.univ-nantes.fr/
    
    * ajouter comme "developer" votre enseignant-référent et M. Arnaud Lanoix 
        
    * déposer sur votre git spécifique les sources récupérées ici : 
    https://gitlab.univ-nantes.fr/iut.info1.ihm/info1-2019-client/-/archive/master/info1-2019-client-master.zip
   
    
 **Vous respecterez la structure imposée en terme de dossiers/paquetages**
 
    
    NB :le source récupéré contient un repertoire lib/ 
    contenant une archive _info1-2019-libraries.jar_
    
    il faut ajouter le .jar comme une librairie dans votre IDE préféré
    
    il s'agit de code qui vous est fourni, que vous devez utiliser
     mais que vous ne pouvez pas modifier
    
   
2. implémenter les classe suivantes (présentes dans _src/_) :

    *  _Coord_

    *  _Ship_ et (si nécessaire) modifier les classes "filles" fournies
    
    *  _NavyFleet_

3. écrire des cas de tests pour les classes fournies suivantes (dans _test/_ ):

    * dans _PlayerTest_ tester _Player_  

    * dans _GameTest_, tester _Game_  
    
    *  dans _ShipsTest_, tester _Aircraft_, _Battleship_, _Cruiser_, _Destroyer_, _Submarine_ 
    (nécessite d'avoir implémenter correctment _Ship_) 
    
    (vous remonterez les éventuels bugs rencontrés à M. Arnaud Lanoix)
    
    
4. utiliser correctmennt la classe _Network_ pour dialoguer avec le serveur

    * nécessite de récupérer le serveur et de le lancer en local ou dans la salle
    voir la doc du serveur : [voir les explications fournies par Jean-François Berdjugin]


5. développer une interface graphique en Swing vous permettant de jouer, cad
    1. positionner des bateaux
    2. créer une flotte de bateaux
    3. initialiser une partie sur le serveur
    4. rejoindre une partie initialisée sur le serveur
    5. jouer via le serveur
    6. gagner 



Idées de fonctionnalités supplémentaires 
    
    * Sauvegarde/chargement d'une flotte
    
    * IA qui joue en mode automatique
    
    * ...
    
    
## Critères d'évaluation

* présence et implication dans le projet

* implémentation correcte et fonctionnelle des classes "modele" : _Coord_ _Ship_  et _NavyFleet_

    * nombre de tentatives pour que les cas de tests "enseignants" fonctionnent tous
    (1ere tentative à partir de mardi 12:00)

* qualité, pertinence et justification des cas de tests écrits 
pour les classes _Player_, _Game_ 
et _Aircraft_, _Battleship_, _Cruiser_, _Destroyer_, _Submarine_ 

* etat d'avancement global du projet (items 1-5 de la section précédente)

* qualité et fonctionnalités de l'IHM proposée


**En bonus** 

* découvrir des bugs dans le code fourni