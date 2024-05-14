package com.thehe.WHGRL.map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.thehe.WHGRL.entity.Obstacle;
import com.thehe.WHGRL.utils.Vector;

public class Map {
	
	public static final int GRID_WIDTH = 24;
	public static final int GRID_HEIGHT = 14;
	
	public List<Tile> backgroundTiles;
	public List<Tile> moveableTiles;
	public List<Tile> spawnTiles;
	public List<Tile> goalTiles;
	public List<Obstacle> obstacles;
	
	public Area moveableArea;
	
	public Map(File file) throws FileNotFoundException {
		backgroundTiles = new ArrayList<Tile>();
		moveableTiles = new ArrayList<Tile>();
		spawnTiles = new ArrayList<Tile>();
		goalTiles = new ArrayList<Tile>();
		obstacles = new ArrayList<Obstacle>();
		
		moveableArea = new Area();
		
		readMapFile(file);
	}
	
	public Map(String fileName) throws FileNotFoundException {
		this(new File(fileName));
	}
	
	public void readMapFile(File file) throws FileNotFoundException {
		
		Scanner scanner = new Scanner(file);
		
		String tileData = "";
		String obstacleData = "";
		
        for(int i = 0; i < GRID_HEIGHT; i++) {
            
        	tileData = scanner.next();
            
            for(int j = 0; j < GRID_WIDTH; j++) {
            	
                addTileToList(tileData.charAt(j), i, j);
            }
            
        }
		
		while(scanner.hasNext()) {
			obstacleData = scanner.next();
			createObstacles(obstacleData);
		}
		
		scanner.close();
		
	}
	
	public void addTileToList(char tileCharacter, int indexX, int indexY) {
		
		switch(tileCharacter) {
			case 'W': 
				backgroundTiles.add(new Tile(TileType.BACKGROUND, indexX, indexY));
				break;
			case 'M': 
				moveableTiles.add(new Tile(TileType.REGULAR, indexX, indexY));
				moveableArea.add(new Area(new Rectangle2D.Double(
						moveableTiles.get(moveableTiles.size() - 1).position.x,
						moveableTiles.get(moveableTiles.size() - 1).position.y,
						Tile.SIZE,
						Tile.SIZE)));
				
				break;
			case 'S': 
				spawnTiles.add(new Tile(TileType.SPAWN, indexX, indexY));
				moveableArea.add(new Area(new Rectangle2D.Double(
						spawnTiles.get(spawnTiles.size() - 1).position.x,
						spawnTiles.get(spawnTiles.size() - 1).position.y,
						Tile.SIZE,
						Tile.SIZE)));
				break;
			case 'E': 
				goalTiles.add(new Tile(TileType.GOAL, indexX, indexY));
				moveableArea.add(new Area(new Rectangle2D.Double(
						goalTiles.get(goalTiles.size() - 1).position.x,
						goalTiles.get(goalTiles.size() - 1).position.y,
						Tile.SIZE,
						Tile.SIZE)));
				break;
		}
	}
	
	public void createObstacles(String obstacleData) {
		
		String[] obstaclePositions = obstacleData.split(",");

		Vector position = new Vector(
				Double.parseDouble(obstaclePositions[0]),
				Double.parseDouble(obstaclePositions[1]));
		
		position.scaleVector(Tile.SIZE);
		position.subtractVector(new Vector(Tile.SIZE / 2, Tile.SIZE / 2));
		
		Vector velocity = new Vector(
				Double.parseDouble(obstaclePositions[obstaclePositions.length - 2]),
				Double.parseDouble(obstaclePositions[obstaclePositions.length - 1]));
		
		Vector phase;
		
		Obstacle newObstacle = new Obstacle(position, velocity);
		
		for(int i = 2; i < obstaclePositions.length - 3; i++) {
			phase = new Vector(Double.parseDouble(obstaclePositions[i]), Double.parseDouble(obstaclePositions[++i]));
			phase.scaleVector(Tile.SIZE);
			phase.subtractVector(new Vector(Tile.SIZE / 2, Tile.SIZE / 2)); // offset it so it centers.
			newObstacle.phases.add(phase);
			System.out.println(phase.x);
		}
		
		// so adding it after fixes the issues of it flying off screen immediately.
		// initial position already counts as a phase.
		// when an obstacle is initialized, we already passed the initial position phase
		// which is why the new one comes first.
		newObstacle.phases.add(new Vector(newObstacle.position.x, newObstacle.position.y));
	
		
		obstacles.add(newObstacle);
		
		
	}
	
	public void render(Graphics2D graphics2D) {
		
		graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		

		for(Tile tile : backgroundTiles) {
			tile.render(graphics2D);
		}
		
		for(Tile tile : moveableTiles) {
			tile.render(graphics2D);
		}
		
		for(Tile tile : spawnTiles) {
			tile.render(graphics2D);
		}
		
		for(Tile tile : goalTiles) {
			tile.render(graphics2D);
		}
		
		for(Obstacle obstacle : obstacles) {
			obstacle.render(graphics2D);
		}
		
		graphics2D.setColor(Color.BLACK);
		graphics2D.setStroke(new BasicStroke(4));
		graphics2D.draw(moveableArea);
		

		
	}
	
	public void tick() {
		
		for(Obstacle obstacle : obstacles) {
			obstacle.tick();
		}

	}
	
}
