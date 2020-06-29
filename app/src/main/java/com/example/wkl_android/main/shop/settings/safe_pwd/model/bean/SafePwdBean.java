package com.example.wkl_android.main.shop.settings.safe_pwd.model.bean;

/**
 * Created by szx
 * on 2020/1/6/006
 */
public class SafePwdBean {

    /**
     * answerOne : string
     * answerTwo : string
     * problemIdOne : 0
     * problemIdTwo : 0
     */

    private String answerOne;
    private String answerTwo;
    private String problemIdOne;
    private String problemIdTwo;

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public String getProblemIdOne() {
        return problemIdOne;
    }

    public void setProblemIdOne(String problemIdOne) {
        this.problemIdOne = problemIdOne;
    }

    public String getProblemIdTwo() {
        return problemIdTwo;
    }

    public void setProblemIdTwo(String problemIdTwo) {
        this.problemIdTwo = problemIdTwo;
    }
}
