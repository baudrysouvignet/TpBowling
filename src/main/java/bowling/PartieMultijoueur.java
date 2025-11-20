package bowling;

import java.util.ArrayList;
import java.util.List;

class PartieMultiJoueurs implements IPartieMultiJoueurs {

    private List<Joueur> joueurs;
    private int indexJoueurCourant;
    private boolean partieCommencee;

    @Override
    public String demarreNouvellePartie(String[] nomsDesJoueurs) throws IllegalArgumentException {
        if (nomsDesJoueurs == null || nomsDesJoueurs.length == 0)
            throw new IllegalArgumentException("Il faut au moins un joueur");

        joueurs = new ArrayList<>();
        for (String nom : nomsDesJoueurs)
            joueurs.add(new Joueur(nom));

        indexJoueurCourant = 0;
        partieCommencee = true;

        Joueur j = joueurs.get(indexJoueurCourant);
        return formatMessage(j);
    }

    @Override
    public String enregistreLancer(int nb) throws IllegalStateException {
        if (!partieCommencee)
            throw new IllegalStateException("Partie non démarrée");

        Joueur joueur = joueurs.get(indexJoueurCourant);
        PartieMonoJoueur p = joueur.getPartie();

        boolean rejouer = p.enregistreLancer(nb);

        if (!rejouer) {
            indexJoueurCourant++;
            if (indexJoueurCourant >= joueurs.size()) {
                indexJoueurCourant = 0;
            }
        }

        boolean partieFinie = true;
        for (Joueur j : joueurs) {
            if (!j.getPartie().estTerminee())
                partieFinie = false;
        }

        if (partieFinie)
            return "Partie terminée";

        Joueur suivant = joueurs.get(indexJoueurCourant);
        return formatMessage(suivant);
    }

    @Override
    public int scorePour(String nom) throws IllegalArgumentException {
        for (Joueur j : joueurs)
            if (j.getNom().equals(nom))
                return j.getPartie().score();

        throw new IllegalArgumentException("Joueur introuvable : " + nom);
    }

    private String formatMessage(Joueur j) {
        PartieMonoJoueur p = j.getPartie();
        return "Prochain tir : joueur " + j.getNom() + ", tour n° " + p.numeroTourCourant() +
                ", boule n° " + p.numeroProchainLancer();
    }
}
