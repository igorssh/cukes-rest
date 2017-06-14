package lv.ctco.cukesrest;

public interface VariableFacade {

    void setVariable(String name, String value);

    void setUUIDToVariable(String name);

    void setCurrentTimestampToVariable(String name);

}
