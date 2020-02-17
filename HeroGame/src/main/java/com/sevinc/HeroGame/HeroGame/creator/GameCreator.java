package com.sevinc.HeroGame.HeroGame.creator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.sevinc.HeroGame.HeroGame.model.Enemy;
import com.sevinc.HeroGame.HeroGame.model.Hero;

public class GameCreator { // Application a özel , file dan input  alıp game objelerini oluşturdugum yer 

	private String file;
	private List<Enemy> enemyList; 		//pozisyonları vs oluşturup pushladıgım yer 
	private List<Enemy> enemyTypeList; //inputta tutulan enemy tipleri
	private Hero currentHero; // Hero null olsa bile var

	public GameCreator(String file) {
		this.file = file;
		enemyList = new ArrayList<>();
		enemyTypeList = new ArrayList<>();
		currentHero = new Hero();
	}

	public void readFileAndCreateGame() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				findElement(line);
				line = reader.readLine();
			}
			reader.close();
			enemyList = enemyList.stream().sorted(Comparator.comparing(Enemy::getPosition)) // enemyListin position ları sıraladık
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void findElement(String line) {
		if (line.contains("Hero") && line.contains("hp")) {
			int healtPoint = getIntegerValue(line);
			currentHero.setHealtPoint(healtPoint);
		} else if (line.contains("Hero") && line.contains("attack")) {
			int attack = getIntegerValue(line);
			currentHero.setAttack(attack);
		} else if (line.contains("Resources")) {
			int resourceMeter = getIntegerValue(line);
			currentHero.setRouteMeter(resourceMeter);
		} else if (line.contains("Enemy")) {
			Enemy enemy = new Enemy();
			enemy.setName(line.split(" ")[0]);
			enemyTypeList.add(enemy);
		} else if (line.contains("hp")) {
			String enemyName = line.split(" ")[0];
			Enemy enemy = enemyTypeList.stream().filter(e -> e.getName().equals(enemyName)).collect(Collectors.toList())
					.get(0);
			enemy.setHealthPoint(getIntegerValue(line));
		} else if (line.contains("attack")) {
			String enemyName = line.split(" ")[0];
			Enemy enemy = enemyTypeList.stream().filter(e -> e.getName().equals(enemyName)).collect(Collectors.toList())
					.get(0);
			enemy.setAttack(getIntegerValue(line));
		} else if (line.contains("There") && line.contains("position")) {
			String enemyName = line.split(" ")[3];
			Enemy enemy = enemyTypeList.stream().filter(e -> e.getName().equals(enemyName)).collect(Collectors.toList())
					.get(0);
			Enemy newEnemy = new Enemy();
			newEnemy.setAttack(enemy.getAttack());
			newEnemy.setHealthPoint(enemy.getHealthPoint());
			newEnemy.setName(enemy.getName());
			newEnemy.setPosition(getIntegerValue(line));
			enemyList.add(newEnemy);
		}

	}

	private int getIntegerValue(String line) {
		String str = line.replaceAll("\\D+", ""); // line daki char ve boslukları kaldır ve
		return Integer.parseInt(str);				// sayıları parse et
	}

	public List<Enemy> getEnemyList() { 
		return enemyList;
	}

	public void setEnemyList(List<Enemy> enemyList) {
		this.enemyList = enemyList;
	}

	public Hero getCurrentHero() {
		return currentHero;
	}

	public void setCurrentHero(Hero currentHero) {
		this.currentHero = currentHero;
	}

}
