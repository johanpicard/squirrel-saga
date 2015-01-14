package com.squirrelsaga.modele;

import android.content.Context;
import android.provider.BaseColumns;

import com.orm.SugarRecord;

/** Structure pour stocker nos arbres remarquables dans la base
 * Created by crobert on 13/01/2015.
 */
public class Arbres  extends SugarRecord<Arbres> {
    //Attributs
    private int _id;
    private String _arbre_nom;
    private int _arbre_posx;
    private int _arbre_posy;
    private int _arbre_rayon;
    private String _arbre_type;

    /**
     * Constructeur par défaut
     */
    public Arbres() {
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
    public Arbres(int _id, String _arbre_nom, int _arbre_posx, int _arbre_posy, int _arbre_rayon, String _arbre_type) {
        this._id = _id;
        this._arbre_nom = _arbre_nom;
        this._arbre_posx = _arbre_posx;
        this._arbre_posy = _arbre_posy;
        this._arbre_rayon = _arbre_rayon;
        this._arbre_type = _arbre_type;
    }

    /**
     * Constructeur pour l'ajout en base
     * @param _arbre_nom
     * @param _arbre_posx
     * @param _arbre_posy
     * @param _arbre_rayon
     * @param _arbre_type
     */
    public Arbres(String _arbre_nom, int _arbre_posx, int _arbre_posy, int _arbre_rayon, String _arbre_type) {
        this._arbre_nom = _arbre_nom;
        this._arbre_posx = _arbre_posx;
        this._arbre_posy = _arbre_posy;
        this._arbre_rayon = _arbre_rayon;
        this._arbre_type = _arbre_type;
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

    public int get_arbre_posx() {
        return _arbre_posx;
    }

    public void set_arbre_posx(int _arbre_posx) {
        this._arbre_posx = _arbre_posx;
    }

    public int get_arbre_posy() {
        return _arbre_posy;
    }

    public void set_arbre_posy(int _arbre_posy) {
        this._arbre_posy = _arbre_posy;
    }

    public int get_arbre_rayon() {
        return _arbre_rayon;
    }

    public void set_arbre_rayon(int _arbre_rayon) {
        this._arbre_rayon = _arbre_rayon;
    }

    public String get_arbre_type() {
        return _arbre_type;
    }

    public void set_arbre_type(String _arbre_type) {
        this._arbre_type = _arbre_type;
    }

}
