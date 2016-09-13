package algo;

import base.Point;
import java.util.ArrayList;
import java.util.Collections;

public class EnvConvexe {
    public ArrayList<Point> listPoints;
    public ArrayList<Point> resPoints;
    public ArrayList<Point> otherPoints;

    public EnvConvexe(ArrayList<Point> points) {
	this.listPoints = points;
	resPoints = new ArrayList<> ();
	otherPoints = new ArrayList<> ();
    }

    public void calculateEnvConvexe() {
	ArrayList<Point> list = new ArrayList<> ();
	//On va chercher le point de départ, le plus à gauche pour etre sur qu'il fasse partie de l'enveloppe
	//On en profite pour générer une copie de la liste de points
	Point base = this.listPoints.get(0);
	int base_index = 0;
	list.add(new Point(base.x, base.y));
	for (int i = 1; i < this.listPoints.size(); i++) {
	    Point p = this.listPoints.get(i);
	    if (p.y < base.y || (p.y == base.y && p.x < base.x)) {
		base = p;
		base_index = i;
	    }
	    list.add(new Point(p.x, p.y));
	}

	Collections.sort(list);

	resPoints.add(base);
	int list_size = list.size();
	Point last_above = null;
	Point current_point = null;
	for (int i = 1; i < list_size; i++) {
	    Point last_point = resPoints.get(resPoints.size()-1);
	    current_point = this.listPoints.get(i);
	    if (current_point.x > last_point.x || resPoints.size() <= 2) {
		if (last_above != null) {
		    float a1 = this.calculateArea(base, last_above, last_point);
		    float a2 = this.calculateArea(base, last_point, current_point);
		    if (a2 < a1) {
			System.out.println("We remove (" + last_point.x + ", " + last_point.y + ")");
			otherPoints.add(last_point);
			resPoints.remove(resPoints.size()-1);
			last_point = null;
		    }
		    last_above = null;
		}
		resPoints.add(current_point);
	    } else {
		if (last_above == null)
		    last_above = last_point;
		resPoints.add(current_point);
	    }
	}
    }

    private float calculateArea(Point p, Point p1, Point p2) {
	float[] vec1 = new float[2];
	float[] vec2 = new float[2];
	vec1[0] = p1.x - p.x; vec1[1] = p1.y - p.y;
	vec2[0] = p2.x - p.x; vec2[1] = p2.y - p.y;
	return (vec1[0] * vec2[1]) - (vec2[0] * vec1[1]);
    }

    public ArrayList<Point> getEnvPoints() {
	return this.resPoints;
    }

    public ArrayList<Point> getOtherPoints() {
	return this.otherPoints;
    }
}