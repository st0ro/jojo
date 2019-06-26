package st0ro.jojo.entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Entity {
	
	protected int direction;
	protected double x, y;
	protected SpriteSheet sheet;
	protected Animation sprite;
	
	public Entity(int dir, float xInit, float yInit, String path, int xSpa, int ySpa) throws SlickException
	{
		direction = dir;
		x = xInit;
		y = yInit;
		sheet = new SpriteSheet(path, xSpa, ySpa);
		sprite = new Animation(sheet, 200);
	}
	public void update()
	{
		//run AI/update projectiles presumably
	}
	public Animation getSprite()
	{
		return sprite;
	}
	public double getX() 
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public int getDir()
	{
		return direction;
	}
	public void setDir(int dir)
	{
		if(dir <= 3 && dir >= 0) direction = dir;
	}
	public void setX(double x)
	{
		this.x = x;
	}
	public void setY(double y)
	{
		this.y = y;
	}
}
class Mob extends Entity {
	
	protected Rectangle feetBox;
	protected boolean inControl;
	
	public Mob(int dir, float xInit, float yInit, String path, int xSpa, int ySpa, float boxW, float boxH) throws SlickException {
		super(dir, xInit, yInit, path, xSpa, ySpa);
		feetBox = new Rectangle(xInit, yInit, boxW, boxH);
		inControl = true;
	}
	public Rectangle getFeetBox()
	{
		return feetBox;
	}

}
