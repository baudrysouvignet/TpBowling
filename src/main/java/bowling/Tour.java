package bowling;

import java.util.ArrayList;

public class Tour {
    private ArrayList<Lancer> lancers = new ArrayList<>();
    private boolean isLastTour = false;

    public Tour(boolean isLastTour) {
        this.isLastTour = isLastTour;
    }

    public boolean isStrike() {
        return lancers.size() >= 1 && lancers.get(0).getQuillesAbattues() == 10;
    }

    public boolean isSpare() {
        if (lancers.size() >= 2) {
            int somme = lancers.get(0).getQuillesAbattues() + lancers.get(1).getQuillesAbattues();
            return somme == 10 && !isStrike();
        }
        return false;
    }

    public boolean estTermine() {

        if (!isLastTour) {
            if (isStrike()) return true;
            return lancers.size() == 2;
        }

        if (isLastTour) {
            if (isStrike()) {
                return lancers.size() == 3;
            }
            if (isSpare()) {
                return lancers.size() == 3;
            }
            return lancers.size() == 2;
        }

        return false;
    }

    public void addLancer(int quillesAbattues) {
        lancers.add(new Lancer(quillesAbattues));
    }

    public int getNumLancerCourant() {
        return lancers.size();
    }

    public ArrayList<Integer> getLancers() {
        ArrayList<Integer> quilles = new ArrayList<>();
        for (Lancer lancer : lancers) {
            quilles.add(lancer.getQuillesAbattues());
        }
        return quilles;
    }
}
