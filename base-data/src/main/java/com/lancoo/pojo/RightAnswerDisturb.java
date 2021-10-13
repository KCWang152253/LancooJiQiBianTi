package com.lancoo.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Right_Answer_Disturb")
public class RightAnswerDisturb {
    @Id
    @Column(name = "Guid")
    private String guid;

    @Column(name = "RighA")
    private String righa;

    @Column(name = "DisturbB")
    private String disturbb;

    @Column(name = "DisturbC")
    private String disturbc;

    @Column(name = "DisturbD")
    private String disturbd;

    @Column(name = "E")
    private String e;

    @Column(name = "F")
    private String f;

    /**
     * @return Guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @param guid
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * @return RighA
     */
    public String getRigha() {
        return righa;
    }

    /**
     * @param righa
     */
    public void setRigha(String righa) {
        this.righa = righa;
    }

    /**
     * @return DisturbB
     */
    public String getDisturbb() {
        return disturbb;
    }

    /**
     * @param disturbb
     */
    public void setDisturbb(String disturbb) {
        this.disturbb = disturbb;
    }

    /**
     * @return DisturbC
     */
    public String getDisturbc() {
        return disturbc;
    }

    /**
     * @param disturbc
     */
    public void setDisturbc(String disturbc) {
        this.disturbc = disturbc;
    }

    /**
     * @return DisturbD
     */
    public String getDisturbd() {
        return disturbd;
    }

    /**
     * @param disturbd
     */
    public void setDisturbd(String disturbd) {
        this.disturbd = disturbd;
    }

    /**
     * @return E
     */
    public String getE() {
        return e;
    }

    /**
     * @param e
     */
    public void setE(String e) {
        this.e = e;
    }

    /**
     * @return F
     */
    public String getF() {
        return f;
    }

    /**
     * @param f
     */
    public void setF(String f) {
        this.f = f;
    }
}