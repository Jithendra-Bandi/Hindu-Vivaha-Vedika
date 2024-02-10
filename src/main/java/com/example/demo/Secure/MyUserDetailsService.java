package com.example.demo.Secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.Profile.Model.MainProfile;
import com.example.demo.Profile.Repository.MainProfileJpaRepository;

public class MyUserDetailsService implements UserDetailsService{
    @Autowired
    private MainProfileJpaRepository mainProfileJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
        MainProfile mainProfile = mainProfileJpaRepository.findByMobileNumber(mobileNumber).get();

        if (mainProfile == null) throw new UsernameNotFoundException("Mobile Number Not Registered");
        return new UserPrinciple(mainProfile);
        
    }
    
    
}
