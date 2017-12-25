package mdd.logistics.service.jwt;

import io.jsonwebtoken.*;
import mdd.logistics.dao.UserDao;
import mdd.logistics.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class JwtCreator {

    @Autowired
    UserDao userDao;
    private final static String issuer = "c5f3257b2c830382";
    private final static String secret_key = "71ee217e2f1bdfcc";
    private final static long ttlMillis = 6000 * 60;

    public String createJwt(Long id) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret_key);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setSubject(String.valueOf(id))
                .setIssuedAt(now)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);
        long expMillis = nowMillis + ttlMillis;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);

        return builder.compact();
    }

    public User getJwtUser(String jwt)  throws ClaimJwtException {
        //This line will throw an exception if it is not a signed JWS (as expected)
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secret_key))
                    .parseClaimsJws(jwt).getBody();
            if (issuer.equals(claims.getIssuer())) {
                long nowMillis = System.currentTimeMillis();
                if (nowMillis < claims.getExpiration().getTime()) {
                    User user = userDao.getById(Long.parseLong(claims.getSubject()));
                    if (user != null) {
                        return user;
                    }
                }
            }
            return null;
        }catch (ClaimJwtException e){
           throw  e;
        }

    }
}
