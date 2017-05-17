package test.com.anyun.cloud.demo.api.node;

import org.junit.Test;
import org.raml.v2.api.RamlModelBuilder;
import org.raml.v2.api.RamlModelResult;
import org.raml.v2.api.model.v10.api.Api;
import org.raml.v2.api.model.v10.bodies.MimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/5/16
 */
public class RamlReaderTest extends BaseApiNodeTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RamlReaderTest.class);

    @Test
    public void testRAMLReader() throws Exception {
        URL url = RamlReaderTest.class.getClassLoader().getResource("./data/example1.raml");
        System.out.println(url);
        File file = new File(url.toURI());
        LOGGER.debug("Example RAML file : {}", file.getAbsoluteFile());
        RamlModelResult ramlModelResult = new RamlModelBuilder().buildApi(file);
        Api api = ramlModelResult.getApiV10();
        String title = api.title().value();
        LOGGER.debug("Title [{}]", title);
        String description = api.description().value();
        LOGGER.debug("Description markdown string [{}]", description);
        List<MimeType> mimeTypes = api.mediaType();
        if (LOGGER.isDebugEnabled()) {
            StringBuilder sb = new StringBuilder();
            for (MimeType type : mimeTypes) {
                sb.append(",").append(type.value());
            }
            LOGGER.debug("Media Type [{}]", sb.toString().substring(1));
        }
    }
}
