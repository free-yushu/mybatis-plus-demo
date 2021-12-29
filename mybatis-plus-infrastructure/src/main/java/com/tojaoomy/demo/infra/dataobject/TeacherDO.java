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
 * 教师
 * </p>
 *
 * @author MybatisPlusGenerator
 * @since 2021-12-29
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("teacher")
public class TeacherDO extends Model<TeacherDO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("level")
    private Integer level;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
