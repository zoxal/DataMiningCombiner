package evm.dmc.model.service.impls;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import evm.dmc.webApi.dto.AccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import evm.dmc.api.model.ProjectModel;
import evm.dmc.api.model.account.Account;
import evm.dmc.api.model.account.AccountExt;
import evm.dmc.api.model.account.Role;
import evm.dmc.model.repositories.AccountRepository;
import evm.dmc.model.service.AccountService;
import evm.dmc.webApi.exceptions.ProjectNotFoundException;
import lombok.extern.slf4j.Slf4j;


@Service
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Slf4j
public class AccountServiceImpl implements AccountService {
	
	@Autowired
    private AccountRepository accountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${dmc.security.admin.username}")
	private String admuser;
	
	@Value("${dmc.security.admin.password}")
	private String admpass;
	
	private static final String ADMIN_EMAIL = "admin@admin";
	private static final String ADMIN_FIRSTNAME = "Admin";
	private static final String ADMIN_LASTNAME = "Admin";
	
	
	@PostConstruct
	protected void init() {
//		save(new Account("id23cat", "password", "id23cat@tut.by", "Alex", "Demidchuk"));
		log.debug("Account service: create admin in DB");
		save(new AccountExt(admuser, admpass, ADMIN_EMAIL, ADMIN_FIRSTNAME, ADMIN_LASTNAME, Role.ADMIN));
	}
	
	@Override
	@Transactional
	public Optional<Account> save(Account account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		return Optional.ofNullable(accountRepository.save(account));
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return createUser(getAccountByName(username));
	}

	@Override
	@Transactional(readOnly = true)
	public Account getAccountByName(String username) throws UsernameNotFoundException {
		return accountRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(username));
	}
	
	@Override
	@Transactional
	public Optional<Account> get(Long id) {
		return accountRepository.findById(id);
	}
	
	@Override
	public void signin(Account account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}
	
	@Override
	@Transactional
	public void delete(Account account) {
		accountRepository.delete(account);
	}
	
	
	@Override
	@Transactional
	public ProjectModel addProject(Account account, ProjectModel project) throws ProjectNotFoundException {
		account = merge(account);
		account.getProjects().add(project);
		project.setAccount(account);
		save(account);
		return findProjectByName(account, project.getName())
				.orElseThrow(
						() -> new ProjectNotFoundException(String.format("Project with name [%s] not found", project.getName()))
				);
	}
	
	@Override
	@Transactional
	public Account delProject(Account account, ProjectModel project) {
		account = merge(account);
		account.getProjects().remove(project);
//		save(account);
		return account;
	}
	
	@Override
	@Transactional
	public Account delProjectsByNames(Account account, Set<String> names) {
		account = merge(account);
//		account.removeProjectsByNames(names);
		Set<ProjectModel> remProjects = account.getProjects().parallelStream()
			.filter((proj) -> names.contains(proj.getName()))
			.collect(Collectors.toSet());
		
		account.getProjects().removeAll(remProjects);
//		accountRepository.flush();
//		return save(account);
		return account;
	}
	
	@Override
	public Optional<ProjectModel> findProjectByName(Account account, String name) {
		return account.getProjects().stream().filter(proj -> proj.getName().equals(name)).findFirst();
	}
	
//	private  void refresh(Account account) {
//		em.refresh(account);
//	}
	
	@Transactional
	private Account merge(Account account) {
//		return em.merge(account);
		return accountRepository.getOne(account.getId());
	}
	
	private Authentication authenticate(Account account) {
		return new UsernamePasswordAuthenticationToken(createUser(account), null, Collections.singleton(createAuthority(account)));
	}
	
	@Transactional
	private User createUser(Account account) {
		return new User(account.getUserName(), account.getPassword(), Collections.singleton(createAuthority(account)));
	}
	
	@Transactional(readOnly=true)
	public Stream<Account> getAll() {
		return accountRepository.streamAll();
	}

	private GrantedAuthority createAuthority(Account account) {
		return new SimpleGrantedAuthority(account.getRole().toString());
	}
	
	

}
