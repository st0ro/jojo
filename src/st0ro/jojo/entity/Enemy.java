package st0ro.jojo.entity;

import org.newdawn.slick.SlickException;

public class Enemy extends Mob {

	public Enemy(int dir, float xInit, float yInit, String path, int xSpa, int ySpa, float boxW, float boxH)
			throws SlickException {
		super(dir, xInit, yInit, path, xSpa, ySpa, boxW, boxH);
		
	}

}
