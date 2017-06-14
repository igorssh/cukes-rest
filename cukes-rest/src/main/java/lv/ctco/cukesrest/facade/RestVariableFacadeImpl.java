package lv.ctco.cukesrest.facade;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.ctco.cukescore.internal.context.GlobalWorldFacade;
import lv.ctco.cukescore.internal.context.InflateContext;

import java.util.UUID;

@Singleton
@InflateContext
public class RestVariableFacadeImpl implements RestVariableFacade {

    @Inject
    private GlobalWorldFacade world;

    @Override
    public void setVariable(String name, String value) {
        if (value.equals("null")) value = null;
        world.put(name, value);
    }

    @Override
    public void setUUIDToVariable(String name) {
        world.put(name, UUID.randomUUID().toString());
    }

    @Override
    public void setCurrentTimestampToVariable(String name) {
        world.put(name, String.valueOf(System.currentTimeMillis()));
    }
}
