/*
 * (C)opyright 2012 ssb Software Service und Beratung GmbH
 */
package org.blub.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.blub.JSBFileGenOptions;
import org.blub.JSFileInfo;
import org.blub.parser.file.JSFile;
import org.blub.parser.file.JSFileCollector;

/**
 * 
 * 
 * @author Nicolas Moser <nicolas.moser@prodyna.de>
 * 
 */
public class JSFileParser {

    private final JSBFileGenOptions options;

    private static final String ENCODING = "UTF-8";

    private static final int DEFAULT_BUFFER_SIZE = 1024;

    private static final Charset CHARSET = Charset.forName(JSFileParser.ENCODING);

    private static final CharsetDecoder DECODER = JSFileParser.CHARSET.newDecoder();

    /**
     * Constructs a JSFileParser.
     * 
     * @param options
     *            the parser options
     */
    public JSFileParser(JSBFileGenOptions options) {

        if (options == null) {
            throw new IllegalArgumentException("Cannot create Parser for Options 'null'.");
        }

        this.options = options;
    }

    public List<JSFileInfo> parse() {

        List<JSFileInfo> infos = new ArrayList<JSFileInfo>();

        JSFileCollector collector = new JSFileCollector();
        List<JSFile> files = collector.collectFiles(this.options);

        for (JSFile file : files) {

            this.parse(file);

            infos.add(new JSFileInfo(file.getObjectName(), file));
        }

        return infos;
    }

    private void parse(JSFile file) {

        try {
            String content = this.readFile(new FileInputStream(file.getFile()));

            // TODO Remove All Comments (Line and Block)
            content = content.replaceAll("\\/\\/.*", "");
            content = content.replaceAll("\\/\\*.*\\*\\/\\/m", "");

            System.out.println(content);

            Pattern pattern = Pattern.compile("Ext\\.define\\(('|\")(.*)('|\")");
            Matcher matcher = pattern.matcher(content);

            if (matcher.find()) {
                file.setObjectName(matcher.group(2));
            } else {
                file.setObjectName("Not Found");
            }

            // Ext.define('disoweb.apvertrieb.webclient.eplanung.store.Plantafeln'

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    private String readFile(InputStream in)
        throws IOException {

        StringBuilder result = new StringBuilder();
        ReadableByteChannel channel = Channels.newChannel(in);

        try {
            ByteBuffer buffer = ByteBuffer.allocateDirect(JSFileParser.DEFAULT_BUFFER_SIZE);

            CharBuffer charBuffer = CharBuffer.allocate(JSFileParser.DEFAULT_BUFFER_SIZE);

            while (channel.read(buffer) != -1) {
                buffer.flip();

                JSFileParser.DECODER.decode(buffer, charBuffer, false);
                charBuffer.flip();

                result.append(charBuffer);

                buffer.clear();
                charBuffer.clear();
            }
        } finally {
            if (channel != null) {
                channel.close();
            }
        }

        return result.toString();
    }
}
