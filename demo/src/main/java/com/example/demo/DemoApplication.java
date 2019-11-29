package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.coxautodev.graphql.tools.SchemaParser;
import com.example.demo.resolvers.Mutation;
import com.example.demo.resolvers.Query;
import com.example.demo.resolvers.TalkResolver;
import com.example.demo.services.AttendeeService;
import com.example.demo.services.SpeakerService;
import com.example.demo.services.TalkService;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLHttpServlet;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Autowired
	private TalkService talkService;
	
	@Autowired
	private AttendeeService attendeeService;
	
	@Autowired
	private SpeakerService speakerService;
	
	@Bean
	public ServletRegistrationBean graphQLServlet() {
		return new ServletRegistrationBean(SimpleGraphQLHttpServlet.newBuilder(buildSchema(talkService, attendeeService, speakerService)).build(),"/graphql");
	}
	
	
	public static GraphQLSchema buildSchema(TalkService talkService, AttendeeService attendeeService, SpeakerService speakerService) {
			return SchemaParser
					.newParser()
				.file("graphql/schema.graphqls")
					.resolvers(new Query(talkService, attendeeService, speakerService),
							new TalkResolver(speakerService),
							new Mutation(speakerService))
					.build()
					.makeExecutableSchema();
	}

}
