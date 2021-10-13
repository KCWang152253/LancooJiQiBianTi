package com.lancoo.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author KCWang
 * @date 2021/7/22 11:28
 * @Email:KCWang@aliyun.com
 */


@Data
@Table(name = "Lgdb_F_Klg_OrgResource")
public class LgdbFKlgOrgResource {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "KnowledgeCode")
    private String knowledgecode;

    @Column(name = "KnowledgeName")
    private String  knowledgename;

    @Column(name = "KnowledgeType")
    private String knowledgetypep;

    @Column(name = "MainContext")
    private String maincontext;

    @Column(name = "SecondContext")
    private String secondcontext;

    @Column(name = "Sentence")
    private String sentence;

    @Column(name = "Translate")
    private String translate;

    @Column(name = "SingleType")
    private String singletype;

    @Column(name = "UN_Phonetic")
    private String unphonetic;

    @Column(name = "US_Phonetic")
    private String usphonetic;

    @Column(name = "Source")
    private String source;

    @Column(name = "IsSelect")
    private String isselect;

    @Column(name = "Flag")
    private String flag;

    @Column(name ="TaskId" )
    private String taskid;

    @Column(name ="QuesType" )
    private String questype;


    @Column(name ="IsDelete" )
    private String isdelete;

    @Column(name ="IsRepeat" )
    private String isrepeat;

    @Override
    public String toString() {
        return "LgdbFKlgOrgResource{" +
                "id=" + id +
                ", knowledgecode='" + knowledgecode + '\'' +
                ", knowledgename='" + knowledgename + '\'' +
                ", knowledgetypep='" + knowledgetypep + '\'' +
                ", maincontext='" + maincontext + '\'' +
                ", secondcontext='" + secondcontext + '\'' +
                ", sentence='" + sentence + '\'' +
                ", translate='" + translate + '\'' +
                ", singletype='" + singletype + '\'' +
                ", unphonetic='" + unphonetic + '\'' +
                ", usphonetic='" + usphonetic + '\'' +
                ", source='" + source + '\'' +
                ", isselect='" + isselect + '\'' +
                ", flag='" + flag + '\'' +
                ", taskid='" + taskid + '\'' +
                ", questype='" + questype + '\'' +
                ", isdelete='" + isdelete + '\'' +
                ", isrepeat='" + isrepeat + '\'' +
                '}';
    }
}
