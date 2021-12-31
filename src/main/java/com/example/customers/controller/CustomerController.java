package com.example.customers.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customers.dto.CustomerDto;
import com.example.customers.dto.ResponseDto;
import com.example.customers.service.CustomerService;

@RestController
@RequestMapping(value="/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping()
	public ResponseDto<List<CustomerDto>> findAll(){
		return customerService.findAll();
	}
	
	@PostMapping()
	public ResponseDto<List<CustomerDto>> addCustomer(@RequestBody CustomerDto dto){
		return customerService.addCustomer(dto);
	}
	
	@PutMapping()
	public ResponseDto<List<CustomerDto>> updateCustomer(@RequestBody CustomerDto dto) {
		return customerService.updateCustomer(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseDto<List<CustomerDto>> deleteCustomer(@PathVariable UUID id){
		return customerService.deleteCustomer(id);
	}
}
