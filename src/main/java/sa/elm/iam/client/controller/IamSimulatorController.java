package sa.elm.iam.client.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.elm.iam.client.model.AuthorizationRequest;
import sa.elm.iam.client.service.IamSimulatorService;

@RestController
@RequestMapping("/authservice/authorize")
@AllArgsConstructor
public class IamSimulatorController {

    private final IamSimulatorService iamSimulatorService;

    /**
     * @Author Yousef Yagoub
     * @param authorizationRequest
     * @return
     *
     * used to simulate IAM authorization request endpoint
     * * NOTE: IAM return redirect url, hence the simulator return dummy url
     */
    @GetMapping
    public String simulateAuthorizationEndpoint(AuthorizationRequest authorizationRequest){
        return this.iamSimulatorService.checkRequest(authorizationRequest);
    }
}
