/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameObjects;

/**
 *
 * @author Daniel Muñoz
 */
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import graphics.Text;
import math.Vector2D;
/**
 * Esta clase representa un mensaje que se muestra en pantalla, con la opción
 * de desvanecerse  y moverse verticalmente.
 * Usada para mostrar textos temporales como "Game Over", "Level Up", etc.
 * 
 * @author Daniel Muñoz
 * @author Juan Simon
 */
public class Message {

    private float alpha;                // Nivel de transparencia del mensaje (0.0 = invisible, 1.0 = totalmente visible)
    private String text;               // Texto del mensaje
    private Vector2D position;         // Posición del mensaje en pantalla
    private Color color;               // Color del texto
    private boolean center;            // Si el texto debe centrarse en su posición
    private boolean fade;              // Indica si debe desvanecerse
    private Font font;                 // Fuente del texto
    private final float deltaAlpha = 0.01f; // Cambio por frame en la transparencia
    private boolean dead;              // Indica si el mensaje ya terminó su ciclo

    /**
     * Constructor de la clase Message.
     * 
     * @param position Posición en la que se dibujará el mensaje.
     * @param fade     Si el mensaje debe desaparecer gradualmente.
     * @param text     El texto a mostrar.
     * @param color    El color del texto.
     * @param center   Si el texto debe centrarse en su posición.
     * @param font     Fuente del texto.
     */
    public Message(Vector2D position, boolean fade, String text, Color color,
                   boolean center, Font font) {
        this.font = font;
        this.text = text;
        this.position = new Vector2D(position); // Copia para evitar modificar el original
        this.fade = fade;
        this.color = color;
        this.center = center;
        this.dead = false;

        // Si el mensaje debe desvanecerse, comienza opaco; si no, inicia invisible
        if (fade)
            alpha = 1;
        else
            alpha = 0;
    }

    /**
     * Dibuja el mensaje en pantalla y actualiza su estado posición y transparencia
     * 
     */
    public void draw(Graphics2D g2d) {
        // Establece la transparencia
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        // Dibuja el texto con las propiedades indicadas
        Text.drawText(g2d, text, position, center, color, font);

        // Restaura la opacidad al 100% para evitar afectar otros dibujos
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

        // Mueve el mensaje hacia arriba cada vez que se dibuja
        position.setY(position.getY() - 1);

        // Si está en fade out, reduce la transparencia
        if (fade)
            alpha -= deltaAlpha;
        else
            alpha += deltaAlpha;

        // Si el mensaje se desvaneció completamente, se marca como "muerto"
        if (fade && alpha < 0) {
            dead = true;
        }

        // Si terminó el fade in, comienza a desvanecerse
        if (!fade && alpha > 1) {
            fade = true;
            alpha = 1;
        }
    }

    /**
     * Retorna si el mensaje ya desapareció.
     * 
     * @return true si el mensaje ya completó su ciclo.
     */
    public boolean isDead() {
        return dead;
    }
}