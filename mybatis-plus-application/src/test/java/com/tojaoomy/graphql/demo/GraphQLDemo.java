package com.tojaoomy.graphql.demo;

import com.alibaba.fastjson.JSON;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.math.BigDecimal;

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
                .type("ComposeQuery", typeWiring -> typeWiring
                        .dataFetcher("user", environment -> {
                            Long id = environment.getArgument("id");
                            return new User(id, "yushu_" + System.currentTimeMillis(), 15);
                        })
                )
                .type("ComposeQuery", typeWiring -> typeWiring
                        .dataFetcher("order", environment -> {
                            String orderId = environment.getArgument("orderId");
                            return new Order(orderId, "Apple 12", new BigDecimal(22));
                        })
                )
                .build();
    }

    public static void main(String[] args) throws IOException {

//        读取Schema文件
        String fileName = "order.graphqls";
        String content = readFile(fileName);

//        创建注册器
        TypeDefinitionRegistry typeDefinitionRegistry = createTypeDefinitionRegistry(content);

//        创建resolver
        RuntimeWiring runtimeWiring = createRuntimeWiring();

//        载入Schema
        GraphQL graphQL = GraphQL.newGraphQL(createGraphqlSchema(typeDefinitionRegistry, runtimeWiring)).build();

//      使用query查询
//        String query = "{order(orderId:1){productName}}";
//      String query = "{user(id:1){age}}";
      String query = "{user(id:1){age},order(orderId:\"1234\"){productName}}";
        System.out.println(JSON.toJSONString(query, true));
        ExecutionResult execute = graphQL.execute(query);
        System.out.println(JSON.toJSONString(execute.getData(), true));

    }

}