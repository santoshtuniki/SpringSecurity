package com.greatlearning.librarymgmt.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatlearning.librarymgmt.model.User;
import com.greatlearning.librarymgmt.repository.UserRepository;
import com.greatlearning.librarymgmt.security.MyUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}else {
			return new MyUserDetails(user);
		}
	}

}
