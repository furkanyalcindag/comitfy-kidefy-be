package com.comitfy.kidefy.util.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;

	public String generateToken(String email) throws IllegalArgumentException, JWTCreationException {
		return JWT.create()
				.withSubject("User Details")
				.withClaim("email", email)
				.withIssuedAt(new Date())
				.withIssuer("HEALTIE/COMITFY")
				.sign(Algorithm.HMAC256(secret));
	}

	public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
				.withSubject("User Details")
				.withIssuer("HEALTIE/COMITFY")
				.build();
		DecodedJWT jwt = verifier.verify(token);
		return jwt.getClaim("email").asString();
	}

}
