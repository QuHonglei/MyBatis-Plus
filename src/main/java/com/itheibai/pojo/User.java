package com.itheibai.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data //Getter,Setter,equals,canEqual,hasCode,toString等方法
@AllArgsConstructor //带参构造
@NoArgsConstructor  //无参构造
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @Version // 乐观锁Version注解
    private Integer version;
    @TableLogic
    private Integer deleted;
    //字段添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
