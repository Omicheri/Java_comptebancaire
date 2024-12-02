package TPMiniJeu.xefi.com;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class TPGestionCompte {
    private static GestionDeComptes gestionComptes = new GestionDeComptes();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choix;
        do {
            afficherMenu();
            choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer le retour à la ligne

            switch (choix) {
                case 1:
                    creerCompteCourant();
                    break;
                case 2:
                    creerCompteEpargne();
                    break;
                case 3:
                    crediterCompte();
                    break;
                case 4:
                    debiterCompte();
                    break;
                case 5:
                    effectuerVirement();
                    break;
                case 6:
                    afficherListeComptes();
                    break;
                case 7:
                    trierComptes();
                    break;
                case 8:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        } while (choix != 8);
    }

    private static void afficherMenu() {
        System.out.println("\n----- Menu de Gestion Bancaire -----");
        System.out.println("1. Créer un compte courant");
        System.out.println("2. Créer un compte épargne");
        System.out.println("3. Créditer un compte");
        System.out.println("4. Débiter un compte");
        System.out.println("5. Effectuer un virement");
        System.out.println("6. Afficher la liste des comptes");
        System.out.println("7. Trier les comptes");
        System.out.println("8. Quitter");
        System.out.print("Votre choix : ");
    }

    private static void creerCompteCourant() {
        System.out.print("Nom du propriétaire : ");
        String proprietaire = scanner.nextLine();
        System.out.print("Découvert autorisé : ");
        double decouvertAutorise = scanner.nextDouble();
        scanner.nextLine();

        CompteCourant compte = new CompteCourant(proprietaire, decouvertAutorise);
        gestionComptes.ajouterCompte(compte);
        System.out.println("Compte courant créé avec succès !");
    }

    private static void creerCompteEpargne() {
        System.out.print("Nom du propriétaire : ");
        String proprietaire = scanner.nextLine();
        System.out.print("Taux d'abondement (en %) : ");
        double taux = scanner.nextDouble() / 100;
        scanner.nextLine();

        CompteEpargne compte = new CompteEpargne(proprietaire, taux);
        gestionComptes.ajouterCompte(compte);
        System.out.println("Compte épargne créé avec succès !");
    }

    private static void crediterCompte() {
        afficherListeComptes();
        System.out.print("Numéro du compte à créditer : ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.print("Montant à créditer : ");
        double montant = scanner.nextDouble();
        scanner.nextLine();

        gestionComptes.getComptes().get(index).crediter(montant);
        System.out.println("Compte crédité avec succès !");
    }

    private static void debiterCompte() {
        afficherListeComptes();
        System.out.print("Numéro du compte à débiter : ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.print("Montant à débiter : ");
        double montant = scanner.nextDouble();
        scanner.nextLine();

        gestionComptes.getComptes().get(index).debiter(montant);
        System.out.println("Compte débité avec succès !");
    }

    private static void effectuerVirement() {
        System.out.println("Comptes disponibles :");
        afficherListeComptes();

        System.out.print("Numéro du compte à débiter : ");
        int indexDebiteur = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.print("Numéro du compte à créditer : ");
        int indexCrediteur = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.print("Montant du virement : ");
        double montant = scanner.nextDouble();
        scanner.nextLine();

        Compte compteDebiteur = gestionComptes.getComptes().get(indexDebiteur);
        Compte compteCrediteur = gestionComptes.getComptes().get(indexCrediteur);

        compteDebiteur.debiter(montant, compteCrediteur);
        System.out.println("Virement effectué avec succès !");
    }

    private static void afficherListeComptes() {
        if (gestionComptes.getComptes().isEmpty()) {
            System.out.println("Aucun compte n'a été créé.");
            return;
        }
        System.out.println("Liste des comptes :");
        for (int i = 0; i < gestionComptes.getComptes().size(); i++) {
            System.out.println((i + 1) + ". " +
                    gestionComptes.getComptes().get(i).getProprietaire());
        }
    }

    private static void trierComptes() {
        gestionComptes.trierComptes();
        System.out.println("Comptes triés par solde.");
        gestionComptes.afficherComptes();
    }
}