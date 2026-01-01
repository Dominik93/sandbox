package com.slusasrz.mavenplugins.sample;

import com.slusarz.annotation.SampleAnnotation;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.reflections.Configuration;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mojo(name = "class-scan",
        defaultPhase = LifecyclePhase.PACKAGE,
        requiresDependencyResolution = ResolutionScope.COMPILE)
public class ClassScanMojo extends AbstractMojo {
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Override
    public void execute() {
        getLog().info("Class scan");

        try {
            URLClassLoader classLoader = getClassLoader();
            getLog().info("Class loader: " + classLoader);

            Reflections reflections = new Reflections(classLoader);

            Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(SampleAnnotation.class);
            for (Class<?> aClass : typesAnnotatedWith) {
                getLog().info("Class: " + aClass.getSimpleName());
            }

        } catch (Exception e) {
            getLog().error("Class scan failed.", e);
            throw new RuntimeException(e);
        }
    }

    public URLClassLoader getClassLoader() throws Exception {
        try {
            List<String> classpathElements = project.getCompileClasspathElements();
            List<URL> projectClasspathList = new ArrayList<>();
            for (String element : classpathElements) {
                try {
                    projectClasspathList.add(new File(element).toURI().toURL());
                } catch (MalformedURLException e) {
                    throw new MojoExecutionException(element + " is an invalid classpath element", e);
                }
            }

            return new URLClassLoader(projectClasspathList.toArray(new URL[0]));
        } catch (DependencyResolutionRequiredException e) {
            throw new MojoExecutionException("Dependency resolution failed", e);
        }

    }


}
