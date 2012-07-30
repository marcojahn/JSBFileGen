/*
 * (C)opyright 2012 ssb Software Service und Beratung GmbH
 */
package org.blub;

import java.util.List;

import org.blub.parser.JSFileParser;

/**
 * 
 * 
 * @author Nicolas Moser <nicolas.moser@prodyna.de>
 * 
 */
public class JSBFileGen {

    public static void main(String[] args) {

        final JSBFileGenOptions options = new JSBFileGenOptions();
        options.setProject("EPlanung");
        options.setSourceLocation("../EPlanung/src/main");
        options.setNameSpace("disoweb.apvertrieb.webclient.eplanung");

        JSFileParser parser = new JSFileParser(options);

        List<JSFileInfo> infos = parser.parse();
        for (JSFileInfo info : infos) {
            System.out.println(info);
        }
    }

}
