package bowling;

class Joueur {
    private String nom;
    private PartieMonoJoueur partie;

    public Joueur(String nom) {
        this.nom = nom;
        this.partie = new PartieMonoJoueur();
    }

    public String getNom() {
        return nom;
    }

    public PartieMonoJoueur getPartie() {
        return partie;
    }
}
