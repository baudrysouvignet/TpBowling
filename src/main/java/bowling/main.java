package bowling;
public class main {
    public static void main(String[] args) {
        IPartieMultiJoueurs partie = new PartieMultiJoueurs();

        String[] joueurs = { "Alice", "Bob" };
        System.out.println(partie.demarreNouvellePartie(joueurs));

        System.out.println(partie.enregistreLancer(5));
        System.out.println(partie.enregistreLancer(4));
        System.out.println(partie.enregistreLancer(10));

        System.out.println("Score Alice : " + partie.scorePour("Alice"));
        System.out.println("Score Bob : " + partie.scorePour("Bob"));
    }
}
