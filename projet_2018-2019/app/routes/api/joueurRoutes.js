/* Load Modules */
const express = require('express');
const router = express.Router();

/* Load controller */
const JoueurController = require('../../controller/joueurController');
const joueurController = new JoueurController();

/**
 * Liste des utilistateurs
 * curl http://localhost:3000/api/v0/joueur/
 * HTTP status : 200
 *
 * [{"nom":"..."},{"nom":"...."}]
 */
router.get('/',function(req, res){
    joueurController.findAll(res);
});

/**
 * Creation d'un utilisateur
 * curl -H "Content-Type: application/json" -X POST -d '{"nom":"jub1"}'
 *
 *  HTTP status 201
 * {"nom":"jub1"}
 * ou
 * {"errorCode":11,"message":"Invalid arguments"}
 */
router.post('/', function (req, res) {
    joueurController.create(req, res);
});


module.exports = router;