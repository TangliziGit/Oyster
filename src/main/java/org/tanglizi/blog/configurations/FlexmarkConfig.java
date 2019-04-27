package org.tanglizi.blog.configurations;

import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.toc.SimTocExtension;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class FlexmarkConfig {

    public static class FlexmarkParser{
        static MutableDataSet options = new MutableDataSet();
        static Parser parser;// = Parser.builder(options).build();
        static HtmlRenderer renderer;// = HtmlRenderer.builder(options).build();

        static {
            options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(),
                        JekyllTagExtension.create(), TocExtension.create(), SimTocExtension.create()))
                    .set(TocExtension.LEVELS, 255)
                    .set(TocExtension.TITLE, "TOC")
                    .set(TocExtension.DIV_CLASS, "toc");
            parser= Parser.builder(options).build();
            renderer= HtmlRenderer.builder(options).build();
        }

        public static String parse(String markdown){
            return renderer.render(parser.parse(markdown));
        }
    }
}
