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
		
		player.collideLeft = (!map.moveableArea.contains(player.topLeft.x - player.movementSpeed, player.topLeft.y) || 
				!map.moveableArea.contains(player.bottomLeft.x - player.movementSpeed, player.bottomLeft.y));
		
		player.collideRight = (!map.moveableArea.contains(player.topRight.x + player.movementSpeed, player.topRight.y) || 
				!map.moveableArea.contains(player.bottomRight.x + player.movementSpeed, player.bottomRight.y));
		
		player.collideTop = (!map.moveableArea.contains(player.topLeft.x, player.topLeft.y - player.movementSpeed) || 
				!map.moveableArea.contains(player.topRight.x, player.topRight.y - player.movementSpeed));
		
		player.collideBottom = (!map.moveableArea.contains(player.bottomLeft.x, player.bottomLeft.y + player.movementSpeed) || 
				!map.moveableArea.contains(player.bottomRight.x, player.bottomRight.y + player.movementSpeed));
		
	}
	
	public void checkPlayerCollisionsWithObstacles() {
		for(Obstacle obstacle : map.obstacles) {
			hitPlayer = (
					player.collides(obstacle.topLeft.x, obstacle.topLeft.y) ||
					player.collides(obstacle.topRight.x, obstacle.topRight.y) ||
					player.collides(obstacle.bottomLeft.x, obstacle.bottomLeft.y) ||
					player.collides(obstacle.bottomRight.x, obstacle.bottomRight.y));
			
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
	    	player.dead = false; 
	    	player.opacity = 255; 


	        
	        
	    }
	}
	
	public void respawnPlayer() {
		player = new Player(spawnAreaStart);
	}
	
	public void checkLevelFinished() {
		
		levelEnded = (
				goalArea.contains(player.topLeft.x, player.topLeft.y) || 
				goalArea.contains(player.topRight.x, player.topRight.y) || 
				goalArea.contains(player.bottomLeft.x, player.bottomLeft.y) || 
				goalArea.contains(player.bottomRight.x, player.bottomRight.y));
		
	}
	

	
	public void render(Graphics2D graphics2D) {
		map.render(graphics2D);
		player.render(graphics2D);
		
	}
	
	public void tick() {
		
		checkPlayerCollisionsWithMap();
		checkPlayerCollisionsWithObstacles();
		handlePlayerDeath();
		checkLevelFinished();

		map.tick();
		player.tick();


		if(levelEnded) {
			respawnPlayer();
		}
		

		



	}
	


	
	

}
