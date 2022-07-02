package com.tojaoomy.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * @author hejian
 * @date 2020/11/5
 */
@Slf4j
@Configuration
public class TestDataSourceConfig {

    @Profile("test")
    @Primary
    @Bean(name = "embeddedDataSource", destroyMethod = "shutdown")
    @ConditionalOnResource(resources = {"classpath:h2_sql/ddl.sql", "classpath:h2_sql/dml.sql"})
    public EmbeddedDatabase testBiliDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).addScript("classpath:h2_sql/ddl.sql")
                .addScript("classpath:h2_sql/dml.sql").build();
        log.info(">>> init datasource embedded");
        return db;
    }
}
