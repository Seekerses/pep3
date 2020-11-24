package utils.fabrics;

import exceptions.InvalidParameterValue;
import model.entites.Hit;

public interface HitFabric {

    Hit createHit(double x, double y, double r) throws InvalidParameterValue;
}
