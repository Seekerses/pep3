package utils.checkers;

import javax.ejb.Stateless;

@Stateless
public class HitCheckerImpl implements HitChecker{
    @Override
    public boolean checkHit(double x, double y, double r) {

        if ((x >=0 && y >= 0) && (y <=  -x + r )){
            return true;
        }
        if ((x <= 0 && y <= 0) && (Math.pow(x,2) + Math.pow(y,2) <= Math.pow(r,2))) {
            return true;
        }
        if ((x >= 0 && y <=0) && (x <= r && y >= -r)) {
            return true;
        }
        return false;
    }
}
