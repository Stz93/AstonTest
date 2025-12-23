package org.example;

import java.util.List;

public interface InputStrategy {
    CustomArrayList<Student> loadData() throws DataLoadingException, ValidationException;
    //если ошибки не работают значит не то в тех классах накалякал, посмотри
    //про стримы надо понять сюда вообще их надо или нет
}
//если тут напишу то можно будет кинуть снова