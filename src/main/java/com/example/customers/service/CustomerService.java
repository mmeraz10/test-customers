package com.example.customers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.customers.dto.CustomerDto;
import com.example.customers.dto.ResponseDto;
import com.example.customers.entities.Customer;
import com.example.customers.util.CustomerUtil;

@Service
public class CustomerService {
	
	private List<Customer> customers = new ArrayList<>();
	private String message = "";
	
	public ResponseDto<List<CustomerDto>> findAll() {
		try {
			
			List<CustomerDto> listDto = CustomerUtil.entityToDtoList(customers);
			message = customers.size() > 0 ? "Se encontraron clientes." : "Todavía no hay clientes registrados.";
			return new ResponseDto<>(true, 200, message, listDto);
	
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseDto<>(false, 500, "Ocurrio un error.");
		}
	}
	
	public ResponseDto<List<CustomerDto>> addCustomer(CustomerDto dto) {
		try {
			Customer newCustomer = CustomerUtil.dtoToEntity(dto);
			newCustomer.setId(UUID.randomUUID());
			Boolean resp = customers.add(newCustomer);
			message = resp ? "Se registro el cliente exitosamente." : "No se pudo insertar el cliente, favor de intentar de nuevo."; 
			return new ResponseDto<>(true,200, message, CustomerUtil.entityToDtoList(customers));
	
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseDto<>(false, 500, "Ocurrio un error.");
		}
	}
	
	public ResponseDto<List<CustomerDto>> updateCustomer(CustomerDto dto) {
		try {

			Integer index = IntStream.range(0, customers.size())
									 .filter(i -> customers.get(i).getId().equals(dto.getId()))
									 .findFirst().orElse(-1);
			
			if(!index.equals(-1))
			customers.set(index, CustomerUtil.dtoToEntityUpdate(dto));
			
			message = index.equals(-1) ? "No se encontro el cliente, favor de validar." : "Se edito con exito el cliente."; 
			return new ResponseDto<>(true,200, message, CustomerUtil.entityToDtoList(customers));
	
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseDto<>(false, 500, "Ocurrio un error.");
		}
	} 

	
	public ResponseDto<List<CustomerDto>> deleteCustomer(UUID id) {
		try {
			
			Boolean resp = customers.removeIf(c -> c.getId().equals(id));
			message = resp ? "Se elimino el cliente exitosamente." : "No se encontro el cliente para eliminar.";
			return new ResponseDto<>(true, 200, message, CustomerUtil.entityToDtoList(customers));
	
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseDto<>(false, 500, "Ocurrio un error.");
		}
	} 
}
