package com.tojaoomy.demo.domain;

import org.mapstruct.MapperConfig;

/**
 * @author 玉书
 * @date 2021/2/7
 */
@MapperConfig
public interface BaseMapping<DO, DOMAIN> {

    /**
     * dataObject到domain
     * @param dataObject
     * @return
     */
    DOMAIN toDomain(DO dataObject);

    /**
     * domain到dataObject
     * @param domain
     * @return
     */
    DO toDataObject(DOMAIN domain);
}