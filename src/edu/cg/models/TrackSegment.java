package edu.cg.models;

import java.io.File;
import java.util.LinkedList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import edu.cg.algebra.Point;
import edu.cg.models.Car.Materials;

public class TrackSegment implements IRenderable {
	public final static double ASPHALT_TEXTURE_WIDTH = 20.0;
	public final static double ASPHALT_TEXTURE_DEPTH = 10.0;
	public final static double GRASS_TEXTURE_WIDTH = 10.0;
	public final static double GRASS_TEXTURE_DEPTH = 10.0;
	public final static double TRACK_LENGTH = 500.0;
	public final static double BOX_LENGTH = 1.5;
	private LinkedList<Point> boxesLocations;
	private SkewedBox boxTemplate = new SkewedBox(1.5, true);
	private Texture roadTexture;
	private Texture grassTexture;


	public void setDifficulty(double difficulty) {
		difficulty = Math.max(0.05, Math.min(difficulty, 0.95));
		difficulty = Math.max(difficulty, 0.05);
		double numberOfLanes = 4.0;
		double deltaZ = difficulty < 0.25 ? 100.0 : (difficulty < 0.5 ? 75.0 : 50.0);

		boxesLocations = new LinkedList<Point>();
		for (double dz = deltaZ; dz < TRACK_LENGTH - BOX_LENGTH / 2.0; dz += deltaZ) {
			int boxCount = 0;
			boolean flag = false;
			for (int i = 0; i < 12; i++) {
				double dx = -((double) numberOfLanes / 2.0) * ((ASPHALT_TEXTURE_WIDTH - 2.0) / numberOfLanes) + BOX_LENGTH / 2.0
						+ i * BOX_LENGTH;
				if (Math.random() < difficulty) {
					boxesLocations.add(new Point(dx, BOX_LENGTH / 2.0, -dz));
					boxCount += 1;
				} else if (!flag) {// The first time we don't sample a box then we also don't sample the box next to. We want enough space for the car to pass through. 
					i += 1;
					flag = true;
				}
				if (boxCount > difficulty * 10) {
					break;
				}
			}
		}
	}

	public TrackSegment(double difficulty) {
		setDifficulty(difficulty);
	}

	@Override
	public void render(GL2 gl) {
		this.renderBoxes(gl);
		this.renderAsphalt(gl);
		this.renderGrass(gl);
	}


	private void renderBoxes(GL2 gl) {
		Materials.setWoodenBoxMaterial(gl);
		for (Point p : this.boxesLocations) {
			gl.glPushMatrix();
			gl.glTranslated(p.x, 0.0, p.z);
			this.box.render(gl);
			gl.glPopMatrix();
		}
	}

	@Override
	public void init(GL2 gl) {
		this.boxTemplate.init(gl);
		try {
			this.roadTexture = TextureIO.newTexture(new File("Textures/RoadTexture.jpg"), true);
			this.grassTexture= TextureIO.newTexture(new File("Textures/GrassTexture.jpg"), true);

		}
		catch (Exception e) {
			System.err.print("Unable to read texture : " + e.getMessage());
		}	}

	public void destroy(GL2 gl) {
		this.roadTexture.destroy(gl);
		this.grassTexture.destroy(gl);
		this.boxTemplate.destroy(gl);	}

}
