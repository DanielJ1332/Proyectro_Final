/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameObjects;

/**
 *
 * @author Daniel Muñoz
 * @author Juan Simon
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import graphics.Assets;
import graphics.Sound;
import math.Vector2D;
import states.GameState;
import ui.Action;

// Clase PowerUp que representa una mejora temporal en el juego
public class PowerUp extends MovingObject {

	// Duración de vida del PowerUp en pantalla
	private long duration;

	// Acción que se ejecutará cuando el jugador recoja el PowerUp
	private Action action;

	// Sonido que se reproduce al recoger el PowerUp
	private Sound powerUpPick;

	// Imagen que representa el tipo de PowerUp (por ejemplo, fuego, escudo, etc.)
	private BufferedImage typeTexture;

	// Constructor del PowerUp
	public PowerUp(Vector2D position, BufferedImage texture, Action action, GameState gameState) {
		// Llama al constructor de la superclase MovingObject con velocidad 0 y sin rotación
		super(position, new Vector2D(), 0, Assets.orb, gameState);

		// Asigna la acción a ejecutar y la textura específica
		this.action = action;
		this.typeTexture = texture;

		// Inicializa la duración en 0
		duration = 0;

		// Carga el sonido de recoger PowerUp
		powerUpPick = new Sound(Assets.powerUp);
	}

	// Ejecuta la acción del PowerUp al ser recogido por el jugador
	void executeAction() {
		action.doAction();     // Ejecuta la acción (ej. vida extra, velocidad, etc.)
		powerUpPick.play();    // Reproduce el sonido
	}

	// Se ejecuta cada fotograma para actualizar el estado del PowerUp
	@Override
	public void update(float dt) {
		angle += 0.1;         // Gira lentamente en pantalla
		duration += dt;       // Aumenta la duración que lleva en pantalla

		// Si se supera la duración máxima permitida, se elimina el PowerUp
		if (duration > Constants.POWER_UP_DURATION) {
			this.Destroy();
		}

		// Verifica colisiones con otros objetos (como el jugador)
		collidesWith();
	}

	// Dibuja el PowerUp en pantalla con rotación
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		// Calcula la transformación para centrar y rotar la textura del tipo de PowerUp
		at = AffineTransform.getTranslateInstance(
			position.getX() + Assets.orb.getWidth()/2 - typeTexture.getWidth()/2,
			position.getY() + Assets.orb.getHeight()/2 - typeTexture.getHeight()/2
		);

		// Aplica la rotación sobre el centro de la textura del tipo de PowerUp
		at.rotate(angle,
			typeTexture.getWidth()/2,
			typeTexture.getHeight()/2
		);

		// Dibuja la esfera base (orb) del PowerUp sin rotación
		g.drawImage(Assets.orb, (int)position.getX(), (int)position.getY(), null);

		// Dibuja la textura del tipo de PowerUp encima, con rotación
		g2d.drawImage(typeTexture, at, null);
	}
}

