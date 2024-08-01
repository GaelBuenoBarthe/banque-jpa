package entite;

import jakarta.persistence.Entity;
import java.util.List;

@Entity
public class LivretA extends Compte {
    private double taux;

    public LivretA(String numero, double solde, List<Client> clients, double taux) {
        super(numero, solde, clients);
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