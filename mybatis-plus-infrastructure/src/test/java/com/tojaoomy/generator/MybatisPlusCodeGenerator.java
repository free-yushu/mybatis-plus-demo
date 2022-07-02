package com.tojaoomy.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;

/**
 * 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 */
public class MybatisPlusCodeGenerator {

    public static GeneratorDataSourceConfig createShardingUser() {
        return GeneratorDataSourceConfigFactory.createShardingUser().setTableNames(Arrays.asList(
                "t_sharding_user_0"
        ));
    }

    public static GeneratorDataSourceConfig createTradeOrder() {
        return GeneratorDataSourceConfigFactory.createTradeOrder().setTableNames(Arrays.asList(
                "t_order"
        ));
    }

    public static GeneratorDataSourceConfig createSchool() {
        return GeneratorDataSourceConfigFactory.createSchool().setTableNames(Arrays.asList(
                "teacher",
                "student"
        ));
    }

    public static void main(String[] args) {
//        dataSourceGenerator(createShardingUser());
//        dataSourceGenerator(createTradeOrder());
        dataSourceGenerator(createSchool());
    }

    private static void dataSourceGenerator(GeneratorDataSourceConfig generatorDataSourceConfig) {
        String projectPath = System.getProperty("user.dir") + "/mybatis-plus-infrastructure";
        FastAutoGenerator.create(new DataSourceConfig.Builder(
                        "jdbc:mysql://" + generatorDataSourceConfig.getDataSourceIp() + ":" + generatorDataSourceConfig.getDataSourcePort() + "/" + generatorDataSourceConfig.getDataSourceSchema() + "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true",
                        generatorDataSourceConfig.getUsername(),
                        generatorDataSourceConfig.getPassword())
                        .typeConvert(new MySqlTypeConvertCustom()))
                .globalConfig(builder -> {
                    builder.author("MybatisPlusGenerator") // 设置作者
                            .commentDate("yyyy-MM-dd")
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir()
                            .outputDir(projectPath + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.tojaoomy.demo.infra") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath + "/src/main/resources/mapper"))
                            .entity("dataobject")
                            .mapper("mapper")
                            .xml("xml");
                })
                .strategyConfig(builder -> {
                    builder.enableCapitalMode()
                            .addTablePrefix(generatorDataSourceConfig.getTablePrefixes())
                            .addTableSuffix(generatorDataSourceConfig.getTableSuffixes())
                            .addInclude(generatorDataSourceConfig.getTableNames())
                            .mapperBuilder()
                            .formatXmlFileName("%sMapper")
                            .enableBaseColumnList()
                            .enableBaseResultMap()
                            .enableMapperAnnotation()

                            .entityBuilder()
                            .formatFileName("%sDO")
                            .idType(IdType.AUTO)
                            .enableRemoveIsPrefix()
                            .enableTableFieldAnnotation()
                            .enableLombok()

                            .enableActiveRecord()
//                            .disableSerialVersionUID()

                            .logicDeleteColumnName("deleted")
                            .logicDeletePropertyName("deleted")
                            .versionColumnName("version")
                            .versionPropertyName("version")
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .enableChainModel()
                            .addTableFills(new Column("ctime", FieldFill.INSERT))
                            .addTableFills(Arrays.asList(new Column("mtime", FieldFill.INSERT_UPDATE)));

                })
                .templateConfig(builder -> {
                    builder.disable(TemplateType.CONTROLLER, TemplateType.SERVICE, TemplateType.SERVICEIMPL);
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    /**
     * 自定义类型转换
     */
    static class MySqlTypeConvertCustom extends MySqlTypeConvert implements ITypeConvert {
        @Override
        public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
            String t = fieldType.toLowerCase();
            if (t.contains("tinyint(1)")) {
                return DbColumnType.INTEGER;
            }
            return super.processTypeConvert(globalConfig, fieldType);
        }
    }
}