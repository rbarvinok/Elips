package ua.elips.interfaces;

import ua.elips.objects.Gap;

public interface GapTable {

    // добавить запись
    void add(Gap gap);

    // внести измененные значения (подтвердить измененные данные)
    void update(Gap gap);

    // удалить запись
    void delete(Gap gap);

}
