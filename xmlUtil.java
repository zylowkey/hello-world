package com.goldcard.services.util;

import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;





/**
 * ----------------------------------------------------------------
 * Copyright (C) 2010 浙江金卡股份有限公司
 * 版权所有。 
 * 
 * 文件名：JAXBUtil.java
 * 文件功能描述：TODO
 * 
 * 
 * 创建标识 1933 2016年12月11日
 * 
 * 修改标识：
 * 修改描述：
 * 
 * 修改标识：
 * 修改描述：
 * ----------------------------------------------------------------
*/
public class JAXBUtil {

    /**
     * 将对象转换为XML
     * @param object
     * @param beanClass
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static String objectToXmlStr(Object object, Class beanClass) throws Exception {
        JAXBContext context = JAXBContext.newInstance(beanClass);
        // 根据上下文获取marshaller对象
        Marshaller marshaller = context.createMarshaller();
        // 设置编码字符集
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        // 格式化XML输出,有分行和缩进
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //是否省略XML头声明信息
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        
        // 打印到控制台
        marshaller.marshal(object, System.out);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Result result = new StreamResult(out);
        marshaller.marshal(object, result);
        //转码
        byte[] bystr = out.toByteArray();
        String str = "";
        str = new String(bystr,"UTF-8");
        String xmlObj = str;
        //String xmlObj = new String(baos.toByteArray());
        return xmlObj.replace(" standalone=\"yes\"", "");
    }

    /**
     * 将对象转换为XML并写入文件
     * @param object
     * @param beanClass
     * @param file
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public void objectToXmlStr(Object object, Class beanClass, File file) throws Exception {
        JAXBContext context = JAXBContext.newInstance(beanClass);
        // 根据上下文获取marshaller对象
        Marshaller marshaller = context.createMarshaller();
        // 设置编码字符集
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        // 格式化XML输出,有分行和缩进
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // 打印到控制台
        marshaller.marshal(object, System.out);
        marshaller.marshal(object, file);
    }

    /**
     * XML转换为对象
     * @param file
     * @param beanClass
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T xmlStrToObject(File file, Class<T> beanClass) throws Exception {
        T bean = beanClass.newInstance();
        JAXBContext context = JAXBContext.newInstance(beanClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        bean = (T) unmarshaller.unmarshal(file);
        return bean;
    }
}
