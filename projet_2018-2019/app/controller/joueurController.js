/* Load Driver Data Access Object */
const JoueurDao = require('../dao/joueurDao');

/* Load Controller Common function */
const controllerCommon = require('./common/controllerCommon');

/* Load Joueur entity */
const Joueur = require('../model/joueur');

/**
 * Joueur Controller
 */
class JoueurController {

    constructor() {
        this.joueurDao = new JoueurDao();
        this.common = new controllerCommon();
    }

    /**
     * Permet d'obtenir la liste des joueurs
     * @param res
     */
    findAll(res) {
        this.joueurDao.findAll()
            .then(this.common.findSuccess(res))
            .catch(this.common.findError(res));
    };

    /**
     * Permet de créér un joueur
     * @param req
     * @param res
     * @returns {Promise<T | never>}
     */
    create(req, res) {
        let joueur = new Joueur();
        joueur.nom = req.body.nom;
        return this.joueurDao.create(joueur)
                .then(()=>{
                    this.joueurDao.findByNom(joueur.nom).then((joueur) => {
                        console.log('-- joueurController create --',joueur);
                        res.status(201);
                        res.json(joueur);

                        }
                    )
                 })
                .catch(this.common.serverError(res));
    };


}

module.exports = JoueurController;