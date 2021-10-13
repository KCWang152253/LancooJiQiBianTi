package com.lancoo.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Lgdb_Basic_QuesType")
public class LgdbBasicQuestype {
    @Id
    @Column(name = "REC_ID")
    private Integer recId;

    @Column(name = "QUESTYPE_CODE")
    private String questypeCode;

    @Column(name = "QUESTYPE_NAME")
    private String questypeName;

    @Column(name = "QUESTYPE_SEQUENCE")
    private Long questypeSequence;

    @Column(name = "ANS_TYPE")
    private Long ansType;

    @Column(name = "MULTI_ANS_TYPE")
    private Long multiAnsType;

    @Column(name = "SUB_OBJ")
    private Long subObj;

    @Column(name = "TEST_SKILL")
    private Long testSkill;

    @Column(name = "QUES_TAG")
    private Long quesTag;

    @Column(name = "OLD_CODE")
    private String oldCode;

    @Column(name = "OLD_CODE_MULTI")
    private String oldCodeMulti;

    /**
     * @return REC_ID
     */
    public Integer getRecId() {
        return recId;
    }

    /**
     * @param recId
     */
    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    /**
     * @return QUESTYPE_CODE
     */
    public String getQuestypeCode() {
        return questypeCode;
    }

    /**
     * @param questypeCode
     */
    public void setQuestypeCode(String questypeCode) {
        this.questypeCode = questypeCode;
    }

    /**
     * @return QUESTYPE_NAME
     */
    public String getQuestypeName() {
        return questypeName;
    }

    /**
     * @param questypeName
     */
    public void setQuestypeName(String questypeName) {
        this.questypeName = questypeName;
    }

    /**
     * @return QUESTYPE_SEQUENCE
     */
    public Long getQuestypeSequence() {
        return questypeSequence;
    }

    /**
     * @param questypeSequence
     */
    public void setQuestypeSequence(Long questypeSequence) {
        this.questypeSequence = questypeSequence;
    }

    /**
     * @return ANS_TYPE
     */
    public Long getAnsType() {
        return ansType;
    }

    /**
     * @param ansType
     */
    public void setAnsType(Long ansType) {
        this.ansType = ansType;
    }

    /**
     * @return MULTI_ANS_TYPE
     */
    public Long getMultiAnsType() {
        return multiAnsType;
    }

    /**
     * @param multiAnsType
     */
    public void setMultiAnsType(Long multiAnsType) {
        this.multiAnsType = multiAnsType;
    }

    /**
     * @return SUB_OBJ
     */
    public Long getSubObj() {
        return subObj;
    }

    /**
     * @param subObj
     */
    public void setSubObj(Long subObj) {
        this.subObj = subObj;
    }

    /**
     * @return TEST_SKILL
     */
    public Long getTestSkill() {
        return testSkill;
    }

    /**
     * @param testSkill
     */
    public void setTestSkill(Long testSkill) {
        this.testSkill = testSkill;
    }

    /**
     * @return QUES_TAG
     */
    public Long getQuesTag() {
        return quesTag;
    }

    /**
     * @param quesTag
     */
    public void setQuesTag(Long quesTag) {
        this.quesTag = quesTag;
    }

    /**
     * @return OLD_CODE
     */
    public String getOldCode() {
        return oldCode;
    }

    /**
     * @param oldCode
     */
    public void setOldCode(String oldCode) {
        this.oldCode = oldCode;
    }

    /**
     * @return OLD_CODE_MULTI
     */
    public String getOldCodeMulti() {
        return oldCodeMulti;
    }

    /**
     * @param oldCodeMulti
     */
    public void setOldCodeMulti(String oldCodeMulti) {
        this.oldCodeMulti = oldCodeMulti;
    }
}