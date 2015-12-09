package com.github.ivos.jbehave.config;

import org.apache.commons.lang.StringUtils;
import org.jbehave.core.io.StoryNameResolver;

import static org.apache.commons.lang.StringUtils.contains;
import static org.apache.commons.lang.StringUtils.substringAfterLast;

public class SuffixRemovingStoryNameResolver implements StoryNameResolver {

    public static final String SUFFIX = ".story";

    @Override
    public String resolveName(String path) {
        String name = path;
        if (name.endsWith(SUFFIX)) {
            name = name.substring(0, name.length() - SUFFIX.length());
        }
        if (contains(name, '/')) {
            name = substringAfterLast(name, "/");
        }
        if (contains(name, '.')) {
            name = substringAfterLast(name, ".");
        }
        name = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(name), ' ');
        return name;
    }

}
