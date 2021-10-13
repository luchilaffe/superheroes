package com.mindata.superheroes.service.imple;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.mindata.superheroes.dao.UsersRepository;
import com.mindata.superheroes.mapper.UserMapper;
import com.mindata.superheroes.model.Users;

/**
 * Implementation of UserDetailsService
 * 
 * @author carlos.lafferriere
 *
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private UsersRepository usersRepository;

    public JwtUserDetailsServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * {@inheritDoc}
     * 
     * @author carlos.lafferriere
     */
    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        Users userFound = usersRepository.findByUser(user).orElseThrow(
                () -> new UsernameNotFoundException("User not found with user: " + user));
        return UserMapper.toUserDetails(userFound);
    }
}
