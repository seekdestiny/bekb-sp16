/**
 * Created by jifeiqian on 12/2/16.
 */
public class NBody {
    public static double readRadius(String filePath) {
        In in = new In(filePath);
        int nPlanets = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filePath) {
        In in = new In(filePath);
        int nPlanets = in.readInt();
        double raidus = in.readDouble();
        Planet[] allPlanets = new Planet[nPlanets];

        for (int i = 0; i < nPlanets; i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            allPlanets[i] = new Planet(xPos, yPos, xVel, yVel, mass, img);
        }

        return allPlanets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);

        StdDraw.setScale(-1 * radius, 1 * radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        for (Planet p : allPlanets) {
            p.draw();
        }

        StdAudio.loop("audio/2001.mid");

        for (int t = 0; t <= T; t += dt) {
            double[] xForces = new double[allPlanets.length];
            double[] yForces = new double[allPlanets.length];

            for (int i = 0; i < allPlanets.length; i++) {
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }

            for (int i = 0; i < allPlanets.length; i++) {
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Planet p : allPlanets) {
                p.draw();
            }

            StdDraw.show(10);
        }

        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
	        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   		    allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel, allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        }
    }
}
