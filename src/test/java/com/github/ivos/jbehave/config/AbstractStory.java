package com.github.ivos.jbehave.config;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryLoader;
import org.jbehave.core.io.StoryPathResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.FreemarkerViewGenerator;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ParameterControls;

import java.util.Collections;

import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;

public abstract class AbstractStory extends JUnitStory {

    private static final String STORY_TIMEOUTS = "120";

    public AbstractStory() {
        Embedder embedder = new Embedder();
        embedder.useEmbedderControls(embedderControls());
        embedder.useMetaFilters(Collections.singletonList("-skip"));
        useEmbedder(embedder);
    }

    private EmbedderControls embedderControls() {
        return new EmbedderControls()
//                .doIgnoreFailureInView(true)
                .useStoryTimeouts(STORY_TIMEOUTS);
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryPathResolver(storyPathResolver())
                .useStoryLoader(storyLoader())
                .useStoryReporterBuilder(storyReporterBuilder())
                .useParameterControls(parameterControls())
                .useViewGenerator(new FreemarkerViewGenerator(new SuffixRemovingStoryNameResolver()));
    }

    private StoryPathResolver storyPathResolver() {
        return new SuffixRemovingStoryPathResolver();
    }

    private StoryLoader storyLoader() {
        return new LoadFromClasspath();
    }

    private StoryReporterBuilder storyReporterBuilder() {
        return new StoryReporterBuilder()
                .withCodeLocation(CodeLocations.codeLocationFromClass(this.getClass()))
                .withPathResolver(new FilePrintStreamFactory.ResolveToPackagedName())
                .withFailureTrace(true)
                .withDefaultFormats()
                .withFormats(CONSOLE, HTML);
    }

    private ParameterControls parameterControls() {
        return new ParameterControls()
                .useDelimiterNamedParameters(true);
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new ReusingStepsFactory(configuration(), getClass());
    }

}
