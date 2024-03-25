package com.thehe.WHGRL;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;

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
	public boolean hitPlayer;
	
	public Game() throws FileNotFoundException {
		
		map = new Map("maps/level_1.txt");
		
		spawnPlayer();
		setUpGoalArea();

		hitPlayer = false;

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
			hitPlayer = player.collides(obstacle);
			
			if (hitPlayer) {
				break;
			}
		}
	}
	
	public void handlePlayerDeath() {
	    if(hitPlayer && !player.dead) {
	        player.dead = true; 
	    }

	    if(player.dead && player.opacity == 0) {
	    	
	    	respawnPlayer();
	        
	    }
	}
	
	public void respawnPlayer() {
		player = new Player(spawnAreaStart);
	}
	
	public void checkLevelFinished() {
		
		levelEnded = (player.collides(
				goalArea.getMinX(),
				goalArea.getMinY(),
				goalArea.getMaxX(),
				goalArea.getMaxY()));
		
	}
	

	
	public void render(Graphics2D graphics2D) {
		map.render(graphics2D);
		player.render(graphics2D);
		
	}
	
	public void tick() throws FileNotFoundException {
		
		checkPlayerCollisionsWithMap();
		checkPlayerCollisionsWithObstacles();
		handlePlayerDeath();
		checkLevelFinished();

		map.tick();
		player.tick();


		if(levelEnded) {
			//map = new Map("maps/level_2.txt"); just a way to load in new maps
			map = new Map("maps/level_2.txt");
			respawnPlayer();
		}
		

	}
	


	
	

}
