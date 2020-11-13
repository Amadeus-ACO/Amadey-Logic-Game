/*    
Copyright (C) Paul Falstad and Iain Sharp
	Modified by Pplos Studio
    
    This file is a part of Avrora Logic Game, which based on CircuitJS1
    https://github.com/Pe3aTeJlb/Avrora-logic-game
    
    CircuitJS1 was originally written by Paul Falstad.
	http://www.falstad.com/
	https://github.com/pfalstad/circuitjs1

	JavaScript conversion by Iain Sharp.
	http://lushprojects.com/
	https://github.com/sharpie7/circuitjs1
    
    Avrora Logic Game is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 1, 2 of the License, or
    (at your option) any later version.
    Avrora Logic Game is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License 
    along with Avrora Logic Game.  If not, see <http://www.gnu.org/licenses/>.
*/

package AmadeyLogicGame;

import javafx.scene.text.Font;

class LogicInputElm extends SwitchElm {
    	
	final int FLAG_TERNARY = 1;
	final int FLAG_NUMERIC = 2;
	double hiV=5, loV=0;

	
	public LogicInputElm(int xx, int yy) {
	    super(xx, yy, false);
	    numHandles=1;
	    hiV = 5;
	    loV = 0;
	    
	}
	
	public LogicInputElm(int xa, int ya, int xb, int yb, int f) {
	    super(xa, ya, xb, yb, f);
	    numHandles=1;		
	    hiV = 5;
		loV = 0;
	}
	
	boolean isNumeric() { return true; }
		
	int getPostCount() { return 1; }
	
	void setPoints() {
	    super.setPoints();
	    lead1 = interpPoint(point1, point2, 1-12/dn);
	}
	
	void draw(Graphics g) {

		Font oldf = g.getFont();
	    Font f = new Font("SansSerif",20);
	    g.setFont(f);
	    g.setColor(needsHighlight() ? selectColor : whiteColor);
	    String s = position == 0 ? "L" : "H";
	    if (isNumeric())
		s = "" + position;
	    
	    setBbox(point1, new Point(lead1.x-50,lead1.y), 0);
	    drawCenteredText(g, s, x2, y2, true);
	    setVoltageColor(g, volts[0]);
	    drawThickLine(g, point1, lead1);
	    updateDotCount();
	    //drawDots(g, point1, lead1, curcount);
		g.setColor(Color.white);
	    drawPosts(g);
	    g.setFont(oldf);
	    
	}
	
	Rectangle getSwitchRect() {
	    return new Rectangle(x2-10, y2-10, 20, 20);
	}	

	void setCurrent(int vs, double c) { current = -c; }
	
	void stamp() {

	    double v = (position == 0) ? loV : hiV;
	    sim.stampVoltageSource(0, nodes[0], voltSource, v);
	}
	
	int getVoltageSourceCount() { return 1; }
	
	double getVoltageDiff() { return volts[0]; }
	
	boolean hasGroundConnection(int n1) { return true; }
	
	double getCurrentIntoNode(int n) {
	    return -current;
	}
	
}
