package TPMiniJeu.xefi.com;

public class CompteEpargne extends Compte {
    private double tauxAbondement;

    // Constructeurs
    public CompteEpargne() {
        super();
    }

    public CompteEpargne(String proprietaire, double tauxAbondement) {
        super(proprietaire);
        this.tauxAbondement = tauxAbondement;
    }

    // Calcul des bénéfices basé sur le taux d'abondement
    @Override
    public double calculBenefice() {
        return calculSolde() * tauxAbondement;
    }

    // Affichage des informations du compte épargne
    @Override
    public void information() {
        System.out.println("*******************************************");
        System.out.printf("Solde : %.2f\n", soldeFinal());
        System.out.printf("Taux d'abondement : %.1f %%\n", tauxAbondement * 100);
        System.out.println("Opérations :");
        for (Operation op : operations) {
            System.out.println(op);
        }
        System.out.println("*******************************************");
    }

    // Getter et setter pour le taux d'abondement
    public double getTauxAbondement() {
        return tauxAbondement;
    }

    public void setTauxAbondement(double tauxAbondement) {
        this.tauxAbondement = tauxAbondement;
    }
}