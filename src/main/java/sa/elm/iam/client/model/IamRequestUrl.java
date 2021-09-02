package sa.elm.iam.client.model;

import lombok.Data;

/**
 * https://iambeta.elm.sa/authservice/authorize?scope=openid&response_type=id_token&
 * response_mode=form_post&client_id=16367906&redirect_uri=https://ersalonline-u.alinma.com/ErsalWeb-UAT/iam/callback&
 * nonce=161be2d6-751f-4268-a686-ea8613a40272&ui_locales=en&prompt=login&max_age=1629886779&
 * state=PLRTlwT4PvnlkjMUuq0pet1CaEh9EBsh4qA86keWUw%2FNSBxbH4xC9wnbR4E3ViZRu8q0s0DVYN78GyKiSyvMSNNywsqvSVUv%2BkNAz94yDaq8YE%2BRNO8b7jIk5jPyIErSEL6jCeA7gdMm632sb%2Fxcr7lzQ%2BADO%2BOQasDRl4t17BIq6Grca5dBFqpJgnuzK0ayWR%2B1bYuN3sqk08tm8d%2FEj6gOxKoqcs%2BRdRYNzTX2M%2BxMv9XX93uxrwIct40SYJCnkXCr2LdriQdAD0pU8Tj0fQBI%2FBn7UBUQFI6VoLIALD6rgTwQCnjTmE2nOeE8sel%2F%2FBhWB21JXi7%2Fn4ZUvfc7Gg%3D%3D
 */
@Data
public class IamRequestUrl implements Cloneable{

    /*
     * production: https://www.iam.gov.sa/authservice/authorize?
     * or :
     * staging: https://www.iam.sa/authservice/authorize?
     * scope=openid
     * &response_type=id_token
     * &response_mode=form_post
     * &client_id=<CLIENT ID>
     * &redirect_uri=https://www.service_provider.com/callback
     * &nonce=GUID_RANDOM (example: b55224f7-e83d-4250-aa4a-451d32666e59)
     * &ui_locales=ar/en
     * &prompt=login (optional, when set the user will be forced to re-authenticate)
     * &max_age=timestamp (issue time of the request in seconds - using Saudi time is a must) in epoch format
     * &state=<Message_Signature>
     */

    private String host;
    private String scope;
    private String responseType;
    private String responseMode;
    private String clientId;
    private String redirectUri;
    private String nonce;
    private String uiLocales;
    private String prompt;
    private String maxAge;
    private String state;

    public String toBeSignedUrl(){
        return host+"?"
                + "scope" + "=" + scope
                + "&" + "response_type" + "=" + responseType
                + "&" + "response_mode" + "=" + responseMode
                + "&" + "client_id" + "=" + clientId
                + "&" + "redirect_uri" + "=" + redirectUri
                + "&" + "nonce" + "=" + nonce
                + "&" + "ui_locales" + "=" + uiLocales
                + "&" + "prompt" + "=" + prompt
                + "&" + "max_age" + "=" + maxAge
                //+ "&" + "state" + "=" + state
                ;
    }

    public String signedUrl(){
        return host+"?"
                + "scope" + "=" + scope
                + "&" + "response_type" + "=" + responseType
                + "&" + "response_mode" + "=" + responseMode
                + "&" + "client_id" + "=" + clientId
                + "&" + "redirect_uri" + "=" + redirectUri
                + "&" + "nonce" + "=" + nonce
                + "&" + "ui_locales" + "=" + uiLocales
                + "&" + "prompt" + "=" + prompt
                + "&" + "max_age" + "=" + maxAge
                + "&" + "state" + "=" + state
                ;
    }

    public IamRequestUrl clone() throws CloneNotSupportedException {
        return (IamRequestUrl) super.clone();
    }

}
