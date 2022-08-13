package com.ace.multidatasource.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.util.StringUtils;

import com.google.common.base.CaseFormat;


/**
 * 
 * @author Jacky Leung
 * 
 * Prefixes the bean's name with the specified prefix. This is needed to autowire two 
 * sets of mapper classes, one for local database and one for remote database. 
 * 
 *
 */
public class _MapperNameGenerator implements BeanNameGenerator {

    private String prefix;

    /**
     * Prefix should be in lower camel case.
     * @param prefix
     */
    public _MapperNameGenerator(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        String[] nameList = definition.getBeanClassName().split("\\.");
        String name = nameList[nameList.length - 1];

        if (StringUtils.isEmpty(this.prefix)) {
            return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, name);
        } else {
            return this.prefix + name;
        }
    }
}
