package org.spring.boot.msk.mobile.accessory.repository;

import org.spring.boot.msk.mobile.accessory.model.MobileAccessory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MobileAccessoryRepository extends ReactiveMongoRepository<MobileAccessory, String> {

	Mono<MobileAccessory> findByName(String name);
	
	Flux<MobileAccessory> findByMobileType(String mobileType);
	
}
