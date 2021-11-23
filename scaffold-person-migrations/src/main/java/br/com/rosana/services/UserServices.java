package br.com.rosana.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.rosana.repository.UserRepository;

    //@service é usado para que o spring cuide da injeção de dependência dessa classe.
	//quando for usar essa classe em outra classe, nao precisa dar um new, nem deixar isso static
	@Service
	public class UserServices implements UserDetailsService{
		
		@Autowired
		UserRepository repository;

		//este método deve ser implementado em razão de estarmos utilizando a interface UserDetailsService
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			var user = repository.findByUserName(username);
			if (user!=null) {
				return user;
			} else {
				throw new UsernameNotFoundException("Username " + username + " not found");
			}
		}

		public UserServices(UserRepository repository) {
			this.repository = repository;
		}
		
		

	}

