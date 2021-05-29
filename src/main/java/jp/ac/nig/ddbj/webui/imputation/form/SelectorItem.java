package jp.ac.nig.ddbj.webui.imputation.form;


import lombok.Data;

@Data
public class SelectorItem {

    String id;
    String name;

    public SelectorItem(String id, String name) {
        this.id = id;
        this.name = name;
    } 

}
