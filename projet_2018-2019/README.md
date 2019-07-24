## Projet issu d'un projet de Romuald Tuffreau lui même basé sur Telosys Tools
Cette partie est le serveur de votre jeu de bataille navale, ce serveur est basé sur une architecture MVC et fourni un service RESTful 
Vous accéderez à ce serveur en utilisant les classes java fournies par M. Lanoix

## Installation :
L'installation doit avoir lieu sur une réprtoire local et non sur un partage réseau 
1. Cloner le dépot  :  https://gitlab.univ-nantes.fr/dev-web/projet_2018-2019.git
2. Se placer dans le répertoire créé projet_2018-2019
3. A la première utilisation, lancer la récupération des modules avec `npm install`.
Cette procèdure vous créera un répertoire node_modules avec les dépendance (décrites dans pasckage.json)

## configuration (optionnelle) :
Par défaut les parties ne sont pas sauvegardées et durent le temps d'une instance du serveur.
Je vous conseile le temps du développement d'utiliser ce mode mais i vous souhaitez activer la persitance, il vous faut remplacer dans app/config/dbconfig.js : 
const db = new sqlite3.Database(':memory:'); par
const db = new sqlite3.Database('le_nom_de_la_base'); 
Une base sera créée avec le nom :  le_nom_de_la_base
                                                               
## Exigences (si vous utilisez vos propres machines) :
L'ensemble des dépendances est déjà installé sur les machines de l'IUT, cet section ne concerne que vos propres machines 

- [Git](https://git-scm.com/) pour cloner le dépot.
- [NodeJS](https://nodejs.org/en/) pour lancer l'appilcation.
- [Npm](https://www.npmjs.com/) fourni avec nodeJS pour installes les modules.


## pour lancer le serveur :

1. Installer l'application (cf. la section précédente)
2. Dans le répertoire de l'appilcation lancer le seveur avec : `node index.js` par default le port est 3000

Le port peut-être modifié avec `node index.js un_port_libre`

## arrêter le serveur
Il vous tuer le processus : CTRL+C dans le terminal dans lequel vous avez lancé l'application  

## Credits
- Arnaud Lanoix et Jean-François Berdjugin 
- sur une base de [Romuald Tuffreau](https://github.com/romwaldtff) elle même générée avec Telosys Tools
- [Laurent Guerin](https://github.com/l-gu), creator of [Telosys Tools](https://sites.google.com/site/telosystools/).

## License

This project uses the [LGPL v3 License](https://www.gnu.org/licenses/lgpl-3.0.en.html) (See the LICENSE file in this repository).