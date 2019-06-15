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

		boxesLocations = new LinkedList<>();
		for (double dz = deltaZ; dz < TRACK_LENGTH - BOX_LENGTH / 2.0; dz += deltaZ) {
			int boxCount = 0;
			boolean flag = false;
			for (int i = 0; i < 12; i++) {
				double dx = -( numberOfLanes / 2.0) * ((ASPHALT_TEXTURE_WIDTH - 2.0) / numberOfLanes) + BOX_LENGTH / 2.0
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

	private void renderGrass(GL2 gl) {
		Materials.setGreenMaterial(gl);
		double dx = 15.0;
		gl.glTranslated(dx, 0.0, 0.0);
		this.renderQuadraticTexture(gl, this.grassTexture, GRASS_TEXTURE_WIDTH, GRASS_TEXTURE_DEPTH, 2, TrackSegment.TRACK_LENGTH);
		gl.glTranslated(-2.0 * dx, 0.0, 0.0);
		this.renderQuadraticTexture(gl, this.grassTexture, GRASS_TEXTURE_WIDTH, GRASS_TEXTURE_DEPTH, 2, TrackSegment.TRACK_LENGTH);
		gl.glPopMatrix();
	}

	private void renderAsphalt(GL2 gl) {
		Materials.setAsphaltMaterial(gl);
		gl.glPushMatrix();
		this.renderQuadraticTexture(gl, this.roadTexture, ASPHALT_TEXTURE_WIDTH, ASPHALT_TEXTURE_DEPTH, 6, TrackSegment.TRACK_LENGTH);
		gl.glPopMatrix();
	}

	private void renderQuadraticTexture(GL2 gl, Texture tex, double quadWidth, double quadDepth, int split, double totalDepth) {
		gl.glEnable(GL2.GL_TEXTURE_2D);
		tex.bind(gl);

		gl.glTexEnvi(GL2.GL_TEXTURE_ENV,GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAX_LOD, 1);
		gl.glColor3d(1.0, 0.0, 0.0);
		GLU glu = new GLU();
		GLUquadric quad = glu.gluNewQuadric();
		gl.glColor3d(1.0, 0.0, 0.0);
		gl.glNormal3d(0.0, 1.0, 0.0);
		double d = 1.0 / (double)split;
		double dz = quadDepth / (double)split;
		double dx = quadWidth / (double)split;
		for (double tz = 0.0; tz < totalDepth; tz += quadDepth) {
			for (double i = 0.0; i < (double)split; i += 1.0) {
				gl.glBegin(5);
				for (double j = 0.0; j <= (double)split; j += 1.0) {
					gl.glTexCoord2d(j * d, (i + 1.0) * d);
					gl.glVertex3d(-quadWidth / 2.0 + j * dx, 0.0, -tz - (i + 1.0) * dz);
					gl.glTexCoord2d(j * d, i * d);
					gl.glVertex3d(-quadWidth / 2.0 + j * dx, 0.0, -tz - i * dz);
				}
				gl.glEnd();
			}
		}
		glu.gluDeleteQuadric(quad);
		gl.glDisable(GL2.GL_TEXTURE_2D);
	}


	private void renderBoxes(GL2 gl) {
		Materials.setWoodenBoxMaterial(gl);
		for (Point p : this.boxesLocations) {
			gl.glPushMatrix();
			gl.glTranslated(p.x, 0.0, p.z);
			this.boxTemplate.render(gl);
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
