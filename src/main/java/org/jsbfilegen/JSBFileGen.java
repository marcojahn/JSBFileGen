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
        CmdLineParser.Option cmdHelpOption = cmdLineParser.addBooleanOption('h', "help");
        CmdLineParser.Option cmdProjectNameOption = cmdLineParser.addStringOption("projectname");
        CmdLineParser.Option cmdSrcPathOption = cmdLineParser.addStringOption('s', "sourcepath");
        CmdLineParser.Option cmdModuleNameOption = cmdLineParser.addStringOption("module");

        try {
            cmdLineParser.parse(args);
        } catch (IllegalOptionValueException e) {
            e.printStackTrace();
            usage();
            System.exit(1);
        } catch (UnknownOptionException e) {
            e.printStackTrace();
            usage();
            System.exit(1);
        }

        Boolean help = (Boolean) cmdLineParser.getOptionValue(cmdHelpOption);
        if (help != null && help.booleanValue()) {
            usage();
            System.exit(0);
        }

        String srcPath = (String) cmdLineParser.getOptionValue(cmdSrcPathOption);
        if (srcPath == null) {
            System.err.println("[ERROR] No path specified {" + srcPath + "}");
            usage();
            System.exit(2);
        }

        String projectName = (String) cmdLineParser.getOptionValue(cmdProjectNameOption);
        String moduleName = (String) cmdLineParser.getOptionValue(cmdModuleNameOption);

        final JSBFileGenOptions options = new JSBFileGenOptions();
        options.setProject(projectName);
        options.setSourceLocation(srcPath);
        options.setNameSpace("disoweb.apvertrieb.webclient." + moduleName);

        JSFileParser parser = new JSFileParser(options);

        List<JSFileInfo> infos = parser.parse();
        for (JSFileInfo info : infos) {
            System.out.println(info);
        }

    }

    private static void usage() {

        System.err.println("\nUsage: java -jar JSBFileGen.jar [options]\n\n" +

        "Global Options\n" + "  -h, --help                 Displays this information\n");
    }
}
