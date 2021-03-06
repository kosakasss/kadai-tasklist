package models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "getAllTasks",
            query = "SELECT m FROM Task AS m ORDER BY m.id DESC"
            ),
    @NamedQuery(
            name = "getTasksCount",
            query = "SELECT COUNT(m) FROM Task AS m"
    )
})
@Table(name = "tasks")
public class Task {
    //ID
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //タスク内容
    @Column(name = "content", length = 255, nullable = false)
    private String content;

    //作成日時
    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    //更新日時
    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    //タスクの進捗具合（1.未完了　3.完了）
    @Column(name = "status_flag")
    private int status_flag;

    //締切日
    @Column(name = "deadline", nullable = false)
    private Date deadline;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public int getStatus_flag() {
        return status_flag;
    }

    public void setStatus_flag(int status_flag) {
        this.status_flag = status_flag;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
 }

