/* Load Driver entity */
const Joueur = require('../model/joueur');

/* Load DAO Common functions */
const daoCommon = require('./commons/daoCommon');

/**
 * Driver Data Access Object
 */
class JoueurDao {

    constructor() {
        this.common = new daoCommon();
    }

    findByNom(nom) {

        let sqlRequest = "SELECT nom FROM joueur WHERE nom=$nom";
        let sqlParams = {$nom: nom};
        return this.common.findOne(sqlRequest, sqlParams).then(row =>
            new Joueur(row.nom));
    };

    findAll() {
        let sqlRequest = "SELECT nom FROM joueur";
        return this.common.findAll(sqlRequest).then(rows => {
            let joueurs = [];
            for (const row of rows) {
               joueurs.push(new Joueur(row.nom));
            }
            return joueurs;
        });
    };


    create(joueur) {
        let sqlRequest = "INSERT into joueur(nom) VALUES ($nom)";
        let sqlParams = {
            $nom: joueur.nom
        };

        return this.common.run(sqlRequest, sqlParams);
    };

}

module.exports = JoueurDao;