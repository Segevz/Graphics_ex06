package edu.cg.models;

import java.io.File;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import edu.cg.algebra.Vec;

public class SkewedBox implements IRenderable {
	// TODO: Add you implementation from previous exercise.
	//       * Note you may want to enable textures here to render
	//         the wooden boxes.
	
	private double length, height1, height2, depth1, depth2;

	public SkewedBox() {
		length = .1;
		height1 = .2;
		height2 = .1;
		depth1 = .2;
		depth2 = .1;
	};

	public SkewedBox(double length, double h1, double h2, double d1, double d2) {
		this.length = length;
		this.height1 = h1;
		this.height2 = h2;
		this.depth1 = d1;
		this.depth2 = d2;
	}

	@Override
	public void render(GL2 gl) {
		gl.glNormal3d(1.0, 0.0, 0.0);
		gl.glBegin(7);
		gl.glVertex3d(length / 2.0, 0.0, depth2 / 2.0);
		gl.glVertex3d(length / 2.0, 0.0, -depth2 / 2.0);
		gl.glVertex3d(length / 2.0, height2, -depth2 / 2.0);
		gl.glVertex3d(length / 2.0, height2, depth2 / 2.0);
		gl.glEnd();

		gl.glNormal3d(-1.0, 0.0, 0.0);
		gl.glBegin(7);
		gl.glVertex3d(-length / 2.0, 0.0, -depth1 / 2.0);
		gl.glVertex3d(-length / 2.0, 0.0, depth1 / 2.0);
		gl.glVertex3d(-length / 2.0, height1, depth1 / 2.0);
		gl.glVertex3d(-length / 2.0, height1, -depth1 / 2.0);
		gl.glEnd();

		gl.glNormal3d(0.0, 1.0, 0.0);
		gl.glBegin(7);
		gl.glVertex3d(-length / 2.0, height1, depth1 / 2.0);
		gl.glVertex3d(length / 2.0, height2, depth2 / 2.0);
		gl.glVertex3d(length / 2.0, height2, -depth2 / 2.0);
		gl.glVertex3d(-length / 2.0, height1, -depth1 / 2.0);
		gl.glEnd();

		gl.glNormal3d(0.0, -1.0, 0.0);
		gl.glBegin(7);
		gl.glVertex3d(-length / 2.0, 0.0, depth1 / 2.0);
		gl.glVertex3d(-length / 2.0, 0.0, -depth1 / 2.0);
		gl.glVertex3d(length / 2.0, 0.0, -depth2 / 2.0);
		gl.glVertex3d(length / 2.0, 0.0, depth2 / 2.0);
		gl.glEnd();

		gl.glNormal3d(0.0, 0.0, 1.0);
		gl.glBegin(7);
		gl.glVertex3d(-length / 2.0, height1, depth1 / 2.0);
		gl.glVertex3d(-length / 2.0, 0.0, depth1 / 2.0);
		gl.glVertex3d(length / 2.0, 0.0, depth2 / 2.0);
		gl.glVertex3d(length / 2.0, height2, depth2 / 2.0);
		gl.glEnd();

		gl.glNormal3d(0.0, 0.0, -1.0);
		gl.glBegin(7);
		gl.glVertex3d(-length / 2.0, 0.0, -depth1 / 2.0);
		gl.glVertex3d(-length / 2.0, height1, -depth1 / 2.0);
		gl.glVertex3d(length / 2.0, height2, -depth2 / 2.0);
		gl.glVertex3d(length / 2.0, 0.0, -depth2 / 2.0);
		gl.glEnd();
	}

	@Override
	public void init(GL2 gl) {
	}
	
	@Override
	public String toString() {
		return "SkewedBox";
	}

}
