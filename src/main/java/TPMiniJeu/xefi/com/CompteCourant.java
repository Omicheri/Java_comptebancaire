package TPMiniJeu.xefi.com;

public class CompteCourant extends Compte {
    private double decouvertAutorise;

    // Constructeurs
    public CompteCourant() {
        super();
    }

    public CompteCourant(String proprietaire, double decouvertAutorise) {
        super(proprietaire);
        this.decouvertAutorise = decouvertAutorise;
    }

    // Pas de bénéfices pour un compte courant
    @Override
    public double calculBenefice() {
        return 0;
    }

    // Affichage des informations du compte courant
    @Override
    public void information() {
        System.out.println("*******************************************");
        System.out.printf("Solde : %.2f\n", soldeFinal());
        System.out.printf("Découvert autorisé : %.2f\n", decouvertAutorise);
        System.out.println("Opérations :");
        for (Operation op : operations) {
            System.out.println(op);
        }
        System.out.println("*******************************************");
    }

    // Getter et setter pour le découvert autorisé
    public double getDecouvertAutorise() {
        return decouvertAutorise;
    }

    public void setDecouvertAutorise(double decouvertAutorise) {
        this.decouvertAutorise = decouvertAutorise;
    }
}