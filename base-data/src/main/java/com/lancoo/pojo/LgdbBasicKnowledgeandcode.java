package com.lancoo.pojo;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "Lgdb_Basic_KnowledgeAndCode")
public class LgdbBasicKnowledgeandcode {
    @Column(name = "Knowledge")
    private String knowledge;

    @Column(name = "KnowledgeCode")
    private String knowledgecode;

    @Column(name = "Stage")
    private String stage;

    @Column(name = "SubSubject")
    private String subsubject;

    @Column(name = "Topic")
    private String topic;

    @Column(name = "Theme")
    private String theme;

    @Column(name = "Is_shield")
    private Integer isShield;

    /**
     * @return Knowledge
     */
    public String getKnowledge() {
        return knowledge;
    }

    /**
     * @param knowledge
     */
    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
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
     * @return Stage
     */
    public String getStage() {
        return stage;
    }

    /**
     * @param stage
     */
    public void setStage(String stage) {
        this.stage = stage;
    }

    /**
     * @return SubSubject
     */
    public String getSubsubject() {
        return subsubject;
    }

    /**
     * @param subsubject
     */
    public void setSubsubject(String subsubject) {
        this.subsubject = subsubject;
    }

    /**
     * @return Topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @param topic
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * @return Theme
     */
    public String getTheme() {
        return theme;
    }

    /**
     * @param theme
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * @return Is_shield
     */
    public Integer getIsShield() {
        return isShield;
    }

    /**
     * @param isShield
     */
    public void setIsShield(Integer isShield) {
        this.isShield = isShield;
    }
}