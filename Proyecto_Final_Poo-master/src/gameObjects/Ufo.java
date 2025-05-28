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
import java.util.ArrayList;

import graphics.Assets;
import graphics.Sound;
import math.Vector2D;
import states.GameState;

// Clase que representa un enemigo ufo que se mueve por un camino y dispara
public class Ufo extends MovingObject {

	// Lista de puntos que el Ufo seguirá como ruta
	private ArrayList<Vector2D> path;

	// Punto actual del camino que está siguiendo
	private Vector2D currentNode;

	// Índice del nodo actual 
	private int index;

	// Indica si el Ufo aún está siguiendo la ruta
	private boolean following;

	// Controla la velocidad de disparo 
	private long fireRate;

	// Sonido del disparo del Ufo
	private Sound shoot;

	// Constructor
	public Ufo(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture,
			   ArrayList<Vector2D> path, GameState gameState) {
		super(position, velocity, maxVel, texture, gameState);
		this.path = path;
		index = 0;
		following = true;
		fireRate = 0;
		shoot = new Sound(Assets.ufoShoot);
	}

	// Calcula el siguiente paso hacia el nodo actual del camino
	private Vector2D pathFollowing() {
		currentNode = path.get(index);

		// Distancia entre el centro del Ufo y el nodo actual
		double distanceToNode = currentNode.subtract(getCenter()).getMagnitude();

		// Si llega suficientemente cerca al nodo, pasa al siguiente
		if (distanceToNode < Constants.NODE_RADIUS) {
			index++;
			if (index >= path.size()) {
				following = false;
			}
		}

		// Calcula el vector de fuerza hacia el nodo actual
		return seekForce(currentNode);
	}

	// Calcula la fuerza necesaria para dirigirse hacia un objetivo
	private Vector2D seekForce(Vector2D target) {
		Vector2D desiredVelocity = target.subtract(getCenter());
		desiredVelocity = desiredVelocity.normalize().scale(maxVel);
		return desiredVelocity.subtract(velocity);
	}

	// Actualiza el estado del Ufo
	@Override
	public void update(float dt) {
		fireRate += dt;

		Vector2D pathFollowing;

		if (following)
			pathFollowing = pathFollowing();
		else
			pathFollowing = new Vector2D();

		// Aplica la fuerza dividiéndola por la masa
		pathFollowing = pathFollowing.scale(1 / Constants.UFO_MASS);

		// Actualiza la velocidad y limita al máximo
		velocity = velocity.add(pathFollowing);
		velocity = velocity.limit(maxVel);

		// Actualiza la posición
		position = position.add(velocity);

		// Si se sale de la pantalla, se destruye
		if (position.getX() > Constants.WIDTH || position.getY() > Constants.HEIGHT
				|| position.getX() < -width || position.getY() < -height)
			Destroy();

		// Disparo automático hacia el jugador
		if (fireRate > Constants.UFO_FIRE_RATE) {
			Vector2D toPlayer = gameState.getPlayer().getCenter().subtract(getCenter());
			toPlayer = toPlayer.normalize();

			// Dirección del disparo con un ángulo aleatorio dentro de un rango
			double currentAngle = toPlayer.getAngle();
			currentAngle += Math.random() * Constants.UFO_ANGLE_RANGE - Constants.UFO_ANGLE_RANGE / 2;

			// Ajuste del ángulo si apunta a la izquierda
			if (toPlayer.getX() < 0)
				currentAngle = -currentAngle + Math.PI;

			// Asigna la dirección modificada
			toPlayer = toPlayer.setDirection(currentAngle);

			// Crea un nuevo láser y lo añade al principio de la lista
			Laser laser = new Laser(
					getCenter().add(toPlayer.scale(width)), // Posición de salida
					toPlayer,                               // Dirección
					Constants.LASER_VEL,                    // Velocidad
					currentAngle + Math.PI / 2,             // Rotación visual
					Assets.redLaser,                        // Textura
					gameState
			);

			gameState.getMovingObjects().add(0, laser); // Se añade al inicio de la lista
			fireRate = 0; // Reinicia el tiempo de espera
			shoot.play(); // Reproduce el sonido de disparo
		}

		// Detiene el sonido si se reproduce por demasiado tiempo
		if (shoot.getFramePosition() > 8500) {
			shoot.stop();
		}

		// Rota lentamente el Ufo
		angle += 0.08;

		// Revisa colisiones con otros objetos
		collidesWith();
	}

	// Método sobrescrito para destrucción del Ufo
	@Override
	public void Destroy() {
		gameState.addScore(Constants.UFO_SCORE, position); // Añade puntaje
		gameState.playExplosion(position);                 // Reproduce explosión
		super.Destroy();                                   // Marca como destruido
	}

	// Dibuja el Ufo con rotación
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
		at.rotate(angle, width / 2, height / 2);

		g2d.drawImage(texture, at, null);
	}
}
