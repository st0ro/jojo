package st0ro.jojo.main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class May {
	
	private SpriteSheet maySheet;
	public Animation currentMay;
	private Animation standUp, standDown, standLeft, standRight, walkUp, walkDown, walkLeft, walkRight, runUp, runDown, runLeft, runRight;
	public int direction = 2, speed = 0; //directions start at 0 at top and go quarterly clockwise
	
	public May() throws SlickException
	{
		maySheet = new SpriteSheet("assets/sprites/may.png", 64, 96); //load SpriteSheet with each sprite of size 64x96
		Image[] stand = new Image[1];
		stand[0] = maySheet.getSprite(1, 0); //fill Animations with needed sprites
		standDown = new Animation(stand, 5);
		stand[0] = maySheet.getSprite(1, 1);
		standRight = new Animation(stand, 5);
		stand[0] = maySheet.getSprite(1, 2);
		standUp = new Animation(stand, 5);
		stand[0] = maySheet.getSprite(1, 3);
		standLeft = new Animation(stand, 5);
		
		Image[] walk = new Image[4];
		walk[0] = maySheet.getSprite(0, 0);
		walk[1] = maySheet.getSprite(1, 0);
		walk[2] = maySheet.getSprite(2, 0);
		walk[3] = maySheet.getSprite(1, 0);
		walkDown = new Animation(walk, 170);
		walk[0] = maySheet.getSprite(0, 1);
		walk[1] = maySheet.getSprite(1, 1);
		walk[2] = maySheet.getSprite(2, 1);
		walk[3] = maySheet.getSprite(1, 1);
		walkRight = new Animation(walk, 170);
		walk[0] = maySheet.getSprite(0, 2);
		walk[1] = maySheet.getSprite(1, 2);
		walk[2] = maySheet.getSprite(2, 2);
		walk[3] = maySheet.getSprite(1, 2);
		walkUp = new Animation(walk, 170);
		walk[0] = maySheet.getSprite(0, 3);
		walk[1] = maySheet.getSprite(1, 3);
		walk[2] = maySheet.getSprite(2, 3);
		walk[3] = maySheet.getSprite(1, 3);
		walkLeft = new Animation(walk, 170);
		
		Image[] run = new Image[4];
		run[0] = maySheet.getSprite(3, 0);
		run[1] = maySheet.getSprite(4, 0);
		run[2] = maySheet.getSprite(5, 0);
		run[3] = maySheet.getSprite(4, 0);
		runDown = new Animation(run, 100);
		run[0] = maySheet.getSprite(3, 1);
		run[1] = maySheet.getSprite(4, 1);
		run[2] = maySheet.getSprite(5, 1);
		run[3] = maySheet.getSprite(4, 1);
		runRight = new Animation(run, 100);
		run[0] = maySheet.getSprite(3, 2);
		run[1] = maySheet.getSprite(4, 2);
		run[2] = maySheet.getSprite(5, 2);
		run[3] = maySheet.getSprite(4, 2);
		runUp = new Animation(run, 100);
		run[0] = maySheet.getSprite(3, 3);
		run[1] = maySheet.getSprite(4, 3);
		run[2] = maySheet.getSprite(5, 3);
		run[3] = maySheet.getSprite(4, 3);
		runLeft = new Animation(run, 100);
		
		currentMay = standDown; //set default animation on load
	}
	
	public void update()
	{
		if(speed == 2) //speed 2 = running
		{
			switch(direction)
			{
			case 0: //direction 0 = up
				currentMay = runUp;
				break;
			case 1: //1 = right
				currentMay = runRight;
				break;
			case 2: //2 = down
				currentMay = runDown;
				break;
			case 3: //3 = left
				currentMay = runLeft;
				break;
			}
		}
		else if(speed == 1) //speed 1 = walking
		{
			switch(direction)
			{
			case 0:
				currentMay = walkUp;
				break;
			case 1:
				currentMay = walkRight;
				break;
			case 2:
				currentMay = walkDown;
				break;
			case 3:
				currentMay = walkLeft;
				break;
			}
		}
		else if (speed == 0) //speed 0 = standing
		{
			switch(direction)
			{
			case 0:
				currentMay = standUp;
				break;
			case 1:
				currentMay = standRight;
				break;
			case 2:
				currentMay = standDown;
				break;
			case 3:
				currentMay = standLeft;
				break;
			}
		}
	}

}
