package com.crud.backendcrud.service.impl;
import com.crud.backendcrud.service.ApiOpenStreetMap;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ApiOpenStreet implements ApiOpenStreetMap{

    private final RestClient restClient;

    public ApiOpenStreet(RestClient restClient){
        this.restClient = restClient;
    }

    @Override
    public String buscarCiudad(String ciudad) {
        return restClient.get()
                .uri("https://nominatim.openstreetmap.org/search?q={ciudad}&format=json", ciudad)
                .header("User-Agent", "backendCrud")
                .retrieve()
                .body(String.class);
    }
}
