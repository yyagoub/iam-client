package sa.elm.iam.client.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.elm.iam.client.service.IamRequestUrlService;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

@RestController
@RequestMapping("iam")
@AllArgsConstructor
public class IamController {

    private final IamRequestUrlService iamRequestUrlService;

    @GetMapping
    public String getMapping() throws CloneNotSupportedException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException {
        return iamRequestUrlService.createIamRequestUrl();
    }

}
