package com.thehe.WHGRL.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import com.thehe.WHGRL.utils.Vector;

public class Coin extends Entity {

	public Coin(Vector position) {
		super(position, new Vector(), 0.25, 0.5);
		
		body = new Ellipse2D.Double();

		entityColor = new Color(255, 255, 0, (int) opacity);
	
	}
	
	public void recreateCoin() {
		calculateAABB();
		entityColor = new Color(255, 255, 0, (int) opacity);
		outlineColor = new Color(0, 0, 0, (int) opacity);
	}
	
	public void destroy() {
		hitbox = null;
		entityColor = new Color(255, 255, 0, 0);
		outlineColor = new Color(0, 0, 0, 0);
	}
	
	public void render(Graphics2D graphics2D) {	
		super.render(graphics2D);	
	}
	
	
}
