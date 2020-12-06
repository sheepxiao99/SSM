package com.ssm.dao;

import com.ssm.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountdao {

    @Select("select * from account")
    public List<Account> findAll();

    @Insert("insert into account(name,money) value(#{name},#{money})")
    public void saveAccount(Account account);

}
