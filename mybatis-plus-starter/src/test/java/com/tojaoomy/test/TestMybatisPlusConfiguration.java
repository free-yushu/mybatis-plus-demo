package com.tojaoomy.test;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.tojaoomy.demo.infra.config.LocalDateTimeTypeHandler;
import com.tojaoomy.demo.infra.config.MyMetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.LocalDateTypeHandler;
import org.apache.ibatis.type.LocalTimeTypeHandler;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 玉书
 * @date 2021/9/2
 */
@Slf4j
@Profile("test")
@Configuration("mybatisPlusConfiguration")
@MapperScan(basePackages = {"com.tojaoomy.demo.infra.mapper"})
@PropertySource(value = {"classpath:config/shardingsphere.properties", "file:${CONF_PATH}/shardingsphere.properties"}, ignoreResourceNotFound = true)
public class TestMybatisPlusConfiguration {

    @Value("${spring.profiles.active}")
    private String env;


    @Bean(name = "paymentSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("embeddedDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        Resource[] resources = this.resolveMapperLocations();
        bean.setMapperLocations(resources);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.getTypeHandlerRegistry().register(LocalDateTime.class, new LocalDateTimeTypeHandler());
        configuration.getTypeHandlerRegistry().register(LocalDate.class, new LocalDateTypeHandler());
        configuration.getTypeHandlerRegistry().register(LocalTime.class, new LocalTimeTypeHandler());

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        configuration.addInterceptor(interceptor);

        configuration.setMapUnderscoreToCamelCase(true);
        //本地开发打印SQL
        if ("LOCAL".equalsIgnoreCase(env)) {
            configuration.setLogImpl(StdOutImpl.class);
        }
        GlobalConfig globalConfig = GlobalConfigUtils.getGlobalConfig(configuration);

        globalConfig.setMetaObjectHandler(new MyMetaObjectHandler());
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    @Bean(name = "paymentTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("embeddedDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "paymentSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("paymentSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "paymentTransactionTemplate")
    public TransactionTemplate transactionTemplate(@Qualifier("paymentTransactionManager")PlatformTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }


    public Resource[] resolveMapperLocations() {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<String> mapperLocations = new ArrayList<>();
        mapperLocations.add("classpath:mapper/*.xml");
        mapperLocations.add("classpath*:payment/mapper/**/*.xml");
        List<Resource> resources = new ArrayList<>();
        if (!CollectionUtils.isEmpty(mapperLocations)) {
            for (String mapperLocation : mapperLocations) {
                try {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException e) {
                    log.error("Get myBatis resources happened exception", e);
                }
            }
        }

        return resources.toArray(new Resource[0]);
    }
}