package application.auth;

import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Operation(summary = "Autenticar usuário", description = "Autentica as credenciais e retorna um token JWT")
    @ApiResponse(responseCode = "200", description = "Autenticação bem-sucedida", content = @Content)
    public ResponseEntity<application.dto.ApiResponse> login(@RequestBody Usuario usuario) {
        UsernamePasswordAuthenticationToken tk =
            new UsernamePasswordAuthenticationToken(
                usuario.getNomeDeUsuario(), usuario.getSenha());
        authenticationManager.authenticate(tk);
        
        String token = tokenService.generateToken(usuario);
        Map<String, String> data = Map.of("token", token);
        return ResponseEntity.ok(application.dto.ApiResponse.ok(data, "Autenticado"));
    }
}
