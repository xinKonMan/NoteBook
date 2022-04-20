package com.xinkon.notebook.entity;

/**
 * @author xinkonman
 * @desription:
 * @date: 2022/4/20 15:02
 */

public class Note {
    //主键
    private long id;
    //标题
    private String title;
    //内容
    private String content;
    //记录时间
    private String time;
    //tag分类
    private int tag;
    //是否已经收藏
    private int collect;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public Note() {

    }

    public Note(String title, String content, String time, int tag, int collect) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.tag = tag;
        this.collect = collect;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", tag=" + tag +
                ", collect=" + collect +
                '}';
    }
}
