package com.example.Imobiliaria.Security;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.example.Imobiliaria.Model.User;
import com.example.Imobiliaria.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;



public class TokenUtil {
    private static final String EMISSOR = "IRINEU_VOCE_NAO_SABE_NEM_EU";
    private static final String TOKEN_HEADER = "Bearer ";
    private static final String TOKEN_KEY =
            "rm'!@N=Ke!~p8VTA2ZRK~nMDQX5Uvm!m'D&]{@Vr?G;2?XhbC:Qa#9#eMLN\\}x3?JR3.2zr~v)gYF^8\\:8>:XfB:Ww75N/emt9Yj[bQMNCWwW\\J?N,nvH.<2\\.r~w]*e~vgak)X\"v8H`MH/7\"2E`,^k@n<vE-wD3g9JWPy;CrY*.Kd2_D])=><D?YhBaSua5hW%{2]_FVXzb9`8FH^b[X3jzVER&:jw2<=c38=>L/zBq`}C6tT*cCSVC^c]-L}&/";
    private static final long expiration = 86400000;


    @Autowired
    UserRepository userRepository;

    public static String geraToken(User usuario) {
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + (expiration));
        String subject = usuario.getId().toString();
        return Jwts.builder().setIssuer(EMISSOR).setSubject(subject).setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, TOKEN_KEY).compact();
    }

    public static boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public static UUID pegaIdPeloToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody();
        String subject = claims.getSubject();
        return UUID.fromString(subject);

    }

    public static Authentication decodeToken(HttpServletRequest request,
            UserRepository userRepository) {

        String token = request.getHeader("Authorization");
        System.out.println(token);
        if (token == null || !token.startsWith(TOKEN_HEADER)) {
            return null;
        }
        token = token.substring(7);
        System.out.println(token);
        if (isTokenValido(token)) {
            Optional<User> usuario = userRepository.findById(pegaIdPeloToken(token));
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(usuario.get(), null,
                            usuario.get().getAuthorities());
            return authentication;
        }
        return null;
    }
    public static String decoTokenByString(String token){
        return token.substring(7);
    }

}

