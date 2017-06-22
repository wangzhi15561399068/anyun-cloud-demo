package test.com.anyun.cloud.management.web;

import com.anyun.cloud.management.web.annotation.ThymesController;
import com.anyun.cloud.management.web.controller.ThymeleafController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 22/06/2017
 */
@ThymesController(mapping = "/test")
public class TestThymeleafController implements ThymeleafController {
    private static final String TEMPLATE_TEST = "/test";

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return TEMPLATE_TEST;
    }
}
