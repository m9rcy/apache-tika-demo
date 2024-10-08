package org.apache.tika.mime.apache_tika_demo;

import com.example.apache_tika_demo.TikaAnalysis;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import static org.hamcrest.CoreMatchers.containsString;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TikaAnalysisTest {

    @Test
    public void whenUsingDetector_thenDocumentTypeIsReturned()
            throws IOException {
        InputStream stream = this.getClass().getClassLoader()
                .getResourceAsStream("tika.txt");
        String mediaType = TikaAnalysis.detectDocTypeUsingDetector(stream);

        Assertions.assertEquals("application/pdf", mediaType);

        stream.close();
    }

    @Test
    public void whenUsingParser_thenContentIsReturned()
            throws IOException, TikaException, SAXException {
        InputStream stream = this.getClass().getClassLoader()
                .getResourceAsStream("tika.docx");
        String content = TikaAnalysis.extractContentUsingFacade(stream);
        System.out.println(content);

        MatcherAssert.assertThat(content,
                CoreMatchers.containsString("Apache Tika - a content analysis toolkit"));
        MatcherAssert.assertThat(content,
                CoreMatchers.containsString("detects and extracts metadata and text"));

        stream.close();
    }

    @Test
    public void whenUsingParser_thenMetadataIsReturned()
            throws IOException, TikaException, SAXException {
        InputStream stream = this.getClass().getClassLoader()
                .getResourceAsStream("tika.xlsx");
        Metadata metadata = TikaAnalysis.extractMetadatatUsingFacade(stream);

        Assertions.assertEquals("org.apache.tika.parser.DefaultParser",
                metadata.get("X-Parsed-By"));
        Assertions.assertEquals("Microsoft Office User", metadata.get("Author"));

        stream.close();
    }

}