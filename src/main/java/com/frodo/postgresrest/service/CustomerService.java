package com.frodo.postgresrest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frodo.postgresrest.domain.Customer;
import com.frodo.postgresrest.domain.CustomerDTO;
import com.frodo.postgresrest.repository.CustomerRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerDTO> findAll() {
        //List<Customer> all = customerRepository.findAll();
        List<Customer> all = customerRepository.findAllByOrderByIdDesc();
        List<CustomerDTO> allDTOs = new ArrayList<>();
        for (Customer c : all) {
            CustomerDTO customerDTO = convertToDto(c);
            allDTOs.add(customerDTO);
        }
        //return all;
        return allDTOs;
    }

    public CustomerDTO findById(Long id) {
        Optional<Customer> one = customerRepository.findById(id);
        CustomerDTO customerDTO = convertToDto(one.get());
        //return one.get();
        return customerDTO;
    }

    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        Customer created = customerRepository.save(customer);
        return convertToDto(created);
        //return created;
    }

    public CustomerDTO delete(Long id) {
        Optional<Customer> customerToDelete = customerRepository
                .findById(id);

        customerRepository.delete(customerToDelete.get());
        return convertToDto(customerToDelete.get());
    }

    public void deleteAll() {
        List<Customer> customersToDelete = customerRepository
                .findAll();

        for (Customer c : customersToDelete) {
            customerRepository.delete(c);
        }
    }

    private CustomerDTO convertToDto(Customer customer) {
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        String jsonAsString = customer.getInfo();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Object o = objectMapper.readValue(jsonAsString, Object.class);
            customerDTO.setInfo(o);
        } catch (IOException e) {
            System.out.println("ERROR PARSING JSON STRING");
        }

        return customerDTO;
    }

    private Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Object info = customerDTO.getInfo();
        String jsonString = new JSONObject((LinkedHashMap) info).toString();
        customer.setInfo(jsonString);
        return customer;

    }
}
