package st0ro.jojo.main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import st0ro.jojo.entity.May;
import st0ro.jojo.entity.NPC;

public class MapState extends BasicGameState{

	private May may;
	private Map map;
	private int lockoutTimer = 0; //0 for unlocked controls, >0 for locked, -1 for locked waiting for response
	private String nextMap = null;
	private int nextX, nextY, nextDir;
	private boolean doorTrans = false;
	
	@Override
	public void init(GameContainer container, StateBasedGame sBG) throws SlickException {
		container.setVSync(true); //use VSync to cap fps (caps at screen refresh rate, supports most monitors except GSync)
		map = new Map("assets/maps/big empty room.tmx"); //create map based on file path
		may = new May();
		map.addEntity(new NPC(2, 4, 4, "assets/sprites/lucas.png", 64, 96, .8f, .8f));
	}

	@Override
	public void render(GameContainer container, StateBasedGame sBG, Graphics g2d) throws SlickException {
		map.tMap.render((int)(may.getX()*-64+64*7.5),(int)(may.getY()*-64+64*4),0); //draw the TiledMap (simplest way to draw)
		for(int i = 0; i < Map.entityListLength; i++)
		{
			if(map.getEntity(i) != null && map.getEntity(i).getY() <= may.getY())
				map.getEntity(i).getSprite().draw((float)((map.getEntity(i).getX()-may.getX())*64+64*7.5), (float)((map.getEntity(i).getY()-may.getY())*64+64*3.5));
		}	
		may.getSprite().draw(480, 224); //draw May in the middle of the screen
		for(int i = 0; i < Map.entityListLength; i++)
		{
			if(map.getEntity(i) != null && map.getEntity(i).getY() > may.getY())
				map.getEntity(i).getSprite().draw((float)((map.getEntity(i).getX()-may.getX())*64+64*7.5), (float)((map.getEntity(i).getY()-may.getY())*64+64*3.5));
		}		
		g2d.setColor(Color.white);
		g2d.drawString("x: " + may.getX(), 10, 30);
		g2d.drawString("y: " + may.getY(), 10, 45);
		g2d.drawString("feet: " + map.checkBox(may.getFeetBox()), 10, 60);
		if(doorTrans)
		{
			if(lockoutTimer > 250) g2d.setColor(new Color(0,0,0,255-((lockoutTimer-250)*(255/250))));
			if(lockoutTimer < 250) g2d.setColor(new Color(0,0,0,lockoutTimer*(255/250)));
			g2d.fillRect(0, 0, container.getWidth(), container.getHeight());
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame sBG, int delta) throws SlickException {
		may.speed = 0; //default to standing if no keys pressed
		boolean running = false; //default not running if run key not pressed
		if(lockoutTimer == 0 && container.getInput().isKeyDown(Input.KEY_LSHIFT))
		{
			running = true; //run if run key is pressed...
		}
		if(lockoutTimer == 0 && container.getInput().isKeyDown(Input.KEY_S) && !container.getInput().isKeyDown(Input.KEY_W))
		{
			for(int i = 1; i <= delta; i++) //run once for every millisecond passed since last update, ensures smoothness across devices and system load
			{
				if(map.getTile((int)Math.round(may.getX()), (int)Math.round(may.getX()+0.5)).getType() != 0 && !map.getTile((int)Math.round(may.getX()), (int)Math.round(may.getY()+0.5)).getDestMap().equals(""))
				{
					Tile tile = map.getTile((int)Math.round(may.getX()), (int)Math.round(may.getY()+0.5)); //if trying to enter door (tile type != 0) load connected map
					nextMap = "assets/maps/" + tile.getDestMap() + ".tmx";
					nextX = tile.getDestX();
					nextY = tile.getDestY();
					nextDir = tile.getDestDir();
					lockoutTimer = 500;
					doorTrans = true;
					return;
				}
				else if(!map.getTile((int) Math.floor(may.getX()+0.1), (int) Math.round(may.getY()+0.5)).getBlock() && !map.getTile((int) Math.ceil(may.getX()-0.1), (int) Math.round(may.getY()+0.5)).getBlock())
				{
					may.setY(may.getY()+0.005f); //if can move, move
					if(running && !map.getTile((int) Math.floor(may.getX()+0.1), (int) Math.round(may.getY()+0.5)).getBlock() && !map.getTile((int) Math.ceil(may.getX()-0.1), (int) Math.round(may.getY()+0.5)).getBlock())
					{
						may.setY(may.getY()+0.005f); //if can move more and running, move more and set running animation
						may.speed = 2;
					}
					else may.speed = 1; //otherwise walking animation
				}
			}
			may.setDir(2); //set direction
		}
		else if(lockoutTimer == 0 && container.getInput().isKeyDown(Input.KEY_W) && !container.getInput().isKeyDown(Input.KEY_S))
		{
			for(int i = 1; i <= delta; i++)
			{
				if(map.getTile((int)Math.round(may.getX()), (int)Math.round(may.getY()-0.5)).getType() != 0 && !map.getTile((int)Math.round(may.getX()), (int)Math.round(may.getY()-0.5)).getDestMap().equals(""))
				{
					Tile tile = map.getTile((int)Math.round(may.getX()), (int)Math.round(may.getY()-0.5));
					nextMap = "assets/maps/" + tile.getDestMap() + ".tmx";
					nextX = tile.getDestX();
					nextY = tile.getDestY();
					nextDir = tile.getDestDir();
					lockoutTimer = 500;
					doorTrans = true;
					return;
				}
				else if(!map.getTile((int) Math.floor(may.getX()+0.1), (int) Math.round(may.getY()-0.5)).getBlock() && !map.getTile((int) Math.ceil(may.getX()-0.1), (int) Math.round(may.getY()-0.5)).getBlock())
				{
					may.setY(may.getY()-0.005f);
					if(running && !map.getTile((int) Math.floor(may.getX()+0.1), (int) Math.round(may.getY()-0.5)).getBlock() && !map.getTile((int) Math.ceil(may.getX()-0.1), (int) Math.round(may.getY()-0.5)).getBlock())
					{
						may.setY(may.getY()-0.005f);
						may.speed = 2;
					}
					else may.speed = 1;
				}
			}
			may.setDir(0);

		}
		else if(lockoutTimer == 0 && container.getInput().isKeyDown(Input.KEY_A) && !container.getInput().isKeyDown(Input.KEY_D))
		{
			for(int i = 1; i <= delta; i++)
			{
				if(map.getTile((int) Math.round(may.getX()-0.5), (int)Math.round(may.getY())).getType() != 0 && !map.getTile((int) Math.round(may.getX()-0.5), (int)Math.round(may.getY())).getDestMap().equals(""))
				{
					Tile tile = map.getTile((int) Math.round(may.getX()-0.5), (int)Math.round(may.getY()));
					nextMap = "assets/maps/" + tile.getDestMap() + ".tmx";
					nextX = tile.getDestX();
					nextY = tile.getDestY();
					nextDir = tile.getDestDir();
					lockoutTimer = 500;
					doorTrans = true;
					return;
				}
				else if(!map.getTile((int) Math.round(may.getX()-0.5), (int) Math.floor(may.getY()+0.1)).getBlock() && !map.getTile((int) Math.round(may.getX()-0.5), (int) Math.ceil(may.getY()-0.1)).getBlock())
				{
					may.setX(may.getX()-0.005f);
					if(running && !map.getTile((int) Math.round(may.getX()-0.5), (int) Math.floor(may.getY()+0.1)).getBlock() && !map.getTile((int) Math.round(may.getX()-0.5), (int) Math.ceil(may.getY()-0.1)).getBlock())
					{
						may.setX(may.getX()-0.005f);
						may.speed = 2;
					}
					else may.speed = 1;
				}
			}
			may.setDir(3);

		}
		else if(lockoutTimer == 0 && container.getInput().isKeyDown(Input.KEY_D) && !container.getInput().isKeyDown(Input.KEY_A))
		{
			for(int i = 1; i <= delta; i++)
			{
				if(map.getTile((int) Math.round(may.getX()-0.5), (int)Math.round(may.getY())).getType() != 0 && !map.getTile((int) Math.round(may.getX()+0.5), (int)Math.round(may.getY())).getDestMap().equals(""))
				{
					Tile tile = map.getTile((int) Math.round(may.getX()+0.5), (int)Math.round(may.getY()));
					nextMap = "assets/maps/" + tile.getDestMap() + ".tmx";
					nextX = tile.getDestX();
					nextY = tile.getDestY();
					nextDir = tile.getDestDir();
					lockoutTimer = 500;
					doorTrans = true;
					return;
				}
				else if(!map.getTile((int) Math.round(may.getX()+0.5), (int) Math.floor(may.getY()+0.1)).getBlock() && !map.getTile((int) Math.round(may.getX()+0.5), (int) Math.ceil(may.getY()-0.1)).getBlock())
				{
					may.setX(may.getX()+0.005f);
					if(running && !map.getTile((int) Math.round(may.getX()+0.5), (int) Math.floor(may.getY()+0.1)).getBlock() && !map.getTile((int) Math.round(may.getX()+0.5), (int) Math.ceil(may.getY()-0.1)).getBlock())
					{
						may.setX(may.getX()+0.005f);
						may.speed = 2;
					}
					else may.speed = 1;
				}
			}
			may.setDir(1);

		}
		if(nextMap != null && lockoutTimer >= 250 && lockoutTimer - delta < 250 && doorTrans)
		{
			loadMap(nextMap, nextX, nextY, nextDir);
			nextMap = null;
		}
		may.update(); //update May's sprite
		if(lockoutTimer > 0) 
		{
			if(lockoutTimer-delta <= 0 && doorTrans) doorTrans = false;
			lockoutTimer = Math.max(lockoutTimer - delta, 0);
		}
		if(container.getInput().isKeyDown(Input.KEY_ESCAPE)) container.exit();
	}

	@Override
	public int getID() {
		return 0; //used to change game states for StateBasedGame
	}
	private void loadMap(String mapName, int newX, int newY, int newDir) throws SlickException
	{
		map = new Map(mapName); //load new map based on nested info
		may.setX(newX);
		may.setY(newY);
		may.setDir(newDir);
	}
}
