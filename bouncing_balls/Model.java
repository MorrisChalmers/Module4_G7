package bouncing_balls;

import java.util.List;

/**
 * The physics model.
 * 
 * This class is where you should implement your bouncing balls model.
 * 
 * The code has intentionally been kept as simple as possible, but if you wish, you can improve the design.
 * 
 * @author Simon Robillard
 *
 */
class Model {

	double areaWidth, areaHeight;
	
	Ball [] balls;

	Model(double width, double height) {
		areaWidth = width;
		areaHeight = height;
		
		// Initialize the model with a few balls
		balls = new Ball[2];
		balls[0] = new Ball(width / 3, height * 0.9, 1.2, 1.6, 0, 0, 0.2);
		balls[1] = new Ball(2 * width / 3, height * 0.7, -0.6, 0.6, 0, 0, 0.2);
	}

	void step(double deltaT) {
		// TODO this method implements one step of simulation with a step deltaT
		for (Ball b : balls) {
			// detect collision with the border
			if (b.x < b.radius || b.x > areaWidth - b.radius) {
				b.vx *= -1; // change direction of ball
			}
			if (b.y < b.radius || b.y > areaHeight - b.radius) {
				b.vy *= -1;
			}		

	

			gravity(b);


			// compute new position according to the speed of the ball
			b.x += deltaT * b.vx;
			b.y += deltaT * b.vy;
			b.vx += deltaT * b.ax;
			b.vy += deltaT * b.ay;
		}

		if (isColliding(balls[0] , balls[1])){
			double [] polarveolocityball0 = rectToPolar(balls[0].vx, balls[0].vy);
			double [] polarveolocityball1 = rectToPolar(balls[1].vx, balls[1].vy);
			double i = 1 * polarveolocityball0[0] + 1 * polarveolocityball1[0];
			double r = polarveolocityball1[0] - polarveolocityball0[0];

			double v2 = (i - r)/2;
			double v1 = (i+r)/2;
			
			
			
		}
	}

	void gravity(Ball ball) {
		ball.ay = -9.82;
	}

	boolean isColliding (Ball ball1, Ball ball2){

		double distance = Math.sqrt(Math.pow(ball1.x - ball2.x, 2) + Math.pow(ball1.y - ball2.y, 2));
        return distance <= (ball1.radius + ball2.radius);
	}
	
	double[] polarToRect(double r, double theta) {
		double[] result = new double[2];
		result[0] = r * Math.cos(theta);
		result[1] = r * Math.sin(theta);
		return result;
	}

	double[] rectToPolar(double x, double y) {
		double[] result = new double[2];
		result[0] = Math.sqrt(x*x + y*y);
		result[1] = Math.atan(y / x);
		return result;
	}


	/**
	 * Simple inner class describing balls.
	 */
	class Ball {
		
		Ball(double x, double y, double vx, double vy, double ax, double ay, double r) {
			this.x = x;
			this.y = y;
			this.vx = vx;
			this.vy = vy;
			this.ax = ax;
			this.ay = ay;
			this.radius = r;
		}

		/**
		 * Position, speed, and radius of the ball. You may wish to add other attributes.
		 */
		double x, y, vx, vy, ax, ay, radius;
	}
}
