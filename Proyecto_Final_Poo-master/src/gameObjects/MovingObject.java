/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameObjects;

/**
 *
 * @author Daniel Muñoz
 */

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import graphics.Assets;
import graphics.Sound;
import math.Vector2D;
import states.GameState;

// Clase abstracta que representa un objeto en movimiento dentro del juego
public abstract class MovingObject extends GameObjects {

	// Velocidad del objeto
	protected Vector2D velocity;

	// Transformación para dibujar con rotación y posición
	protected AffineTransform at;

	// Ángulo de rotación del objeto
	protected double angle;

	// Velocidad máxima que puede alcanzar el objeto
	protected double maxVel;

	// Dimensiones de la imagen del objeto
	protected int width;
	protected int height;

	// Referencia al estado actual del juego
	protected GameState gameState;

	// Sonido que se reproduce al explotar
	private Sound explosion;

	// Indica si el objeto ha sido destruido
	protected boolean Dead;

	// Constructor de MovingObject
	public MovingObject(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
		// Llama al constructor de la clase base GameObjects
		super(position, texture);

		// Inicializa atributos
		this.velocity = velocity;
		this.maxVel = maxVel;
		this.gameState = gameState;

		// Toma el tamaño de la textura
		width = texture.getWidth();
		height = texture.getHeight();

		// Inicia sin rotación
		angle = 0;

		// Carga el sonido de explosión
		explosion = new Sound(Assets.explosion);

		// Marca como vivo inicialmente
		Dead = false;
	}

	// Verifica colisiones con otros objetos en movimiento
	protected void collidesWith() {

		// Obtiene todos los objetos en movimiento
		ArrayList<MovingObject> movingObjects = gameState.getMovingObjects(); 

		// Recorre todos los objetos en movimiento
		for (int i = 0; i < movingObjects.size(); i++) {
			MovingObject m = movingObjects.get(i);

			// Se ignora a sí mismo
			if (m.equals(this)) continue;

			// Calcula la distancia entre centros
			double distance = m.getCenter().subtract(getCenter()).getMagnitude();

			// Si están lo suficientemente cerca y ambos están vivos
			if (distance < m.width / 2 + width / 2 && movingObjects.contains(this) && !m.Dead && !Dead) {
				// Ejecuta colisión entre los objetos
				objectCollision(this, m);
			}
		}
	}

	// Lógica de colisión entre dos objetos
	private void objectCollision(MovingObject a, MovingObject b) {

		Player p = null;

		// Verifica si alguno de los objetos es el jugador
		if (a instanceof Player) p = (Player)a;
		else if (b instanceof Player) p = (Player)b;

		// Si el jugador está reapareciendo (invulnerable), se ignora la colisión
		if (p != null && p.isSpawning()) return;

		// Si ambos son meteoritos, no colisionan entre ellos
		if (a instanceof Meteor && b instanceof Meteor) return;

		// Si ninguno es PowerUp, ambos se destruyen
		if (!(a instanceof PowerUp || b instanceof PowerUp)) {
			a.Destroy();
			b.Destroy();
			return;
		}

		// Si hay jugador y PowerUp, se ejecuta la acción del PowerUp
		if (p != null) {
			if (a instanceof Player) {
				((PowerUp)b).executeAction();
				b.Destroy();
			} else if (b instanceof Player) {
				((PowerUp)a).executeAction();
				a.Destroy();
			}
		}
	}

	// Marca el objeto como destruido y reproduce sonido si no es láser ni PowerUp
	protected void Destroy() {
		Dead = true;

		// Evita reproducir sonido si es láser o power-up
		if (!(this instanceof Laser) && !(this instanceof PowerUp))
			explosion.play();
	}

	// Calcula el centro del objeto (útil para colisiones)
	protected Vector2D getCenter() {
		return new Vector2D(position.getX() + width / 2, position.getY() + height / 2);
	}

	// Devuelve si el objeto está destruido
	public boolean isDead() {
		return Dead;
	}
}
