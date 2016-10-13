package music;

public class MusicComposition {
	private EMusicGenres musicGenre;
	private String name;
	private String autor;
	private int continuance;

	public MusicComposition(EMusicGenres musicGenre, String name, String autor, int continuance) {
		this.musicGenre = musicGenre;
		this.name = name;
		this.autor = autor;
		this.continuance = continuance;
	}

	public EMusicGenres getMusicGenre() {
		return musicGenre;
	}

	public String getName() {
		return name;
	}

	public String getAutor() {
		return autor;
	}

	public int getContinuance() {
		return continuance;
	}

}
