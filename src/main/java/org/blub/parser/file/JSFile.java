/*
 * (C)opyright 2012 ssb Software Service und Beratung GmbH
 */
package org.blub.parser.file;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 
 * @author Nicolas Moser <nicolas.moser@prodyna.de>
 * 
 */
public class JSFile {

    private final File file;

    private String objectName;

    private final Set<String> references;

    /**
     * Constructs a JSFile.
     * 
     * @param file
     *            the file
     */
    public JSFile(File file) {

        if (file == null) {
            throw new IllegalArgumentException("Cannot create JS File for File 'null'.");
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("JS File '" + file + "' does not exist.");
        }

        this.file = file;
        this.references = new HashSet<String>();
    }

    public File getFile() {

        return this.file;
    }

    public String getObjectName() {

        return this.objectName;
    }

    public void setObjectName(String objectName) {

        this.objectName = objectName;
    }

    public void addReference(String reference) {

        this.references.add(reference);
    }

    @Override
    public String toString() {

        return String.valueOf(this.file);
    }
}
