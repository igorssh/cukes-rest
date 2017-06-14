package lv.ctco.cukesrest.facade;

public interface RestVariableFacade {

    void setVariable(String name, String value);

    void setUUIDToVariable(String name);

    void setCurrentTimestampToVariable(String name);

}
