package br.unifor.si.rosos.testeTeam;

import br.unifor.si.rosos.GameSimulator;
import br.unifor.si.rosos.RobotBasic;
import processing.core.PApplet;

public class TesteRobo extends RobotBasic {
	private float velocidade;

	public TesteRobo(GameSimulator g) {
		super(g);
		// TODO Auto-generated constructor stub
	}
	
	public void setup(){
		velocidade = 0.5f;
		/*
			You should use this method to initialize your code,
			setup Sensors and variables.

			It will be runned once.
		*/
	}

	public void loop(){
		caminhar(velocidade);
		//velocidade+= 0.5f;
	}
	
	void caminhar(float distancia) {
		setSpeed(distancia);
		delay(1000);
		stopMotors();
		//setRotation(-360);
		//delay(1000);
		//stopMotors();
		/*setSpeed(-distancia);
		delay(1000);
		stopMotors();*/
	}

	/*
		If you want to code the thread method yourself, instead of
		using the already made `setup` and `loop` methods, you can
		override the method `run`. Uncomment those lines to use.
	*/
	// public void run(){
		
	// }

	/*
		You can use this method to decorate your robot.
		use Processing methods from the `canvas` object.

		The center of the robot is at [0,0], and the limits
		are 100px x 100px.
	*/
	public void decorateRobot(PApplet canvas){
		
	}

	/*
		Called whenever a robot is:
			PAUSED
			REMOVED from field
			PLACED in field
			STARTED
	*/
	public void onStateChanged(String state){
	}

}
