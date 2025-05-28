/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameObjects;

/**
 *
 * @author Daniel Muñoz
 */
import java.awt.image.BufferedImage;

import graphics.Assets;

// Enumeración que representa los tamaños posibles de un meteorito o un  objeto similar
public enum Size {

	// Tamaños posibles, cada uno
	BIG(2, Assets.meds),     // Meteorito grande que se divide en medianos usa texturas "meds"
	MED(2, Assets.smalls),   // Meteorito mediano que se divide en pequeños usa texturas "smalls"
	SMALL(2, Assets.tinies), // Meteorito pequeño que se divide en diminutos usa texturas "tinies"
	TINY(0, null);           // Meteorito diminuto que no se divide más no tiene textura propia

	// Cantidad de meteoritos que generará al dividirse
	public int quantity;

	// Arreglo de texturas que se usan para representar gráficamente este tamaño
	public BufferedImage[] textures;

	// Constructor del enum que asigna la cantidad de divisiones y las texturas
	private Size(int quantity, BufferedImage[] textures) {
		this.quantity = quantity;
		this.textures = textures;
	}
}
