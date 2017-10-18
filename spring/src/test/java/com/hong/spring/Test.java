package com.hong.spring;

import com.hong.spring.bean.MyTest;
import static org.junit.Assert.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by caihongwei on 27/09/2017 2:34 PM.
 */
public class Test {
    @org.junit.Test
    public void test() {
        BeanFactory bf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader((DefaultListableBeanFactory) bf);
        Resource resource = new ClassPathResource("META-INF/spring/springtest.xml");
        xmlBeanDefinitionReader.createReaderContext(resource);
        MyTest myTest = (MyTest) bf.getBean("myTest");
        assertEquals("test str", myTest.getTest());
    }
}
