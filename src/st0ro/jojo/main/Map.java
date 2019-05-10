package st0ro.jojo.main;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
	//class representing a single area map
	public TiledMap tMap; //TiledMap that is being loaded and used
	private Tile[][] mapArray; //2D matrix of Tiles for collisions and interactions
	private Tile[] tileSet; //array of all collision tiles used
	public Map(String path) throws SlickException
	{
		tMap = new TiledMap(path); //load the TiledMap
		mapArray = new Tile[tMap.getWidth()][tMap.getHeight()]; //create empty matrix to recieve Tiles
		tileSet = new Tile[tMap.getTileSetByGID(tMap.getTileId(0, 0, 1)).lastGID-tMap.getTileSetByGID(tMap.getTileId(0, 0, 1)).firstGID]; //create empty tileSet array
		int k = 0; //for filling tileSet
		for(int i = tMap.getTileSetByGID(tMap.getTileId(0, 0, 1)).firstGID; i < tMap.getTileSetByGID(tMap.getTileId(0, 0, 1)).lastGID; i++)
		{
			tileSet[k] = new Tile(tMap, i); //fill the tileSet with the TileSet used on the collision layer
			k++;
		}
		for(int i = 0; i < tMap.getWidth(); i++)
		{
			for(int j = 0; j < tMap.getHeight(); j++)
			{
				mapArray[i][j] = tileSet[tMap.getTileId(i, j, 1)-tMap.getTileSetByGID(tMap.getTileId(0, 0, 1)).firstGID]; //fill the mapArray
			}
		}
	}
	public Tile getTile(int x, int y)
	{
		if(x >= 0 && x < mapArray.length && y >=0 && y < mapArray[x].length)
		{
			return mapArray[x][y]; //pass wanted tile
		}
		else return tileSet[1];
	}
}

class Tile {
	//class representing the all tiles of collision layer of maps
	private boolean moveBlock; //does tile block movement
	private int tileType, destX, destY; //0 = no interaction, 1 = insta door, 2 = walk door
	private String destMap;
	public Tile(TiledMap map, int GID)
	{
		moveBlock = Boolean.parseBoolean(map.getTileProperty(GID, "moveBlock", "false"));
		tileType = Integer.parseInt(map.getTileProperty(GID, "tileType", "0"));
		destMap = map.getTileProperty(GID, "destMap", "");
		destX = Integer.parseInt(map.getTileProperty(GID, "destX", "-1"));
		destY = Integer.parseInt(map.getTileProperty(GID, "destY", "-1"));
	}
	public boolean getBlock()
	{
		return moveBlock;
	}
	public int getType()
	{
		return tileType;
	}
	public int getDestX()
	{
		return destX;
	}
	public int getDestY()
	{
		return destY;
	}
	public String getDestMap()
	{
		return destMap;
	}
}
