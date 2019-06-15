package edu.cg.models.Car;

import com.jogamp.opengl.GL2;

public class Materials {
	public static void SetBlackMetalMaterial(GL2 gl) {
		float[] ambient = new float[]{0f, 0f, 0f, 1f};
		float[] diffuse = new float[]{.01f, .01f, .01f, 1f};
		float[] specular = new float[]{.5f, .5f, .5f, 1f};
		float shine = 32f;

		setMaterial(gl, ambient, diffuse, specular, shine);
	}

	public static void SetRedMetalMaterial(GL2 gl) {
		float[] ambient = new float[]{.1745f, .01175f, .01175f, 1f};
		float[] diffuse = new float[]{.61424f, .04136f, .04136f, 1f};
		float[] specular = new float[]{.727811f, .626959f, .626959f, 1f};
		float shine = 76.8f;

		setMaterial(gl, ambient, diffuse, specular, shine);
	}

	public static void SetDarkRedMetalMaterial(GL2 gl) {
		float[] ambient = new float[]{0f, 0f, 0f, 1f};
		float[] diffuse = new float[]{.4f, 0f, 0f, 1f};
		float[] specular = new float[]{.4f, .3f, .3f, 1f};
		float shine = 32f;

		setMaterial(gl, ambient, diffuse, specular, shine);
	}

	public static void SetDarkGreyMetalMaterial(GL2 gl) {
		float[] ambient = new float[]{.25f, .25f, .25f, 1f};
		float[] diffuse = new float[]{.4f, .4f, .4f, 1f};
		float[] specular = new float[]{.774597f, .774597f, .774597f, 1f};
		float shine = 76.8f;

		setMaterial(gl, ambient, diffuse, specular, shine);
	}

	public static void setMaterialTire(GL2 gl) {
		final float[] ambient = { .01f, .01f, .01f , 1};
		final float[] diffuse = { .05f, .05f, .05f , 1};
		final float[] specular = { .2f, .2f, .2f , 1};

		setMaterial(gl, ambient, diffuse, specular, 24);
	}

	public static void setMaterialRims(GL2 gl) {
		final float[] ambient = {.09f, .1f, .11f, 1};
		final float[] diffuse = {.2f, .2f, .2f, 1};
		final float[] specular = {.8f, .8f, .8f, 1};

		setMaterial(gl, ambient, diffuse, specular, 150);
	}

	public static void setAsphaltMaterial(GL2 gl) {
		float[] ambient = new float[]{.15375f, .15f, .16625f, 1f};
		float[] diffuse = new float[]{.68275f, .67f, .72525f, 1f};
		float[] specular = new float[]{.33274f, .32863f, .34643f, 1f};
		float shine = 38.4f;

		setMaterial(gl, ambient, diffuse, specular, shine);
	}

	public static void setWoodenBoxMaterial(GL2 gl) {
		float[] ambient = new float[]{.4f, .4f, .4f, 1f};
		float[] diffuse = new float[]{.714f, .4284f, .18144f, 1f};
		float[] specular = new float[]{.393548f, .271906f, .166721f, 1f};
		float shine = 25.6f;

		setMaterial(gl, ambient, diffuse, specular, shine);
	}

	public static void setGreenMaterial(GL2 gl) {
		float[] ambient = new float[]{.0215f, .1745f, .0215f, 1f};
		float[] diffuse = new float[]{.0755f, .6142f, .0756f, 1f};
		float[] specular = new float[]{.63f, .7278f, .633f, 1f};
		float shine = 128f;

		setMaterial(gl, ambient, diffuse, specular, shine);
	}

	private static void setMaterial(GL2 gl, float[] ambient, float[] diffuse, float[] specular, float shine) {
		gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, shine);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, ambient, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, diffuse, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, specular, 0);
	}
}

