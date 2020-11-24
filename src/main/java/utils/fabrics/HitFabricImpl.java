package utils.fabrics;

import exceptions.InvalidParameterValue;
import model.entites.Hit;
import utils.checkers.HitChecker;
import utils.validators.HitValidator;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class HitFabricImpl implements HitFabric {
    @EJB
    HitChecker checker;

    @EJB
    HitValidator validator;


    @Override
    public Hit createHit(double x, double y, double r) throws InvalidParameterValue {

        validator.validate(x,y,r);
        boolean isInArea = checker.checkHit(x,y,r);

        return new Hit(x,y,r,isInArea);
    }
}
