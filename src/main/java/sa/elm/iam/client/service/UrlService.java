package sa.elm.iam.client.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sa.elm.iam.client.config.exception.UnprocessableEntityException;
import sa.elm.iam.client.config.security.signature.PrivateCertificateSignature;
import sa.elm.iam.client.config.security.signature.PublicCertificateSignature;
import sa.elm.iam.client.model.IamRequestUrl;
import sa.elm.iam.client.util.IamRequestUrlUtil;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Base64;

@Service
@AllArgsConstructor
public class UrlService {

    private final IamRequestUrlService iamRequestUrlService;
    private final PrivateCertificateSignature clientPrivateCertificateSignature;
    private final PublicCertificateSignature clientPublicCertificateSignature;
    private final IamRequestUrlUtil util;

    public String createIamRequestUrl() throws CloneNotSupportedException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException {
        IamRequestUrl url = iamRequestUrlService.createIamRequestUrl(util.createNonce(),util.createMaxAge());
        String state = this.encodeAndSignUrl(iamRequestUrlService.toBeSignedUrl(url));
        url.setState(state);
        return iamRequestUrlService.signedUrl(url);
    }

    public String validateUrl(String requestUrl) throws UnsupportedEncodingException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        URL url = this.prepareUrl(requestUrl);
        this.iamRequestUrlService.validateUrlsHost(url);
        this.iamRequestUrlService.validateUrlsQuery(url);
        String state = this.iamRequestUrlService.getStateFromUrl(url);
        this.clientPublicCertificateSignature.verify(url.toString(), state.getBytes(StandardCharsets.UTF_8));
        return "this is url have been validated and it seems fine";
    }

    private String encodeAndSignUrl(String url) throws UnsupportedEncodingException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        byte[] signedUrl = clientPrivateCertificateSignature.sign(url);
        String encodedSignedUrl = Base64.getEncoder().encodeToString(signedUrl);
        return URLEncoder.encode(encodedSignedUrl, "UTF-8");
    }

    private URL prepareUrl(String requestUrl) {
        try {
            return new URL(requestUrl);
        } catch (MalformedURLException e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
    }

}
