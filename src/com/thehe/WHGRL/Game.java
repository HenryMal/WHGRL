package com.thehe.WHGRL;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;

import com.thehe.WHGRL.entity.Coin;
import com.thehe.WHGRL.entity.Obstacle;
import com.thehe.WHGRL.entity.Player;
import com.thehe.WHGRL.map.Map;
import com.thehe.WHGRL.map.Tile;
import com.thehe.WHGRL.utils.Vector;

public class Game {

	public Map map;
	public Player player;
	public Vector spawnAreaStart;
	
	public Rectangle2D goalArea;
	
	public boolean levelEnded;
	public int coinsCollected;

	
	public Game() throws FileNotFoundException {
		
		map = new Map("maps/level_2.txt");
		
		spawnPlayer();
		setUpGoalArea();


	}
	
	public void spawnPlayer() {

		spawnAreaStart = new Vector(
				map.spawnTiles.get(0).position.x,
				map.spawnTiles.get(0).position.y);
		
		Vector spawnAreaEnd = new Vector(
				map.spawnTiles.get(map.spawnTiles.size() - 1).position.x + Tile.SIZE,
				map.spawnTiles.get(map.spawnTiles.size() - 1).position.y + Tile.SIZE);
		
		spawnAreaStart.addVector(spawnAreaEnd);
		spawnAreaStart.scaleVector(0.5);
		
		respawnPlayer();

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
		handlePlayerDeath();
		checkLevelFinished();

		map.tick();
		player.tick();


		if(levelEnded) {

			map = new Map("maps/level_2.txt");
			respawnPlayer();
			coinsCollected = 0;
		}
		

	}
	


	
	

}
