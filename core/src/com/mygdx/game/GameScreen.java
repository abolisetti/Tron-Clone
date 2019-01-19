package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen {
	// Game screen for the main running of the game
	
	final TronGame game;
	
	private Sound explosion;
	private Player player1;
	private Player player2;
	private OrthographicCamera camera;
	
	public GameScreen(TronGame manager) {
		this.game = manager;
		
		//load the explosion sound file
		explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.wav"));
		
		//Instantiate the two players
		player1 = new Player(480, 540, 1);
		player2 = new Player(1440, 540, 2);
		
		//set up the camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1080);
	}
	
	@Override
	public void render(float delta) {
		//Set background to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);
        
        // begin a new batch and draw the two players and their last visited spot
        game.batch.begin();
        game.batch.draw(player1.sprite, player1.x, player1.y);
        game.batch.draw(player2.sprite, player2.x, player2.y);
        game.batch.end();
        //TODO: draw last visited spot

        //process player1 input
        if(Gdx.input.isKeyPressed(Keys.A))
        	player1.setDirection(new Vector2(-1, 0));
        if(Gdx.input.isKeyPressed(Keys.D))
        	player1.setDirection(new Vector2(1, 0));
        if(Gdx.input.isKeyPressed(Keys.W))
        	player1.setDirection(new Vector2(0, 1));
        if(Gdx.input.isKeyPressed(Keys.S))
        	player1.setDirection(new Vector2(0, -1));
        
        //process player2 input
        if(Gdx.input.isKeyPressed(Keys.LEFT))
        	player2.setDirection(new Vector2(-1, 0));
        if(Gdx.input.isKeyPressed(Keys.RIGHT))
        	player2.setDirection(new Vector2(1, 0));
        if(Gdx.input.isKeyPressed(Keys.UP))
        	player2.setDirection(new Vector2(0, 1));
        if(Gdx.input.isKeyPressed(Keys.DOWN))
        	player2.setDirection(new Vector2(0, -1));
        
        //Update players 1 and 2
        player1.updatePosition(delta);
        player2.updatePosition(delta);
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		explosion.dispose();
		player1.dispose();
		player2.dispose();
	}
}
