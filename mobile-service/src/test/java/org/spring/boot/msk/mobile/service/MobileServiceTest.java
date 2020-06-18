package org.spring.boot.msk.mobile.service;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.spring.boot.msk.mobile.exception.MobileNotFoundException;
import org.spring.boot.msk.mobile.model.LOB;
import org.spring.boot.msk.mobile.model.Mobile;
import org.spring.boot.msk.mobile.model.Status;
import org.spring.boot.msk.mobile.repository.MobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.spring.boot.msk.common.dto.MobileDto;

@SpringBootTest(classes = MobileService.class)
public class MobileServiceTest {

	
	@MockBean
	private MobileRepository mobileRepository;
	
	@Autowired
	private MobileService mobileService;
	
	@Test
	public void testGetMobileByIdWhenDBReturnValidData() {
	
	// Mocking Data - Starts
	Optional<Mobile> dbData = Optional.of(Mobile.builder()
												.id(1)
												.name("MI")
												.status(Status.AVAILABLE)
												.lob(LOB.ONLINE)
												.publicationDate(LocalDate.now())
												.price(100)
												.build());
	when(mobileRepository.findById(1)).thenReturn(dbData);
	// Mocking Data - End	
		
	MobileDto mobileDto = mobileService.getMobileById(1);
		
	Assertions.assertEquals(1, mobileDto.getId());
	Assertions.assertEquals("MI", mobileDto.getName());
		
		
	}
	
	
	

	@Test
	public void testGetMobileByIdWhenDBReturnNull() {
		when(mobileRepository.findById(1)).thenReturn(Optional.empty());
		
		MobileNotFoundException exception = Assertions.assertThrows(MobileNotFoundException.class, 
				
													() -> mobileService.getMobileById(1)
				
													);
		
		Assertions.assertEquals(1001, exception.getErrorCode());
		
		Assertions.assertEquals("Mobile Not Found with given Mobile Id 1", exception.getMessage());
		
		
	}
	
	
}
