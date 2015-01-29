package com.squirrelsaga.modele;

import com.orm.SugarRecord;

/** Structure pour stocker nos arbres remarquables dans la base
 * Created by crobert on 13/01/2015.
 */
public class Arbre extends SugarRecord<Arbre> {
    //Attributs
    private int _id;
    private String _arbre_nom;
    private double _arbre_posx;
    private double _arbre_posy;
    private double _arbre_rayon;
    private String type;

    /**
     * Constructeur par défaut
     */
    public Arbre() {
    }

    /**
     * Constructeur complet
     * @param _id
     * @param _arbre_nom
     * @param _arbre_posx
     * @param _arbre_posy
     * @param _arbre_rayon
     * @param _arbre_type
     */
    public Arbre(int _id, String _arbre_nom, double _arbre_posx, double _arbre_posy, double _arbre_rayon, String _arbre_type) {
        this._id = _id;
        this._arbre_nom = _arbre_nom;
        this._arbre_posx = _arbre_posx;
        this._arbre_posy = _arbre_posy;
        this._arbre_rayon = _arbre_rayon;
        this.type = _arbre_type;
    }

    /**
     * Constructeur pour l'ajout en base
     * @param _arbre_nom
     * @param _arbre_posx
     * @param _arbre_posy
     * @param _arbre_rayon
     * @param _arbre_type
     */
    public Arbre(String _arbre_nom, double _arbre_posx, double _arbre_posy, double _arbre_rayon, String _arbre_type) {
        this._arbre_nom = _arbre_nom;
        this._arbre_posx = _arbre_posx;
        this._arbre_posy = _arbre_posy;
        this._arbre_rayon = _arbre_rayon;
        this.type = type;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_arbre_nom() {
        return _arbre_nom;
    }

    public void set_arbre_nom(String _arbre_nom) {
        this._arbre_nom = _arbre_nom;
    }

    public double get_arbre_posx() {
        return _arbre_posx;
    }

    public void set_arbre_posx(int _arbre_posx) {
        this._arbre_posx = _arbre_posx;
    }

    public double get_arbre_posy() {
        return _arbre_posy;
    }

    public void set_arbre_posy(int _arbre_posy) {
        this._arbre_posy = _arbre_posy;
    }

    public double get_arbre_rayon() {
        return _arbre_rayon;
    }

    public void set_arbre_rayon(int _arbre_rayon) {
        this._arbre_rayon = _arbre_rayon;
    }

    public String get_arbre_type() {
        return type;
    }

    public void set_arbre_type(String _arbre_type) {
        this.type = _arbre_type;
    }

}
