package com.tojaoomy.demo.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tojaoomy.demo.infra.dataobject.StudentDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 学生 Mapper 接口
 * </p>
 *
 * @author MybatisPlusGenerator
 * @since 2021-12-29
 */
@Mapper
public interface StudentMapper extends BaseMapper<StudentDO> {

}
