/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.entities;

/**
 *
 * @author ksaay
 */
public class Livraison {
    private int id;
    private String dateliv;
    private String localisation;
    private Livreur livreur;

    public Livraison(int id, String dateliv, String localisation, Livreur livreur) {
        this.id = id;
        this.dateliv = dateliv;
        this.localisation = localisation;
        this.livreur = livreur;
    }

    public Livraison(String dateliv, String localisation, Livreur livreur) {
        this.dateliv = dateliv;
        this.localisation = localisation;
        this.livreur = livreur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateliv() {
        return dateliv;
    }

    public void setDateliv(String dateliv) {
        this.dateliv = dateliv;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    @Override
    public String toString() {
        return "Livraison{" + "dateliv=" + dateliv + ", localisation=" + localisation + ", livreur=" + livreur + '}';
    }
    
}
