import fenetre.Fenetre;
import fenetre.Panneau;
import base.Point;
import base.Segment;
import base.Polyedre;
import algo.EnvConvexe;
import java.util.ArrayList;


public class Main {
    public static void main(String[] arg) {
	Panneau panneau = new Panneau();

	ArrayList<Point> list = new ArrayList<> ();
	list.add(new Point(120, 110));
	list.add(new Point(150, 140));
	list.add(new Point(160, 160));
	list.add(new Point(150, 170));
	list.add(new Point(160, 190));

	EnvConvexe conv = new EnvConvexe(list);
	ArrayList<Point> test = conv.getConvexe();

	for (Point p : test) {
	    System.out.println (p.x + ", " + p.y);
	}

	for (int i = 0; i < list.size(); i++) {
	    panneau.add(conv.listPoints.get(i));
	}

	Fenetre fenetre = new Fenetre(panneau);
	fenetre.show();





	// Point[] A = { new Point(400, 400), new Point(100, 100),
	// 	      new Point(200, 200), new Point(400, 100), new Point(70, 70) };

	// Segment s1 = new Segment(A[0],A[1]);
	// Segment s2 = new Segment(A[2],A[3]);

	// Point p = new Point(180,70);

	// panneau.add(s1);
	// panneau.add(s2);
	// panneau.add(p);

	// Point p = s1.intersection(s2);

	// if (p!= null)
	//     panneau.add(p);

    }
}
