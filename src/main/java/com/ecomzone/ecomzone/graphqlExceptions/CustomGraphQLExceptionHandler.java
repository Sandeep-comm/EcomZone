package com.ecomzone.ecomzone.graphqlExceptions;

import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;

@Component
public class CustomGraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter{
	
	@Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
		
		if(ex instanceof CustomGraphqlException)
		{
			
			if (ex instanceof CustomGraphqlException) {
	            ErrorTypes errorType;
	            if(((CustomGraphqlException) ex).getStatusCode()==400){
	                errorType = ErrorTypes.BAD_REQUEST;
	                return graphQLError(errorType, (CustomGraphqlException) ex, env);
	            }
	            if(((CustomGraphqlException) ex).getStatusCode()==404){
	                errorType = ErrorTypes.NOT_FOUND;
	                return graphQLError(errorType, (CustomGraphqlException) ex, env);
	            }
				
		}
			
			
		return null;
		}
		return null;
	}
	private GraphQLError graphQLError(ErrorTypes errorType, CustomGraphqlException ex,DataFetchingEnvironment env){
        return GraphqlErrorBuilder.newError()
                .errorType(errorType)
                .message(ex.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }
	
	

}
