package com.carole.secure.framework.service.impl;

import java.io.InputStream;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.carole.secure.framework.service.CosService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.URLUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author CaroLe
 * @Date 2023/7/23 22:55
 * @Description
 */
@Slf4j
@Service
public class CosServiceImpl implements CosService {

    @Value("${tencentCloud.secretId}")
    private String secretId;

    @Value("${tencentCloud.secretKey}")
    private String secretKey;

    @Value("${tencentCloud.bucketName}")
    private String bucketName;

    @Value("${tencentCloud.cosRegion}")
    private String cosRegion;

    /**
     * 上传到腾讯云cos
     *
     * @param file 文件
     * @param directory 目录
     * @return cos路径
     */
    @Override
    public String uploadCosFile(MultipartFile file, String directory) {
        COSClient cosClient = createCosClient();
        String fileName = directory + "/" + new DateTime().toString("yyyy-MM-dd") + "/" + IdUtil.simpleUUID()
            + file.getOriginalFilename();
        try {
            InputStream inputStream = file.getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata);
            putObjectRequest.setStorageClass(StorageClass.Standard_IA);
            cosClient.putObject(putObjectRequest);
        } catch (Exception e) {
            log.error("文件上传失败", e);
        }
        cosClient.shutdown();
        return "https://" + bucketName + ".cos." + cosRegion + ".myqcloud.com" + "/" + fileName;
    }

    /**
     * 删除文件cos
     *
     * @param path 文件路径
     */
    @Override
    public void deleteCosFile(String path) {
        COSClient cosClient = createCosClient();
        try {
            cosClient.deleteObject(bucketName, URLUtil.getPath(path));
        } catch (Exception e) {
            log.error("文件删除失败", e);
        } finally {
            cosClient.shutdown();
        }
    }

    private COSClient createCosClient() {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setRegion(new Region(cosRegion));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        clientConfig.setSocketTimeout(30 * 1000);
        clientConfig.setConnectionTimeout(30 * 1000);
        return new COSClient(cred, clientConfig);
    }

}
