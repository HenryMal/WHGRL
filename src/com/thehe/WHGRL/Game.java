package com.thehe.WHGRL;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.thehe.WHGRL.entity.Coin;
import com.thehe.WHGRL.entity.Player;
import com.thehe.WHGRL.entity.obstacles.Obstacle;
import com.thehe.WHGRL.map.Map;
import com.thehe.WHGRL.map.Tile;
import com.thehe.WHGRL.utils.Vector;

public class Game {
	
	public final static String LEVELS_TO_PLAY = "maps/levels.txt";

	public Map map;
	public Player player;
	public Vector spawnAreaStart;
	
	public Rectangle2D goalArea;
	
	public List<String> levels;
	
	public boolean levelEnded;
	public int coinsCollected;
	public int levelsProgressed;

	
	public Game() throws FileNotFoundException {
		
		readLevels();
		
		map = new Map(levels.get(levelsProgressed));
		
		spawnPlayer();
		setUpGoalArea();
	
	}
	
	public void readLevels() throws FileNotFoundException {
		
		levels = new ArrayList<String>();
		levelsProgressed = 0;
		
		Scanner scanner = new Scanner(new File(LEVELS_TO_PLAY));
		
		while(scanner.hasNext()) {
			levels.add("maps/" + scanner.next() + ".txt");
		}
		
		scanner.close();
	}
	
	public void spawnPlayer() {
		
		calculateSpawnPosition(map.goalTiles.get(0).position, map.goalTiles.get(map.goalTiles.size() - 1).position);

		// meaning it doesnt exist, use the end area
		if(map.spawnTiles.size() != 0) {
			calculateSpawnPosition(map.spawnTiles.get(0).position, map.spawnTiles.get(map.spawnTiles.size() - 1).position);
			
		}
		
		respawnPlayer();

	}
	
	public void calculateSpawnPosition(Vector vectorOne, Vector vectorTwo) {
		
		spawnAreaStart = new Vector(vectorOne);
		Vector spawnAreaEnd = new Vector(vectorTwo);
	
		spawnAreaEnd.addVector(Tile.SIZE, Tile.SIZE);
		
		spawnAreaStart.addVector(spawnAreaEnd);
		spawnAreaStart.scaleVector(0.5);
				
		
	}

	
	public void setUpGoalArea() {
		
		goalArea = new Rectangle.Double(
				map.goalTiles.get(0).position.x,
				map.goalTiles.get(0).position.y,
				(map.goalTiles.get(map.goalTiles.size() - 1).position.x + Tile.SIZE) - (map.goalTiles.get(0).position.x),
				(map.goalTiles.get(map.goalTiles.size() - 1).position.y + Tile.SIZE) - (map.goalTiles.get(0).position.y));
		
	}

	public void checkPlayerCollisionsWithMap() {
		
		player.collideLeft = (!map.moveableArea.contains(player.hitbox.minX - player.movementSpeed, player.hitbox.minY) || 
				!map.moveableArea.contains(player.hitbox.minX - player.movementSpeed, player.hitbox.maxY));
		
		player.collideRight = (!map.moveableArea.contains(player.hitbox.maxX + player.movementSpeed, player.hitbox.minY) || 
				!map.moveableArea.contains(player.hitbox.maxX + player.movementSpeed, player.hitbox.maxY));
		
		player.collideTop = (!map.moveableArea.contains(player.hitbox.minX, player.hitbox.minY - player.movementSpeed) || 
				!map.moveableArea.contains(player.hitbox.maxX, player.hitbox.minY - player.movementSpeed));
		
		player.collideBottom = (!map.moveableArea.contains(player.hitbox.minX, player.hitbox.maxY + player.movementSpeed) || 
				!map.moveableArea.contains(player.hitbox.maxX, player.hitbox.maxY + player.movementSpeed));
		
	}
	
	public void checkPlayerCollisionsWithObstacles() {
		for(Obstacle obstacle : map.obstacles) {
			if (player.collides(obstacle)) {
				player.dead = true;
				break;
			}
		}
	}
	
	public void checkPlayerCollisionsWithCoins() {
		for(Coin coin : map.coins) {
			if (player.collides(coin)) {
				coin.destroy();
				coinsCollected++;
				break;
			}
		}
	}
	
	public void checkPlayerCollisionsSavePoint() {
		for(Rectangle2D savePoint : map.savePoints) {
			
			if (player.collides(savePoint.getX(), savePoint.getY(), savePoint.getX() + savePoint.getWidth(), savePoint.getY() + savePoint.getHeight())) {
				spawnAreaStart.setVector(savePoint.getCenterX(), savePoint.getCenterY());
				break;
			}
		}
	}
	
	public void handlePlayerDeath() {

	    if(player.dead && player.opacity == 0) {
	    	
	    	respawnPlayer();
	    	respawnCoins();
	        
	    }
	}
	
	public void respawnPlayer() {
		player = new Player(spawnAreaStart);
	}
	
	public void respawnCoins() {
		coinsCollected = 0;
		for(Coin coin : map.coins) {
			coin.recreateCoin();
		}
	}
	
	public void checkLevelFinished() {
		
		levelEnded = (player.collides(
				goalArea.getMinX(),
				goalArea.getMinY(),
				goalArea.getMaxX(),
				goalArea.getMaxY()) && coinsCollected == map.coins.size());
		
	}
	

	
	public void render(Graphics2D graphics2D) {
		map.render(graphics2D);
		player.render(graphics2D);
		
	}
	
	public void tick() throws FileNotFoundException {
		
		checkPlayerCollisionsWithMap();
		checkPlayerCollisionsWithObstacles();
		checkPlayerCollisionsWithCoins();
		checkPlayerCollisionsSavePoint();
		handlePlayerDeath();
		checkLevelFinished();

		map.tick();
		player.tick();


		if(levelEnded) {
			
			if (levelsProgressed == levels.size() - 1) {
				System.out.println("you finished");
				System.exit(0);
			}

			levelsProgressed += 1;
			map = new Map(levels.get(levelsProgressed));
			spawnPlayer();
			setUpGoalArea();
			coinsCollected = 0;
			
			
		}
		

	}
	


	
	

}
