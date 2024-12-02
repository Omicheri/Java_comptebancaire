package TPMiniJeu.xefi.com;

import java.util.ArrayList;
import java.util.List;

public abstract class Compte implements Comparable<Compte> {
    protected List<Operation> operations;
    protected String proprietaire;

    // Constructeurs
    public Compte() {
        this.operations = new ArrayList<>();
    }

    public Compte(String proprietaire) {
        this.proprietaire = proprietaire;
        this.operations = new ArrayList<>();
    }

    // Méthodes de crédit et débit
    public void crediter(double montant) {
        operations.add(new Operation(montant, Mouvement.CREDIT));
    }

    public void debiter(double montant) {
        operations.add(new Operation(montant, Mouvement.DEBIT));
    }

    public void crediter(double montant, Compte compteADebiter) {
        this.crediter(montant);
        compteADebiter.debiter(montant);
    }

    public void debiter(double montant, Compte compteACrediter) {
        this.debiter(montant);
        compteACrediter.crediter(montant);
    }

    // Calcul du solde
    public double calculSolde() {
        return operations.stream()
                .mapToDouble(op -> op.getType() == Mouvement.CREDIT ? op.getMontant() : -op.getMontant())
                .sum();
    }

    // Méthode abstraite pour calculer les bénéfices
    public abstract double calculBenefice();

    // Solde final avec bénéfices
    public double soldeFinal() {
        return calculSolde() + calculBenefice();
    }

    // Comparaison des comptes basée sur le solde
    @Override
    public int compareTo(Compte autreCompte) {
        return Double.compare(this.calculSolde(), autreCompte.calculSolde());
    }

    // Méthode d'information abstraite
    public abstract void information();

    // Getters et setters
    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public List<Operation> getOperations() {
        return operations;
    }
}
