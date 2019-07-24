/**
 * Coordonnées sur le grille
 */
class Coordonnee {
    /**
     * Construit une coordonnée
     * @param x abscisse
     * @param y ordonnee
     */
    constructor(x,y){
        this.x = x;
        this.y = y;
    }

    toString(){
        return `(${this.x}, ${this.y})`;
    }

    estValide(dimension){
        return this.x >=0 && this.x < dimension && this.y>=0 && this.y < dimension;
    }
}

/**
 * Une coordonnée de bateau peut être touchée
 */
class CoordonneeBateau extends Coordonnee{
    /**
     * Construit une coordonnée non touchée
     * @param x
     * @param y
     * @touche true si touche par defaut false
     */
    constructor(x,y,touche=false){
        super(x,y);
        this.touche = touche;
    }
    toString(){
        return super.toString()+` ${this.touche}`
    }


}

/**
 * Un bateau est un nom et des coordonnées
 */
class Bateau {
    /**
     * Un bateau
     * @param bateau le nom du bateau
     * @param coordonneesBateau un tableau de coordonnéees
     */
    constructor(bateau, coordonneesBateau){
        this.nom= bateau;
        this.coordonnees = coordonneesBateau;
    }

    estCoule(){
        for(let coordonneeBateau of this.coordonnees)
            if (coordonneeBateau.touche == false)
                return false;
        return true;
    }

    /**
     * renvoie true si le bateau est touche, l'état est mémorisé
     * @param x
     * @param y
     * @returns {boolean}
     */
    estTouche(x,y){
        for(let coordonneeBateau of this.coordonnees)
            if (coordonneeBateau.x == x && coordonneeBateau.y ==y) {
                coordonneeBateau.touche = true;
                return true;
            }
        return false;
    }

    toString(){
        return `${this.nom} ${this.coordonnees} \n`;
    }


   estValide(dimension){
        //idée trie sur les abscisses et les ordonnées et vérification en incrementant et en comparant avec la taille
        let res = true;
        this.coordonnees.sort((b1,b2) => b1.x - b2.x).sort((b1,b2) => b1.y - b2.y);

        const length = this.coordonnees.length;
        if (length < 1)
            res =false;
        else
            if (!this.coordonnees[0].estValide(dimension) || !this.coordonnees[length-1].estValide(dimension))
                res = false;
            else
            {
                if (length>1) {
                    const dx = this.coordonnees[1].x - this.coordonnees[0].x;
                    const dy = this.coordonnees[1].y - this.coordonnees[0].y;
                    if (!((dx == 0 && dy == 1) || (dx == 1 || dy == 0)))
                        res = false;
                    else {
                        let cpt = 0;
                        for (let i = 0; i < length - 1 && res; i++)
                            if (this.coordonnees[i + 1].x - this.coordonnees[i].x != dx || this.coordonnees[i + 1].x - this.coordonnees[i].x != dx)
                                res = false;
                    }
                }
            }

        return res;
   }

}



class Partie {

    constructor(){
        this.id = null;
        this.initiateur = null;
        this.invite = null;
        this.joueurCourant = null;
        this.etat = "";
        this.positions = new Map();
        this.gagnant=null;
        this.dimension = 10;
    }

    /**
     * Permet d'initier une partie avec un joueur et les positions des bateaux
     * @param joueur
     * @param positions
     * @returns {boolean} true si les coordonnées sont bonne false sinon
     */
    initie(joueur, positions){
        let initieOk = true;
        this.initiateur = joueur;
        this.joueurCourant = joueur;
        let mesPositions = [];

        const tabTemp = positions.map((item) => item.coordonnees).reduce((acc, val) => acc.concat(val), []);
        const  tabTempSansDoublons = tabTemp.filter((elem, index, self) => self.findIndex(
            (t) => {return (t.x === elem.x && t.y === elem.y)}) === index);


        if (tabTemp.length == tabTempSansDoublons.length) {
            for (let position of positions) {
                let mesCoordonnees = [];
                for (let coordonnee of position.coordonnees) {
                    mesCoordonnees.push(new CoordonneeBateau(coordonnee.x, coordonnee.y))
                }
                let bateau = new Bateau(position.bateau, mesCoordonnees);
                if (!bateau.estValide(this.dimension))
                    initieOk = false;
                mesPositions.push(bateau);
            }
            this.positions.set(this.initiateur, mesPositions);
            if (initieOk)
                this.etat = "initie";
            else
                this.etat = "error";
            return initieOk;
        }
        else
            return false;
    }

    joins(id, joueur, positions){
        let joinsOk = true;
        this.id = id;
        this.invite = joueur;
        let mesPositions = [];
        const tabTemp = positions.map((item) => item.coordonnees).reduce((acc, val) => acc.concat(val), []);
        const  tabTempSansDoublons = tabTemp.filter((elem, index, self) => self.findIndex(
            (t) => {return (t.x === elem.x && t.y === elem.y)}) === index);


        if (tabTemp.length == tabTempSansDoublons.length) {
        for(let position of positions){
            let mesCoordonnees = [];
            for(let coordonnee of position.coordonnees){
                mesCoordonnees.push(new CoordonneeBateau(coordonnee.x, coordonnee.y))
            }
            let bateau =  new Bateau(position.bateau, mesCoordonnees);
            if (! bateau.estValide(this.dimension))
                joinsOk = false;
            mesPositions.push(new Bateau(position.bateau, mesCoordonnees));
        }
        this.positions.set(this.invite,mesPositions);
        if (joinsOk)
            this.etat="joins";
        else
            this.etat="error";
        return joinsOk;
        }
        else
            return false;

    }

    toJSON(){
        return {
            id: this.id,
            initiateur : this.initiateur,
            invite : this.invite,
            joueurCourant: this.joueurCourant,
            etat: this.etat,
            positions_initiateur : this.positions.has(this.initiateur) ? this.positions.get(this.initiateur) : null ,
            positions_invite : this.positions.has(this.invite) ? this.positions.get(this.invite) : null,

            gagnant: this.gagnant
        };
    }

    /**
     * Renvoir null si aucun bateau n'est touché par le joueur courant, sinon le bateau
     * le bateau est modifie
     * @param x
     * @param y
     * @returns {null || Bateau}
     */
    touche(x,y){
        //truc horrible car equalite sur la mémoire
       let tab = (Array.from(this.positions));
       let bateaux= null;
       if (tab[0][0].nom == this.joueurCourant.nom)
           bateaux = tab[1][1];
       else
           bateaux = tab[0][1];

        for(let bateau of bateaux)
            if (bateau.estTouche(x,y)){
              return bateau;
            }

        return null;
    }

    /**
     * Renvoie true si le joueur courant gagne et modifie le gagnant
     */
    gagne(){
        //truc horrible car equalite sur la mémoire
        let tab = (Array.from(this.positions));
        let bateaux= null;
        if (tab[0][0].nom == this.joueurCourant.nom)
            bateaux = tab[1][1];
        else
            bateaux = tab[0][1];
        for (let bateau of bateaux)
            if (!bateau.estCoule())
                return false;
        this.gagnant = this.joueurCourant;
        return true;
    }

    /**
     * Renvoie le joueur suivant
     * @returns {null}
     */
    nextUser(){
        if (this.joueurCourant.nom == this.initiateur.nom)
            return this.invite;
        else
            return  this.initiateur;
    }

    toString(){

        return `id: ${partie.id} initiateur: ${partie.initiateur.nom} invite: ${partie.invite.nom} joueur courant: ${partie.joueurCourant.nom}
        etat: ${partie.etat}
        initiateur: ${partie.positions.get(partie.initiateur)}
        invite: : ${partie.positions.get(partie.invite)}`;

    }
}

module.exports = {Partie: Partie, Bateau: Bateau, CoordonneeBateau: CoordonneeBateau, Coordonnee: Coordonnee};