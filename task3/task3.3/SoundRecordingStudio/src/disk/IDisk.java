package disk;

import music.MusicComposition;

public interface IDisk {
	void addMusicComposition(MusicComposition ñomposition);

	void deleteMusicComposition(MusicComposition ñomposition);

	int countContinuance();

	String getGenre();

}
