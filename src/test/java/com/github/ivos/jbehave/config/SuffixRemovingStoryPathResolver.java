package com.github.ivos.jbehave.config;

import org.jbehave.core.Embeddable;
import org.jbehave.core.io.CasePreservingResolver;

public class SuffixRemovingStoryPathResolver extends CasePreservingResolver {

    public static final String SUFFIX = "Story";

    @Override
    protected String resolveName(Class<? extends Embeddable> embeddableClass) {
        String name = embeddableClass.getSimpleName();
        if (name.endsWith(SUFFIX)) {
            name = name.substring(0, name.length() - SUFFIX.length());
        }
        return name;
    }

}
