package Model;

import java.lang.String;

/**
 * Created by gnedelec001 on 15/02/17.
 */
public class KeyStyleGRAPHML {

    private String id;
    private boolean forNode;
    private String attrName;
    private String attrType;


    public KeyStyleGRAPHML(String id, String forNode, String attrName, String attrType) {
        this.id = id;
        if(forNode.equals("node"))
            this.forNode = true;
        else
            this.forNode = false;
        this.attrName = attrName;
        this.attrType = attrType;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isForNode() {
        return forNode;
    }

    public void setForNode(boolean forNode) {
        this.forNode = forNode;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

}
