package com.sevinc.HeroGame.HeroGame.model;

import com.sevinc.HeroGame.HeroGame.behaviour.IDamageable;

public class Hero implements IDamageable {

	private int healtPoint;
	private int attack;
	private int routeMeter;
	private boolean isAlive = true;

	@Override
	public void hit(int attack) {
		healtPoint -= attack;
		if (healtPoint <= 0) {
			isAlive = false;
		}
	}

	public int getHealtPoint() {
		return healtPoint;
	}

	public void setHealtPoint(int healtPoint) {
		this.healtPoint = healtPoint;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getRouteMeter() {
		return routeMeter;
	}

	public void setRouteMeter(int routeMeter) {
		this.routeMeter = routeMeter;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

}
