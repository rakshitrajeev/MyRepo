package com.example.demo.providers;

import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.demo.databasemodels.Attendee;
import com.example.demo.databasemodels.Speaker;
import com.example.demo.fetchers.AttendeeFetcher;
import com.example.demo.fetchers.SpeakerFetcher;
import com.example.demo.fetchers.TalkFetcher;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import graphql.GraphQL;
import graphql.TypeResolutionEnvironment;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.TypeResolver;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQlProvider {
	
	 @Autowired
	 private TalkFetcher talkFetcher;
	 
	@Autowired
	private SpeakerFetcher speakerFetcher;
	 
	@Autowired
	private AttendeeFetcher attendeeFetcher;
	
	private GraphQL graphQl;
	
	@Bean
    public GraphQL graphQL() {
        return graphQl;
    }
	
	@PostConstruct
	public void init() throws IOException{
		URL url = Resources.getResource("graphql/schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQl = GraphQL.newGraphQL(graphQLSchema).build();
	}
	
	
	private GraphQLSchema buildSchema(String sdl) {

        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }
	
	private RuntimeWiring buildWiring() {
		TypeResolver typeResolver =  new TypeResolver() {
		    @Override
		    public GraphQLObjectType getType(TypeResolutionEnvironment env) {
		        Object javaObject = env.getObject();
		        if (javaObject instanceof Speaker) {
		            return env.getSchema().getObjectType("Speaker");
		        } else if (javaObject instanceof Attendee) {
		            return env.getSchema().getObjectType("Attendee");
		        }
				return null;
		    }
		};
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("allTalks", talkFetcher.fetchAll())
                        .dataFetcher("allSpeakers", speakerFetcher.fetchAll())
                        .dataFetcher("allAttendees", attendeeFetcher.fetchAll()))
                .type(newTypeWiring("Talk")
                        .dataFetcher("speakers", speakerFetcher.fetchAllSpeakersForTalk()))
                .type("Human", typeWriting ->
                typeWriting
                        .typeResolver(typeResolver)
        )
                .build();
    }

}
