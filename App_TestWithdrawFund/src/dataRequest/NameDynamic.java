/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataRequest;

/**
 *
 * @author CPU01661-local
 */
public class NameDynamic {
    private String name;
    private String isDyn;

    public NameDynamic() {
    }

    public NameDynamic(String name, String isDyn) {
        this.name = name;
        this.isDyn = isDyn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsDyn() {
        return isDyn;
    }

    public void setIsDyn(String isDyn) {
        this.isDyn = isDyn;
    }
    
}
