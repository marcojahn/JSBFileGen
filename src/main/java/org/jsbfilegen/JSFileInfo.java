/*
 * (C)opyright 2012 ssb Software Service und Beratung GmbH
 */
package org.blub;

import org.blub.parser.file.JSFile;

/**
 * 
 * 
 * @author Nicolas Moser <nicolas.moser@prodyna.de>
 * 
 */
public class JSFileInfo {

    private final String name;

    private final JSFile file;

    // TODO existing

    private String alternateClassName;

    private String alias;

    /**
     * Constructs a JSFileInfo.
     * 
     * @param name
     *            name of the definition
     * @param file
     *            the JS file including the location
     */
    public JSFileInfo(String name, JSFile file) {

        if (name == null) {
            throw new IllegalArgumentException("Cannot create JSFileInfo for name 'null'.");
        }
        if (file == null) {
            throw new IllegalArgumentException("Cannot create JSFileInfo for file 'null'.");
        }

        this.name = name;
        this.file = file;
    }

    /**
     * @return the name
     */
    public String getName() {

        return this.name;
    }

    /**
     * @return the file
     */
    public JSFile getFile() {

        return this.file;
    }

    /**
     * @return the alias
     */
    public String getAlias() {

        return this.alias;
    }

    /**
     * @param alias
     *            the alias to set
     */
    public void setAlias(String alias) {

        this.alias = alias;
    }

    /**
     * @return the alternateClassName
     */
    public String getAlternateClassName() {

        return this.alternateClassName;
    }

    /**
     * @param alternateClassName
     *            the alternateClassName to set
     */
    public void setAlternateClassName(String alternateClassName) {

        this.alternateClassName = alternateClassName;
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        result.append(this.name);

        result.append(" [");
        result.append(this.file);
        result.append("] ");

        return result.toString();
    }

}
