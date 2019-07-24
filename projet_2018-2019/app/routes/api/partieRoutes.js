/* Load Modules */
const express = require('express');
const router = express.Router();

/* Load controller */
const PartieController = require('../../controller/partieController');
const partieController = new PartieController();

/**
 * Création d'une partie
 *  POST
 *    0   1   2   3   4   5   6   7   8   9
 *0
 *1       P   P   P   P   P
 *2
 *3               C
 *4               C       D   D   D
 *5               C
 *6               C
 *7
 *8       D   D   D                   T
 *9                                   T
 *
 *  body
 *{
 *   joueur: jub1,
 *   positions: [
 *       {
 *           bateau: "porte-avion",
 *           coordonnees: [
 *               {x: 1, y:1}, {x: 2, y:1}, {x: 3, y:1}, {x: 4, y:1}, {x: 5, y:1}]
 *       },
 *       {
 *           bateau: "croiseur",
 *           coordonnees: [
 *               {x: 3, y:3}, {x: 3, y:4}, {x: 3, y:5}, {x: 3, y:6}]
 *       },
 *       {
 *           bateau: "contre torpilleur 1",
 *           coordonnees: [
 *               {x: 5, y:4}, {x: 6, y:4}, {x: 7, y:4}]
 *       },
 *       {
 *           bateau: "contre torpilleur 2",
 *           coordonnees: [
 *               {x: 1, y:8}, {x: 2, y:8}, {x: 3, y:8}]
 *       },
 *       {
 *           bateau: "torpilleur",
 *           coordonnees: [
 *               {x: 8, y:8}, {x: 8, y:9}]
 *       }
 *   ]
 *}
 * reponses
 * HTTP  status : 201
 * {"id":1,
 * "initiateur":{"nom":"jub1"},
 * "invite":null,
 * "joueurCourant":{"nom":"jub1"},
 * "etat":"initie",
 * "positions_initiateur":[ {"nom":"porte-avion",
 *                          "coordonnees":[{"x":1,"y":1,"touche":0},
 *                              {"x":2,"y":1,"touche":0},
 *                              {"x":3,"y":1,"touche":0},
 *                              {"x":4,"y":1,"touche":0},
 *                              {"x":5,"y":1,"touche":0}]},
 *                          {"nom":"contre torpilleur 2",
 *                          "coordonnees":[{"x":1,"y":8,"touche":0},
 *                              {"x":2,"y":8,"touche":0},
 *                              {"x":3,"y":8,"touche":0}]},
 *                          {"nom":"croiseur",
 *                          "coordonnees":[{"x":3,"y":3,"touche":0},
 *                               {"x":3,"y":4,"touche":0},
 *                               {"x":3,"y":5,"touche":0},
 *                               {"x":3,"y":6,"touche":0}]},
 *                          {"nom":"contre torpilleur 1",
 *                          "coordonnees":[{"x":5,"y":4,"touche":0},
 *                              {"x":6,"y":4,"touche":0},
 *                              {"x":7,"y":4,"touche":0}]},
 *                           {"nom":"torpilleur",
 *                           "coordonnees":[{"x":8,"y":8,"touche":0},
 *                           {"x":8,"y":9,"touche":0}]}],
 *"positions_invite":null,
 * "tirs_initiateur":null,
 * "tirs_invite":null,
 * "gagnant":null}
 *
 * ou si erreur coordonnées du bateau invalides
 * HTTP status : 200
 * {"error": "coordonnées illégales"}
 **/
router.post('/', function (req, res) {
    partieController.create(req, res);
});

/**
 * Permet de connaître les parties initiées (crée mais pas en cours)
 * GET
 * HTTP status : 200
 * [{"nom":"jub1"},{"nom":"jub2"},{"nom":"jub3"}]
 */
router.get('/initie',function(req,res){
    partieController.getInitie(res);
});

/**
 * Joindre une partie
 * PUT
 * param : id (de la partie : 1)
 *
 * {
 *   joueur: jub2,
 *   positions: [
 *       {
 *           bateau: "porte-avion",
 *           coordonnees: [
 *               {x: 7, y:2}, {x: 7, y:3}, {x: 7, y:4}, {x: 7, y:5}, {x: 7, y:6}]
 *       },
 *       {
 *           bateau: "croiseur",
 *           coordonnees: [
 *               {x: 0, y:3}, {x: 1, y:3}, {x: 2, y:3}, {x: 3, y:3}]
 *       },
 *       {
 *           bateau: "contre torpilleur 1",
 *           coordonnees: [
 *               {x: 9, y:2}, {x: 9, y:3}, {x: 9, y:4}]
 *       },
 *       {
 *           bateau: "contre torpilleur 2",
 *           coordonnees: [
 *               {x: 1, y:6}, {x: 2, y:6}, {x: 3, y:6}]
 *       },
 *       {
 *           bateau: "torpilleur",
 *           coordonnees: [
 *               {x: 7, y:8}, {x: 7, y:8}]
 *       }
 *   ]
 *}
 * reponse si ok
 * HTTP status : 201
 * {"id":1,
 * "initiateur":{"nom":"jub1"},
 * "invite":{"nom":"jub2"},
 * "joueurCourant":{"nom":"jub1"},
 * "etat":"joins",
 * "positions_initiateur":[
 *      {"nom":"porte-avion",
 *          "coordonnees":[{"x":1,"y":1,"touche":0},{"x":2,"y":1,"touche":0},{"x":3,"y":1,"touche":0},{"x":4,"y":1,"touche":0},{"x":5,"y":1,"touche":0}]},
 *      {"nom":"contre torpilleur 2",
 *          "coordonnees":[{"x":1,"y":8,"touche":0},{"x":2,"y":8,"touche":0},{"x":3,"y":8,"touche":0}]},
 *      {"nom":"croiseur",
 *          "coordonnees":[{"x":3,"y":3,"touche":0},{"x":3,"y":4,"touche":0},{"x":3,"y":5,"touche":0},{"x":3,"y":6,"touche":0}]},
 *      {"nom":"contre torpilleur 1",
 *          "coordonnees":[{"x":5,"y":4,"touche":0},{"x":6,"y":4,"touche":0},{"x":7,"y":4,"touche":0}]},
 *      {"nom":"torpilleur","coordonnees":[{"x":8,"y":8,"touche":0},{"x":8,"y":9,"touche":0}]}],
 * "positions_invite":[
 *      {"nom":"croiseur",
 *          "coordonnees":[{"x":0,"y":3,"touche":0},{"x":1,"y":3,"touche":0},{"x":2,"y":3,"touche":0},{"x":3,"y":3,"touche":0}]},
 *      {"nom":"contre torpilleur 1",
 *          "coordonnees":[{"x":1,"y":6,"touche":0},{"x":2,"y":6,"touche":0},{"x":3,"y":6,"touche":0}]},
 *      {"nom":"porte-avion",
 *          "coordonnees":[{"x":7,"y":2,"touche":0},{"x":7,"y":3,"touche":0},{"x":7,"y":4,"touche":0},{"x":7,"y":5,"touche":0},{"x":7,"y":6,"touche":0}]},
 *      {"nom":"torpilleur",
 *          "coordonnees":[{"x":7,"y":8,"touche":0},{"x":8,"y":8,"touche":0}]},
 *      {"nom":"contre torpilleur 2",
 *          "coordonnees":[{"x":9,"y":2,"touche":0},{"x":9,"y":3,"touche":0},{"x":9,"y":4,"touche":0}]}],
 * "tirs_initiateur":null,
 * "tirs_invite":null,
 * "gagnant":null}
 *
 *
 * reponse si pas ok : partie non initiée ou bateau non valide
 * HTTP status : 200
 * {
 *      "error": "coordonnées illégales"
 *  }
 *  ou {"error":"partie non initiée"}
 **/

router.put('/joins/:id', function(req,res){
   partieController.joins(req, res);
});

/**
 * Affiche pour debug les info de la partie dans la console
 */
router.get('/debug/:id',function(req,res){
    partieController.debug(req, res);
});

/**
 * Permet de joueur un coup
 * PUT
 * param : id partie
 * body :
 * {
 *     joueur: {"nom":"jub1"},
 *     coordonnee: {x:0, y:0}
 * }
 *
 * Reponse
 * HTTP: 201
 * {
 *     "bateau" : "touche" || "bateau" : "non touche" || "bateau" :"coule"
 *     "gagnant": null || "gagnant" : {"nom": xxx}
 *  }
 *
 *  HTTP: 200
 *  ou {
 *      "error": "pas mon tour"
 *  }
 *  ou {
 *      error: "coordonnees non valides"
 *  }
 *  ou {"error": "création impossible"} dans les autres cas
 **/
router.put("/joue/:id", function(req,res) {
    partieController.joue(req,res);
});

/**
 * Renvoie les infos relatives à une partie exception faite des positions des bateaux
 * GET
 * HTTP : 200
 * {"id":1,
 * "initiateur":{"nom":"jub1"},
 * "invite":{"nom":"jub2"}, //ou null
 * "joueurCourant":{"nom":"jub1"},
 * "etat":"finie", //ou intie ou joins
 * "positions_initiateur":null,
 * "positions_invite":null,
 * "gagnant":{"nom":"jub2"}} //ou null
 * ou
 * {"error":"partie non disponible"}
 * ou
 * {error: "coup impossible"} si join non associé ou partie non crée
 */
router.get("/info/:id", function(req,res) {
    partieController.info(req,res);
});


module.exports = router;