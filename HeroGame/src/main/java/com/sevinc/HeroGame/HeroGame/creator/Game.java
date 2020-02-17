package com.sevinc.HeroGame.HeroGame.creator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import com.sevinc.HeroGame.HeroGame.behaviour.IWritable;
import com.sevinc.HeroGame.HeroGame.model.Enemy;
import com.sevinc.HeroGame.HeroGame.model.Hero;

public class Game implements IWritable {

	private static Game _instance;

	private Game() {
	}

	Path path = Paths.get("files\\output.txt");

	public static Game getInstance() { //Singleton 

		if (_instance == null) { // ilk nerede getinstance metodunu cagırdıysam buraya girer.
			_instance = new Game();
		}

		return _instance; // zaten var ise çagırdıysan onu döndür  

	}

	public void startGame(Hero hero, List<Enemy> enemyList) {
		String string = "Hero started journey with " + hero.getHealtPoint() + " HP!";
		write(string);
		Enemy enemy = enemyList.get(0);

		for (int i = 0; i <= hero.getRouteMeter(); i++) { // metre metre gidiyor hero
			if (i == enemy.getPosition()) { // enemy varsa basla fighta
				while (enemy.isAlive() && hero.isAlive()) {// enemy ve hero ikisi hayatta kaldıgı sürece savasmaya devam edecek
					enemy.hit(hero.getAttack());//enemy vurdugunda hero nun attack ı kadar gidicek
					hero.hit(enemy.getAttack());//hero vurdugunda enemy nun attack ı kadar gidice
				}
				if (!enemy.isAlive()) {// enemy hayatta degilse 
					string = "Hero defeated " + enemy.getName() + " with " + hero.getHealtPoint() + " HP remaining"; // hero öldürdü enemy adı healthpoint hesapladık
					write(string);
					enemyList.remove(enemy);//enemylistten öldürülen enemy i kaldırıyorum
					if (enemyList.size() > 0) {
						enemy = enemyList.get(0); // listenin ilk ini al
					} else {// listtede kimse yoksa Hero hayattadır
						string = "Hero survived!";
						write(string);
						break;
					}
				} else { // enemy hayatta ise
					string = enemy.getName() + " defeated Hero with " + enemy.getHealthPoint() + " HP remaining"; // Hero öldü
					write(string);
					string = "Hero is Dead!! Last seen at position " + i + "!!";
					write(string);
					break;
				}
			}

		}

	}

	@Override
	public void write(String input) {

		System.out.println(input);

		try {

			Files.write(path, (input + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
