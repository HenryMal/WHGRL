package com.thehe.WHGRL.map;

import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.thehe.WHGRL.entity.Obstacle;

public class Map {
	
	public static final int GRID_WIDTH = 22;
	public static final int GRID_HEIGHT = 14;
	
	public List<Tile> backgroundTiles;
	public List<Tile> moveableTiles;
	public List<Tile> spawnTiles;
	public List<Tile> endTiles;
	public List<Obstacle> obstacles;
	
	public Area moveableArea;
	
	
	public Map(File file) throws FileNotFoundException {
		backgroundTiles = new ArrayList<Tile>();
		moveableTiles = new ArrayList<Tile>();
		spawnTiles = new ArrayList<Tile>();
		endTiles = new ArrayList<Tile>();
		obstacles = new ArrayList<Obstacle>();
		
		readMapFile(file);
	}
	
	public Map(String fileName) throws FileNotFoundException {
		this(new File(fileName));
	}
	
	public void readMapFile(File file) throws FileNotFoundException {
		
		/*
		 *  WWWWWWWWWWWWWWWWWWWWWW
			WWWWWWWWWWWWWWWWWWWWWW
			WWWWWWWWWWWWWWWWWWWWWW
			WWWWWWWWWWWWWWWWWWWWWW
			WWSSSWWWWWWWWWWMMEEEWW
			WWSSSWMMMMMMMMMMWEEEWW
			WWSSSWMMMMMMMMMMWEEEWW
			WWSSSWMMMMMMMMMMWEEEWW
			WWSSSWMMMMMMMMMMWEEEWW
			WWSSSMMWWWWWWWWWWEEEWW
			WWWWWWWWWWWWWWWWWWWWWW
			WWWWWWWWWWWWWWWWWWWWWW
			WWWWWWWWWWWWWWWWWWWWWW
			WWWWWWWWWWWWWWWWWWWWWW
			
			16 | 6 | 6 | 6 | 5 | 0
			6 | 7 | 16 | 7 | 5 | 0
			16 | 8 | 6 | 8 | 5 | 0
			6 | 9 | 16 | 9 | 5 | 0
		 */
		Scanner scanner = new Scanner(file);
		
		String tileData = "";
		String obstacleData = "";
		
        for(int i = 0; i < GRID_HEIGHT; i++) {
            
        	tileData = scanner.next();
            
            for(int j = 0; j < GRID_WIDTH; j++) {
            	
                addTileToList(tileData.charAt(j), i, j);
            }
            
        }
		
		String tileCharacter = scanner.next();
		
		
		
		scanner.close();
		
	}
	
	public void addTileToList(char tileCharacter, int indexX, int indexY) {
		switch(tileCharacter) {
			case 'W': 
				backgroundTiles.add(new Tile(TileType.BACKGROUND, indexX, indexY));
				break;
			case 'M': 
				moveableTiles.add(new Tile(TileType.REGULAR, indexX, indexY));
				break;
			case 'S': 
				spawnTiles.add(new Tile(TileType.SPAWN, indexX, indexY));
				break;
			case 'E': 
				endTiles.add(new Tile(TileType.GOAL, indexX, indexY));
				break;
		}
	}
	
	public void render(Graphics2D graphics2D) {
		
		for(Tile tile : backgroundTiles) {
			tile.render(graphics2D);
		}
		
		for(Tile tile : moveableTiles) {
			tile.render(graphics2D);
		}
		
		for(Tile tile : spawnTiles) {
			tile.render(graphics2D);
		}
		
		for(Tile tile : endTiles) {
			tile.render(graphics2D);
		}
		
	}
	
}
