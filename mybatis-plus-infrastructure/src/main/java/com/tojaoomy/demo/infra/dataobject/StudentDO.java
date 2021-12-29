package com.tojaoomy.demo.infra.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 学生
 * </p>
 *
 * @author MybatisPlusGenerator
 * @since 2021-12-29
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("student")
public class StudentDO extends Model<StudentDO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("age")
    private Integer age;

    @TableField("name")
    private String name;

    @TableField("telephone")
    private String telephone;

    @TableField("teacher_id")
    private Long teacherId;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
