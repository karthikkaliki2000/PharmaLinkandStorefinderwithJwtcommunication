package com.CN.PharmaLink.communicator;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import com.CN.PharmaLink.dto.MedicalStoreDto;
//import com.CN.StoreFinder.model.MedicalStore;
import com.CN.PharmaLink.model.MedicalStore;




@Service
public class StoreFinderCommunicator {

	private final RestTemplate restTemplate;

	@Autowired
	public StoreFinderCommunicator(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate=restTemplateBuilder.build();
	}

	public List<MedicalStoreDto> getNearestMedicalStores(Long userId, Long distance, String token) {
		String url ="http://localhost:8081/stores/getNearestStores/"+userId+"/"+distance;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);

		HttpEntity<Map<String, Long>> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<List<MedicalStore>> storeResponse = restTemplate.exchange(
	            url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<MedicalStore>>() {});
		List<MedicalStoreDto> dtoList=new ArrayList<MedicalStoreDto>();
		for(MedicalStore m:storeResponse.getBody()) {
			MedicalStoreDto mdto=new MedicalStoreDto();
			mdto.setArea(m.getArea());
			mdto.setContact(m.getContact());
			mdto.setId(m.getId());
			mdto.setMedicines(m.getMedicines());
			mdto.setName(m.getName());
			mdto.setXCoordinate(m.getXCoordinate());
			mdto.setYCoordinate(m.getYCoordinate());
			dtoList.add(mdto);
		}
		return dtoList;
	
		
		
	}

	public List<MedicalStoreDto> getMedicalStoresWithMedicine(String medicine, String token) {
		
		String url ="http://localhost:8081/stores/getStoresWithMedicine/"+medicine;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);

		HttpEntity<Map<String, Long>> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<List<MedicalStore>> storeResponse = restTemplate.exchange(
	            url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<MedicalStore>>() {});
		List<MedicalStoreDto> dtoList=new ArrayList<MedicalStoreDto>();
		for(MedicalStore m:storeResponse.getBody()) {
			MedicalStoreDto mdto=new MedicalStoreDto();
			mdto.setArea(m.getArea());
			mdto.setContact(m.getContact());
			mdto.setId(m.getId());
			mdto.setMedicines(m.getMedicines());
			mdto.setName(m.getName());
			mdto.setXCoordinate(m.getXCoordinate());
			mdto.setYCoordinate(m.getYCoordinate());
			dtoList.add(mdto);
		}
		return dtoList;
	}
	
	
	
}
