package org.spring.boot.msk.mobile.intigration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.spring.boot.msk.mobile.dto.SaveMobileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
public class MobileServiceIntegrationTest {

	
	@Autowired
	MockMvc mvc;
	
	//@BeforeClass
	
	
	@Test
	public void testGetMobileId() throws Exception {		
		mvc.perform( 
				get("/mobiles/1")
                // .header(name, values)
				.accept(MediaType.APPLICATION_JSON)
				)
		    .andExpect(status().isOk())
		    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		    .andExpect(jsonPath("$.response.id").value("1"))
		    .andExpect(jsonPath("$.response.name").value("Samsung"));		 
	}
	
	@Test
	public void testGetMobileId2() throws Exception {
		
		mvc.
			perform(get("/mobiles/2"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.response.id").value(2))
			.andExpect(jsonPath("$.response.name").value("Samsung"))
			.andExpect(jsonPath("$.response.lob").value("RETAIL"))
			.andExpect(jsonPath("$.response.status").value("NOTAVAILABLE"));
		
	}
	
	@Test
	public void testGetAllMobiles() throws Exception {
		
		mvc.
			perform(get("/mobiles"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.response").isArray())
			.andExpect(jsonPath("$.response" ,  hasSize(15)) )
			.andExpect(jsonPath("$.response[0].id").value(1) )
			.andExpect(jsonPath("$.response[0].lob").value("ONLINE"))
			.andExpect(jsonPath("$.response[0].name").value("Samsung"))
			.andExpect(jsonPath("$.response[0].status").value("AVAILABLE"))
					.andExpect(jsonPath("$.response[1].id").value(2))
					.andExpect(jsonPath("$.response[1].name").value("Samsung"))
					.andExpect(jsonPath("$.response[1].lob").value("RETAIL"))
					.andExpect(jsonPath("$.response[1].status").value("NOTAVAILABLE"));
		
	}

	
	@Test
	public void testPostToSaveMobileInfoInDB() throws Exception {
		
		SaveMobileRequest request = new SaveMobileRequest();
		request.setName("Apple");
		request.setLob("ONLINE");
		request.setStatus("AVAILABLE");
		request.setCountryCode("USA");
		request.setPrice(100000);
		
		String json = new ObjectMapper().writeValueAsString(request);
		
		mvc.perform(post("/mobiles")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.response.id").value(15));
	}
	
	
	
}
