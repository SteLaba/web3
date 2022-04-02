package Beans;

import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

import Entity.Hit;
import Utils.DatabaseManager;
import lombok.*;


@Getter
@Setter
@ManagedBean(name = "hitsData", eager = true)
@ApplicationScoped
public class HitsData implements Serializable {
    private static final long serialVersionUID = 1L;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private DatabaseManager dbManager = new DatabaseManager();
    private List<Hit> hitsList = dbManager.getHitList();

    private double x = -3;
    private double y;
    private double r = 1;

    private String r1 = "r-value";
    private String r2 = "r2";
    private String r3 = "r3";
    private String r4 = "r4";
    private String r5 = "r5";

    private double r1val = 1;
    private double r2val = 1.5;
    private double r3val = 2;
    private double r4val = 2.5;
    private double r5val = 3;


    private String curTime;
    private double execTime;
    private boolean result;

    synchronized public void addHit() {
        addHitHelper(x, y, r);

    }

    private double svgX;
    private double svgY;
    private double svgR;

    public void svgAddHit() {
        addHitHelper(svgX, svgY, svgR);
    }

    private void addHitHelper(double x, double y, double r) {
        long startTime = System.nanoTime();

        curTime = dateFormat.format(new Date());
        result = checkHitResult(x, y, r);
        execTime = (double) (System.nanoTime() - startTime) / 1000000;

        Hit newHit = new Hit(x, y, r, curTime, execTime, result);
        if (dbManager.addHit(newHit)){
            hitsList.add(newHit);
        }
    }

    public void clear() {
        if (dbManager.clearList()) {
            hitsList.clear();
        }
    }

    public List<Hit> getHitsList() {
        return hitsList;
    }

    private boolean checkHitResult(double x, double y, double r) {
        return checkTriangle(x, y, r) || checkRectangle(x, y, r) || checkCircle(x, y, r);
    }

    private boolean checkTriangle(double x, double y, double r){
        return x <= 0 && y >= 0 && (2*y-x) <= r;
    }

    private boolean checkRectangle(double x, double y, double r){
        return x >= 0 && x <= r/2 && y >= 0 && y <= r;
    }

    private boolean checkCircle(double x, double y, double r){
        return x <= 0 && y <= 0 && x*x + y*y <= r*r/4;
    }

    public void rToggle(ActionEvent event) {
        UIComponent component = event.getComponent();
        r = (Double) component.getAttributes().get("value");

        r1 = "r1";
        r2 = "r2";
        r3 = "r3";
        r4 = "r4";
        r5 = "r5";

        if (Double.compare(r, 1) == 0){
            r1 = "r-value";
        }
        if (Double.compare(r, 1.5) == 0){
            r2 = "r-value";
        }
        if (Double.compare(r, 2) == 0){
            r3 = "r-value";
        }
        if (Double.compare(r, 2.5) == 0){
            r4 = "r-value";
        }
        if (Double.compare(r, 3) == 0){
            r5 = "r-value";
        }
    }
}
