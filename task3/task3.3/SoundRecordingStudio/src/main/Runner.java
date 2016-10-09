package main;

import disk.Disk;
import music.EMusicGenres;
import music.MusicComposition;

public class Runner {
	private static MusicComposition m1 = new MusicComposition(EMusicGenres.POP, "Choke", "OneRepublic", 226);
	private static MusicComposition m2 = new MusicComposition(EMusicGenres.POP, "Cheap Thrills", "Sia", 224);
	private static MusicComposition m3 = new MusicComposition(EMusicGenres.CLASSIK, "Requiem in D minor", "Mozart",
			534);
	private static MusicComposition m4 = new MusicComposition(EMusicGenres.ROCK, "Born Again Tomorrow", "Bon Jovi ",
			252);
	private static Disk disk;

	public static void main(String[] args) {
		disk = new Disk(new MusicComposition[] { m1, m2, m3 });
		disk.addMusicComposition(m4);
		disk.deleteMusicComposition(m3);
		System.out.println("Continuance: " + disk.countContinuance() + " second.");
		System.out.println("Common genre: " + disk.getGenre());
		disk.printDisk();
	}

}
