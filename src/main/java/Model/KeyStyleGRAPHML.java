package Model;

import java.lang.String;

/**
 * Created by gnedelec001 on 15/02/17.
 */
public class KeyStyleGRAPHML {


    private String id;
    private boolean forNode;
    private String attrName;
    private String attrType = null;


    public KeyStyleGRAPHML(String id, String forNode, String attrName) {
        this.id = id;
        this.forNode = forNode.equals("node");
        this.attrName = attrName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

}
