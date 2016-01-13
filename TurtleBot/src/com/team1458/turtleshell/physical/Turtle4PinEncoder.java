package com.team1458.turtleshell.physical;

import com.team1458.turtleshell.TurtleEncoder;

import edu.wpi.first.wpilibj.Encoder;

public class Turtle4PinEncoder implements TurtleEncoder {
	private Encoder encoder;
	public Turtle4PinEncoder(int aChannel, int bChannel) {
		encoder = new Encoder(aChannel, bChannel);
		encoder.setDistancePerPulse(1.0);
	}
	@Override
	public int getTicks() {
		return encoder.get();
	}

	@Override
	public double getRate() {
		return encoder.getRate();
	}

	@Override
	public void reset() {
		encoder.reset();
	}

}
