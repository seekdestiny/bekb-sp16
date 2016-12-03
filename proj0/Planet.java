/**
 * Created by jifeiqian on 12/2/16.
 */
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        double G = 6.67 / 100000000000.0;
        double r = this.calcDistance(p);
        return (G * this.mass * p.mass) / (r * r);
    }

    public double calcForceExertedByX (Planet p) {
        double r = this.calcDistance(p);
        double F = this.calcForceExertedBy(p);
        return F * (p.xxPos - this.xxPos) / r;
    }

    public double calcForceExertedByY (Planet p) {
        double r = this.calcDistance(p);
        double F = this.calcForceExertedBy(p);
        return F * (p.yyPos - this.yyPos) / r;
    }

    public double calcNetForceExertedByX (Planet[] allPlanets) {
        double sum = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) {
                sum += this.calcForceExertedByX(p);
            }
        }
        return sum;
    }

    public double calcNetForceExertedByY (Planet[] allPlanets) {
        double sum = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) {
                sum += this.calcForceExertedByY(p);
            }
        }
        return sum;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += dt * aX;
        yyVel += dt * aY;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw() {
        String filePath = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, filePath);
    }
}
