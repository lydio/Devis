public class Section {
    public String nomSection;
    public String [] produit = new String[30];
    public int [] prix = new int[30];
    public int indexProduit = 0;

    public Section(String nom) {
        nomSection = nom;
    }

    public void ajouterProduit(String nomProduit, int prixProduit) {
        produit[indexProduit] = nomProduit;
        prix[indexProduit] = prixProduit;
        indexProduit++;
    }

    public void supprimerProduit(int numProduit) {
        if (numProduit < 0 || numProduit >= indexProduit || (numProduit == 0 && indexProduit == 0)) {
            Terminal.ecrireStringln("Erreur se produit n'existe pas.");
            Terminal.sautDeLigne();
        }
        else {
            produit[numProduit] = null;
            prix[numProduit] = 0;
            for(int i = numProduit; i<indexProduit; i++) {
                produit[i] = produit[i+1];
                prix[i] = prix[i+1];
            }
            indexProduit--;
        }
    }

    public void modifierPrix(int numProduit, int prixProduit) {
        if (numProduit < 0 || numProduit >= indexProduit || (numProduit == 0 && indexProduit == 0)) {
            Terminal.ecrireStringln("Erreur se produit n'existe pas.");
            Terminal.sautDeLigne();
        }
        else {
            prix[numProduit] = prixProduit;
        }
    }

    public int totalSection() {
        int total = 0;
        for(int i = 0; i<indexProduit; i++) {
            total = total + prix[i];
        }

        return total;
    }
    
    public void afficherSection() {
        Terminal.ecrireStringln(nomSection);

        for(int i=0; i<indexProduit; i++) { 
            System.out.printf("\t%-25s %4d\n", produit[i], prix[i]);
        }

        System.out.printf("\t%-25s %4d\n", "sous-total", totalSection());
    }
}
