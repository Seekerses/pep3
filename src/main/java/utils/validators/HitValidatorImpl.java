package utils.validators;

import exceptions.InvalidParameterValue;

import javax.ejb.Stateless;
import java.util.Arrays;
import java.util.List;

@Stateless
public class HitValidatorImpl implements HitValidator {

    private final List<Double> rValues;
    private final List<Double> xValues;

    public HitValidatorImpl() {
        rValues = (List<Double>) Arrays.asList(1D, 1.5D, 2D, 2.5D, 3D);
        xValues = (List<Double>) Arrays.asList(-5D, -4D, -3D, -2D, -1D, 0D, 1D, 2D, 3D);
    }

    @Override
    public void validate(Double x, Double y, Double r) throws InvalidParameterValue {
        checkX(x);
        checkY(y);
        check(r);
    }

    private void check(Double r) throws InvalidParameterValue {

        if (r == null) throw new InvalidParameterValue();
        if (!rValues.contains(r)) throw new InvalidParameterValue();
    }

    private void checkY(Double y) throws InvalidParameterValue {

        if (y == null) throw new InvalidParameterValue();
        if (y < -5D || y > 3D) throw new InvalidParameterValue();
    }

    private void checkX(Double x) throws InvalidParameterValue {

        if (x == null) throw new InvalidParameterValue();
        if (x < -5D || x > 3D) throw new InvalidParameterValue();
    }


}
