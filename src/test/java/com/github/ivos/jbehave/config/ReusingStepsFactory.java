package com.github.ivos.jbehave.config;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.steps.ScanningStepsFactory;

import java.util.HashMap;
import java.util.Map;

public class ReusingStepsFactory extends ScanningStepsFactory {

    private final Map<Class<?>, Object> stepInstances;

    public ReusingStepsFactory(
            Configuration configuration,
            Class<? extends AbstractStory> storyClass) {
        super(configuration, storyClass);
        stepInstances = new HashMap<>();
    }

    public Object createInstanceOfType(Class<?> type) {
        Object instance = stepInstances.get(type);
        if (null == instance) {
            try {
                instance = type.newInstance();
                stepInstances.put(type, instance);
            } catch (Exception e) {
                throw new StepsInstanceNotFound(type, this);
            }
        }
        return instance;
    }

}
