package com.lancoo.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "Lgdb_TaskInfo")
public class LgdbTaskinfo {
    @Id
    @Column(name = "TaskId")
    private String taskid;

    @Column(name = "KnowledgeCodes")
    private String knowledgecodes;

    @Column(name = "Stage")
    private String stage;

    @Column(name = "QuesType")
    private String questype;

    @Column(name = "Result")
    private String result;

    @Column(name = "TaskDate")
    private Date taskdate;

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
     * @return KnowledgeCodes
     */
    public String getKnowledgecodes() {
        return knowledgecodes;
    }

    /**
     * @param knowledgecodes
     */
    public void setKnowledgecodes(String knowledgecodes) {
        this.knowledgecodes = knowledgecodes;
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
     * @return Result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return TaskDate
     */
    public Date getTaskdate() {
        return taskdate;
    }

    /**
     * @param taskdate
     */
    public void setTaskdate(Date taskdate) {
        this.taskdate = taskdate;
    }
}