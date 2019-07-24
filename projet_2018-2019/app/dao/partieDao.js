const Partie = require('../model/partie').Partie;
const Bateau = require('../model/partie').Bateau;
const CoordonneeBateau = require('../model/partie').CoordonneeBateau;

const Joueur = require('../model/joueur');
const daoCommon = require('./commons/daoCommon');

/* Pour la serialisation*/
const database = require('../config/dbconfig');
const DaoError = require('./commons/daoError');

class PartieDao {

    constructor() {
        this.common = new daoCommon();
    }

    findPositions(id,nom){
        return new Promise(function (resolve, reject) {
            let sqlRequest = "SELECT joueur,partie,bateau,x, y, touche from position where joueur = $joueur and partie = $partie";
            let sqlParams = {
                $partie: id,
                $joueur: nom
            }

            let stmt = database.db.prepare(sqlRequest);

            stmt.all(sqlParams, function (err, rows) {
                if (err) {
                    reject(
                        new DaoError(11, "Invalid arguments")
                    );
                } else if (rows === null || rows.length === 0) {
                    reject(
                        new DaoError(21, "Entity not found")
                    );
                } else {
                    let positions = [];
                    let bateaux = new Map();
                    for (let row of rows) {
                        let coordonneeBateau = new CoordonneeBateau(row.x, row.y, row.touche);
                        if (bateaux.has(row.bateau))
                            bateaux.get(row.bateau).push(coordonneeBateau);
                        else
                            bateaux.set(row.bateau, [coordonneeBateau]);
                    }
                    for (let [nom, coordonnees] of bateaux) {
                        let bateau = new Bateau(nom, coordonnees);
                        positions.push(bateau);
                    }
                    resolve(positions);
                }
            })
        });

    }

    findById(id){
        return new Promise( (resolve, reject) => {
            let sqlRequest = "SELECT id_partie, initiateur, invite, joueur_courant,etat, gagnant, dimension from partie where id_partie=$id";
            let sqlParams = {
                $id: id
            };
            let partie = new Partie();
            this.common.findOne(sqlRequest, sqlParams).then(row => {
                partie.id = row.id_partie;
                partie.initiateur = new Joueur(row.initiateur);
                partie.invite = (row.invite == null) ? null : new Joueur(row.invite);
                partie.joueurCourant = (row.joueur_courant == null) ? null : new Joueur(row.joueur_courant);
                partie.etat = row.etat;
                partie.gagnant = (row.gagnant == null) ? null : new Joueur(row.gagnant);
                partie.dimension = row.dimension;
                let promesses = [];
               promesses.push(this.findPositions(partie.id, partie.initiateur.nom));
               if (partie.invite !=null)
                   promesses.push(this.findPositions(partie.id, partie.invite.nom));
               Promise.all(promesses).then((positionsArray)=>{
                   partie.positions.set(partie.initiateur, positionsArray[0]);
                   if (positionsArray.length == 2)
                       partie.positions.set(partie.invite, positionsArray[1]);
                   resolve(partie);
               })

            }).catch(()=> reject(
                    new DaoError(21, "Entity not found")));

            });

    }

    create(partie) {
       return new Promise( (resolve, reject)=> {
           database.db.serialize(() => {
               let sqlRequest = "INSERT into partie(initiateur,joueur_courant,etat,gagnant,dimension ) VALUES ($initiateur,$joueurCourant,$etat, $gagnant, $dimension)";
               let sqlParams = {
                   $initiateur: partie.initiateur.nom,
                   $joueurCourant: partie.joueurCourant.nom,
                   $etat: partie.etat,
                   $gagnant: partie.gagnant,
                   $dimension : partie.dimension
               };

               /*
               this.common.run(sqlRequest, sqlParams).then(() => {

                   sqlRequest = "select last_insert_rowid() as id";
                   sqlParams = {};
                   this.common.findOne(sqlRequest, sqlParams).then(row => {
                       partie.id = row.id;
                       let promesses = [];
                       for (let bateau of partie.positions.get(partie.initiateur)) {

                           for (let coordonnee of bateau.coordonnees) {
                               sqlRequest = "insert into position(joueur,partie,bateau,x,y,touche) VALUES ($joueur,$partie,$bateau,$x,$y,$touche)";
                               sqlParams = {
                                   $joueur: partie.initiateur.nom,
                                   $partie: partie.id,
                                   $bateau: bateau.nom,
                                   $x: coordonnee.x,
                                   $y: coordonnee.y,
                                   $touche: coordonnee.touche
                               }
                               promesses.push(this.common.run(sqlRequest, sqlParams));
                           }

                       }
                       Promise.all(promesses).then(()=>resolve(partie.id)).catch(()=>{
                           console.log(err);
                           reject(
                               new DaoError(11, "Invalid arguments")
                           )
                       })

                   })

               });*/
               const common = this.common;
           database.db.run(sqlRequest,sqlParams, function (err) {
               partie.id = this.lastID;
               let promesses = [];
               for (let bateau of partie.positions.get(partie.initiateur)) {

                   for (let coordonnee of bateau.coordonnees) {
                       sqlRequest = "insert into position(joueur,partie,bateau,x,y,touche) VALUES ($joueur,$partie,$bateau,$x,$y,$touche)";
                       sqlParams = {
                           $joueur: partie.initiateur.nom,
                           $partie: partie.id,
                           $bateau: bateau.nom,
                           $x: coordonnee.x,
                           $y: coordonnee.y,
                           $touche: coordonnee.touche
                       }
                       promesses.push(common.run(sqlRequest, sqlParams));
                   }

               }
               Promise.all(promesses).then(()=>resolve(partie.id)).catch(()=>{
                   console.log(err);
                   reject(
                       new DaoError(11, "Invalid arguments")
                   )
               })


           })
           });
       });
    };

    joins(partie) {
        return new Promise( (resolve, reject)=> {

            database.db.serialize(() => {
                let promesses = [];
                for (let bateau of partie.positions.get(partie.invite)) {
                      for (let coordonnee of bateau.coordonnees) {
                            let sqlRequest = "insert into position(joueur,partie,bateau,x,y,touche) VALUES ($joueur,$partie,$bateau,$x,$y,$touche)";
                             let sqlParams = {
                                    $joueur: partie.invite.nom,
                                    $partie: partie.id,
                                    $bateau: bateau.nom,
                                    $x: coordonnee.x,
                                    $y: coordonnee.y,
                                    $touche: coordonnee.touche
                                }
                                promesses.push(this.common.run(sqlRequest, sqlParams));
                            }

                        }
                        Promise.all(promesses).then(()=>{
                            let sqlRequest = "UPDATE partie SET invite=$invite, etat=$etat where id_partie = $partie";
                            let sqlParams = {
                                $invite : partie.invite.nom,
                                $etat: partie.etat,
                                $partie: partie.id
                            };
                            this.common.run(sqlRequest, sqlParams).then(()=> resolve(partie.id));
                            }).catch(()=>{
                            console.log(err);
                            reject(
                                new DaoError(11, "Invalid arguments")
                            )
                        })

                    });

                });

        };



    getInitie(){
        let sqlRequest = "SELECT initiateur, id_partie FROM partie WHERE etat='initie'";

        return this.common.findAll(sqlRequest).then(rows => {
            let res = [];
            for (const row of rows) {
                res.push({
                    nom: row.initiateur,
                    id: row.id_partie
                });
            }
            return res;
        });
    }

    switchUser(partie){
          let sqlRequest = "UPDATE partie SET joueur_courant = $joueur where id_partie=$id";
          let nom = partie.initiateur.nom;
          if (nom == partie.joueurCourant.nom)
              nom = partie.invite.nom
            let sqlParams = {
                $joueur : nom,
                $id: partie.id
            };
            return this.common.run(sqlRequest, sqlParams);
        };


    toucheBateau(partie,x,y){
        let sqlRequest="UPDATE position set touche=true where joueur=$joueur AND partie=$partie AND x=$x AND y=$y";
        let sqlParams = {
            $joueur: partie.nextUser().nom,
            $partie: partie.id,
            $x: x,
            $y: y};
        return this.common.run(sqlRequest, sqlParams);
    }

    finie(partie){
        let sqlRequest = "UPDATE partie SET gagnant = $gagnant, etat='finie' where id_partie=$id";
        let sqlParams = {
            $gagnant : partie.gagnant.nom,
            $id: partie.id
        };
        return this.common.run(sqlRequest, sqlParams);
    };

}

module.exports = PartieDao;