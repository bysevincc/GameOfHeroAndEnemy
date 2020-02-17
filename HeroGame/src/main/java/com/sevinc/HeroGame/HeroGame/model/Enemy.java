package com.sevinc.HeroGame.HeroGame.model;

import com.sevinc.HeroGame.HeroGame.behaviour.IDamageable;

public class Enemy extends BaseEnemy implements IDamageable {

	private String name; 
	private int position;

	@Override
	public void hit(int attack) {
		setHealthPoint(getHealthPoint() - attack);
		if (getHealthPoint() <= 0) {
			setAlive(false);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
