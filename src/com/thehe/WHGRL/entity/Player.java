package com.thehe.WHGRL.entity;

import java.awt.Color;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import com.thehe.WHGRL.map.Tile;
import com.thehe.WHGRL.utils.Input;
import com.thehe.WHGRL.utils.Vector;

public class Player extends Entity {
	
	public Vector topLeft;
	public Vector topRight;
	public Vector bottomLeft;
	public Vector bottomRight;
	
	public boolean collideTop;
	public boolean collideBottom;
	public boolean collideLeft;
	public boolean collideRight;
	
	public double movementSpeed = 2;
	
	public boolean dead;
	
	public Player(Vector vector) {
		super();
		
		position.setVector(vector);
		
		size = Tile.SIZE / 2;
		outlineSize = Tile.SIZE * 0.67;
		outlineOffset = outlineSize / 2;
		sizeOffset = size / 2;
		
		calculateCornerPositions();

		body = new Rectangle2D.Double();
		
		opacity = 255;
		
		entityColor = new Color(255, 0, 0, (int) opacity);
		outlineColor = new Color(0, 0, 0, (int) opacity);
		
		dead = false;
		
	}
	
	public void calculateCornerPositions() {
		topLeft = new Vector(position.x - outlineOffset, position.y - outlineOffset);
		topRight = new Vector(position.x - outlineOffset + outlineSize, position.y - outlineOffset);
		bottomLeft = new Vector(position.x - outlineOffset, position.y - outlineOffset + outlineSize);
		bottomRight = new Vector(position.x - outlineOffset + outlineSize, position.y - outlineOffset + outlineSize);
	}
	
	public boolean collides(double x, double y) {
		return (x >= topLeft.x &&
				y >= topLeft.y &&
				x < bottomRight.x &&
				y < bottomRight.y);
	}
	
	
	@Override
	public void render(Graphics2D graphics2D) {
		super.render(graphics2D);
		
	}
	
	public void tick() {
		
		if(!dead) {
			
			if(!collideLeft && Input.leftKey.pressed) {
				velocity.x = -movementSpeed;
			}
			
			if(!collideRight && Input.rightKey.pressed) {
				velocity.x = movementSpeed;
			}

			if(!collideTop && Input.upKey.pressed) {
				velocity.y = -movementSpeed;
			}
			
			if(!collideBottom && Input.downKey.pressed) {
				velocity.y = movementSpeed;
			}
			
		}
		
		else {
			
			opacity -= 255.0 / 75.0;
			
			if (opacity <= 0) {
				opacity = 0;
			}
			
		    entityColor = new Color(255, 0, 0, (int) opacity);
		    outlineColor = new Color(0, 0, 0, (int) opacity);

		}
		
		position.addVector(velocity);
		velocity.setVector(0, 0);
		
		calculateCornerPositions();
		
	}


}
