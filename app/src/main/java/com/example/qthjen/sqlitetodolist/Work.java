package com.example.qthjen.sqlitetodolist;

public class Work {

    private String nameWork;
    private int idWork;

    public Work(int idWork, String nameWork) {

        this.idWork = idWork;
        this.nameWork = nameWork;

    }

    public int getIdWork() {
        return idWork;
    }

    public void setIdWork(int idWork) {
        this.idWork = idWork;
    }

    public String getNameWork() {
        return nameWork;
    }

    public void setNameWork(String nameWork) {
        this.nameWork = nameWork;
    }



}
