/* Load Car Data Access Object */
const PartieDao = require('../dao/partieDao');
const JoueurDao =  require('../dao/joueurDao');

/* Load Controller Common function */
const ControllerCommon = require('./common/controllerCommon');

/* Load Partie entity */
const Partie = require('../model/partie').Partie;
const Coordonnee = require('../model/partie').Coordonnee;
/* Load Joueur entity */
const Joueur =  require('../model/joueur');



/**
 * Car Controller
 */
class PartieController {

    constructor() {
        this.partieDao = new PartieDao();
        this.joueurDao = new JoueurDao();
        this.common = new ControllerCommon();
    }

    /**
     * Permet de créer une partie, le joueur unutiteur et les positions doivent-être fournis
     * @param req
     * @param res
     * @returns {Promise<T | never>}
     */
    create(req, res) {

        const joueurName = req.body.joueur;
        const positions = req.body.positions;
        let partie = new Partie();
        console.log('-- partieController create -- ',joueurName, positions);

        this.joueurDao.findByNom(joueurName).then(()=> {

            const partieOk = partie.initie(new Joueur(joueurName), positions);
            if (partieOk)

                return this.partieDao.create(partie)
                    .then((id) => {
                        this.partieDao.findById(id).then((partie) => {

                            res.status(201);
                            res.json(partie);
                        });
                    })
                    .catch(this.common.serverError(res));
            else {
                res.status(200);
                res.json({error: "coordonnées illégales"});

            }
        }).catch((err)=> {
            res.status(200);
            res.json({error : "création impossible"});
        } );
    }

    /**
     * Renvoie la liste des parties disponibles (etat: initie)
     * @param res
     */
    getInitie(res){
        console.log('-- partieController getInitie -- ');
        this.partieDao.getInitie().then(this.common.findSuccess(res))
            .catch(this.common.findError(res));
    }

    /**
     * Permet de rejoindre une partie necessite un joueur, ses positions et que la partie soit initiée
      * @param req
     * @param res
     * @returns {*}
     */
    joins(req, res){
        const id = req.params.id;

        const joueurName = req.body.joueur;
        const positions = req.body.positions;
        console.log('-- partieController joins -- ',joueurName, positions);

        let partie = new Partie();
        const partieOk  = partie.initie(new Joueur(joueurName), positions);

        if (partieOk) {

            this.partieDao.findById(id).then((partie) => {
                if (partie.etat == "initie") {


                    partie.joins(id, new Joueur(joueurName), positions);
                    this.partieDao.joins(partie).then((id) => this.partieDao.findById(id).then((partie) => {
                        partie.positions.delete(partie.initiateur);
                        res.status(201);
                        res.json(partie);

                    }));
                } else {
                    res.status(200);
                    res.json({error: "partie non disponible"});
                }
            });

        }
        else
        {
            res.status(200);
            res.json({error: "coordonnées illégales"});
        }


    }

    /**
     * Permet de jouer un coup necessite une  partie un joueur et des coordonnées,
     * la partie doit-être dans l'état joins
     * @param req
     * @param res
     */
    joue(req,res){
        const id = req.params.id;
        const coordonnne =  new Coordonnee(req.body.coordonnee.x, req.body.coordonnee.y) ;
        const joueurName = req.body.joueur;
        console.log('-- partieController joue -- ',id,joueurName,coordonnne);
        this.partieDao.findById(id).then((partie) => {

               if (partie.etat != "joins") {
                    res.status(200);
                    res.json({error: "partie non disponible"});
                } else if (partie.joueurCourant.nom != joueurName) {
                    res.status(200);
                    res.json({error: "pas mon tour"});
                } else {

                   if (!coordonnne.estValide(partie.dimension)) {

                       res.status(200);
                       res.json({error: "coordonnees non valides"});
                   } else {
                       const bateauTouche = partie.touche(coordonnne.x, coordonnne.y);

                       if (bateauTouche == null) {
                           res.status(201);
                           res.json({bateau: "non touche", gagnant: partie.gagnant});
                       } else {
                           this.partieDao.toucheBateau(partie, coordonnne.x, coordonnne.y);

                           if (!partie.gagne()) {
                               if (bateauTouche.estCoule()) {
                                   res.status(201);
                                   res.json({bateau: "coule", gagnant: partie.gagnant})
                               }else {
                               res.status(201);
                               res.json({bateau: "touche", gagnant: partie.gagnant})
                               }

                           } else {
                               //isme
                               this.partieDao.finie(partie);
                               res.status(201);
                               res.json({bateau: "coule", gagnant: partie.gagnant});

                           }

                       }
                       this.partieDao.switchUser(partie);
                   }

               }

        }).catch((err) => {
            res.status = 200;
            res.json({error: "coup impossible"});
        })

    };

    /**
     * Permet l'addichage d'une partie
     * @param req
     * @param res
     */
    debug(req, res) {
        const id = req.params.id;
        this.partieDao.findById(id).then((partie) => {
            console.log('-- partie debug --');

            console.log(`-- id : ${partie.id} initiateur: ${partie.initiateur.nom} invite: ${partie.invite.nom} joueur courant: ${partie.joueurCourant.nom}`);
            console.log(`-- etat : ${partie.etat} gagnant: ${partie.gagnant.nom}`);
            console.log(`-- positions  ${partie.initiateur.nom} : `);
            console.log(`${partie.positions.get(partie.initiateur)}`);
            console.log(`-- positions  ${partie.invite.nom} : `);
            console.log(`${partie.positions.get(partie.invite)}`);
            res.status(200);
            res.json({text: 'info dans la console du serveur'});
        })
    }

    info(req,res){
        const id = req.params.id;
        console.log('-- partieController joue -- ',id);
        this.partieDao.findById(id).then((partie) => {
            partie.positions.clear();

            res.status(200);
            res.json(partie);

        }).catch(()=> {

            res.status(200);
            res.json({error : "partie non disponible"});
        });
    }

}

module.exports = PartieController;