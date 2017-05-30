package com.anyun.cloud.demo.api.management.http.callback;

import com.anyun.cloud.demo.api.management.core.service.ApiManagementService;
import com.anyun.cloud.demo.api.management.raml.RamlApiRamlParser;
import com.anyun.common.lang.FileUtil;
import com.anyun.common.lang.RandomUtils;
import com.anyun.common.lang.Resources;
import com.anyun.common.lang.StringUtils;
import com.anyun.common.lang.bean.InjectorsBuilder;
import com.anyun.common.lang.http.ApiCallback;
import com.anyun.common.lang.http.ApiServer;
import com.anyun.common.lang.http.entity.BaseResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 29/05/2017
 */
public class ApiDeployCallback implements ApiCallback {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiDeployCallback.class);
    private static final String RES_CLASSPATH = "classpath:";
    private static final String RES_FILE = "file:";
    private ApiManagementService apiManagementService;
    private RamlApiRamlParser ramlApiRamlParser;

    public ApiDeployCallback() {
        apiManagementService = InjectorsBuilder.getBuilder().getInstanceByType(ApiManagementService.class);
        ramlApiRamlParser = InjectorsBuilder.getBuilder().getInstanceByType(RamlApiRamlParser.class);
    }

    @Override
    public BaseResponseEntity callback(HttpServletRequest request) throws Exception {
        String uploadDir = getServerUploadDirectory();
        FileUtil.mkdir(uploadDir, false);
        String expression = "yyyyMMdd-HHmmss";
        String filePrefix = "/" + StringUtils.formatDate(new Date(), expression) + "-";
        String randomFileName = RandomUtils.generateByhashId(4);
        String fileName = uploadDir + filePrefix + randomFileName;
        LOGGER.debug("Upload file [{}]", fileName);
        FileUtil.write(fileName, request.getInputStream(), false);
//        String raml = RequestUtil.getPostBody(request);
//        LOGGER.debug("Deploy RAML body [\n{}\n]", raml);
//        DefaultResponseEntity responseEntity = new DefaultResponseEntity();
//        Api api = null;
//        try {
//            api = ramlApiRamlParser.buildV10Api();
//            if (api == null)
//                throw new Exception("Bad RAML format");
//        } catch (Exception ex) {
//            responseEntity.setCode(500);
//            responseEntity.setMessage(ex.getMessage());
//        }
//        return responseEntity;
        return null;
    }

    private String getServerUploadDirectory() throws Exception {
        String uploadDirConfig = InjectorsBuilder.getBuilder().getInstanceByType(ApiServer.class).getConfig().getUploadDir();
        LOGGER.debug("upload config dir [{}]: ", uploadDirConfig);
        String uploadDir = null;
        if (uploadDirConfig.startsWith(RES_CLASSPATH)) {
            String tmp = uploadDirConfig.substring(RES_CLASSPATH.length());
            if (!tmp.startsWith("./") && !tmp.startsWith("/"))
                tmp = "./" + tmp;
            uploadDir = Resources.getResourceURL("/").getFile() + tmp;
            LOGGER.debug("Server upload classpath [{}]", tmp);
        } else if (uploadDirConfig.startsWith(RES_FILE)) {
            uploadDir = uploadDirConfig.substring(RES_FILE.length());
        } else {
            throw new Exception("Unsupported upload file path type ["
                    + uploadDirConfig.substring(0, uploadDir.indexOf(':')) + "]");
        }
        LOGGER.debug("upload dir [{}]: ", uploadDir);
        return uploadDir;
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.PUT;
    }
}
