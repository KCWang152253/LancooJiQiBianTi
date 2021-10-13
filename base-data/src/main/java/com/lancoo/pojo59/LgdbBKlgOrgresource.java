package com.lancoo.pojo59;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Lgdb_B_Klg_OrgResource")
public class LgdbBKlgOrgresource {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "KnowledgeCode")
    private String knowledgecode;

    @Column(name = "KnowledgeName")
    private String knowledgename;

    @Column(name = "KnowledgeType")
    private String knowledgetype;

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
    private String unPhonetic;

    @Column(name = "US_Phonetic")
    private String usPhonetic;

    @Column(name = "Source")
    private String source;

    @Column(name = "IsSelect")
    private Integer isselect;

    @Column(name = "Flag")
    private Integer flag;

    @Column(name = "TaskId")
    private String taskid;

    @Column(name = "QuesType")
    private String questype;

    @Column(name = "IsDelete")
    private Integer isdelete;

    @Column(name = "IsRepeat")
    private String isrepeat;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return KnowledgeCode
     */
    public String getKnowledgecode() {
        return knowledgecode;
    }

    /**
     * @param knowledgecode
     */
    public void setKnowledgecode(String knowledgecode) {
        this.knowledgecode = knowledgecode;
    }

    /**
     * @return KnowledgeName
     */
    public String getKnowledgename() {
        return knowledgename;
    }

    /**
     * @param knowledgename
     */
    public void setKnowledgename(String knowledgename) {
        this.knowledgename = knowledgename;
    }

    /**
     * @return KnowledgeType
     */
    public String getKnowledgetype() {
        return knowledgetype;
    }

    /**
     * @param knowledgetype
     */
    public void setKnowledgetype(String knowledgetype) {
        this.knowledgetype = knowledgetype;
    }

    /**
     * @return MainContext
     */
    public String getMaincontext() {
        return maincontext;
    }

    /**
     * @param maincontext
     */
    public void setMaincontext(String maincontext) {
        this.maincontext = maincontext;
    }

    /**
     * @return SecondContext
     */
    public String getSecondcontext() {
        return secondcontext;
    }

    /**
     * @param secondcontext
     */
    public void setSecondcontext(String secondcontext) {
        this.secondcontext = secondcontext;
    }

    /**
     * @return Sentence
     */
    public String getSentence() {
        return sentence;
    }

    /**
     * @param sentence
     */
    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    /**
     * @return Translate
     */
    public String getTranslate() {
        return translate;
    }

    /**
     * @param translate
     */
    public void setTranslate(String translate) {
        this.translate = translate;
    }

    /**
     * @return SingleType
     */
    public String getSingletype() {
        return singletype;
    }

    /**
     * @param singletype
     */
    public void setSingletype(String singletype) {
        this.singletype = singletype;
    }

    /**
     * @return UN_Phonetic
     */
    public String getUnPhonetic() {
        return unPhonetic;
    }

    /**
     * @param unPhonetic
     */
    public void setUnPhonetic(String unPhonetic) {
        this.unPhonetic = unPhonetic;
    }

    /**
     * @return US_Phonetic
     */
    public String getUsPhonetic() {
        return usPhonetic;
    }

    /**
     * @param usPhonetic
     */
    public void setUsPhonetic(String usPhonetic) {
        this.usPhonetic = usPhonetic;
    }

    /**
     * @return Source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return IsSelect
     */
    public Integer getIsselect() {
        return isselect;
    }

    /**
     * @param isselect
     */
    public void setIsselect(Integer isselect) {
        this.isselect = isselect;
    }

    /**
     * @return Flag
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * @param flag
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * @return TaskId
     */
    public String getTaskid() {
        return taskid;
    }

    /**
     * @param taskid
     */
    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    /**
     * @return QuesType
     */
    public String getQuestype() {
        return questype;
    }

    /**
     * @param questype
     */
    public void setQuestype(String questype) {
        this.questype = questype;
    }

    /**
     * @return IsDelete
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    /**
     * @param isdelete
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    /**
     * @return IsRepeat
     */
    public String getIsrepeat() {
        return isrepeat;
    }

    /**
     * @param isrepeat
     */
    public void setIsrepeat(String isrepeat) {
        this.isrepeat = isrepeat;
    }
}