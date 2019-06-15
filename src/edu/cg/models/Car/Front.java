package edu.cg.models.Car;

import com.jogamp.opengl.GL2;

import edu.cg.models.IRenderable;
import edu.cg.models.SkewedBox;

public class Front implements IRenderable {
	private SkewedBox hoodBox1 = new SkewedBox(Specification.F_HOOD_LENGTH_1,Specification.F_HOOD_HEIGHT_1,Specification.F_HOOD_HEIGHT_2,Specification.F_HOOD_DEPTH_1,Specification.F_HOOD_DEPTH_2);
	private SkewedBox hoodBox2 = new SkewedBox(Specification.F_HOOD_LENGTH_2,Specification.F_HOOD_HEIGHT_2,Specification.F_BUMPER_HEIGHT_1,Specification.F_HOOD_DEPTH_2,Specification.F_HOOD_DEPTH_3);
	private SkewedBox bumperBox = new SkewedBox(Specification.F_BUMPER_LENGTH,Specification.F_BUMPER_HEIGHT_1,Specification.F_BUMPER_HEIGHT_2,Specification.F_BUMPER_DEPTH,Specification.F_BUMPER_DEPTH);
	private SkewedBox bumperWingsBox = new SkewedBox(Specification.F_BUMPER_LENGTH,Specification.F_BUMPER_WINGS_HEIGHT,Specification.F_BUMPER_HEIGHT_2,Specification.F_BUMPER_WINGS_DEPTH,Specification.F_BUMPER_WINGS_DEPTH);
	private PairOfWheels wheels = new PairOfWheels();


	@Override
	public void render(GL2 gl) {
		// Render bumper and bumper wings
		gl.glPushMatrix();
		gl.glTranslated((Specification.F_FRONT_LENGTH - Specification.F_BUMPER_LENGTH) / 2, 0, 0);
		Materials.SetDarkRedMetalMaterial(gl);
		bumperBox.render(gl);
		
		Materials.SetRedMetalMaterial(gl);
		gl.glTranslated(0, 0, (-1) * (Specification.F_BUMPER_DEPTH + Specification.F_BUMPER_WINGS_DEPTH) / 2);
		bumperWingsBox.render(gl);
		gl.glTranslated(0, 0, (Specification.F_BUMPER_DEPTH + Specification.F_BUMPER_WINGS_DEPTH));
		bumperWingsBox.render(gl);
		gl.glPopMatrix();

		// Set color red metal for both hood-boxes
		Materials.SetRedMetalMaterial(gl);

		// Render first hood-box 
		gl.glPushMatrix();
		gl.glTranslated((Specification.F_HOOD_LENGTH_1 - Specification.F_FRONT_LENGTH) / 2, 0, 0);
		hoodBox1.render(gl);
		gl.glPopMatrix();

		// Render second hood-box 
		gl.glPushMatrix();
		gl.glTranslated((Specification.F_HOOD_LENGTH_2- Specification.F_FRONT_LENGTH) / 2 + Specification.F_HOOD_LENGTH_1, 0, 0);
		hoodBox2.render(gl);

		// Render front pair of wheels
		wheels.render(gl);
		gl.glPopMatrix();
	}

	@Override
	public void init(GL2 gl) {
	}

	@Override
	public void destroy(GL2 gl) {
	}
}
