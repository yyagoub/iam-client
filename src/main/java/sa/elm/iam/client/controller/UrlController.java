package sa.elm.iam.client.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sa.elm.iam.client.service.UrlService;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

@RestController
@RequestMapping("url")
@AllArgsConstructor
public class UrlController {

    private final UrlService urlService;

    /**
     *
     * @Author Yousef Yagoub
     * @return String
     * @throws CloneNotSupportedException
     * @throws NoSuchAlgorithmException
     * @throws SignatureException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     *
     * used to return new valid iam login link
     */
    @GetMapping
    public String getNewUrl() throws CloneNotSupportedException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException {
        return urlService.createIamRequestUrl();
    }

    /**
     *
     * @Author Yousef Yagoub
     * @param url
     * @return String
     *
     * used to validate iam login link
     */
    @PostMapping
    public String validateLoginUrl(@RequestBody String url) {
        return urlService.validateUrl(url);
    }

}
