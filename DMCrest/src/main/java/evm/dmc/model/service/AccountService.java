package evm.dmc.model.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import evm.dmc.webApi.dto.AccountDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import evm.dmc.api.model.ProjectModel;
import evm.dmc.api.model.account.Account;
import evm.dmc.webApi.exceptions.ProjectNotFoundException;

public interface AccountService extends UserDetailsService {

	Optional<Account> save(Account account);
	
	Account getAccountByName(String username) throws UsernameNotFoundException;

	Optional<Account> get(Long id);
	
	void signin(Account account);
	
	void delete(Account account);
	
//	void refresh(Account account);
//	
//	Account merge(Account account);
	
	ProjectModel addProject(Account account, ProjectModel project) throws ProjectNotFoundException;
	
	Account delProject(Account account, ProjectModel project);
	
	Account delProjectsByNames(Account account, Set<String> names);

	Optional<ProjectModel> findProjectByName(Account account, String name);
	
	Stream<Account> getAll();
}
