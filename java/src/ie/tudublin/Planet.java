package ie.dit;

import processing.data.TableRow;

public class Planet
{
    private String displayName;
    private float distance;
    private float rotation;
    private float diameter;
    private float xG;
    private float yG;
    private float zG;
    private double angle;

    public Planet(TableRow row)
    {
        displayName = row.getString("Display Name");
        distance = row.getFloat("Distance");
        rotation = row.getFloat("Rotation");
        diameter = row.getFloat("Diameter");
        xG = row.getFloat("Xg");
        yG = row.getFloat("Yg");
        zG = row.getFloat("Zg");
        angle = row.getDouble("Angle");
    }

    public void spin(Planet center) {

        float dx, dy;
        Planet body = center;
        Planet planet = this;
        planet.setAngle(planet.getAngle() + (double) (900f / planet.getRotation()));
        // this.setxG(((float)Math.cos(this.getAngle()) * this.getDistance()) +
        // body.getxG());
        // this.setyG(((float)Math.sin(this.getAngle()) * this.getDistance()) +
        // body.getyG());
        double x = Math.cos(planet.getAngle()) * (double) planet.getDistance() + (double) body.getxG();
        dx = (float) x;
        double y = Math.sin(planet.getAngle()) * (double) planet.getDistance() + (double) body.getyG();
        dy = (float) y;
        planet.setxG(dx);
        planet.setyG(dy);
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the distance
     */
    public float getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(float distance) {
        this.distance = distance;
    }

    /**
     * @return the rotation
     */
    public float getRotation() {
        return rotation;
    }

    /**
     * @param rotation the rotation to set
     */
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    /**
     * @return the diameter
     */
    public float getDiameter() {
        return diameter;
    }

    /**
     * @param diameter the diameter to set
     */
    public void setDiameter(float diameter) {
        this.diameter = diameter;
    }

    /**
     * @return the xG
     */
    public float getxG() {
        return xG;
    }

    /**
     * @param xG the xG to set
     */
    public void setxG(float xG) {
        this.xG = xG;
    }

    /**
     * @return the yG
     */
    public float getyG() {
        return yG;
    }

    /**
     * @param yG the yG to set
     */
    public void setyG(float yG) {
        this.yG = yG;
    }

    /**
     * @return the zG
     */
    public float getzG() {
        return zG;
    }

    /**
     * @param zG the zG to set
     */
    public void setzG(float zG) {
        this.zG = zG;
    }

    /**
     * @return the angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * @param angle the angle to set
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public String toString() {
        return "Planet [angle=" + angle + ", diameter=" + diameter + ", displayName=" + displayName + ", distance="
                + distance + ", rotation=" + rotation + ", xG=" + xG + ", yG=" + yG + ", zG=" + zG + "]";
    }

}
