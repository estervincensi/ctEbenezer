package br.com.ctebenezer.config.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.ctebenezer.domain.*;

public class AuditorAwareImpl implements AuditorAware<Pessoa> {

	@Override
	public Pessoa getCurrentAuditor() {
		return ((UserImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPessoa();
	}
}
