
package com.fengjx.commons.utils;

import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * 对象序列化，用于将对象实例保存
 * 
 * @author fengjx 2014-04-09
 */
public final class SerializableUtil {

    public static String serObject2String(Object object) throws UnsupportedEncodingException,
            IOException {
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        String serStr;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            serStr = byteArrayOutputStream.toString("ISO-8859-1");
            serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(objectOutputStream);
            IOUtils.closeQuietly(byteArrayOutputStream);
        }
        return serStr;
    }

    /**
     * 将字符串反序列化为对象
     * 
     * @param serStr
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static <T> T string2Object(String serStr) throws UnsupportedEncodingException,
            IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        T t = null;
        try {
            String redStr = java.net.URLDecoder.decode(serStr, "UTF-8");
            byteArrayInputStream = new ByteArrayInputStream(redStr.getBytes("ISO-8859-1"));
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            t = (T) objectInputStream.readObject();
        } catch (UnsupportedEncodingException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } catch (ClassNotFoundException e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(objectInputStream);
            IOUtils.closeQuietly(byteArrayInputStream);
        }
        return t;
    }

}
