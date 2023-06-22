package kz.akello.project.superapp.service;

import kz.akello.project.superapp.model.User;
import kz.akello.project.superapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user!=null){
            return user;
        }else {
            throw new UsernameNotFoundException("User Not Found");
        }
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        User checkUser = userRepository.findByEmail(user.getEmail());
        if(checkUser==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        return null;
    }

}
