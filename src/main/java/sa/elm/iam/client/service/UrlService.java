package sa.elm.iam.client.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sa.elm.iam.client.config.security.signature.PrivateCertificateSignature;
import sa.elm.iam.client.model.IamRequestUrl;
import sa.elm.iam.client.util.IamRequestUrlUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Base64;

@Service
@AllArgsConstructor
public class UrlService {

    private final IamRequestUrl iamRequestUrl;
    private final PrivateCertificateSignature clientPrivateCertificateSignature;
    private final IamRequestUrlUtil util;

    public String createIamRequestUrl() throws CloneNotSupportedException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException {
        IamRequestUrl url = this.iamRequestUrl.clone();
        url.setNonce(util.createNonce());
        url.setMaxAge(util.createMaxAge());
        String state = this.encodeAndSignUrl(url.toBeSignedUrl());
        url.setState(state);
        return url.signedUrl();
    }

    private String encodeAndSignUrl(String url) throws UnsupportedEncodingException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        byte[] signedUrl = clientPrivateCertificateSignature.sign(url);
        String encodedSignedUrl = Base64.getEncoder().encodeToString(signedUrl);
        return URLEncoder.encode(encodedSignedUrl, "UTF-8");
    }

    public String validateUrl(String url) {

        return null;
    }
}
