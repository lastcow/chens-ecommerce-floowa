package me.chen.floowa.service;

import me.chen.floowa.model.Country;
import me.chen.floowa.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public Country save(String code, String name){
        Country country = new Country();
        country.setCode(code);
        country.setName(name);

        return countryRepository.save(country);
    }
    public Country save(Country country){
        return countryRepository.save(country);
    }
    public List<Country> getCountries(){return countryRepository.findAll();}
}
