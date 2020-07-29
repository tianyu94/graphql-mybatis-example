package com.tianyu94.graphqlmybatisexample.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Configuration
public class GraphQLProvider {

    private final GraphQLDataFetchers graphQLDataFetchers;

    @Value("classpath:schema.graphqls")
    private Resource schemaResource;

    private GraphQL graphQL;

    public GraphQLProvider(GraphQLDataFetchers graphQLDataFetchers) {
        this.graphQLDataFetchers = graphQLDataFetchers;
    }

    @PostConstruct
    public void init() throws IOException {
        String sdl = StreamUtils.copyToString(schemaResource.getInputStream(), StandardCharsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("userById", graphQLDataFetchers.getUserByIdDataFetcher()))
                .type(newTypeWiring("Query")
                        .dataFetcher("usersByName", graphQLDataFetchers.getUsersByNameDataFetcher()))
                .build();
    }

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

}
