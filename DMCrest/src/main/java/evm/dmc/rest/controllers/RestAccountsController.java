package evm.dmc.rest.controllers;

import evm.dmc.api.model.account.Account;
import evm.dmc.model.service.AccountService;
import evm.dmc.rest.annotation.HateoasRelation;
import evm.dmc.webApi.dto.AccountDto;
import evm.dmc.webApi.exceptions.AccountNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(RestAccountsControllerOld.BASE_URL)
public class RestAccountsController extends AbstractRestCrudController<AccountDto> {

    public final static String BASE_URL = "/rest/user";

    @Autowired
    private AccountService accountService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @GetMapping("/{accountId}")
    @HateoasRelation("getAccount")
    public AccountDto getInstance(@PathVariable Long accountId, Long id) {
        return convertToDto(accountService.get(accountId)
                .orElseThrow(AccountNotFoundException.supplier(accountId)));
    }

    @Override
    @GetMapping
    @HateoasRelation("getAccountList")
    public List<AccountDto> getInstancesList(Long accountId) {
        return accountService.getAll()
                .map(this :: convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @PostMapping
    @HateoasRelation("addAccount")
    public AccountDto addInstance(Long accountId, AccountDto dto) {
        Account account = accountService.save(convertToEntity(dto))
                .orElseThrow(RuntimeException::new);
        return convertToDto(account);
    }

    @Override
    @PutMapping
    @HateoasRelation("updateAccount")
    public AccountDto updateInstance(Long accountId, AccountDto dto) {
        return null;
    }

    @Override
    @DeleteMapping
    @HateoasRelation("deleteAccount")
    public AccountDto deleteInstance(Long accountId, Long id) {
        return null;
    }

    @Override
    public AccountDto deleteInstance(Long accountId, AccountDto dto) {
        return null;
    }

    private AccountDto convertToDto(Account account) {
        return modelMapper.map(account, AccountDto.class);
    }

    private Account convertToEntity(AccountDto dto) {
        return modelMapper.map(dto, Account.class);
    }
}
