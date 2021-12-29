package com.tojaoomy.graphql.demo;

import com.alibaba.fastjson.JSON;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * https://github.com/graphql-dotnet/graphql-dotnet/issues/565   不能定义多个Query，使用extend
 * https://juejin.cn/post/7020216021206335524
 * https://juejin.cn/post/6844903992292540423#heading-5
 */
public class GraphQLDemo {

    /***
     * 定义Schema schema { #定义查询 query: UserQuery }
     * @return
     */
    public static GraphQLSchema createGraphqlSchema(TypeDefinitionRegistry typeRegistry, RuntimeWiring wiring) {
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, wiring);
    }


    /***
     * 读取文件内容
     *
     * @param fileName classpath:文件名称
     */
    public static String readFile(String fileName) throws IOException {
        return IOUtils.toString(GraphQLDemo.class.getClassLoader().getResourceAsStream(fileName), "utf-8");
    }


    /***
     * 定义类型的注册器
     *
     * ** @param fileContent* @return
     * */
    public static TypeDefinitionRegistry createTypeDefinitionRegistry(String fileContent) {
        SchemaParser schemaParser = new SchemaParser();
        return schemaParser.parse(fileContent);
    }


    public static RuntimeWiring createRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("RootQuery", typeWiring -> typeWiring
                        .dataFetcher("user", environment -> {
                            Long id = environment.getArgument("id");
                            return new User(id, "yushu_" + System.currentTimeMillis(), 15);
                        })
                )
                .type("RootQuery", typeWiring -> typeWiring
                        .dataFetcher("order_", environment -> {
                            String orderId = environment.getArgument("orderId");
                            return new Order(orderId, "Apple 12", new BigDecimal(22));
                        })
                )
                .type("RootQuery", typeWiring -> typeWiring
                        .dataFetcher("test", environment -> {
                            return "hello world";
                        })
                )
                .build();
    }

    private static String loadFiles() {
        StringBuilder builder = new StringBuilder();
        List<String> files = Arrays.asList("schema.graphqls","order.graphqls","user.graphqls","test.graphqls");
        files.stream().forEach(file -> {
            try {
                builder.append(FileUtils.readFileToString(new ClassPathResource(file).getFile())).append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return builder.toString();
    }

    public static void main(String[] args) throws IOException {

        String content = loadFiles();
//        创建注册器
        TypeDefinitionRegistry typeDefinitionRegistry = createTypeDefinitionRegistry(content);

//        创建resolver
        RuntimeWiring runtimeWiring = createRuntimeWiring();

//        载入Schema
        GraphQL graphQL = GraphQL.newGraphQL(createGraphqlSchema(typeDefinitionRegistry, runtimeWiring)).build();

//      使用query查询
//        String query = "{order(orderId:1){productName}}";
//      String query = "{user(id:1){age}}";
//        String query = "{test}";
        String query = "{user(id:1){age},order_(orderId:\"1234\"){productName},test}";
        System.out.println(JSON.toJSONString(query, true));
        ExecutionResult execute = graphQL.execute(query);
        System.out.println(JSON.toJSONString(execute.getData(), true));

    }

}