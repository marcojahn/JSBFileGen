/*
 * (C)opyright 2012 ssb Software Service und Beratung GmbH
 */
package org.blub;

/**
 * 
 * 
 * @author Nicolas Moser <nicolas.moser@prodyna.de>
 * 
 */
public class JSBFileGenOptions {

    private String project;

    private String sourceLocation;

    private String nameSpace;

    /**
     * @return the project
     */
    public String getProject() {

        return this.project;
    }

    /**
     * @param project
     *            the project to set
     */
    public void setProject(String project) {

        this.project = project;
    }

    /**
     * @return the sourceLocation
     */
    public String getSourceLocation() {

        return this.sourceLocation;
    }

    /**
     * @param sourceLocation
     *            the sourceLocation to set
     */
    public void setSourceLocation(String projectLocation) {

        this.sourceLocation = projectLocation;
    }

    /**
     * @return the nameSpace
     */
    public String getNameSpace() {

        return this.nameSpace;
    }

    /**
     * @param nameSpace
     *            the nameSpace to set
     */
    public void setNameSpace(String nameSpace) {

        this.nameSpace = nameSpace;
    }

}
