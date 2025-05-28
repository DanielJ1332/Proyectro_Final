/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameObjects;

/**
 *
 * @author Daniel Muñoz
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import math.Vector2D;
import states.GameState;

// Clase que representa un meteorito en el juego
public class Meteor extends MovingObject {
	// Tamaño del meteorito 
	private Size size;
	// Constructor del meteorito
	public Meteor(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState, Size size) {
		// Llama al constructor de la clase padre MovingObject
		super(position, velocity, maxVel, texture, gameState);
		
		// Asigna el tamaño del meteorito
		this.size = size;

		// Escala la velocidad inicial al máximo permitido
		this.velocity = velocity.scale(maxVel);
	}

	// Método que actualiza el estado del meteorito en cada frame
	@Override
	public void update(float dt) {
		
		// Obtiene la posición del jugador
		Vector2D playerPos = new Vector2D(gameState.getPlayer().getCenter());
		
		// Calcula la distancia entre el meteorito y el jugador
		int distanceToPlayer = (int) playerPos.subtract(getCenter()).getMagnitude();

		// Si el meteorito está cerca del jugador y el escudo está activado
		if(distanceToPlayer < Constants.SHIELD_DISTANCE / 2 + width / 2) {
			if(gameState.getPlayer().isShieldOn()) {
				// Aplica fuerza de escape para alejar el meteorito del jugador
				Vector2D fleeForce = fleeForce();
				velocity = velocity.add(fleeForce);
			}
		}
		
		// Si la velocidad es mayor al máximo, frena en la dirección opuesta
		if(velocity.getMagnitude() >= this.maxVel) {
			Vector2D reversedVelocity = new Vector2D(-velocity.getX(), -velocity.getY());
			velocity = velocity.add(reversedVelocity.normalize().scale(0.01f));
		}

		// Limita la velocidad máxima del meteorito
		velocity = velocity.limit(Constants.METEOR_MAX_VEL);

		// Actualiza la posición según la velocidad
		position = position.add(velocity);

		// Lógica de teletransporte si sale por los bordes de la pantalla
		if(position.getX() > Constants.WIDTH)
			position.setX(-width);
		if(position.getY() > Constants.HEIGHT)
			position.setY(-height);
		if(position.getX() < -width)
			position.setX(Constants.WIDTH);
		if(position.getY() < -height)
			position.setY(Constants.HEIGHT);

		// Aumenta el ángulo para que el meteorito rote
		angle += Constants.DELTAANGLE / 2;
	}

	// Calcula la fuerza que hace huir al meteorito del jugador
	private Vector2D fleeForce() {
		// Dirección desde el meteorito hacia el jugador
		Vector2D desiredVelocity = gameState.getPlayer().getCenter().subtract(getCenter());
		// Se normaliza y se multiplica por la velocidad máxima
		desiredVelocity = (desiredVelocity.normalize()).scale(Constants.METEOR_MAX_VEL);
		// Se invierte la dirección para que huya
		Vector2D v = new Vector2D(velocity);
		return v.subtract(desiredVelocity);
	}
        
	// Método llamado cuando el meteorito es destruido
	@Override
	public void Destroy() {
		// Divide el meteorito en otros más pequeños
		gameState.divideMeteor(this);
		// Reproduce explosión
		gameState.playExplosion(position);
		// Suma puntos al jugador
		gameState.addScore(Constants.METEOR_SCORE, position);
		// Llama al método Destroy de la clase padre
		super.Destroy();
	}

	// Dibuja el meteorito en pantalla con rotación
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;

		// Aplica transformación para posicionar y rotar el meteorito
		at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
		at.rotate(angle, width/2, height/2);

		// Dibuja la imagen del meteorito
		g2d.drawImage(texture, at, null);
	}

	// Devuelve el tamaño del meteorito
	public Size getSize() {
		return size;
	}
}
