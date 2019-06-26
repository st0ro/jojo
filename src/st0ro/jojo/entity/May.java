package st0ro.jojo.entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

public class May extends Mob{
	
	private Animation[] stand, walk, run;
	public int speed = 0; //directions start at 0 at top and go quarterly clockwise
	
	public May() throws SlickException
	{
		super(2, 3, 3, "assets/sprites/may.png", 64, 96, .8f, .8f);
		stand = new Animation[4];
		walk = new Animation[4];
		run = new Animation[4];
		for(int i = 0; i <= 3; i++)
		{
			stand[i] = new Animation(sheet, 1, i, 1, i, true, 1, false);
			stand[i].setPingPong(true);
			walk[i] = new Animation(sheet, 0, i, 2, i, true, 170, true);
			walk[i].setPingPong(true);
			run[i] = new Animation(sheet, 3, i, 5, i, true, 100, true);
			run[i].setPingPong(true);
		}
		sprite = stand[2]; //set default animation on load
	}
	@Override
	public void update()
	{
		if(speed == 2) //speed 2 = running
		{
			sprite = run[direction];
		}
		else if(speed == 1) //speed 1 = walking
		{
			sprite = walk[direction];
		}
		else if (speed == 0) //speed 0 = standing
		{
			sprite = stand[direction];
		}
	}
}
