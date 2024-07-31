package com.example.testDatabase;

import com.example.testDatabase.DTO.userDto;
import com.example.testDatabase.Entity.UserEntity;
import com.example.testDatabase.Repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class ServiceImpl {

    @Autowired
    private final userRepo userRepo;

    public ServiceImpl(userRepo userRepo) {
        this.userRepo = userRepo;
    }


    public String SaveUser(@AuthenticationPrincipal OAuth2User principal) {
//        String fname = "Nisha";
//        String lname ="Jain";
//        String email ="jainnisha1314";
//        String image_url ="abc";
        String fname =(String) principal.getAttribute("given_name");
        String lname =(String) principal.getAttribute("family_name");
        String email =(String) principal.getAttribute("email");
        String image_url = (String)principal.getAttribute("picture");
        userDto userDto = new userDto(fname,lname,email,image_url);

        UserEntity userEntity = new UserEntity();
        userEntity.setFname(userDto.getFname());
        userEntity.setLname(userDto.getLname());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setImageurl(userDto.getImageurl());
        try
        {
            userRepo.save(userEntity);
            return "User Saved";
        }
        catch (Exception e){
            return "Failed To save";
        }
    }
}
