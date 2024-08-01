package entite;

import jakarta.persistence.Entity;

@Entity
public class LivretA extends Compte {

    private double taux;

    public LivretA(String numero, double solde, Client client, double taux) {
        super(numero, solde, client);
        this.taux = taux;
    }

    public LivretA() {
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }
}