/*
 * (C)opyright 2012 ssb Software Service und Beratung GmbH
 */
package org.jsbfilegen;

import jargs.gnu.CmdLineParser;
import jargs.gnu.CmdLineParser.IllegalOptionValueException;
import jargs.gnu.CmdLineParser.UnknownOptionException;

import java.util.List;

import org.jsbfilegen.parser.JSFileParser;

/**
 * 
 * 
 * @author Nicolas Moser <nicolas.moser@prodyna.de>
 * @author Marco Jahn <marco.jahn@prodyna.de>
 */
public class JSBFileGen {

    public static void main(String[] args) {

        CmdLineParser cmdLineParser = new CmdLineParser();

        try {
            cmdLineParser.parse(args);
            CmdLineParser.Option helpOpt = cmdLineParser.addBooleanOption('h', "help");

            Boolean help = (Boolean) cmdLineParser.getOptionValue(helpOpt);
            if (help != null && help.booleanValue()) {
                usage();
                System.exit(0);
            }

            final JSBFileGenOptions options = new JSBFileGenOptions();
            options.setProject("EPlanung");
            options.setSourceLocation("../EPlanung/src/main");
            options.setNameSpace("disoweb.apvertrieb.webclient.eplanung");

            JSFileParser parser = new JSFileParser(options);

            List<JSFileInfo> infos = parser.parse();
            for (JSFileInfo info : infos) {
                System.out.println(info);
            }

        } catch (IllegalOptionValueException e) {
            e.printStackTrace();
            usage();
            System.exit(1);
        } catch (UnknownOptionException e) {
            e.printStackTrace();
            usage();
            System.exit(1);
        }

    }

    private static void usage() {

        System.err.println("\nUsage: java -jar JSBFileGen.jar [options]\n\n" +

        "Global Options\n" + "  -h, --help                 Displays this information\n");
    }
}
