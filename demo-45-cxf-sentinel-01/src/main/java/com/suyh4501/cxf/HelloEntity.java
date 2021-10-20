package com.suyh4501.cxf;

public class HelloEntity {

    Long id;

    String msg;

    public HelloEntity() {
    }

    public HelloEntity(String msg) {
        this.msg = msg;
    }

    public HelloEntity(Long id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "HelloEntity{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                '}';
    }
}
