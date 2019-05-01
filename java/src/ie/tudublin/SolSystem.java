package ie.dit;

import java.util.ArrayList;
import java.lang.Math;

import processing.core.PApplet;
import processing.core.PVector;
import processing.data.Table;
import processing.data.TableRow;

public class SolSystem extends PApplet
{
    private ArrayList<Planet> planets = new ArrayList<Planet>();

    int selected1 = -1;
    int selected2 = -1;

    float border;
    float angle = 0;
    float dx, dy;

    int scale = 4;

    boolean outline = true;
    boolean toScale = false;
    String selected;

    boolean[] keys = new boolean[1024];

    public void settings() {
        size(1000, 1000);        
    }

    public void setup() {
        loadData();
        printPlanets();
        border = width * 0.1f;
    }

    public void loadData() {
        Table table = loadTable("Planets.csv", "header"); 

        for (TableRow row : table.rows()) {
            Planet planet = new Planet(row);
            planets.add(planet);
        }
    }

    public void printPlanets() {
        for (Planet planet : planets) {
            System.out.println(planet);
        }
    }

    private float logb(float a) {
        double log = (double) a;
        double result = Math.log(log);
        float output = (float) result;

        return output;
    }

    private float powb(float a) {
        double pow = (double) a;
        double result = Math.pow(pow, 10);
        float output = (float) result;

        return output;
    }

    private double map1(double a, double b, double c, double d, double e) {
        double range1 = c - b;
        double howFar = a - b;
        double range2 = e - d;

        double output = d + (howFar / range1) * range2;

        return output;
    }

    public void spin() {
        Planet sol = planets.get(0);
        for (Planet p : planets) {

            p.spin(sol);

            /*
            p.setAngle(p.getAngle() + (360f / p.getRotation()));
            //p.setxG((float)Math.cos(p.getAngle()) * p.getDistance() + sol.getxG());
            //p.setyG((float)Math.sin(p.getAngle()) * p.getDistance() + sol.getyG());
            dx = (float)Math.cos(p.getAngle()) * p.getDistance() + sol.getxG();
            dy = (float)Math.sin(p.getAngle()) * p.getDistance() + sol.getyG();
            p.setxG(dx);
            p.setyG(dy);
            */
        }
    }

    public void mouseClicked() {
        for (int i = 0; i < planets.size(); i++) {
            Planet p = planets.get(i);

            float x = map((float)p.getxG(), -40, 40, border, width - border);
            float y = map((float)p.getyG(), -40, 40, border, height - border);

            if (dist(mouseX, mouseY, x, y) < logb(p.getDiameter()) * scale / 2) {
                if (selected1 == -1) {
                    selected1 = i;
                } else if (selected2 == -1) {
                    selected2 = i;
                } else {
                    selected1 = i;
                    selected2 = -1;
                }
            }
        }
    }

    public void keyPressed()
    {
        keys[keyCode] = true;
    }
    public void keyReleased()
    {
        keys[keyCode] = false;
    }

    public boolean checkKey(int c)
    {
        return keys[c] || keys [Character.toUpperCase(c)];
    }

    public void drawPlanets() {
        textAlign(LEFT, CENTER);
        if(toScale = true){
            for (Planet p : planets) {

                float x = (float)map1(p.getxG(), 0, 40, border, width - border);
                float y = (float)map1(p.getyG(), 0, 40, border, height - border);

                stroke(255, 255, 0);
                noFill();

                ellipse(x, y, logb(p.getDiameter()) * scale, logb(p.getDiameter()) * scale);

                stroke(0, 255, 255);
                line(x, y - 5, x, y + 5);
                line(x - 5, y, x + 5, y);
                fill(255);
                text(p.getDisplayName(), x + 20, y);
            }
        }
        else if(toScale = false){
            for (int i = 0; i < planets.size(); i++) {

                Planet p = planets.get(i);

                float x = (float)map1(p.getxG(), 0, 40, border, width - border);
                float y = (float)map1(i, 0, planets.size(), border, height - border);

                stroke(255, 255, 0);
                noFill();

                ellipse(x, y, logb(p.getDiameter()) * scale, logb(p.getDiameter()) * scale);

                stroke(0, 255, 255);
                line(x, y - 5, x, y + 5);
                line(x - 5, y, x + 5, y);
                fill(255);
                text(p.getDisplayName(), x + 20, y);
            }
        }
    }

    public void drawBorder() {
        float edge1 = border * 0.35f;
        float edge2 = border * 0.32f;
        float chamfer = 50.0f;
        line(edge1, edge1, edge1, width - edge1);
        line(edge1, width - edge1, height - edge1, width - edge1);
        line(height - edge1, width - edge1, height - edge1, edge1);
        line(height - edge1, edge1, edge1, edge1);

        line(edge2, edge2, edge2, width - edge2);
        line(edge2, width - edge2, height - edge2, width - edge2);
        line(height - edge2, width - edge2, height - edge2, edge2);
        line(height - edge2, edge2, edge2, edge2);
    }

    public void drawLights() {
        float aline = border * 0.15f;
        float diaBall = border * 0.07f;
        
        ellipse(width /2, aline, diaBall, diaBall);
        
    }

    public void draw() 
    {
        background(0);
        
        //spin();
        
        drawPlanets();
        drawBorder();
        drawLights();

        if (selected1 != -1 && selected2 == -1) {
            Planet planet1 = planets.get(selected1);
            stroke(255, 255, 0);
            float x = map(planet1.getxG(), 0, 40, border, width - border);
            float y = map(planet1.getyG(), 0, 40, border, height - border);

            line(x, y, mouseX, mouseY);
        } else if (selected1 != -1 && selected2 != -1) {
            Planet planet1 = planets.get(selected1);
            float x1 = map(planet1.getxG(), 0, 40, border, width - border);
            float y1 = map(planet1.getyG(), 0, 40, border, height - border);

            Planet planet2 = planets.get(selected2);
            float x2 = map(planet2.getxG(), 0, 40, border, width - border);
            float y2 = map(planet2.getyG(), 0, 40, border, height - border);

            stroke(255, 255, 0);
            line(x1, y1, x2, y2);
            fill(255);
            float dist = dist(planet1.getxG(), planet1.getyG(), planet1.getzG(), planet2.getxG(), planet2.getyG(), planet2.getzG());
            text("Distance from " + planet1.getDisplayName() + " to " + planet2.getDisplayName() + " is " + dist + " astronomical units", border * 0.17f, height - border * 0.15f);
        }

    }
}