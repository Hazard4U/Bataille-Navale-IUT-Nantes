#!/bin/bash

#creation de 2 joueurs jub1 jub2
echo creation de jub1
body='{"nom":"jub1"}'
curl  -H "Content-Type: application/json" -X POST -d $body  http://localhost:3000/api/v0/joueur/
echo

echo creation de jub2
body='{"nom":"jub2"}'
curl  -H "Content-Type: application/json" -X POST -d $body  http://localhost:3000/api/v0/joueur/
echo

echo creation de jub3
body='{"nom":"jub3"}'
curl  -H "Content-Type: application/json" -X POST -d $body  http://localhost:3000/api/v0/joueur/
echo

echo creation de jub4
body='{"nom":"jub4"}'
curl  -H "Content-Type: application/json" -X POST -d $body  http://localhost:3000/api/v0/joueur/
echo

echo liste des joueurs
curl  http://localhost:3000/api/v0/joueur/
echo

#creation d'une partie par un joueur non existant
echo creation d une partie par joueur non existant
read -d '' body <<EOF
{"joueur": "jub5",
 "positions": [
                  {
                      "bateau": "porte-avion",
                      "coordonnees": [
                        {"x": 1, "y":1}, {"x": 2, "y":1}, {"x": 3, "y":1}, {"x": 4, "y":1}, {"x": 5, "y":1}]
                  },
                      {
                        "bateau": "croiseur",
                        "coordonnees": [
                           {"x": 3, "y":3}, {"x": 3, "y":4}, {"x": 3, "y":5}, {"x": 3, "y":6}]
                      },
                      {
                         "bateau": "contre torpilleur 1",
                         "coordonnees": [
                           {"x": 5, "y":4}, {"x": 6, "y":4}, {"x": 7, "y":4}]
                      },
                      {
                         "bateau": "contre torpilleur 2",
                         "coordonnees": [
                           {"x": 1, "y":8}, {"x": 2, "y":8}, {"x": 3, "y":8}]
                      },
                      {
                         "bateau": "torpilleur",
                         "coordonnees": [
                            {"x": 8, "y":8}, {"x": 8, "y":9}]
                      },
                      {
                        "bateau": "choux-marin",
                        "coordonnees": [
                            {"x": 2, "y":2}]
                      }
                ]
        }
EOF

curl  -H "Content-Type: application/json" -X POST -d "$body"  http://localhost:3000/api/v0/partie/
echo

#creation d une partie
echo creation d une partie par jub1 avec un bateau hors grille
read -d '' body <<EOF
{"joueur": "jub1",
 "positions": [
                  {
                      "bateau": "porte-avion",
                      "coordonnees": [
                        {"x": 1, "y":10}, {"x": 2, "y":1}, {"x": 3, "y":1}, {"x": 4, "y":1}, {"x": 5, "y":1}]
                  },
                      {
                        "bateau": "croiseur",
                        "coordonnees": [
                           {"x": 3, "y":3}, {"x": 3, "y":4}, {"x": 3, "y":5}, {"x": 3, "y":6}]
                      },
                      {
                         "bateau": "contre torpilleur 1",
                         "coordonnees": [
                           {"x": 5, "y":4}, {"x": 6, "y":4}, {"x": 7, "y":4}]
                      },
                      {
                         "bateau": "contre torpilleur 2",
                         "coordonnees": [
                           {"x": 1, "y":8}, {"x": 2, "y":8}, {"x": 3, "y":8}]
                      },
                      {
                         "bateau": "torpilleur",
                         "coordonnees": [
                            {"x": 8, "y":8}, {"x": 8, "y":9}]
                      }
                ]
        }
EOF

curl  -H "Content-Type: application/json" -X POST -d "$body"  http://localhost:3000/api/v0/partie/
echo

#info sur la partie id : 2 qui n'existe pas
echo info sur une partie inexistante
curl  http://localhost:3000/api/v0/partie/info/2
echo

#creation d une partie
echo creation d une partie par jub1 avec deux bateau qui se chevauchent
read -d '' body <<EOF
{"joueur": "jub1",
 "positions": [
                  {
                      "bateau": "porte-avion",
                      "coordonnees": [
                        {"x": 1, "y":1}, {"x": 2, "y":1}, {"x": 3, "y":1}, {"x": 4, "y":1}, {"x": 5, "y":1}]
                  },
                      {
                        "bateau": "croiseur",
                        "coordonnees": [
                           {"x": 3, "y":3}, {"x": 3, "y":4}, {"x": 3, "y":5}, {"x": 3, "y":6}]
                      },
                      {
                         "bateau": "contre torpilleur 1",
                         "coordonnees": [
                           {"x": 5, "y":4}, {"x": 6, "y":4}, {"x": 7, "y":4}]
                      },
                      {
                         "bateau": "contre torpilleur 2",
                         "coordonnees": [
                           {"x": 7, "y":4}, {"x": 8, "y":4}, {"x": 9, "y":4}]
                      },
                      {
                         "bateau": "torpilleur",
                         "coordonnees": [
                            {"x": 8, "y":8}, {"x": 8, "y":9}]
                      }
                ]
        }
EOF

curl  -H "Content-Type: application/json" -X POST -d "$body"  http://localhost:3000/api/v0/partie/
echo
#creation d une partie
echo creation d une partie par jub1 avec un bateau impossible
read -d '' body <<EOF
{"joueur": "jub1",
 "positions": [
                  {
                      "bateau": "porte-avion",
                      "coordonnees": [
                        {"x": 1, "y":1}, {"x": 2, "y":1}, {"x": 3, "y":2}, {"x": 4, "y":1}, {"x": 5, "y":1}]
                  },
                      {
                        "bateau": "croiseur",
                        "coordonnees": [
                           {"x": 3, "y":3}, {"x": 3, "y":4}, {"x": 3, "y":5}, {"x": 3, "y":6}]
                      },
                      {
                         "bateau": "contre torpilleur 1",
                         "coordonnees": [
                           {"x": 5, "y":4}, {"x": 6, "y":4}, {"x": 7, "y":4}]
                      },
                      {
                         "bateau": "contre torpilleur 2",
                         "coordonnees": [
                           {"x": 1, "y":8}, {"x": 2, "y":8}, {"x": 3, "y":8}]
                      },
                      {
                         "bateau": "torpilleur",
                         "coordonnees": [
                            {"x": 8, "y":8}, {"x": 8, "y":9}]
                      }
                ]
        }
EOF

curl  -H "Content-Type: application/json" -X POST -d "$body"  http://localhost:3000/api/v0/partie/
echo


#creation d une partie
echo creation d une partie par jub1
read -d '' body <<EOF
{"joueur": "jub1",
 "positions": [
                  {
                      "bateau": "porte-avion",
                      "coordonnees": [
                        {"x": 1, "y":1}, {"x": 2, "y":1}, {"x": 3, "y":1}, {"x": 4, "y":1}, {"x": 5, "y":1}]
                  },
                      {
                        "bateau": "croiseur",
                        "coordonnees": [
                           {"x": 3, "y":3}, {"x": 3, "y":4}, {"x": 3, "y":5}, {"x": 3, "y":6}]
                      },
                      {
                         "bateau": "contre torpilleur 1",
                         "coordonnees": [
                           {"x": 5, "y":4}, {"x": 6, "y":4}, {"x": 7, "y":4}]
                      },
                      {
                         "bateau": "contre torpilleur 2",
                         "coordonnees": [
                           {"x": 1, "y":8}, {"x": 2, "y":8}, {"x": 3, "y":8}]
                      },
                      {
                         "bateau": "torpilleur",
                         "coordonnees": [
                            {"x": 8, "y":8}, {"x": 8, "y":9}]
                      }
                ]
        }
EOF

curl  -H "Content-Type: application/json" -X POST -d "$body"  http://localhost:3000/api/v0/partie/
echo

#creation d'une partie pour jub4 avec un bateau de 1 case
echo creation d une partie par jub4 avec un bateau de une case
read -d '' body <<EOF
{"joueur": "jub4",
 "positions": [
                  {
                      "bateau": "porte-avion",
                      "coordonnees": [
                        {"x": 1, "y":1}, {"x": 2, "y":1}, {"x": 3, "y":1}, {"x": 4, "y":1}, {"x": 5, "y":1}]
                  },
                      {
                        "bateau": "croiseur",
                        "coordonnees": [
                           {"x": 3, "y":3}, {"x": 3, "y":4}, {"x": 3, "y":5}, {"x": 3, "y":6}]
                      },
                      {
                         "bateau": "contre torpilleur 1",
                         "coordonnees": [
                           {"x": 5, "y":4}, {"x": 6, "y":4}, {"x": 7, "y":4}]
                      },
                      {
                         "bateau": "contre torpilleur 2",
                         "coordonnees": [
                           {"x": 1, "y":8}, {"x": 2, "y":8}, {"x": 3, "y":8}]
                      },
                      {
                         "bateau": "torpilleur",
                         "coordonnees": [
                            {"x": 8, "y":8}, {"x": 8, "y":9}]
                      },
                      {
                        "bateau": "choux-marin",
                        "coordonnees": [
                            {"x": 2, "y":2}]
                      }
                ]
        }
EOF

curl  -H "Content-Type: application/json" -X POST -d "$body"  http://localhost:3000/api/v0/partie/
echo


#info sur la partie id : 1 qui existe
echo info sur une partie inexistante
curl  http://localhost:3000/api/v0/partie/info/1
echo


#liste des parties initiee
echo liste des parties initiéées
curl http://localhost:3000/api/v0/partie/initie
echo

#joindre la partie 1
echo jub2 joint la partie 1
read -d '' body <<EOF
{"joueur": "jub2",
 "positions": [
                  {
                      "bateau": "porte-avion",
                      "coordonnees": [
                        {"x": 7, "y":2}, {"x": 7, "y":3}, {"x": 7, "y":4}, {"x": 7, "y":5}, {"x": 7, "y":6}]
                  },
                      {
                        "bateau": "croiseur",
                        "coordonnees": [
                           {"x": 0, "y":3}, {"x": 1, "y":3}, {"x": 2, "y":3}, {"x": 3, "y":3}]
                      },
                      {
                         "bateau": "contre torpilleur 1",
                         "coordonnees": [
                           {"x": 1, "y":6}, {"x": 2, "y":6}, {"x": 3, "y":6}]
                      },
                      {
                         "bateau": "contre torpilleur 2",
                         "coordonnees": [
                           {"x": 9, "y":2}, {"x": 9, "y":3}, {"x": 9, "y":4}]
                      },
                      {
                         "bateau": "torpilleur",
                         "coordonnees": [
                            {"x": 7, "y":8}, {"x": 8, "y":8}]
                      }
                ]
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joins/1
echo


echo jub3 joint la partie 1
read -d '' body <<EOF
{"joueur": "jub3",
 "positions": [
                  {
                      "bateau": "porte-avion",
                      "coordonnees": [
                        {"x": 7, "y":2}, {"x": 7, "y":3}, {"x": 7, "y":4}, {"x": 7, "y":5}, {"x": 7, "y":6}]
                  },
                      {
                        "bateau": "croiseur",
                        "coordonnees": [
                           {"x": 0, "y":3}, {"x": 1, "y":3}, {"x": 2, "y":3}, {"x": 3, "y":3}]
                      },
                      {
                         "bateau": "contre torpilleur 1",
                         "coordonnees": [
                           {"x": 1, "y":6}, {"x": 2, "y":6}, {"x": 3, "y":6}]
                      },
                      {
                         "bateau": "contre torpilleur 2",
                         "coordonnees": [
                           {"x": 9, "y":2}, {"x": 9, "y":3}, {"x": 9, "y":4}]
                      },
                      {
                         "bateau": "torpilleur",
                         "coordonnees": [
                            {"x": 7, "y":8}, {"x": 8, "y":8}]
                      }
                ]
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joins/1
echo

#le joueur jub3 joueue en (0,0) mais il n'a pas join la partie
echo joeur jub3 joue en 0 0 mais il n a pas join la partie
read -d '' body <<EOF
{"joueur": "jub3",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub3 joue en (0,0) sur une oartie non exitante
echo joeur jub3 joue en 0 0 joue en 0 0 sur une partie non exitante
read -d '' body <<EOF
{"joueur": "jub3",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/10
echo

#le joueur jub2 joueue en (0,0)ce n'est pas son tour
echo joeur jub2 joue en 0 0
read -d '' body <<EOF
{"joueur": "jub2",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo

#le joueur jub1 jouue en (10,0) est c'est son tour mais les coordonnees ne sont pas bonnes
echo joeur jub1 joue en 10 0
read -d '' body <<EOF
{"joueur": "jub1",
 "coordonnee": {"x":10, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub1 joueue en (0,0) est c'est son tour
echo joeur jub1 joue en 0 0
read -d '' body <<EOF
{"joueur": "jub1",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo



#le joueur jub2 joueue en (1,1) est c'est son tour
echo joeur jub2 joue en 1 1
read -d '' body <<EOF
{"joueur": "jub2",
 "coordonnee": {"x":1, "y":1}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo

#le joueur jub1 joueue en (0,0) est c'est son tour
echo joeur jub1 joue en 0 0
read -d '' body <<EOF
{"joueur": "jub1",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub2 joueue en (2,1) est c'est son tour
echo joeur jub2 joue en 2 1
read -d '' body <<EOF
{"joueur": "jub2",
 "coordonnee": {"x":2, "y":1}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo

#le joueur jub1 joueue en (0,0) est c'est son tour
echo joeur jub1 joue en 0 0
read -d '' body <<EOF
{"joueur": "jub1",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub2 joueue en (3,1) est c'est son tour
echo joeur jub2 joue en 3 1
read -d '' body <<EOF
{"joueur": "jub2",
 "coordonnee": {"x":3, "y":1}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub1 joueue en (0,0) est c'est son tour
echo joeur jub1 joue en 0 0
read -d '' body <<EOF
{"joueur": "jub1",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub2 joueue en (4,1) est c'est son tour
echo joeur jub2 joue en 4 1
read -d '' body <<EOF
{"joueur": "jub2",
 "coordonnee": {"x":4, "y":1}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo

#le joueur jub1 joueue en (0,0) est c'est son tour
echo joeur jub1 joue en 0 0
read -d '' body <<EOF
{"joueur": "jub1",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub2 joueue en (5,1) est c'est son tour
echo joeur jub2 joue en 5 1
read -d '' body <<EOF
{"joueur": "jub2",
 "coordonnee": {"x":5, "y":1}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo

#le joueur jub1 joueue en (0,0) est c'est son tour
echo joeur jub1 joue en 0 0
read -d '' body <<EOF
{"joueur": "jub1",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub2 joueue en (3,1) est c'est son tour
echo joeur jub2 joue en 3 3
read -d '' body <<EOF
{"joueur": "jub2",
 "coordonnee": {"x":3, "y":3}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo

#le joueur jub1 joueue en (0,0) est c'est son tour
echo joeur jub1 joue en 0 0
read -d '' body <<EOF
{"joueur": "jub1",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub2 joueue en (3,4) est c'est son tour
echo joeur jub2 joue en 3 4
read -d '' body <<EOF
{"joueur": "jub2",
 "coordonnee": {"x":3, "y":4}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo

#le joueur jub1 joueue en (0,0) est c'est son tour
echo joeur jub1 joue en 0 0
read -d '' body <<EOF
{"joueur": "jub1",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub2 joueue en (3,5) est c'est son tour
echo joeur jub2 joue en 3 5
read -d '' body <<EOF
{"joueur": "jub2",
 "coordonnee": {"x":3, "y":5}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub1 joueue en (0,0) est c'est son tour
echo joeur jub1 joue en 0 0
read -d '' body <<EOF
{"joueur": "jub1",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub2 joueue en (3,6) est c'est son tour
echo joeur jub2 joue en 3 6
read -d '' body <<EOF
{"joueur": "jub2",
 "coordonnee": {"x":3, "y":6}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo

#le joueur jub1 joueue en (0,0) est c'est son tour
echo joeur jub1 joue en 0 0
read -d '' body <<EOF
{"joueur": "jub1",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub2 joueue en (1,8) est c'est son tour
echo joeur jub2 joue en 1 8
read -d '' body <<EOF
{"joueur": "jub2",
 "coordonnee": {"x":1, "y":8}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo

#le joueur jub1 joueue en (0,0) est c'est son tour
echo joeur jub1 joue en 0 0
read -d '' body <<EOF
{"joueur": "jub1",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub2 joueue en (2,8) est c'est son tour
echo joeur jub2 joue en 2 8
read -d '' body <<EOF
{"joueur": "jub2",
 "coordonnee": {"x":2, "y":8}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo

#le joueur jub1 joueue en (0,0) est c'est son tour
echo joeur jub1 joue en 0 0
read -d '' body <<EOF
{"joueur": "jub1",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub2 joueue en (3,8) est c'est son tour
echo joeur jub2 joue en 3 8
read -d '' body <<EOF
{"joueur": "jub2",
 "coordonnee": {"x":3, "y":8}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo

#le joueur jub1 joueue en (0,0) est c'est son tour
echo joeur jub1 joue en 0 0
read -d '' body <<EOF
{"joueur": "jub1",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub2 joueue en (5,4) est c'est son tour
echo joeur jub2 joue en 5 4
read -d '' body <<EOF
{"joueur": "jub2",
 "coordonnee": {"x":5, "y":4}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo

#le joueur jub1 joueue en (0,0) est c'est son tour
echo joeur jub1 joue en 0 0
read -d '' body <<EOF
{"joueur": "jub1",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub2 joueue en (6,4) est c'est son tour
echo joeur jub2 joue en 6 4
read -d '' body <<EOF
{"joueur": "jub2",
 "coordonnee": {"x":6, "y":4}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub1 joueue en (0,0) est c'est son tour
echo joeur jub1 joue en 0 0
read -d '' body <<EOF
{"joueur": "jub1",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub2 joueue en (7,4) est c'est son tour
echo joeur jub2 joue en 7 4
read -d '' body <<EOF
{"joueur": "jub2",
 "coordonnee": {"x":7, "y":4}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub1 joueue en (0,0) est c'est son tour
echo joeur jub1 joue en 0 0
read -d '' body <<EOF
{"joueur": "jub1",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub2 joueue en (8,8) est c'est son tour
echo joeur jub2 joue en 8 8
read -d '' body <<EOF
{"joueur": "jub2",
 "coordonnee": {"x":8, "y":8}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo

#le joueur jub1 joueue en (0,0) est c'est son tour
echo joeur jub1 joue en 0 0
read -d '' body <<EOF
{"joueur": "jub1",
 "coordonnee": {"x":0, "y":0}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


#le joueur jub2 joueue en (8,9) est c'est son tour
echo joeur jub2 joue en 8 9
read -d '' body <<EOF
{"joueur": "jub2",
 "coordonnee": {"x":8, "y":9}
        }
EOF
curl  -H "Content-Type: application/json" -X PUT -d "$body"  http://localhost:3000/api/v0/partie/joue/1
echo


echo debug partie 1
curl  http://localhost:3000/api/v0/partie/debug/1
echo
