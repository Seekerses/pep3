package model.containers;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public interface HitListDAO<T> {

    T insert(@NotNull T obj);

    T getById(long id);

    List<T> getAll();

    boolean clear();

    boolean delete(@NotNull T obj);
}
