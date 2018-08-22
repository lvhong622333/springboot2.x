package com.lvhong.web.mapper;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.lvhong.web.pojo.TmSysUser;

public interface UserEsMapper extends ElasticsearchRepository<TmSysUser, Long> {

}
