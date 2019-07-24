/* Load modules */
let sqlite3 = require('sqlite3').verbose();

/*
 * Database configuration
 */

/* Load database file (Creates file if not exists) */
//const db = new sqlite3.Database('bataille_navale.db');
const db = new sqlite3.Database(':memory:');

//Activation des contraintes d'intégrté
db.get("PRAGMA foreign_keys = ON");


/**
 * Création de la table joueur caractérisé par son nom
 * @returns {Promise<any>} le joueur ou une erreur
 */
const createJoueur = function() {
    return new Promise(function (resolve, reject) {
        const sqlRequest = `CREATE TABLE IF NOT EXISTS joueur (
              nom  TEXT NOT NULL,
              PRIMARY KEY ("nom")
            )`;

        db.run(sqlRequest,[], (err) => {

            if (err) {
                console.log(err);
                reject(err);
            }
            else {
                console.log("Joueur créée");
                resolve(this);
            }
        });

    })
};
/**
 * Création de la table partie
 * id_partie la clef primaire
 * initiateur, invité, joueur_courant des clefs étrangère ves joueur
 * etat : initité ou joins ou finie
 * gagnant : null ou le joueur gagnant
 * @returns {Promise<any>} la partie ou une erreur
 */
const createPartie = function() {
    return new Promise(function (resolve, reject) {
        const sqlRequest = `CREATE TABLE IF NOT EXISTS partie (
              id_partie INTEGER  PRIMARY KEY AUTOINCREMENT,
              initiateur TEXT NOT NULL,
              invite TEXT,
              joueur_courant TEXT,
              etat TEXT NOT NULL,
              gagnant TEXT DEFAULT NULL,
              dimension INTEGER NOT NULL,
              FOREIGN KEY (joueur_courant) REFERENCES joueur(nom) ON DELETE CASCADE ON UPDATE CASCADE,
              FOREIGN KEY (gagnant) REFERENCES joueur(nom) ON DELETE CASCADE ON UPDATE CASCADE,
              FOREIGN KEY (initiateur) REFERENCES joueur(nom) ON DELETE CASCADE ON UPDATE CASCADE,
              FOREIGN KEY (invite) REFERENCES joueur(nom) ON DELETE CASCADE ON UPDATE CASCADE
);`;

        db.run(sqlRequest, [], (err) => {

            if (err) {
                console.log(err);
                reject(err);
            }
            else {
                console.log("Partie créée");
                resolve(this);
            }

        });

    });
};
/**
 * Les postions des bateaux pour un joueur et une partie choisis
 * joueur: le joueur propriétaire
 * partie: la partie associée
 * bateau: le nom du bateau
 * x,y les coordonnées sur la grille
 * touche : l'état de la case
 * @returns {Promise<any>} La partie ou une erreur
 */
const createPosition = function () {
    return new Promise(function (resolve, reject) {
        const sqlRequest = `CREATE TABLE IF NOT EXISTS position (
          joueur TEXT NOT NULL,
          partie INTEGER NOT NULL,
          bateau TEXT NOT NULL,
          x INTEGER NOT NULL,
          y INTEGER NOT NULL,
          touche INTEGER NOT NULL,
          PRIMARY KEY (joueur,partie,x,y),
          FOREIGN KEY (joueur) REFERENCES joueur(nom) ON DELETE CASCADE ON UPDATE CASCADE,
          FOREIGN KEY (partie) REFERENCES partie(id_partie) ON DELETE CASCADE ON UPDATE CASCADE
);`;
        db.run(sqlRequest, [], (err) => {

            if (err) {
                console.log(err);
                reject(err);
            }
            else {
                console.log("Position créée");
                resolve(this);
            }
        });

    });
};
/*
Non utilisée pour le moment
const createTir = function () {
    return new Promise(function (resolve, reject) {
        const sqlRequest = `CREATE TABLE IF NOT EXISTS tir (
          joueur TEXT NOT NULL,
          partie INTEGER NOT NULL,
          x INTEGER NOT NULL,
          y INTEGER NOT NULL,
          PRIMARY KEY (joueur,partie,x,y),
          FOREIGN KEY (joueur) REFERENCES joueur(nom) ON DELETE CASCADE ON UPDATE CASCADE,
          FOREIGN KEY (partie) REFERENCES partie(id_partie) ON DELETE CASCADE ON UPDATE CASCADE
);`;
        db.run(sqlRequest, [], (err) => {

            if (err) {
                console.log(err);
                reject(err);
            }
            else {
                console.log("Tir créée");
                resolve(this);
            }
        });

    });
};*/

const init = function() {
    db.serialize(() => {
        console.log("Création des tables");
        createJoueur().
        then(
            ()=>createPartie()
        ).then(
            ()=>createPosition()
        )./*then(
            ()=>createTir()
        ).*/catch((err)=>console.log(err));
    });


};


module.exports = {
    init: init,
    db: db
};

