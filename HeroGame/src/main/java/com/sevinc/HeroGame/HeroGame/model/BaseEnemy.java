package com.sevinc.HeroGame.HeroGame.model;

public class BaseEnemy {
	
	private int healthPoint; // her enemy nin healthPoint i var
	private int attack;		// her enemy nin attack ı var
	private boolean isAlive = true; //her bir enemy oluşturulduğunda yaşıyor varsayıyoruz
	
	
	public int getAttack() {
		return attack;
	}
	
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public int getHealthPoint() {
		return healthPoint;
	}
	
	public void setHealthPoint(int healthPoint) {
		this.healthPoint = healthPoint;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	
}
