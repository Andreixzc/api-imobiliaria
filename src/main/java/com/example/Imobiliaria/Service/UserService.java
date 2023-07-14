package com.example.Imobiliaria.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.Imobiliaria.DTO.UserDto;
import com.example.Imobiliaria.Model.User;
import com.example.Imobiliaria.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto create(User user) {
        LocalDate currentDate = LocalDate.now();
        UserDto userDto = new UserDto();
        user.setCreatedAt(currentDate);
        user.setUpdatedAt(currentDate);
        user.setPassword(encriptaSenha(user.getPassword()));
        userRepository.save(user);
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public List<UserDto> listar() {
        List<User> usuarios = userRepository.findAll();
        List<UserDto> usuariosDto = new LinkedList<>();
        
        for (User usuario : usuarios) {
            UserDto usuarioDto = new UserDto();
            BeanUtils.copyProperties(usuario, usuarioDto);
            usuariosDto.add(usuarioDto);
        }
        
        return usuariosDto;
    }

    public boolean validaEmail(String email) {
        if (userRepository.findByEmail(email).isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean validaSeExisteEmail(String email) {
        if (userRepository.findByEmail(email).isEmpty()) {
            return false;
        }
        return true;
    }
    public void deletaUsuario(UUID id){
        userRepository.deleteById(id);
    }

    public boolean validaID(UUID id){
        if (userRepository.findById(id).isEmpty()) {
            return false;
        }
        return true;
    }
    public String encriptaSenha(String rawPassword){
        return passwordEncoder.encode(rawPassword);
    }
    public UserDto updateUser(User usuarioASerEditado){
        LocalDate currentDate = LocalDate.now();
        UserDto userDto = new UserDto();
        Optional<User> u = userRepository.findById(usuarioASerEditado.getId());
        usuarioASerEditado.setCreatedAt(u.get().getCreatedAt());
        usuarioASerEditado.setUpdatedAt(currentDate);
        userRepository.save(usuarioASerEditado);
        BeanUtils.copyProperties(usuarioASerEditado, userDto);
        return userDto; 
    }
    public User retornaUserPorEmail(String email){
        return userRepository.findByEmail(email).get(0);
    }

    public boolean validaAdm(User user){
        return user.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }

    public User retornaUserPeloId(UUID id){
        return userRepository.findById(id).get();
    }
    
  


   
}
