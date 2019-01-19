package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
	
	/* Player class for handling the update of each player each frame - use updatePosition() method
	 * Player 1 is blue, Player 2-infinity is red
	 */
	
	private Color trailColor;
	private int playerNum;
	//Direction is a vector2 that will be multiplied by the speed of the player to determine the new position
	private Vector2 direction;
	private int speed = 400;
	
	public int x;
	public int y;
	public int roundsWon = 0;
	public Vector2 lastVisited;
	public Rectangle collider;
	public Texture sprite;
	
	//Constructor takes an initial X and Y value for where the player starts in the game
	public Player(int initX, int initY, int playerNum) {
		this.x = initX;
		this.y = initY;
		this.lastVisited = new Vector2(this.x, this.y);
		this.playerNum = playerNum;
		
		//Trail color, Texture, and initial direction are based off of player number
		this.trailColor = this.playerNum == 1 ? new Color(252, 30, 30, 1) : new Color(9, 138, 232, 1);
		
		//Set up the Rectangle collider for the player
		this.collider = new Rectangle();
		this.collider.x = this.x;
		this.collider.y = this.y;
		this.collider.width = 64;
		this.collider.height = 64;
		
		this.sprite = playerNum == 1 ? new Texture(Gdx.files.internal("player1.png")) : new Texture(Gdx.files.internal("player2.png"));
		
		this.direction = playerNum == 1 ? new Vector2(1, 0) : new Vector2(-1, 0);
	}
	
	public void updatePosition(float deltaTime) {
		lastVisited = new Vector2(this.x, this.y);
		
		this.x += this.speed * this.direction.x * deltaTime;
		this.y += this.speed * this.direction.y * deltaTime;
		
		//Catch out of bounds
		if(this.x > 1920)
			this.x = 0;
		if(this.x < -64)
			this.x = 1920;
		if(this.y > 1080)
			this.y = 0;
		if(this.y < -64)
			this.y = 1080;
	}
	
	//Returns the color of the player for drawing outside of the class
	public Color getColor() {
		return this.trailColor;
	}
	
	//Set the direction Vector when input is found
	public void setDirection(Vector2 newDir) {
		this.direction = newDir;
	}
	
	//clean up of variables
	public void dispose() {
		sprite.dispose();
	}
}
