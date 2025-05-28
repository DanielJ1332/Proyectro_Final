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

 /** Clase que representa un láser disparado en el juego.
 * Hereda de MovingObject para tener movimiento y comportamiento general.
 */
public class Laser extends MovingObject {

    /**
     * Constructor del láser.
     * 
     * @param position Posición inicial del láser
     * @param velocity Dirección del disparo
     * @param maxVel Velocidad máxima del láser
     * @param angle Ángulo de rotación del láser
     * @param texture Imagen que representa el láser
     * @param gameState Estado del juego  en el que se encuentra el láser
     */
    public Laser(Vector2D position, Vector2D velocity, double maxVel, double angle, BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVel, texture, gameState);
        this.angle = angle; // Ángulo de rotación del láser
        this.velocity = velocity.scale(maxVel); // Escalar velocidad para alcanzar la velocidad máxima
    }

    /**
     * Actualiza la posición del láser y verifica colisiones o límites de pantalla.
     * 
     */
    @Override
    public void update(float dt) {
        position = position.add(velocity); // Mueve el láser en su dirección

        // Si el láser sale de la pantalla, se destruye
        if (position.getX() < 0 || position.getX() > Constants.WIDTH ||
            position.getY() < 0 || position.getY() > Constants.HEIGHT) {
            Destroy();
        }

        // Verifica colisiones con otros objetos
        collidesWith();
    }

    /**
     * Dibuja el láser en pantalla aplicando rotación.
     * 
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Crear la transformación para rotar la imagen del láser
        at = AffineTransform.getTranslateInstance(position.getX() - width / 2, position.getY());
        at.rotate(angle, width / 2, 0); // Rota en su centro horizontal

        // Dibujar el láser con rotación aplicada
        g2d.drawImage(texture, at, null);
    }

    /**
     * Obtiene el centro del láser para propósitos de colisión o posicionamiento.
     * 
     * @return Centro del láser como un Vector2D
     */
    @Override
    public Vector2D getCenter() {
        return new Vector2D(position.getX() + width / 2, position.getY() + width / 2);
    }
}