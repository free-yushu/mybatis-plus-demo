CREATE TABLE t_order_complaint_flow_mapping(
    id BIGINT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    ctime DATETIME NOT NULL default CURRENT_TIMESTAMP COMMENT '创建时间' ,
    mtime DATETIME NOT NULL default CURRENT_TIMESTAMP  COMMENT '更新时间' ,
    deleted INT NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    flow_id BIGINT NOT NULL  DEFAULT 0 COMMENT '工作流id' ,
    flow_detail_id BIGINT NOT NULL  DEFAULT 0 COMMENT '工作流明细id' ,
    is_active_flow INT NOT NULL  DEFAULT 0 COMMENT '是否开启工作流' ,
    version INT NOT NULL  DEFAULT 0 COMMENT '乐观锁' ,
    flow_status VARCHAR(128) NOT NULL  DEFAULT 'init' COMMENT '工作流状态;init-初始化,processing-处理中, approved-已审批' ,
    PRIMARY KEY (id)
);

CREATE TABLE t_order(
    id BIGINT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    ctime DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    mtime DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    deleted INT NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    order_id varchar(64) NOT NULL DEFAULT '',
    version INT NOT NULL  DEFAULT 0 COMMENT '是否开启工作流' ,
    PRIMARY KEY (id)
);