package com.example.Imobiliaria.Controller;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Imobiliaria.DTO.AuthDto;
import com.example.Imobiliaria.DTO.UserLoginDto;
import com.example.Imobiliaria.Model.User;
import com.example.Imobiliaria.Security.TokenUtil;
import com.example.Imobiliaria.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("users")
public class UserController {
    final String TOKEN_TYPE = "Bearer";
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping()
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid User user) {
        if (userService.validaEmail(user.getEmail())) {
            return ResponseEntity.ok(userService.create(user));
        }
        return ResponseEntity.badRequest().body("User already registered");

    }

    @GetMapping()
    public ResponseEntity<?> listarUsuarios() {
        return ResponseEntity.ok(userService.listar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable UUID id) {
        if (userService.validaID(id)) {
            userService.deletaUsuario(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body("User does not exist!");

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editarUsuario(@PathVariable UUID id, @RequestBody @Valid User user,
            HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        token = token.substring(7);
        User usuarioLogado = userService.retornaUserPeloId(TokenUtil.pegaIdPeloToken(token));
        if (userService.validaID(id)) {
            user.setId(id);
            if (userService.validaAdm(usuarioLogado)
                    || TokenUtil.pegaIdPeloToken(token).equals(user.getId())) {
                return ResponseEntity.ok(userService.updateUser(user));

            }

        }
        return ResponseEntity.badRequest().body("Erro");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        if (userService.validaSeExisteEmail(userLoginDto.getEmail())) {
            User user = userService.retornaUserPorEmail(userLoginDto.getEmail());
            System.out.println(userLoginDto.getPassword());
            if (user.isActive()
                    && passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
                String token = TokenUtil.geraToken(user);
                AuthDto authDto = new AuthDto(user.getName(), user.getEmail(), token, TOKEN_TYPE);
                return ResponseEntity.ok(authDto);
            }
        }
        return ResponseEntity.badRequest().body("Email ou senha incorretos!");
    }

}
