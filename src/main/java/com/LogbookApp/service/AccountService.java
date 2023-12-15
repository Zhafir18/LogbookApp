package com.LogbookApp.service;

import com.LogbookApp.dto.account.*;
import com.LogbookApp.entity.Account;
import com.LogbookApp.repository.AccountRepository;
import com.LogbookApp.utility.ApplicationUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean isUsernameExist(String username) {
        var user = accountRepository.countExistingUser(username);
        return (user > 0);
    }

    public void register(RegisterDTO dto) {
        var entity = new Account();
        entity.setUsername(dto.getUsername());
        var hashedPassword = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(hashedPassword);
        entity.setName(dto.getName());
        entity.setRole(dto.getRole());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPosition(dto.getPosition());
        entity.setCompany(dto.getCompany());
        accountRepository.save(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var account = accountRepository.findById(username).get();
        if (account == null) {
            throw  new UsernameNotFoundException("User not found"); // Sengaja dibuat exception
        } else {
            var userDetail = new ApplicationUserDetails(account);
            return userDetail;
        }
    }

    public Boolean checkUserPassword(String username, String password) {
        var isAuthenticated = false;
        var account = accountRepository.findById(username).get();
        if (account != null) {
            var hashedPassword = account.getPassword();
            isAuthenticated = passwordEncoder.matches(password, hashedPassword);
        }
        return isAuthenticated;
    }

    public String getRole(String username) {
        return accountRepository.findById(username).get().getRole();
    }

    public ProfileDTO getProfile(String username) {
        var data = accountRepository.getEmployeeProfile(username);
        return data;
    }

    public EditProfileDTO findOne(String username) {
        var entity = accountRepository.findById(username).get();
        var dto = new EditProfileDTO(
                entity.getName(),
                entity.getBirthDate(),
                entity.getPosition(),
                entity.getCompany()
        );
        return dto;
    }

    public void save(EditProfileDTO dto) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var getOne = accountRepository.findById(username).get();
        var entity = new Account();
        entity.setUsername(getOne.getUsername());
        entity.setPassword(getOne.getPassword());
        entity.setRole(getOne.getRole());
        entity.setName(dto.getName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPosition(dto.getPosition());
        entity.setCompany(dto.getCompany());
        accountRepository.save(entity);
    }

    public Page<EmployeeListDTO> getEmployee(Integer page) {
        var pageable = PageRequest.of(page - 1, 10);
        var data = accountRepository.getEmployee(pageable);
        return data;
    }

    public Page<ClientEmployeeListDTO> getClientEmployee(String username, Integer page) {
        var pageable = PageRequest.of(page - 1, 10);
        var data = accountRepository.getClientEmployee(username, pageable);
        return data;
    }

    public Page<ClientListDTO> getClient(String company, Integer page) {
        var pageable = PageRequest.of(page - 1, 10);
        var data = accountRepository.getClient(company, pageable);
        return data;
    }

    public Page<HRDListDTO> getHRD(String name, Integer page) {
        var pageable = PageRequest.of(page - 1, 10);
        var data = accountRepository.getHRD(name, pageable);
        return data;
    }

    public void delete(String username) {
        accountRepository.deleteById(username);
    }

    public void changePassword(ChangePasswordDTO dto) {
        var entity = accountRepository.findById(dto.getUsername()).get();
        var hashNewPassword = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(hashNewPassword);
        accountRepository.save(entity);
    }
}
