/*
 * (C)opyright 2012 ssb Software Service und Beratung GmbH
 */
package org.blub.parser.file;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 
 * 
 * @author Nicolas Moser <nicolas.moser@prodyna.de>
 * 
 */
public class JSFileFilter
        implements FilenameFilter {

    private static final String FILE_SUFFIX = ".js";

    @Override
    public boolean accept(File dir, String name) {

        if (name.toLowerCase().endsWith(JSFileFilter.FILE_SUFFIX)) {
            return true;
        }

        return false;
    }

}
