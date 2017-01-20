package com.wp.util;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;
import org.json.JSONException;

import java.io.InputStream;


/**
 * Created by 王萍 on 2017/1/20 0020.
 * 七牛云存储服务
 */

public class FileUtils {
    private static final String ACCESS_KEY = "Wgj7teqc3NPzaaoM-rVSBYWZahS0A7e29aDTy5xg";//这里填上面我们讲到的，密钥管理里面的密钥
    private static final String SECRET_KEY = "mb6VlL41Oy3ryEEUsva4fF4VVU8rNNDIGKG-33Sz";
    private static final String BUCKET_NAME = "photos";//填我们在七牛云创建的 Bucket 名字

    /**
     * 上传图片到七牛云存储
     * @param reader
     * @param fileName
     */
    public static void upload(InputStream reader, String fileName) {
        String uptoken;
        try {
            Mac mac = new Mac(ACCESS_KEY, SECRET_KEY);
            PutPolicy putPolicy = new PutPolicy(BUCKET_NAME);
            uptoken = putPolicy.token(mac);
            IoApi.Put(uptoken, fileName, reader, null);
        } catch (AuthException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除七牛云存储上的图片
     * @param key
     */
    public static void delete( String key) {
        Mac mac = new Mac(ACCESS_KEY, SECRET_KEY);
        RSClient client = new RSClient(mac);
        client.delete(BUCKET_NAME, key);
    }
}