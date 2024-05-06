package com.kerem.quizspringbootproject.Constants;

public class EndPoints {
    public static final String VERSION = "/v1";

    //profiller:
    public static final String API = "/api";
    public static final String DEV = "/dev";
    public static final String TEST = "/test";
    public static final String ROOT = API + VERSION;

    //entityler:
    public static final String QUESTION = "/question";
    public static final String ANSWER = "/answer";


    //Methods:

    public static final String SAVE = "/save";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String FINDALL = "/findall";
    public static final String FINDBYID = "/findbyid";
    public static final String DELETEBYQUESTIONID = "/deletebyquestionid";
    public static final String FINDBYQUESTIONID = "/findbyquestionid";
    public static final String GETQUESTIONSWITHANSWERS = "/getquestionswithanswers";

}
