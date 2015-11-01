package com.hong.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by Hongwei on 2015/11/1.
 */
public class PeopleNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("people", new PeopleBeanDefinitionParser());
    }
}
