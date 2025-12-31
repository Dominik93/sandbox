package com.slusasrz.mavenplugins.sample;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.List;

@Mojo(name = "aggregate-plugin", defaultPhase = LifecyclePhase.COMPILE)
public class AggregateMojo extends AbstractMojo {
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter(name = "outputDirectory")
    private String outputDirectory;
    @Parameter
    private List<AggregateConfig> aggregates;
    @Override
    public void execute() {
        getLog().info("Aggregate");
        for (AggregateConfig aggregate : aggregates) {
            getLog().info("Read all from %s. Save to %s, Name as %s with level %s"
                    .formatted(aggregate.getInputDirectory(), outputDirectory, aggregate.getName(), aggregate.getLevel()));
        }
    }

}
