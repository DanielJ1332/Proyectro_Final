/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics;

/**
 *
 * @author Daniel Muñoz
 */
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

public class Assets {
	
	public static boolean loaded = false;
	public static float count = 0;
	public static float MAX_COUNT = 57;
	
	
	public static BufferedImage player;
	public static BufferedImage doubleGunPlayer;
	
	// effects
	
	public static BufferedImage speed;
	
	public static BufferedImage[] shieldEffect = new BufferedImage[3];
	
	// explosion
	
	public static BufferedImage[] exp = new BufferedImage[9];
	
	// lasers
	
	public static BufferedImage blueLaser, greenLaser, redLaser;
	
	// Meteors
	
	public static BufferedImage[] bigs = new BufferedImage[4];
	public static BufferedImage[] meds = new BufferedImage[2];
	public static BufferedImage[] smalls = new BufferedImage[2];
	public static BufferedImage[] tinies = new BufferedImage[2];
	
	// ufo
	
	public static BufferedImage ufo;
	
	// numbers
	
	public static BufferedImage[] numbers = new BufferedImage[11];
	
	public static BufferedImage life;
	
	// fonts
	
	public static Font fontBig;
	public static Font fontMed;
	
	public static Clip backgroundMusic, explosion, playerLoose, playerShoot, ufoShoot, powerUp;
	
	// ui
	
	public static BufferedImage blueBtn;
	public static BufferedImage greyBtn;
	
	// power ups
	
	public static BufferedImage orb, doubleScore, doubleGun, fastFire, shield, star;
	
	public static void init()
	{
            System.out.println("imagen");
		player = loadImage("/res/player.png");
                System.out.println("imagen222222222");
		doubleGunPlayer = loadImage("/res/doubleGunPlayer.png");
		
		speed = loadImage("/res/fire08.png");
		
		blueLaser = loadImage("/res/laserBlue01.png");
		
		greenLaser = loadImage("/res/laserGreen11.png");
		
		redLaser = loadImage("/res/laserRed01.png");
		
		ufo = loadImage("/res/ufo.png");
		
		life = loadImage("/res/life.png");
		
		fontBig = loadFont("/res/futureFont.ttf", 42);
		
		fontMed = loadFont("/res/futureFont.ttf", 20);
		
		for(int i = 0; i < 3; i++)
			shieldEffect[i] = loadImage("/res/shield" + (i + 1) +".png"); 
                
		for(int i = 0; i < bigs.length; i++)
			bigs[i] = loadImage("/res/meteors/meteorBrown_big"+(i+1)+".png");
                 System.out.println("image3333");
		for(int i = 0; i < meds.length; i++)
			meds[i] = loadImage("/res/meteors/meteorBrown_med"+(i+1)+".png");

		for(int i = 0; i < smalls.length; i++)
			smalls[i] = loadImage("/res/meteors/meteorBrown_small"+(i+1)+".png");
		System.out.println("image44444");
		for(int i = 0; i < tinies.length; i++)
			tinies[i] = loadImage("/res/meteors/meteorBrown_tiny"+(i+1)+".png");

		for(int i = 0; i < exp.length; i++)
			exp[i] = loadImage("/res/explosion/"+i+".png");
		System.out.println("image5555");
		for(int i = 0; i < numbers.length; i++)
			numbers[i] = loadImage("/res/numbers/"+i+".png");
		          System.out.println("image6666");
		backgroundMusic = loadSound("/res/backgroundMusic.wav");
		explosion = loadSound("/res/explosion.wav");
		playerLoose = loadSound("/res/playerLoose.wav");
		playerShoot = loadSound("/res/playerShoot.wav");
		ufoShoot = loadSound("/res/ufoShoot.wav");
		powerUp = loadSound("/res/powerUp.wav");
                
		
		greyBtn = loadImage("/res/grey_button.png");
		blueBtn = loadImage("/res/blue_button.png");
		
		orb = loadImage("/res/orb.png");
                System.out.println("image7777");
		doubleScore = loadImage("/res/doubleScore.png");
		doubleGun = loadImage("/res/doubleGun.png");
		fastFire = loadImage("/res/fastFire.png");
                System.out.println("image8888");
		star = loadImage("/res/star.png");
		shield = loadImage("/res/shield.png");
                System.out.println("image9999");
		
		// ===========================================================
		
		loaded = true;
		
	}

	public static BufferedImage loadImage(String path) {
		count ++;
		return Loader.ImageLoader(path);
	}
	public static Font loadFont(String path, int size) {
		count ++;
		return Loader.loadFont(path, size);
	}
	public static Clip loadSound(String path) {
		count ++;
		return Loader.loadSound(path);
	}
	
}
