package com.thehe.WHGRL.entity;

import java.awt.Color;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import com.thehe.WHGRL.utils.Vector;
import com.thehe.WHGRL.utils.input.Input;

public class Player extends Entity {
	
	public boolean collideTop;
	public boolean collideBottom;
	public boolean collideLeft;
	public boolean collideRight;
	
	public double movementSpeed;
	
	public boolean dead;
	
	public Player(Vector position) {
		super(position, new Vector(), 0.5, 0.67);
		
		dead = false;
		movementSpeed = 2;
		
		body = new Rectangle2D.Double();
		entityColor = new Color(255, 0, 0, (int) opacity);
	
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
			
			opacity -= 5.0;
			
			if (opacity <= 0) {
				opacity = 0;
			}
			
		    entityColor = new Color(255, 0, 0, (int) opacity);
		    outlineColor = new Color(0, 0, 0, (int) opacity);

		}
		
		position.addVector(velocity);
		velocity.setVector(0, 0);

		super.tick();
	}


}
