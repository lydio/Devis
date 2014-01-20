public class Devis {    
    public static void main(String [] args) {
        Section [] sections = new Section[20]; 
        int numSections = 0;
        int indexSectionCur = 0;
        int choix = 0;
        boolean exit = false;
        
        Terminal.ecrireStringln("Entrez la premiere section du devis");
        ajouterSection(sections, indexSectionCur); 
        
        while(!exit) {
            choix = affichageMenu();
            if(choix==1) {
                ajouterLigne(sections[indexSectionCur]); // OK  
            }
            else if(choix==2) {
                suprimmerLigne(sections[indexSectionCur]); // OK
            }
            else if(choix==3) {
                modifierPrixProduit(sections[indexSectionCur]); // OK
            }
            else if(choix==4) {
                numSections++;
                indexSectionCur = numSections;
                ajouterSection(sections, numSections); // OK
            }
            else if(choix==5) {
                indexSectionCur = ouvrirSection(sections, numSections, indexSectionCur); // OK
            }
            else if(choix==6) {
                editionDevis(sections, numSections); // OK
            }
            else if(choix==7) {
                editionDevis(sections, numSections); // OK
                exit = true;
            }
            else if(choix==8) {
                exit = true; // OK
            }
            else Terminal.ecrireStringln("Choix incorrect: vous devez taper un nombre entre 1 et 7."); // OK
        }
    }
    
    public static int affichageMenu() {
        Terminal.ecrireStringln("1: Ajouter une ligne.");
        Terminal.ecrireStringln("2: Supprimer une ligne.");
        Terminal.ecrireStringln("3: Modifier le prix d'une ligne.");
        Terminal.ecrireStringln("4: Ajouter une nouvelle section.");
        Terminal.ecrireStringln("5: Ouvrir une section existante.");
        Terminal.ecrireStringln("6: Afficher un apercu du devis.");
        Terminal.ecrireStringln("7: Editer le devis.");
        Terminal.ecrireStringln("8: Quitter sans editer.");
        Terminal.ecrireString("Votre choix: ");

        return verifierSaisieInt();
    }

    public static void ajouterLigne(Section section) {
        String nomProduit;
        int prixProduit;

        Terminal.ecrireString("Entrez l'intitule du produit: ");
        nomProduit = Terminal.lireString();
        
        if ((nomProduit.length() <= 0)) {
            erreurSaisie();
        }
        else {
            Terminal.ecrireString("Entrez le montant du produit: ");
            prixProduit = verifierSaisieInt();
            section.ajouterProduit(nomProduit, prixProduit);
        }
    }

    public static void suprimmerLigne(Section section) {
        for(int i = 0; i < section.indexProduit; i++) {
            Terminal.ecrireStringln("ligne numero " + i + ":  " + 
            section.produit[i] + " " + section.prix[i]);
        }
        
        Terminal.ecrireString("Entrez le numero de la ligne a supprimer : ");    
        int numLigne = verifierSaisieInt();

        section.supprimerProduit(numLigne);
    }

    public static void modifierPrixProduit(Section section) {
        for(int i = 0; i < section.indexProduit; i++) {
            Terminal.ecrireStringln("ligne numero " + i + ":  " + 
            section.produit[i] + " " + section.prix[i]);
        }

        Terminal.ecrireString("Entrez le numero de la ligne a modifier : ");    
        int numLigne = verifierSaisieInt();

        Terminal.ecrireString("Entrez le nouveau montant du produit: ");
        int prixProduit = verifierSaisieInt();

        section.modifierPrix(numLigne, prixProduit);
    } 

    public static void ajouterSection(Section[] sections, int numSections) {
        String nomSection;

        do {
            Terminal.ecrireString("Entrez le nom de la nouvelle section: ");
            nomSection = Terminal.lireString();
        }
        while ((nomSection.length() <= 0));

        sections[numSections] = new Section(nomSection);
    }

    public static int ouvrirSection(Section[] sections, int numSections, int indexSectionCur) {
        for(int i = 0; i<=numSections; i++) {
            Terminal.ecrireStringln("Ouvrir section " + i + ": " + sections[i].nomSection);
        }

        Terminal.ecrireString("Entrez le numero de la section a ouvrir: ");
        int numSection = verifierSaisieInt();
        
        if (numSection < 0 || numSection > numSections) {
            erreurSaisie();
            return indexSectionCur;          
        }
        else {
            return numSection;
        }
    }

    public static void editionDevis(Section[] sections, int numSections) {
        int totalNet = 0;
        
        for(int i = 0; i<=numSections; i++) {
            sections[i].afficherSection();
            totalNet = totalNet + sections[i].totalSection();
        }

        Terminal.sautDeLigne();
        //Terminal.ecrireStringln("\tNET A PAYER\t\t\t" + totalNet);
        System.out.printf("\t%-20s %4d\n", "NET A PAYER", totalNet);
        Terminal.sautDeLigne();
    }

    public static int verifierSaisieInt() {
        try {
            return Terminal.lireInt();    
        }
        catch (TerminalException exc) {
            return -1;
        }
    }

    public static void erreurSaisie() {
        Terminal.ecrireStringln("Erreur de saisie recommencez.");
        Terminal.sautDeLigne(); 
    }
}