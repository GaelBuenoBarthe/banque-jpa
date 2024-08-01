package entite;

import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Virement extends Operation {
    private String beneficiaire;

    public Virement(Date date, double montant, String description, Compte compte, String beneficiaire) {
        super(date, montant, description, compte);
        this.beneficiaire = beneficiaire;
    }

    public Virement() {
    }

    public String getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }
}