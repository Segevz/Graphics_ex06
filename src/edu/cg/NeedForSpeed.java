package edu.cg;

import java.awt.Component;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

import edu.cg.algebra.Vec;
import edu.cg.models.Track;
import edu.cg.models.TrackSegment;
import edu.cg.models.Car.F1Car;
import edu.cg.models.Car.Specification;

/**
 * An OpenGL 3D Game.
 *
 */
public class NeedForSpeed implements GLEventListener {
	private GameState gameState = null;
	private F1Car car = null;
	private Vec carCameraTranslation = null;
	private Track gameTrack = null;
	private FPSAnimator ani;
	private Component glPanel;
	private boolean isModelInitialized = false;
	private boolean isDayMode = true;

	public NeedForSpeed(Component glPanel) {
		this.glPanel = glPanel;
		gameState = new GameState();
		gameTrack = new Track();
		carCameraTranslation = new Vec(0.0);
		car = new F1Car();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		if (!isModelInitialized) {
			initModel(gl);
		}
		if (isDayMode) {
			gl.glClearColor(0.5f, 0.7f, 0.95f, 1.0f);
		} else {
			gl.glClearColor(0.05f, 0.02f, 0.3f, 1.0f);
		}
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();

		updateCarCameraTranslation(gl);
		setupCamera(gl);
		setupLights(gl);
		renderCar(gl);
		renderTrack(gl);
	}

	private void updateCarCameraTranslation(GL2 gl) {
		Vec ret = this.gameState.getNextTranslation();
		this.carCameraTranslation = this.carCameraTranslation.add(ret);
		double dx = Math.max((double)this.carCameraTranslation.x, -7.0);
		this.carCameraTranslation.x = (float)Math.min(dx, 7.0);
		if ((double)Math.abs(this.carCameraTranslation.z) >= TrackSegment.TRACK_LENGTH + 10) {
			this.carCameraTranslation.z = -((float)((double)Math.abs(this.carCameraTranslation.z) % TrackSegment.TRACK_LENGTH));
			this.gameTrack.changeTrack(gl);
		}
	}

	private void setupCamera(GL2 gl) {
		GLU glu = new GLU();
		glu.gluLookAt(0.0 + (double)this.carCameraTranslation.x, 1.8 + (double)this.carCameraTranslation.y, 2.0 + (double)this.carCameraTranslation.z, 0.0 + (double)this.carCameraTranslation.x, 1.5 + (double)this.carCameraTranslation.y, -5.0 + (double)this.carCameraTranslation.z, 0.0, 0.7, -0.3);
	}

	private void setupLights(GL2 gl) {
		if (isDayMode) {
			gl.glDisable(GL2.GL_LIGHT1);
			float[] sunColor = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
			Vec direction = new Vec(0.0, 1.0, 1.0).normalize();
			float[] position = new float[]{direction.x, direction.y, direction.z, 0.0f};
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, sunColor, 0);
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, sunColor, 0);
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, position, 0);
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, new float[]{0.1f, 0.1f, 0.1f, 1.0f}, 0);
			gl.glEnable(GL2.GL_LIGHT0);
		} else {
			gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, new float[]{0.15f, 0.15f, 0.18f, 1.0f}, 0);
			float[] position1 = new float[]{0.0f + this.carCameraTranslation.x, 8.0f + this.carCameraTranslation.y, -0.0f + this.carCameraTranslation.z, 1.0f};
			this.setupSpotlight(gl, GL2.GL_LIGHT0, position1);
			float[] position2 = new float[]{0.0f + this.carCameraTranslation.x, 8.0f + this.carCameraTranslation.y, -15.0f + this.carCameraTranslation.z, 1.0f};
			this.setupSpotlight(gl, GL2.GL_LIGHT1, position2);
		}
	}

	private void setupSpotlight(GL2 gl, int light, float[] pos) {
		float[] sunColor = new float[]{0.85f, 0.85f, 0.85f, 1.0f};
		gl.glLightfv(light, GL2.GL_POSITION, pos, 0);
		gl.glLightf(light, GL2.GL_SPOT_CUTOFF, 75.0f);
		gl.glLightfv(light, GL2.GL_SPOT_DIRECTION, new float[]{0.0f, -1.0f, 0.0f}, 0);
		gl.glLightfv(light, GL2.GL_SPECULAR, sunColor, 0);
		gl.glLightfv(light, GL2.GL_DIFFUSE, sunColor, 0);
		gl.glEnable(light);
	}

	private void renderTrack(GL2 gl) {
		gl.glPushMatrix();
		this.gameTrack.render(gl);
		gl.glPopMatrix();
	}

	private void renderCar(GL2 gl) {
		double carRotation = this.gameState.getCarRotation();
		gl.glPushMatrix();
		gl.glTranslated((double)this.carCameraTranslation.x, 0.15 + (double)this.carCameraTranslation.y, -6.6 + (double)this.carCameraTranslation.z);
		gl.glRotated(-carRotation, 0.0, 1.0, 0.0);
		gl.glRotated(90.0, 0.0, 0.1, 0.0);
		gl.glScaled(4.0, 4.0, 4.0);
		this.car.render(gl);
		gl.glPopMatrix();
	}

	public GameState getGameState() {
		return gameState;
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		ani = new FPSAnimator(30, true);
		ani.add(drawable);
		glPanel.repaint();

		initModel(gl);
		ani.start();
	}

	public void initModel(GL2 gl) {
		gl.glCullFace(GL2.GL_BACK);
		gl.glEnable(GL2.GL_CULL_FACE);

		gl.glEnable(GL2.GL_NORMALIZE);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_SMOOTH);

		car.init(gl);
		gameTrack.init(gl);
		isModelInitialized = true;
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();
		GLU glu = new GLU();
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(57.0, width / (double)height, 2, TrackSegment.TRACK_LENGTH);
	}

	/**
	 * Start redrawing the scene with 30 FPS
	 */
	public void startAnimation() {
		if (!ani.isAnimating())
			ani.start();
	}

	/**
	 * Stop redrawing the scene with 30 FPS
	 */
	public void stopAnimation() {
		if (ani.isAnimating())
			ani.stop();
	}

	public void toggleNightMode() {
		isDayMode = !isDayMode;
	}

}
