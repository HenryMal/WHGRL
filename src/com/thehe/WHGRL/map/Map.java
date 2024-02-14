package com.thehe.WHGRL.map;

import java.awt.geom.Area;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Map {
	
	public static final int GRID_WIDTH = 22;
	public static final int GRID_HEIGHT = 14;
	
	public List<Tile> backgroundTiles;
	public List<Tile> moveableTiles;
	public List<Tile> spawnTiles;
	public List<Tile> endTiles;
	
	public Area moveableArea;
	
	
	public Map() {
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
		
		String tileCharacter = scanner.next();
		
		
		
		scanner.close();
		
	}
	
}
