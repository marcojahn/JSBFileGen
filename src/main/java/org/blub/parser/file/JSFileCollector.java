/*
 * (C)opyright 2012 ssb Software Service und Beratung GmbH
 */
package org.blub.parser.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.blub.JSBFileGenOptions;

/**
 * 
 * 
 * @author Nicolas Moser <nicolas.moser@prodyna.de>
 * 
 */
public class JSFileCollector {

    public List<JSFile> collectFiles(JSBFileGenOptions options) {

        File root = new File(options.getSourceLocation());

        List<JSFile> files = this.findJsFiles(root);

        return files;
    }

    private List<JSFile> findJsFiles(File root) {

        List<JSFile> jsFiles = new ArrayList<JSFile>();

        for (File dir : root.listFiles(new DirectoryFilter())) {
            jsFiles.addAll(this.findJsFiles(dir));
        }

        for (File jsFile : root.listFiles(new JSFileFilter())) {
            jsFiles.add(new JSFile(jsFile));
        }

        return jsFiles;
    }
}
