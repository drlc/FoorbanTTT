package it.daraloca.challenge.tttfoorban.config;

import java.util.UUID;

import org.dozer.BeanFactory;

/**
 * UUIDBeanFactory
 */
public class UUIDBeanFactory implements BeanFactory {

    @Override
    public Object createBean(Object sourceBean, Class<?> sourceClass, String targetBeanId) {
        if (sourceBean == null)
            return null;
        if (!(sourceBean instanceof UUID)) {
            throw new RuntimeException("Wrong Class");
        }
        UUID source = (UUID) sourceBean;
        UUID destination = new UUID(source.getMostSignificantBits(), source.getLeastSignificantBits());
        return destination;
    }
}