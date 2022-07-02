package com.tojaoomy.generator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 玉书
 * @date 2021/12/20
 */
@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class GeneratorDataSourceConfig {

    private String dataSourceSchema;

    private String dataSourceIp;

    private int dataSourcePort;

    private String username;

    private String password;

    private String[] tablePrefixes = new String[0];

    private String[] tableSuffixes = new String[0];

    private List<String> tableNames;

}