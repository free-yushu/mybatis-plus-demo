package com.tojaoomy.demo.infra.dataobject;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author 玉书
 * @since 2021-12-25
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_order")
public class OrderDO extends Model<OrderDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(value = "ctime", fill = FieldFill.INSERT)
    private LocalDateTime ctime;

    /**
     * 更新时间
     */
    @TableField(value = "mtime", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime mtime;

    /**
     * 是否删除
     */
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    @TableField("order_id")
    private String orderId;

    /**
     * 是否开启工作流
     */
    @TableField("version")
    @Version
    private Integer version;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
