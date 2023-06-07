package com.upc.modelhouse.security.api;

import com.upc.modelhouse.security.domain.service.AccountService;
import com.upc.modelhouse.security.mapping.AccountMapper;
import com.upc.modelhouse.security.resource.Account.AccountDto;
import com.upc.modelhouse.security.resource.Account.CreateAccountDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper mapper;

    public AccountController(AccountService accountService, AccountMapper mapper) {
        this.accountService = accountService;
        this.mapper = mapper;
    }
    @GetMapping("account/{Id}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public AccountDto getAccount(@PathVariable("Id") Long id){
        return mapper.toResource(accountService.findById(id));
    }
    @GetMapping("user/{userId}/account")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public AccountDto getAccountByUser(@PathVariable("userId") Long userId){
        return mapper.toResource(accountService.findByUserId(userId));
    }
    @PostMapping("{userId}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public AccountDto createAccount(@PathVariable("userId") Long userId,@RequestBody CreateAccountDto resource){
        return mapper.toResource(accountService.create(userId, mapper.toModel(resource)));
    }
}
