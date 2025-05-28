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
import java.awt.image.BufferedImage;
import math.Vector2D;

/**
 * Clase abstracta base para todos los objetos del juego.
 * Define atributos comunes como posición y textura,
 * y métodos abstractos para actualizar y dibujar el objeto.
 */
public abstract class GameObjects {
    
    /** Imagen que representa visualmente al objeto */
    protected BufferedImage texture;

    /** Posición del objeto en el espacio 2D del juego */
    protected Vector2D position;

    /**
     * Constructor de la clase GameObjects.
     * 
     * @param position Posición inicial del objeto
     * @param texture Imagen que representa al objeto
     */
    public GameObjects(Vector2D position, BufferedImage texture) {
        this.position = position;
        this.texture = texture;
    }

    /**
     * Método abstracto para actualizar el estado del objeto.
     * @param dt Delta time - tiempo transcurrido desde la última actualización
     */
    public abstract void update(float dt);

    /**
     * Método abstracto para dibujar el objeto en pantalla.
     * 
     * @param g Objeto Graphics para renderizar en pantalla
     */
    public abstract void draw(Graphics g);

    /**
     * Devuelve la posición actual del objeto.
     * 
     * @return posición como un objeto Vector2D
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * Establece una nueva posición para el objeto.
     * 
     * @param position Nueva posición como un objeto Vector2D
     */
    public void setPosition(Vector2D position) {
        this.position = position;
    }
}
