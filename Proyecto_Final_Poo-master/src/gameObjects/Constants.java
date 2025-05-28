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
import javax.swing.filechooser.FileSystemView;
/**
 * Clase que contiene todas las constantes globales del juego.
 * Se utiliza para configurar parámetros generales del juego,
 * como dimensiones, físicas, tiempos, rutas etc
 */
public class Constants {
    
    // Dimensiones de la ventana
    
    public static final int WIDTH = 1000;    // Ancho de la pantalla
    public static final int HEIGHT = 600;    // Alto de la pantalla

    // Propiedades del jugador
    
    public static final int FIRERATE = 300;          // Tiempo entre disparos ms
    public static final double DELTAANGLE = 0.1;     // Cambio de ángulo
    public static final double ACC = 0.2;            // Aceleración del jugador
    public static final double PLAYER_MAX_VEL = 7.0; // Velocidad máxima del jugador
    public static final long FLICKER_TIME = 200;     // Tiempo de parpadeo invulnerabilidad en ms
    public static final long SPAWNING_TIME = 3000;   // Tiempo de aparición del jugador
    public static final long GAME_OVER_TIME = 3000;  // Tiempo de espera para  el game over

    // Propiedades del láser
    public static final double LASER_VEL = 15.0;     // Velocidad del disparo láser

    
    // Propiedades del meteorito
    
    public static final double METEOR_INIT_VEL = 2.0;    // Velocidad inicial del meteorito
    public static final int METEOR_SCORE = 20;           // Puntos por destruir un meteorito
    public static final double METEOR_MAX_VEL = 6.0;     // Velocidad máxima de los meteoritos
    public static final int SHIELD_DISTANCE = 150;       // Distancia de activación del escudo

    // Propiedades del OVNI
    
    public static final int NODE_RADIUS = 160;           // Radio de movimiento
    public static final double UFO_MASS = 60;            // Masa del OVNI (afecta la física)
    public static final int UFO_MAX_VEL = 3;             // Velocidad máxima del OVNI
    public static long UFO_FIRE_RATE = 1000;             // Frecuencia de disparo del OVNI (ms)
    public static double UFO_ANGLE_RANGE = Math.PI / 2;  // Rango angular de disparo
    public static final int UFO_SCORE = 40;              // Puntos por destruir un OVNI
    public static final long UFO_SPAWN_RATE = 10000;     // Tiempo entre apariciones de OVNIs (ms)

    // Textos del menú y juego
    public static final String PLAY = "PLAY";
    public static final String EXIT = "EXIT";
    public static final String RETURN = "RETURN";
    public static final String HIGH_SCORES = "HIGHEST SCORES";
    public static final String SCORE = "SCORE";
    public static final String DATE = "DATE";

    // Configuración de interfaz
    public static final int LOADING_BAR_WIDTH = 500;     // Ancho de la barra de carga
    public static final int LOADING_BAR_HEIGHT = 50;     // Alto de la barra de carga

    // Ruta para guardar puntajes
    
    public static final String SCORE_PATH = FileSystemView.getFileSystemView()
            .getDefaultDirectory().getPath() + "\\Space_Ship_Game\\data.json"; 


    public static final String PLAYER = "PLAYER";
    public static final String PLAYERS = "PLAYERS";

    // Potenciadores y tiempos
    
    public static final long POWER_UP_DURATION = 10000;     // Duración de un power-up en ms
    public static final long POWER_UP_SPAWN_TIME = 8000;     // Tiempo entre apariciones

    public static final long SHIELD_TIME = 12000;            // Tiempo de duración del escudo
    public static final long DOUBLE_SCORE_TIME = 10000;      // Tiempo con doble puntuación
    public static final long FAST_FIRE_TIME = 14000;         // Tiempo con disparo rápido
    public static final long DOUBLE_GUN_TIME = 12000;        // Tiempo con doble cañón

    public static final int SCORE_STACK = 1000;              // Puntaje por lotes acumulativos
}