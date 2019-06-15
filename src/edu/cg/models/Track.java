package edu.cg.models;

import com.jogamp.opengl.GL2;

public class Track implements IRenderable {
	private TrackSegment currentTrackSegment = null;
	private TrackSegment nextTrackSegment = null;
	private double currentDifficulty = 0.5; // Current track segment difficulty
	private final double DIFFICULTY_DELTA = 0.05; // Difficulty difference between adjacent track segments
	private final double MAXIMUM_DIFFICULTY = 0.95;

	public Track() {
		currentTrackSegment = new TrackSegment(currentDifficulty);
		nextTrackSegment = new TrackSegment(currentDifficulty + DIFFICULTY_DELTA);
	}

	@Override
	public void render(GL2 gl) {
		gl.glPushMatrix();
		this.currentTrackSegment.render(gl);
		gl.glTranslated(0.0, 0.0, -TrackSegment.TRACK_LENGTH);
		this.nextTrackSegment.render(gl);
		gl.glPopMatrix();	}

	@Override
	public void init(GL2 gl) {
		currentTrackSegment.init(gl);
		nextTrackSegment.init(gl);
	}

	@Override
	public void destroy(GL2 gl) {
		currentTrackSegment.destroy(gl);
		nextTrackSegment.destroy(gl);
		currentTrackSegment = nextTrackSegment = null;
	}

	public void changeTrack(GL2 gl) {
		TrackSegment tmp = currentTrackSegment;
		currentTrackSegment = nextTrackSegment;
		currentDifficulty += DIFFICULTY_DELTA;
		currentDifficulty = Math.min(currentDifficulty, MAXIMUM_DIFFICULTY);
		tmp.setDifficulty(currentDifficulty + DIFFICULTY_DELTA);
		nextTrackSegment = tmp;
	}

}
