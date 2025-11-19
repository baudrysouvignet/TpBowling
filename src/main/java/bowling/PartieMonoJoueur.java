package bowling;

import java.util.ArrayList;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancers successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class PartieMonoJoueur {
	private final int NOMBRE_DE_TOURS = 10;
	private int tourCourant = 1;
	private ArrayList<Tour> tours = new ArrayList<Tour>();

	public PartieMonoJoueur() {
		tours.add(new Tour(false));
	}

	/**
	 * Cette méthode doit être appelée à chaque lancer de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de ce
	 *                                lancer
	 * @throws IllegalStateException si la partie est terminée
	 * @return vrai si le joueur doit lancer à nouveau pour continuer son tour, faux
	 *         sinon
	 */
	public boolean enregistreLancer(int nombreDeQuillesAbattues) {
		if (estTerminee()) {
			throw new IllegalStateException("La partie est terminée");
		}

		Tour tour = tours.getLast();
		tour.addLancer(nombreDeQuillesAbattues);
		if (tour.estTermine() && tourCourant < NOMBRE_DE_TOURS) {
			tourCourant++;
			tours.add(new Tour(tourCourant == NOMBRE_DE_TOURS));
		} else if (tour.estTermine() && tourCourant == NOMBRE_DE_TOURS) {
			tourCourant = 0;
		}

		return !tour.estTermine();
	}

	/**
	 * Cette méthode donne le score du joueur.
	 * Si la partie n'est pas terminée, on considère que les lancers restants
	 * abattent 0 quille.
	 * 
	 * @return Le score du joueur
	 */
	public int score() {
		Integer scoreTotal = 0;

		for (int i = 0; i < tours.size(); i++) {
			Tour tour = tours.get(i);
			ArrayList<Integer> quilles = quilleTour(i);

			for (Integer q : quilles) {
				scoreTotal += q;
			}

			if (i < 9) {
				if (tour.isStrike()) {
					ArrayList<Integer> next = quilleTour(i + 1);
					scoreTotal += next.get(0);

					if (next.size() > 1) {
						scoreTotal += next.get(1);
					} else if (i + 2 < tours.size()) {
						ArrayList<Integer> nextNext = quilleTour(i + 2);
						scoreTotal += nextNext.get(0);
					}
				} else if (tour.isSpare()) {
					scoreTotal += quilleTour(i + 1).get(0);
				}
			}
		}

		return scoreTotal;
	}

	private ArrayList<Integer> quilleTour(int indexTour) {
		if (tours.get(indexTour) == null) {
			ArrayList<Integer> quillesVides = new ArrayList<Integer>();
			quillesVides.add(0);
			quillesVides.add(0);
			quillesVides.add(0);
			return quillesVides;
		}
		return tours.get(indexTour).getLancers();
	}

	/**
	 * @return vrai si la partie est terminée pour ce joueur, faux sinon
	 */
	public boolean estTerminee() {
		return tourCourant == 0;
	}

	/**
	 * @return Le numéro du tour courant [1..10], ou 0 si le jeu est fini
	 */
	public int numeroTourCourant() {
		return tourCourant;
	}

	/**
	 * @return Le numéro du prochain lancer pour tour courant [1..3], ou 0 si le jeu
	 *         est fini
	 */
	public int numeroProchainLancer() {
		return tours.getLast().getNumLancerCourant() + 1;
	}

}
