package com.tojaoomy.demo.infra.graphql;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

/**
 * @author 玉书
 * @date 2021/12/29
 */
//@Component
public class ManualGraphQLProvider {

    private GraphQL graphQL;

    @Autowired
    GraphQLDataFetchers graphQLDataFetchers;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        loadSchema();
        /*GraphQLSchema graphQLSchema = SchemaParser.newParser()
                .file("graphql/base.graphqls")
                .resolvers(new Query(), new Mutation())
                .file("graphql/item.graphqls")
                .resolvers(new ItemResolver(itemService))
//            .file("book.graphqls")
//            .resolvers(new BookResolver())  //其它定义照上面的示例，继续增加
                .build().makeExecutableSchema();

        this.graphQL = graphQL.newGraphQL(graphQLSchema).build();*/
    }

    private void loadSchema() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        // 构建schema
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    // 通过从配置文件里加载的schema来构建GraphQLSchema
    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                // 指定Query对象里定义的方法如何实现
                .type(newTypeWiring("Query")
                        .dataFetcher("bookById", graphQLDataFetchers.getBookByIdDataFetcher()))
                // 指定Book对象里定义的对象如何查询
                .type(newTypeWiring("Book")
                        .dataFetcher("author", graphQLDataFetchers.getAuthorDataFetcher())
                        .dataFetcher("category", graphQLDataFetchers.getCategoryDataFetcher()))
                .build();
    }
}