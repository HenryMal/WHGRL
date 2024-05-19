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

import com.thehe.WHGRL.entity.Coin;
import com.thehe.WHGRL.entity.Obstacle;
import com.thehe.WHGRL.utils.Phase;
import com.thehe.WHGRL.utils.Vector;

public class Map {
	
	public static final int GRID_WIDTH = 24;
	public static final int GRID_HEIGHT = 14;
	
	public List<Tile> backgroundTiles;
	public List<Tile> moveableTiles;
	public List<Tile> spawnTiles;
	public List<Tile> goalTiles;
	public List<Obstacle> obstacles;
	public List<Coin> coins;

	public List<Rectangle2D> savePoints;
	
	public Area moveableArea;
	
	public Map(File file) throws FileNotFoundException {
		backgroundTiles = new ArrayList<Tile>();
		moveableTiles = new ArrayList<Tile>();
		spawnTiles = new ArrayList<Tile>();
		goalTiles = new ArrayList<Tile>();
		obstacles = new ArrayList<Obstacle>();
		coins = new ArrayList<Coin>();
		
		savePoints = new ArrayList<Rectangle2D>();
		
		moveableArea = new Area();
		
		readMapFile(file);
	}
	
	public Map(String fileName) throws FileNotFoundException {
		this(new File(fileName));
	}
	
	public void readMapFile(File file) throws FileNotFoundException {
		
		Scanner scanner = new Scanner(file);
		
		String tileData = "";
		String entityData = "";

		
        for(int i = 0; i < GRID_HEIGHT; i++) {
            
        	tileData = scanner.next();
            
            for(int j = 0; j < GRID_WIDTH; j++) {
            	
                addTileToList(tileData.charAt(j), i, j);
            }
            
        }
        
        while(scanner.hasNext()) {
        	
        	entityData = scanner.next();
        	if(entityData.charAt(0) == 'O') {
        		createObstacle(entityData);
        	}
        	
        	if(entityData.charAt(0) == 'C') {
        		createCoin(entityData);
        	}
        	
        	
        	if(entityData.charAt(0) == 'S') {
        		createSavePoint(entityData);
        	}

        	
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
	
	public void createObstacle(String obstacleData) {
		
		String[] obstaclePositions = obstacleData.split(",");
		List<Phase> phases = new ArrayList<Phase>();
		
		Phase initialPhase = new Phase(
				new Vector(Double.parseDouble(obstaclePositions[1]), Double.parseDouble(obstaclePositions[2])),
				new Vector(Double.parseDouble(obstaclePositions[3]), Double.parseDouble(obstaclePositions[4])));

		Phase phase;
		
		for(int i = 5; i < obstaclePositions.length - 1; i = i + 4) {

			phase = new Phase(
					new Vector(Double.parseDouble(obstaclePositions[i]), Double.parseDouble(obstaclePositions[i + 1])),
					new Vector(Double.parseDouble(obstaclePositions[i + 2]), Double.parseDouble(obstaclePositions[i + 3]))
			);

			phases.add(phase);
		}
		
		phases.add(initialPhase);
	
		Obstacle obstacle = new Obstacle(initialPhase.position, initialPhase.velocity, phases);
		obstacles.add(obstacle);
		
		
	}
	
	public void createCoin(String coinData) {
		
		String[] coinPositions = coinData.split(",");
		
		Vector position = new Vector(
				Double.parseDouble(coinPositions[1]),
				Double.parseDouble(coinPositions[2]));
		
		position.scaleVector(Tile.SIZE);
		position.subtractVector(new Vector(Tile.SIZE / 2, Tile.SIZE / 2));
		
		Coin newCoin = new Coin(position);
		
		coins.add(newCoin);
		
	}
	
	public void createSavePoint(String savePointData) {
		
		String[] savePointPosition = savePointData.split(",");
		
		Rectangle2D shit = new Rectangle2D.Double(
				Tile.SIZE * Double.parseDouble(savePointPosition[1]), 
				Tile.SIZE * Double.parseDouble(savePointPosition[2]), 
				Tile.SIZE * Double.parseDouble(savePointPosition[3]), 
				Tile.SIZE * Double.parseDouble(savePointPosition[4]));
		
		savePoints.add(shit);
		
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
		
		for(Coin coin : coins) {
			coin.render(graphics2D);
		}
		
		for (Rectangle2D savePoint: savePoints) {
			graphics2D.setColor(new Color(188, 255, 183));
			graphics2D.fill(savePoint);
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
