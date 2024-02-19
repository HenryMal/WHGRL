package com.thehe.WHGRL;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.util.Set;

import javax.swing.JPanel;

import com.thehe.WHGRL.entity.Player;
import com.thehe.WHGRL.map.Map;
import com.thehe.WHGRL.map.Tile;
import com.thehe.WHGRL.utils.Vector;

public class Game extends JPanel {

	private static final long serialVersionUID = -1016559436094627976L;
	
	public Map map;
	public Player player;
	
	public Rectangle2D goalArea;
	
	public boolean collideTop;
	public boolean collideBottom;
	public boolean collideLeft;
	public boolean collideRight;

	
	public Game() throws FileNotFoundException {
		
		map = new Map("maps/level_1.txt");
		
		spawnPlayer();
		setUpGoalArea();
		
	}
	
	public void spawnPlayer() {
		player = new Player();
		
		Vector spawnAreaStart = new Vector(
				map.spawnTiles.get(0).position.x,
				map.spawnTiles.get(0).position.y);
		
		Vector spawnAreaEnd = new Vector(
				map.spawnTiles.get(map.spawnTiles.size() - 1).position.x + Tile.SIZE,
				map.spawnTiles.get(map.spawnTiles.size() - 1).position.y + Tile.SIZE);
		
		spawnAreaStart.addVector(spawnAreaEnd);
		spawnAreaStart.scaleVector(0.5);
		
		player.position.setVector(spawnAreaStart);
		
	}
	
	public void setUpGoalArea() {
		
		goalArea = new Rectangle.Double(
				map.goalTiles.get(0).position.x,
				map.goalTiles.get(0).position.y,
				(map.goalTiles.get(map.goalTiles.size() - 1).position.x + Tile.SIZE) - (map.goalTiles.get(0).position.x),
				(map.goalTiles.get(map.goalTiles.size() - 1).position.y + Tile.SIZE) - (map.goalTiles.get(0).position.y));
		
	}
	
	
	public void render(Graphics2D graphics2D) {
		map.render(graphics2D);
		player.render(graphics2D);
		
	}
	
	public void tick() {
		

		



		map.tick();
		player.tick();
		
		// testing collisions
		
		// left
		if(!map.moveableArea.contains(
				player.position.x - player.outlineSize / 2,
				player.position.y - player.outlineSize / 2)
				&& !map.moveableArea.contains(
						player.position.x - player.outlineSize / 2, 
						(player.position.y - player.outlineSize / 2) + player.outlineSize)) {
			player.velocity.x = 0;
			collideLeft = true;
		}
		
		else {
			collideLeft = false;
		}
		
		// right
		if(!map.moveableArea.contains((player.position.x - player.outlineSize / 2) + player.outlineSize,
				player.position.y - player.outlineSize / 2)
				&& !map.moveableArea.contains((player.position.x - player.outlineSize / 2) + player.outlineSize,
						(player.position.y - player.outlineSize / 2) + player.outlineSize)) {
			player.velocity.x = 0;
			collideRight = true;
		}
		
		else {
			collideRight = false;
		}
		
		// up
		if(!map.moveableArea.contains(
				player.position.x - player.outlineSize / 2,
				player.position.y - player.outlineSize / 2)
				&& !map.moveableArea.contains(
						(player.position.x - player.outlineSize / 2) + player.outlineSize, 
						player.position.y - player.outlineSize / 2)) {
			player.velocity.y = 0;
			collideTop = true;
		}
		
		else {
			collideTop = false;
		}
		
		// down
		if(!map.moveableArea.contains(
				player.position.x - player.outlineSize / 2,
				(player.position.y - player.outlineSize / 2) + player.outlineSize)
				&& !map.moveableArea.contains(
						(player.position.x - player.outlineSize / 2) + player.outlineSize, 
						(player.position.y - player.outlineSize / 2) + player.outlineSize)) {
			player.velocity.y = 0;
			collideBottom = true;
		}
		
		else {
			collideBottom = false;
		}
		
		

		

	}
	


	
	

}
