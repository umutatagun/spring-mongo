package com.example.eshop.service;

import com.example.eshop.exception.AddressAlreadyExistsException;
import com.example.eshop.exception.AddressNotFoundException;
import com.example.eshop.model.Address;
import com.example.eshop.model.dto.AddressDto;
import com.example.eshop.repository.AddressRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {
    private final AddressRepo addressRepo;

    public List<AddressDto> getAllAddresses() {
        List<AddressDto> addressDtos = List.of();
        addressRepo.findAll().forEach(
                address -> {
                    addressDtos.add(addressToAddressDto(address));
                    Integer inte = addressDtos.size();
                }
        );
        return addressDtos;
    }

    public AddressDto getAddressById(String id){
        return addressToAddressDto(findAddressById(id));
    }

    public AddressDto addAddress(Address address) {
        if(addressRepo.findById(address.getAddressId()).isPresent()){
            throw new AddressAlreadyExistsException("Address already exists");
        }
        addressRepo.save(address);
        return addressToAddressDto(address);
    }

    public AddressDto updateAddress(String id, Address address) {
        Address a1 = findAddressById(id);
        a1.setAddress1(address.getAddress1());
        a1.setAddress2(address.getAddress2());
        a1.setCity(address.getCity());
        a1.setPostCode(address.getPostCode());
        a1.setState(address.getState());
        a1.setCustomerId(address.getCustomerId());
        a1.setLastModifiedBy(address.getLastModifiedBy());
        a1.setLastModifiedDate(new Date());
        addressRepo.save(a1);

        return addressToAddressDto(a1);
    }

    public void deleteAddress(String id) {
        Address address = findAddressById(id);
        addressRepo.delete(address);
    }

    public void deleteCustomersAllAddresses(String customerId) {
        addressRepo.deleteAllByCustomerId(customerId);
    }


    public Address findAddressById(String id) {
        return addressRepo.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address not found with id "+id));
    }

    public AddressDto addressToAddressDto(Address address){
        AddressDto addressDto = new AddressDto();
        addressDto.setAddressId(address.getAddressId());
        addressDto.setAddress1(address.getAddress1());
        addressDto.setAddress2(address.getAddress2());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setPostCode(address.getPostCode());
        addressDto.setLastModifiedDate(new Date());
        addressDto.setLastModifiedby(address.getLastModifiedBy());

        return addressDto;
    }
}
