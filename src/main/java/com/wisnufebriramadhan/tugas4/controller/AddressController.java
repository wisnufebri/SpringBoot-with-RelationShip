package com.wisnufebriramadhan.tugas4.controller;


import com.wisnufebriramadhan.tugas4.model.Address;
import com.wisnufebriramadhan.tugas4.repository.AddressRepository;
import com.wisnufebriramadhan.tugas4.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;
    @Autowired
    AddressRepository addressRepository;

    @GetMapping("")
    List<Address> getAddress(){
        return addressRepository.findAll();
    }

    @PostMapping("insert")
    Map insertAddress (@RequestBody Address body){

        Map<String,Object> result = new HashMap<>();

        System.out.println("body " + body.toString());
        boolean status = addressService.addAddress(body);
        if (status){
            result.put("succes",true);
            result.put("message","berhasil");
        }else{
            result.put("success",false);
            result.put("message","gagal");
        }

        return result;
    }


    @PutMapping("update")
    Map<String, Object> updateAddress (@RequestBody Address body){
        System.out.println("body : " + body.toString());
        Map<String,Object> result = new HashMap<>();


        if (addressService.updateAddress(body)){
            result.put("succes",true);
            result.put("message","berhasil");
        } else {
            result.put("succes", false);
            result.put("message","gagal");
        }
        return result;
    }

    @DeleteMapping("delete")
    Map<String, Object> deleteAddress (@RequestParam int id) {
        Map<String,Object> result = new HashMap<>();
        if (addressService.delete(id)){
            result.put("Success", true);
        } else {
            result.put("Gagal", false);
        } return result;
    }

}
