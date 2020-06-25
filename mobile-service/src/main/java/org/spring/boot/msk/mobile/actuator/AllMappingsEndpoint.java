package org.spring.boot.msk.mobile.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;



@Component
@WebEndpoint(enableByDefault = true, id = "all-mappings")
public class AllMappingsEndpoint {

	
	@Autowired
	ApplicationContext context;
	
	@ReadOperation
	public void test() {
	
		
		String[] names = context.getBeanDefinitionNames();
		
		RequestMappingHandlerMapping mapping = context.getBean( RequestMappingHandlerMapping.class);
		
		mapping.getHandlerMethods().forEach((req, method) ->{
			//req.get
			System.out.println(" req "+req);
			System.out.println(" method "+method);
		});
		
	}
	
	
}
