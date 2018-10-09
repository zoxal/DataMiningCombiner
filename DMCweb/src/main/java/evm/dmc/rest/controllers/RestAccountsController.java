package evm.dmc.rest.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import evm.dmc.api.model.account.Account;
import evm.dmc.model.repositories.AccountRepository;
import evm.dmc.rest.dto.AccountDto;
import evm.dmc.web.service.AccountService;

/**
 * HATEOAS REST API controller for Account
 *
 * @see evm.dmc.api.model.account.Account
 */
@RestController
@RequestMapping(RestAccountsController.BASE_URL)
public class RestAccountsController {

	public final static String BASE_URL = "/rest/user";
	public final static String LINK_REL_accountsList = "accountsList";
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
    private ModelMapper modelMapper;

	/**
	 * creates HATEOAS self link for Account DTO
	 * @param dto Account DTO
	 * @return Account DTO with self link
	 */
	public static AccountDto selfLink(AccountDto dto) {
		Link selfLink = linkTo(methodOn(RestAccountsController.class)
				.getAccount(dto.getAccountId()))
				.withSelfRel();
		dto.add(selfLink);
		return dto;
	}

	/**
	 * creates HATEOAS links for requested resource
	 * @param resSupport requested resource
	 * @return resource with links for operations available in REST API for Account model
	 */
	public static ResourceSupport accountsListLink(ResourceSupport resSupport) {
		Link listLink = linkTo(methodOn(RestAccountsController.class)
				.getAccountsList())
				.withRel(LINK_REL_accountsList);
		resSupport.add(listLink);
		return resSupport;
	}

	/**
	 * finds all accounts
	 * @return list of all Account DTOs with HATEOAS links
	 */
	@GetMapping
	@Transactional
	public List<AccountDto> getAccountsList() {
		return accountService.getAll()
				.map(this :: convertToDto)
				.map((accDto) -> addLinks(accDto))
				.collect(Collectors.toList());
	}

	/**
	 * finds singular Account
	 * @param accountId identifier of Account
	 * @return particular Account DTO with HATEOAS links
	 */
	@GetMapping("/{accountId}")
	@Transactional
	public AccountDto getAccount(@PathVariable Long accountId) {
		return addLinks(convertToDto(accountRepository.findOne(accountId)));
	}

	/**
	 * adds HATEOAS links for Account DTO
	 * @param accDto Account DTO
	 * @return Account DTO with HATEOAS links
	 */
	private AccountDto addLinks(AccountDto accDto) {
		selfLink(accDto);
		accountsListLink(accDto);
		RestProjectController.projectsListLink(accDto, accDto.getAccountId());
		return accDto;
	}

	/**
	 * converts Account model to Account DTO
	 * @param acc Account model
	 * @return Account DTO
	 */
	private AccountDto convertToDto(Account acc) {
		return modelMapper.map(acc, AccountDto.class);
	}
}
