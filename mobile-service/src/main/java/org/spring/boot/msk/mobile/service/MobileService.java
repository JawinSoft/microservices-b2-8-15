package org.spring.boot.msk.mobile.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.spring.boot.msk.mobile.dto.GetAllMobileRequest;
import org.spring.boot.msk.mobile.dto.SaveMobileRequest;
import org.spring.boot.msk.mobile.exception.MobileNotFoundException;
import org.spring.boot.msk.mobile.model.LOB;
import org.spring.boot.msk.mobile.model.Mobile;
import org.spring.boot.msk.mobile.model.Status;
import org.spring.boot.msk.mobile.repository.MobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.spring.boot.msk.common.dto.MobileDto;

@Service
public class MobileService {

	@Autowired
	private MobileRepository mobileRepository;
	
	
	public List<MobileDto> getAllMobiles(GetAllMobileRequest request){
		
		//Status  enumStatus = request.getStatus() == null ? null : Status.valueOf(request.getLob());
		//LOB enumLob = request.getLob() == null ? null : LOB.valueOf(request.getLob());
		
		//Place Holder 
		//return mobileRepository.findAll(request.getName() , request.getPrice()  , request.getStatus() ,  request.getLob());
		
		
		//Named Parameters
		//return mobileRepository.findAll(request.getName() , request.getPrice()  , enumStatus ,  enumLob);
		
		//Native Query
		//Integer statusNumber = enumStatus != null ?  enumStatus.getValue() : null;
		//Integer lobNumber = eumLob != null ?  eumLob.getValue() : null;
		//return  mobileRepository.findAllByNativeQuery(request.getName() , request.getPrice()  , statusNumber, lobNumber);
		
		//Named Query 
		//return mobileRepository.findAllWithFiltersByNamedQuery(request.getName() , request.getPrice(), enumStatus, enumLob);
		
		Specification<Mobile> nameFilter = createFilter("name", request.getName(), false);
		
		Specification<Mobile> priceFilter = createFilter("price", request.getPrice(), false);
		
		Specification<Mobile> statusFilter = createFilter("status", request.getStatus(), true);
		
		Specification<Mobile> lobFilter = createFilter("lob", request.getLob(), true);
		
		
		//mobileRepository.findAll
		
		List<Mobile> dbMobiles=  mobileRepository.findAll(nameFilter.and(priceFilter).and(statusFilter).and(lobFilter),
				
			  Sort.by(Direction.DESC, "name")			
				
				);
		
		List<MobileDto> mobileDtos = dbMobiles.stream().map(mobile -> convertEntityToDto(mobile)).collect(Collectors.toList());
		
	  return mobileDtos;
		
		
	}
	
	private MobileDto convertEntityToDto(Mobile mobile) {
		return MobileDto.builder()
						.id(mobile.getId())
						.name(mobile.getName())
						.status(mobile.getStatus().name())
						.lob(mobile.getLob().name())
						.countryCode(mobile.getCountryCode())
						.publicateDate(mobile.getPublicationDate().toString())
						.price(mobile.getPrice())
						.build();
	}
	
	private Mobile convertDtoToEntity(MobileDto mobileDto) {
		return Mobile
		          .builder()
		          .name(mobileDto.getName())
		          .price(mobileDto.getPrice())
		          .status(Status.valueOf(mobileDto.getStatus()  ))
		          .lob(  LOB.valueOf( mobileDto.getLob() ) )
		          .countryCode(mobileDto.getCountryCode())
		          .build();
	}
	
	public MobileDto getMobileById(int mobileId) {
		
		Optional<Mobile>  mobile =  mobileRepository.findById(mobileId);
		if(mobile.isPresent()) 
			return convertEntityToDto(mobile.get());
			
		
		throw new MobileNotFoundException(1001, "Mobile Not Found with given Mobile Id "+mobileId);
		
	}



	public void saveMobile(SaveMobileRequest saveMobileRequest) {
		Mobile  mobile = Mobile
		          .builder()
		          .name(saveMobileRequest.getName())
		          .price(saveMobileRequest.getPrice())
		          .status(Status.valueOf(saveMobileRequest.getStatus()  ))
		          .lob(  LOB.valueOf( saveMobileRequest.getLob() ) )
		          .countryCode(saveMobileRequest.getCountryCode())
		          .build();
		mobile.setPublicationDate(LocalDate.now());
		
		mobileRepository.save(mobile);
	}



	public MobileDto updateMobile(MobileDto mobileDto) {
		Mobile mobile = convertDtoToEntity(mobileDto);
		Mobile updatedMobile =  mobileRepository.save(mobile);
		return convertEntityToDto(updatedMobile);
	}
	
	
	@SuppressWarnings("serial")
	private Specification<Mobile> createFilter(String key, Object value, boolean isEnumType){
		
		return  new Specification<Mobile>() {
			@Override
			public Predicate toPredicate(Root<Mobile> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			 if(null == value)  return null;
				
            //Status & Lob
			 if(isEnumType) {
				 if(key.equals("status")) {
				 Status status = Status.valueOf(value.toString());
				 return  criteriaBuilder.equal(root.get(key) ,status );
				 }else {
					 LOB lob = LOB.valueOf(value.toString());
					 return  criteriaBuilder.equal(root.get(key) ,lob );
				 }
			 }
			
			//Name & Any String 
			return  criteriaBuilder.equal(root.get(key) , value);
			}
		};
		
		
		
		
		
	}


}
