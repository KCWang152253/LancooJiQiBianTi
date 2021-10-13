package com.lancoo.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "Lgdb_Ques_Basicinfo_CBH")
public class LgdbQuesBasicinfoCbh {
    @Id
    @Column(name = "Guid")
    private String guid;

    @Column(name = "Res_DigitizationId")
    private String resDigitizationid;

    @Column(name = "TaskId")
    private String taskid;

    @Column(name = "PackageId")
    private String packageid;

    @Column(name = "ResStatus")
    private Integer resstatus;

    @Column(name = "PackageStatus")
    private Integer packagestatus;

    @Column(name = "ResPath")
    private String respath;

    @Column(name = "ResSize")
    private Long ressize;

    @Column(name = "ResFilenum")
    private Long resfilenum;

    @Column(name = "ResMd5Code")
    private String resmd5code;

    @Column(name = "QuesContent")
    private String quescontent;

    @Column(name = "QuesType")
    private String questype;

    @Column(name = "QuesChildnum")
    private Long queschildnum;

    @Column(name = "QuesChildcount")
    private Long queschildcount;

    @Column(name = "QuesGenre")
    private Long quesgenre;

    @Column(name = "QuesMediaUrl")
    private String quesmediaurl;

    @Column(name = "DurationLength")
    private Long durationlength;

    @Column(name = "AudioPlayTimes")
    private Long audioplaytimes;

    @Column(name = "Res_Sub")
    private String resSub;

    @Column(name = "Res_Stage")
    private String resStage;

    @Column(name = "Res_Level")
    private String resLevel;

    @Column(name = "Res_Lib")
    private String resLib;

    @Column(name = "Res_Theme")
    private String resTheme;

    @Column(name = "Res_ThemeCode")
    private String resThemecode;

    @Column(name = "Res_Importantklg")
    private String resImportantklg;

    @Column(name = "Res_ImportantklgCode")
    private String resImportantklgcode;

    @Column(name = "Res_Mainklg")
    private String resMainklg;

    @Column(name = "Res_MainklgCode")
    private String resMainklgcode;

    @Column(name = "WordNum")
    private Long wordnum;

    @Column(name = "DBType")
    private Integer dbtype;

    @Column(name = "Datatable_Name")
    private String datatableName;

    @Column(name = "ReviewUserId")
    private String reviewuserid;

    @Column(name = "ReViewDate")
    private Date reviewdate;

    @Column(name = "CheckUserId")
    private String checkuserid;

    @Column(name = "CheckDate")
    private Date checkdate;

    @Column(name = "TOPIC_CODE")
    private String topicCode;

    @Column(name = "TOPIC_TEXT")
    private String topicText;

    @Column(name = "RELATED_KNOWLEDGE_CODE")
    private String relatedKnowledgeCode;

    @Column(name = "RELATED_KNOWLEDGE_TEXT")
    private String relatedKnowledgeText;

    @Column(name = "THEMATIC_KNOWLEDGE_CODE")
    private String thematicKnowledgeCode;

    @Column(name = "THEMATIC_KNOWLEDGE_TEXT")
    private String thematicKnowledgeText;

    @Column(name = "KnowledgeName")
    private String knowledgename;

    @Column(name = "Out_KnowledgeName")
    private String outKnowledgename;

    @Column(name = "Reason")
    private String reason;

    @Column(name = "Store_Date")
    private String storeDate;

    @Column(name = "Sentence")
    private String sentence;

    @Column(name = "QuesContentStn")
    private String quescontentstn;

    @Column(name = "IMPOR_KN_UNIQUECODE")
    private String imporKnUniquecode;

    @Column(name = "MAIN_KN_UNIQUECODE")
    private String mainKnUniquecode;

    @Column(name = "TOPIC_UNIQUECODE")
    private String topicUniquecode;

    @Column(name = "CommitDate")
    private Date commitdate;

    @Column(name = "NewProperty")
    private String newproperty;

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
     * @return Res_DigitizationId
     */
    public String getResDigitizationid() {
        return resDigitizationid;
    }

    /**
     * @param resDigitizationid
     */
    public void setResDigitizationid(String resDigitizationid) {
        this.resDigitizationid = resDigitizationid;
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
     * @return PackageId
     */
    public String getPackageid() {
        return packageid;
    }

    /**
     * @param packageid
     */
    public void setPackageid(String packageid) {
        this.packageid = packageid;
    }

    /**
     * @return ResStatus
     */
    public Integer getResstatus() {
        return resstatus;
    }

    /**
     * @param resstatus
     */
    public void setResstatus(Integer resstatus) {
        this.resstatus = resstatus;
    }

    /**
     * @return PackageStatus
     */
    public Integer getPackagestatus() {
        return packagestatus;
    }

    /**
     * @param packagestatus
     */
    public void setPackagestatus(Integer packagestatus) {
        this.packagestatus = packagestatus;
    }

    /**
     * @return ResPath
     */
    public String getRespath() {
        return respath;
    }

    /**
     * @param respath
     */
    public void setRespath(String respath) {
        this.respath = respath;
    }

    /**
     * @return ResSize
     */
    public Long getRessize() {
        return ressize;
    }

    /**
     * @param ressize
     */
    public void setRessize(Long ressize) {
        this.ressize = ressize;
    }

    /**
     * @return ResFilenum
     */
    public Long getResfilenum() {
        return resfilenum;
    }

    /**
     * @param resfilenum
     */
    public void setResfilenum(Long resfilenum) {
        this.resfilenum = resfilenum;
    }

    /**
     * @return ResMd5Code
     */
    public String getResmd5code() {
        return resmd5code;
    }

    /**
     * @param resmd5code
     */
    public void setResmd5code(String resmd5code) {
        this.resmd5code = resmd5code;
    }

    /**
     * @return QuesContent
     */
    public String getQuescontent() {
        return quescontent;
    }

    /**
     * @param quescontent
     */
    public void setQuescontent(String quescontent) {
        this.quescontent = quescontent;
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
     * @return QuesChildnum
     */
    public Long getQueschildnum() {
        return queschildnum;
    }

    /**
     * @param queschildnum
     */
    public void setQueschildnum(Long queschildnum) {
        this.queschildnum = queschildnum;
    }

    /**
     * @return QuesChildcount
     */
    public Long getQueschildcount() {
        return queschildcount;
    }

    /**
     * @param queschildcount
     */
    public void setQueschildcount(Long queschildcount) {
        this.queschildcount = queschildcount;
    }

    /**
     * @return QuesGenre
     */
    public Long getQuesgenre() {
        return quesgenre;
    }

    /**
     * @param quesgenre
     */
    public void setQuesgenre(Long quesgenre) {
        this.quesgenre = quesgenre;
    }

    /**
     * @return QuesMediaUrl
     */
    public String getQuesmediaurl() {
        return quesmediaurl;
    }

    /**
     * @param quesmediaurl
     */
    public void setQuesmediaurl(String quesmediaurl) {
        this.quesmediaurl = quesmediaurl;
    }

    /**
     * @return DurationLength
     */
    public Long getDurationlength() {
        return durationlength;
    }

    /**
     * @param durationlength
     */
    public void setDurationlength(Long durationlength) {
        this.durationlength = durationlength;
    }

    /**
     * @return AudioPlayTimes
     */
    public Long getAudioplaytimes() {
        return audioplaytimes;
    }

    /**
     * @param audioplaytimes
     */
    public void setAudioplaytimes(Long audioplaytimes) {
        this.audioplaytimes = audioplaytimes;
    }

    /**
     * @return Res_Sub
     */
    public String getResSub() {
        return resSub;
    }

    /**
     * @param resSub
     */
    public void setResSub(String resSub) {
        this.resSub = resSub;
    }

    /**
     * @return Res_Stage
     */
    public String getResStage() {
        return resStage;
    }

    /**
     * @param resStage
     */
    public void setResStage(String resStage) {
        this.resStage = resStage;
    }

    /**
     * @return Res_Level
     */
    public String getResLevel() {
        return resLevel;
    }

    /**
     * @param resLevel
     */
    public void setResLevel(String resLevel) {
        this.resLevel = resLevel;
    }

    /**
     * @return Res_Lib
     */
    public String getResLib() {
        return resLib;
    }

    /**
     * @param resLib
     */
    public void setResLib(String resLib) {
        this.resLib = resLib;
    }

    /**
     * @return Res_Theme
     */
    public String getResTheme() {
        return resTheme;
    }

    /**
     * @param resTheme
     */
    public void setResTheme(String resTheme) {
        this.resTheme = resTheme;
    }

    /**
     * @return Res_ThemeCode
     */
    public String getResThemecode() {
        return resThemecode;
    }

    /**
     * @param resThemecode
     */
    public void setResThemecode(String resThemecode) {
        this.resThemecode = resThemecode;
    }

    /**
     * @return Res_Importantklg
     */
    public String getResImportantklg() {
        return resImportantklg;
    }

    /**
     * @param resImportantklg
     */
    public void setResImportantklg(String resImportantklg) {
        this.resImportantklg = resImportantklg;
    }

    /**
     * @return Res_ImportantklgCode
     */
    public String getResImportantklgcode() {
        return resImportantklgcode;
    }

    /**
     * @param resImportantklgcode
     */
    public void setResImportantklgcode(String resImportantklgcode) {
        this.resImportantklgcode = resImportantklgcode;
    }

    /**
     * @return Res_Mainklg
     */
    public String getResMainklg() {
        return resMainklg;
    }

    /**
     * @param resMainklg
     */
    public void setResMainklg(String resMainklg) {
        this.resMainklg = resMainklg;
    }

    /**
     * @return Res_MainklgCode
     */
    public String getResMainklgcode() {
        return resMainklgcode;
    }

    /**
     * @param resMainklgcode
     */
    public void setResMainklgcode(String resMainklgcode) {
        this.resMainklgcode = resMainklgcode;
    }

    /**
     * @return WordNum
     */
    public Long getWordnum() {
        return wordnum;
    }

    /**
     * @param wordnum
     */
    public void setWordnum(Long wordnum) {
        this.wordnum = wordnum;
    }

    /**
     * @return DBType
     */
    public Integer getDbtype() {
        return dbtype;
    }

    /**
     * @param dbtype
     */
    public void setDbtype(Integer dbtype) {
        this.dbtype = dbtype;
    }

    /**
     * @return Datatable_Name
     */
    public String getDatatableName() {
        return datatableName;
    }

    /**
     * @param datatableName
     */
    public void setDatatableName(String datatableName) {
        this.datatableName = datatableName;
    }

    /**
     * @return ReviewUserId
     */
    public String getReviewuserid() {
        return reviewuserid;
    }

    /**
     * @param reviewuserid
     */
    public void setReviewuserid(String reviewuserid) {
        this.reviewuserid = reviewuserid;
    }

    /**
     * @return ReViewDate
     */
    public Date getReviewdate() {
        return reviewdate;
    }

    /**
     * @param reviewdate
     */
    public void setReviewdate(Date reviewdate) {
        this.reviewdate = reviewdate;
    }

    /**
     * @return CheckUserId
     */
    public String getCheckuserid() {
        return checkuserid;
    }

    /**
     * @param checkuserid
     */
    public void setCheckuserid(String checkuserid) {
        this.checkuserid = checkuserid;
    }

    /**
     * @return CheckDate
     */
    public Date getCheckdate() {
        return checkdate;
    }

    /**
     * @param checkdate
     */
    public void setCheckdate(Date checkdate) {
        this.checkdate = checkdate;
    }

    /**
     * @return TOPIC_CODE
     */
    public String getTopicCode() {
        return topicCode;
    }

    /**
     * @param topicCode
     */
    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    /**
     * @return TOPIC_TEXT
     */
    public String getTopicText() {
        return topicText;
    }

    /**
     * @param topicText
     */
    public void setTopicText(String topicText) {
        this.topicText = topicText;
    }

    /**
     * @return RELATED_KNOWLEDGE_CODE
     */
    public String getRelatedKnowledgeCode() {
        return relatedKnowledgeCode;
    }

    /**
     * @param relatedKnowledgeCode
     */
    public void setRelatedKnowledgeCode(String relatedKnowledgeCode) {
        this.relatedKnowledgeCode = relatedKnowledgeCode;
    }

    /**
     * @return RELATED_KNOWLEDGE_TEXT
     */
    public String getRelatedKnowledgeText() {
        return relatedKnowledgeText;
    }

    /**
     * @param relatedKnowledgeText
     */
    public void setRelatedKnowledgeText(String relatedKnowledgeText) {
        this.relatedKnowledgeText = relatedKnowledgeText;
    }

    /**
     * @return THEMATIC_KNOWLEDGE_CODE
     */
    public String getThematicKnowledgeCode() {
        return thematicKnowledgeCode;
    }

    /**
     * @param thematicKnowledgeCode
     */
    public void setThematicKnowledgeCode(String thematicKnowledgeCode) {
        this.thematicKnowledgeCode = thematicKnowledgeCode;
    }

    /**
     * @return THEMATIC_KNOWLEDGE_TEXT
     */
    public String getThematicKnowledgeText() {
        return thematicKnowledgeText;
    }

    /**
     * @param thematicKnowledgeText
     */
    public void setThematicKnowledgeText(String thematicKnowledgeText) {
        this.thematicKnowledgeText = thematicKnowledgeText;
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
     * @return Out_KnowledgeName
     */
    public String getOutKnowledgename() {
        return outKnowledgename;
    }

    /**
     * @param outKnowledgename
     */
    public void setOutKnowledgename(String outKnowledgename) {
        this.outKnowledgename = outKnowledgename;
    }

    /**
     * @return Reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return Store_Date
     */
    public String getStoreDate() {
        return storeDate;
    }

    /**
     * @param storeDate
     */
    public void setStoreDate(String storeDate) {
        this.storeDate = storeDate;
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
     * @return QuesContentStn
     */
    public String getQuescontentstn() {
        return quescontentstn;
    }

    /**
     * @param quescontentstn
     */
    public void setQuescontentstn(String quescontentstn) {
        this.quescontentstn = quescontentstn;
    }

    /**
     * @return IMPOR_KN_UNIQUECODE
     */
    public String getImporKnUniquecode() {
        return imporKnUniquecode;
    }

    /**
     * @param imporKnUniquecode
     */
    public void setImporKnUniquecode(String imporKnUniquecode) {
        this.imporKnUniquecode = imporKnUniquecode;
    }

    /**
     * @return MAIN_KN_UNIQUECODE
     */
    public String getMainKnUniquecode() {
        return mainKnUniquecode;
    }

    /**
     * @param mainKnUniquecode
     */
    public void setMainKnUniquecode(String mainKnUniquecode) {
        this.mainKnUniquecode = mainKnUniquecode;
    }

    /**
     * @return TOPIC_UNIQUECODE
     */
    public String getTopicUniquecode() {
        return topicUniquecode;
    }

    /**
     * @param topicUniquecode
     */
    public void setTopicUniquecode(String topicUniquecode) {
        this.topicUniquecode = topicUniquecode;
    }

    /**
     * @return CommitDate
     */
    public Date getCommitdate() {
        return commitdate;
    }

    /**
     * @param commitdate
     */
    public void setCommitdate(Date commitdate) {
        this.commitdate = commitdate;
    }

    /**
     * @return NewProperty
     */
    public String getNewproperty() {
        return newproperty;
    }

    /**
     * @param newproperty
     */
    public void setNewproperty(String newproperty) {
        this.newproperty = newproperty;
    }


    @Override
    public String toString() {
        return "LgdbQuesBasicinfoCbh{" +
                "guid='" + guid + '\'' +
                ", resDigitizationid='" + resDigitizationid + '\'' +
                ", taskid='" + taskid + '\'' +
                ", packageid='" + packageid + '\'' +
                ", resstatus=" + resstatus +
                ", packagestatus=" + packagestatus +
                ", respath='" + respath + '\'' +
                ", ressize=" + ressize +
                ", resfilenum=" + resfilenum +
                ", resmd5code='" + resmd5code + '\'' +
                ", quescontent='" + quescontent + '\'' +
                ", questype='" + questype + '\'' +
                ", queschildnum=" + queschildnum +
                ", queschildcount=" + queschildcount +
                ", quesgenre=" + quesgenre +
                ", quesmediaurl='" + quesmediaurl + '\'' +
                ", durationlength=" + durationlength +
                ", audioplaytimes=" + audioplaytimes +
                ", resSub='" + resSub + '\'' +
                ", resStage='" + resStage + '\'' +
                ", resLevel='" + resLevel + '\'' +
                ", resLib='" + resLib + '\'' +
                ", resTheme='" + resTheme + '\'' +
                ", resThemecode='" + resThemecode + '\'' +
                ", resImportantklg='" + resImportantklg + '\'' +
                ", resImportantklgcode='" + resImportantklgcode + '\'' +
                ", resMainklg='" + resMainklg + '\'' +
                ", resMainklgcode='" + resMainklgcode + '\'' +
                ", wordnum=" + wordnum +
                ", dbtype=" + dbtype +
                ", datatableName='" + datatableName + '\'' +
                ", reviewuserid='" + reviewuserid + '\'' +
                ", reviewdate=" + reviewdate +
                ", checkuserid='" + checkuserid + '\'' +
                ", checkdate=" + checkdate +
                ", topicCode='" + topicCode + '\'' +
                ", topicText='" + topicText + '\'' +
                ", relatedKnowledgeCode='" + relatedKnowledgeCode + '\'' +
                ", relatedKnowledgeText='" + relatedKnowledgeText + '\'' +
                ", thematicKnowledgeCode='" + thematicKnowledgeCode + '\'' +
                ", thematicKnowledgeText='" + thematicKnowledgeText + '\'' +
                ", knowledgename='" + knowledgename + '\'' +
                ", outKnowledgename='" + outKnowledgename + '\'' +
                ", reason='" + reason + '\'' +
                ", storeDate=" + storeDate +
                ", sentence='" + sentence + '\'' +
                ", quescontentstn='" + quescontentstn + '\'' +
                ", imporKnUniquecode='" + imporKnUniquecode + '\'' +
                ", mainKnUniquecode='" + mainKnUniquecode + '\'' +
                ", topicUniquecode='" + topicUniquecode + '\'' +
                ", commitdate=" + commitdate +
                ", newproperty='" + newproperty + '\'' +
                '}';
    }
}