package evm.dmc.webApi.dto;

import java.time.Instant;

import org.springframework.hateoas.ResourceSupport;

import evm.dmc.api.model.account.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for Account model
 * 
 * @see evm.dmc.api.model.account.Account
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class AccountDto extends ResourceSupport {
	
	private Long accountId;
	
	private String userName;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	protected Role role;
	
	private Instant created;
}
