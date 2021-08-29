package sa.elm.iam.client.model;

import lombok.Data;

@Data
public class JksProperty {
    private String path;
    private String pass;
    private String storeType;
}
