package disk;

import music.MusicComposition;

public interface IDisk {
	void addMusicComposition(MusicComposition �omposition);

	void deleteMusicComposition(MusicComposition �omposition);

	int countContinuance();

	String getGenre();

}
