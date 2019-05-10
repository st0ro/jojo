package st0ro.jojo.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MapState extends BasicGameState{

	private float x=5, y=5;
	private May maySprite;
	private Map map;
	@Override
	public void init(GameContainer container, StateBasedGame sBG) throws SlickException {
		container.setVSync(true); //use VSync to cap fps (caps at screen refresh rate, supports most monitors except GSync)
		map = new Map("assets/maps/first.tmx"); //create map based on file path
		maySprite = new May();
	}

	@Override
	public void render(GameContainer container, StateBasedGame sBG, Graphics g2d) throws SlickException {
		map.tMap.render((int)(x*-64+64*7.5),(int)(y*-64+64*4),0); //draw the TiledMap (simplest way to draw)
		maySprite.currentMay.draw(480, 224); //draw May in the middle of the screen
	}

	@Override
	public void update(GameContainer container, StateBasedGame sBG, int delta) throws SlickException {
		maySprite.speed = 0; //default to standing if no keys pressed
		boolean running = false; //default not running if run key not pressed
		if(container.getInput().isKeyDown(Input.KEY_LSHIFT))
		{
			running = true; //run if run key is pressed...
		}
		if(container.getInput().isKeyDown(Input.KEY_S) && !container.getInput().isKeyDown(Input.KEY_W))
		{
			for(int i = 1; i <= delta; i++) //run once for every millisecond passed since last update, ensures smoothness across devices and system load
			{
				if(map.getTile((Math.round(x), (int)Math.round(y+0.5)).getType() != 0)
				{
					//loadMap(map);
				}
				else if(!map.getTile((int) Math.floor(x+0.1), (int) Math.round(y+0.5)).getBlock() && !map.getTile((int) Math.ceil(x-0.1), (int) Math.round(y+0.5)).getBlock())
				{
					y+=0.005; //if can move, move
					if(running && !map.getTile((int) Math.floor(x+0.1), (int) Math.round(y+0.5)) && !map.getTile((int) Math.ceil(x-0.1), (int) Math.round(y+0.5)))
					{
						y+=0.005; //if can move more and running, move more and set running animation
						maySprite.speed = 2;
					}
					else maySprite.speed = 1; //otherwise walking animation
				}
			}
			maySprite.direction = 2; //set direction
		}
		else if(container.getInput().isKeyDown(Input.KEY_W) && !container.getInput().isKeyDown(Input.KEY_S))
		{
			for(int i = 1; i <= delta; i++)
			{
				if(map.getType(Math.round(x), (int)Math.round(y-0.5)) != 0)
				{
					
				}
				else if(!map.getTile((int) Math.floor(x+0.1), (int) Math.round(y-0.5)) && !map.getTile((int) Math.ceil(x-0.1), (int) Math.round(y-0.5)))
				{
					y-=0.005;
					if(running && !map.getTile((int) Math.floor(x+0.1), (int) Math.round(y-0.5)) && !map.getTile((int) Math.ceil(x-0.1), (int) Math.round(y-0.5)))
					{
						y-=0.005;
						maySprite.speed = 2;
					}
					else maySprite.speed = 1;
				}
			}
			maySprite.direction = 0;

		}
		else if(container.getInput().isKeyDown(Input.KEY_A) && !container.getInput().isKeyDown(Input.KEY_D))
		{
			for(int i = 1; i <= delta; i++)
			{
				if(map.getType((int) Math.round(x-0.5), Math.round(y)) != 0)
				{
					
				}
				else if(!map.getTile((int) Math.round(x-0.5), (int) Math.floor(y+0.1)) && !map.getTile((int) Math.round(x-0.5), (int) Math.ceil(y-0.1)))
				{
					x-=0.005;
					if(running && !map.getTile((int) Math.round(x-0.5), (int) Math.floor(y+0.1)) && !map.getTile((int) Math.round(x-0.5), (int) Math.ceil(y-0.1)))
					{
						x-=0.005;
						maySprite.speed = 2;
					}
					else maySprite.speed = 1;
				}
			}
			maySprite.direction = 3;

		}
		else if(container.getInput().isKeyDown(Input.KEY_D) && !container.getInput().isKeyDown(Input.KEY_A))
		{
			for(int i = 1; i <= delta; i++)
			{
				if(map.getType((int) Math.round(x-0.5), Math.round(y)) != 0)
				{
					
				}
				else if(!map.getTile((int) Math.round(x+0.5), (int) Math.floor(y+0.1)) && !map.getTile((int) Math.round(x+0.5), (int) Math.ceil(y-0.1)))
				{
					x+=0.005;
					if(running && !map.getTile((int) Math.round(x+0.5), (int) Math.floor(y+0.1)) && !map.getTile((int) Math.round(x+0.5), (int) Math.ceil(y-0.1)))
					{
						x+=0.005;
						maySprite.speed = 2;
					}
					else maySprite.speed = 1;
				}
			}
			maySprite.direction = 1;

		}
		maySprite.update(); //update May's sprite
	}

	@Override
	public int getID() {
		return 0; //used to change game states for StateBasedGame
	}
	private void loadMap(String map, int x, int y)
	{
		
	}
}
