package com.tojaoomy.demo.infra.graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import com.tojaoomy.demo.infra.repository.IItemService;
import com.tojaoomy.demo.infra.repository.ItemResolver;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 *
 * @author 玉书
 * @date 2021/12/29
 */
@Component
public class AutoGraphQLProvider {

    private GraphQL graphQL;

    @Autowired
    private IItemService itemService;

    @Autowired
    GraphQLDataFetchers graphQLDataFetchers;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        GraphQLSchema graphQLSchema = SchemaParser.newParser()
                .file("graphql/base.graphqls")
                .file("graphql/item.graphqls")
                .resolvers(new ItemResolver(itemService))
                .build().makeExecutableSchema();

        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

}