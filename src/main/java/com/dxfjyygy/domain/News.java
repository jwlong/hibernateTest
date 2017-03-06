package com.dxfjyygy.domain;

/**
 * Created by longjinwen on 2017/2/9.
 */
public class News
{
    //消息类的标识属性
    private Integer id;
    //消息标题
    private String title;
    //消息内容
    private String content;
    //消息全部内容，由系统根据公式生成
    private String fullContent;

    //id属性的setter和getter方法
    public void setId(Integer id)
    {
        this.id = id;
    }
    public Integer getId()
    {
        return this.id;
    }

    //title属性的setter和getter方法
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getTitle()
    {
        return this.title;
    }

    //content属性的setter和getter方法
    public void setContent(String content)
    {
        this.content = content;
    }
    public String getContent()
    {
        return this.content;
    }

    //fullContent属性的setter和getter方法
    public void setFullContent(String fullContent)
    {
        this.fullContent = fullContent;
    }
    public String getFullContent()
    {
        return this.fullContent;
    }
}
