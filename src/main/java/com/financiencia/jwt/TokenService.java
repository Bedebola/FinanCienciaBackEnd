package com.financiencia.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.financiencia.entities.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${spring.seguranca.segredo}")
    private String secret;

    @Value("${spring.seguranca.tempo_validade}")
    private Long expirationTime;

    public String generateToken(Usuario usuario) throws Exception{
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token =JWT.create()
                    .withIssuer("financiencia")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);

            return token;
        }catch (JWTCreationException ex){
            ex.printStackTrace();
            throw new Exception("Ocorreu um erro ao gerar o token!");
        }

    }

    public String validateToken(String token){
        try{
            Algorithm algorithm =Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("financiencia")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex){
            return null;
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusMinutes(expirationTime)
                .toInstant(ZoneOffset.of("-03:00"));
}





}
