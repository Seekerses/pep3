package utils.validators;

import exceptions.InvalidParameterValue;

public interface HitValidator {

    void validate(Double x, Double y, Double r) throws InvalidParameterValue;
}
