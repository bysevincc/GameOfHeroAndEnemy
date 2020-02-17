package com.sevinc.HeroGame.HeroGame;

import com.sevinc.HeroGame.HeroGame.creator.Game;
import com.sevinc.HeroGame.HeroGame.creator.GameCreator;

public class Application {

	public static void main(String[] args) {
		System.out.println("GAME STARTED");

		String path = "files\\input.txt";
		GameCreator gameCreator = new GameCreator(path);
		gameCreator.readFileAndCreateGame();

		Game.getInstance().startGame(gameCreator.getCurrentHero(), gameCreator.getEnemyList());
		// getInstance() cagrıldıgında (istedigimde)nesne olusturmasını istediğim icin Singleton kullandım
		
	}

}