/*
 * (C)opyright 2012 ssb Software Service und Beratung GmbH
 */
package org.blub.parser.file;

import java.io.File;
import java.io.FileFilter;

/**
 * 
 * 
 * @author Nicolas Moser <nicolas.moser@prodyna.de>
 * 
 */
public class DirectoryFilter
        implements FileFilter {

    @Override
    public boolean accept(File pathname) {

        if (pathname.isDirectory()) {
            return true;
        }

        return false;
    }

}
