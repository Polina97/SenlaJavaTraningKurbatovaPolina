package disk;

import music.EMusicGenres;
import music.MusicComposition;

public class Disk implements IDisk {
	private MusicComposition[] music�ompositions;
	private int continuance;
	private EMusicGenres[] genres;
	StringBuilder str = new StringBuilder();

	public Disk(MusicComposition[] music�ompositions) {
		this.music�ompositions = music�ompositions;

	}

	@Override
	public void addMusicComposition(MusicComposition �omposition) {
		if (music�ompositions != null) {
			if (!isInDisk(�omposition)) {
				MusicComposition[] music�ompositions2 = new MusicComposition[music�ompositions.length + 1];
				for (int i = 0; i < music�ompositions.length; i++) {
					music�ompositions2[i] = music�ompositions[i];
				}
				music�ompositions2[music�ompositions.length] = �omposition;
				music�ompositions = music�ompositions2;
				System.out.println(�omposition.getName() + " was added.");
			} else {
				System.out.println(�omposition.getName() + " is already exist.");
			}

		} else {
			music�ompositions = new MusicComposition[1];
			music�ompositions[0] = �omposition;
			System.out.println(�omposition.getName() + " was added.");
		}
	}

	@Override
	public void deleteMusicComposition(MusicComposition �omposition) {
		if (music�ompositions != null && isInDisk(�omposition)) {
			MusicComposition[] music�ompositions2 = new MusicComposition[music�ompositions.length - 1];
			int i = 0;
			for (MusicComposition m : music�ompositions) {
				if (m.getName() != �omposition.getName())
					music�ompositions2[i++] = m;
			}
			music�ompositions = music�ompositions2;
			System.out.println(�omposition.getName() + " was deleted.");
		}

	}

	@Override
	public int countContinuance() {
		continuance = 0;
		for (MusicComposition m : music�ompositions) {
			continuance += m.getContinuance();
		}
		return continuance;
	}

	@Override
	public String getGenre() {
		str.delete(0, str.length());
		for (MusicComposition m : music�ompositions) {
			addGenre(m);
		}
		for (EMusicGenres g : genres) {
			str.append(g.toString().toLowerCase()).append(" ,");
		}
		return str.toString();
	}

	public void printDisk() {
		System.out.println("Our disk!");
		for (MusicComposition m : music�ompositions) {
			str.delete(0, str.length());
			System.out.println(str.append(m.getMusicGenre().toString()).append(", Name: ").append(m.getName())
					.append(", Autor: ").append(m.getAutor()).append(", Continuance: ").append(m.getContinuance()));
		}

	}

	private void addGenre(MusicComposition composition) {
		if (genres != null) {
			if (!inGenres(composition.getMusicGenre())) {
				EMusicGenres[] genres2 = new EMusicGenres[genres.length + 1];
				for (int i = 0; i < genres.length; i++) {
					genres2[i] = genres[i];
				}
				genres2[genres.length] = composition.getMusicGenre();
				genres = genres2;
			}

		} else {
			genres = new EMusicGenres[1];
			genres[0] = composition.getMusicGenre();
		}
	}

	private boolean isInDisk(MusicComposition �omposition) {
		boolean answer = false;
		for (MusicComposition music�omposition : music�ompositions) {
			if (music�omposition.getName() == �omposition.getName()
					&& music�omposition.getAutor() == �omposition.getAutor()) {
				answer = true;
				break;
			}
		}
		return answer;
	}

	private boolean inGenres(EMusicGenres genre) {
		boolean answer = false;
		for (EMusicGenres g : genres) {
			if (g.equals(genre)) {
				answer = true;
				break;
			}
		}
		return answer;
	}

}
