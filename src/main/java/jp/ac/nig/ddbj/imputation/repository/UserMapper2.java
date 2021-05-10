/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.ac.nig.ddbj.imputation.repository;

import java.util.List;
import jp.ac.nig.ddbj.imputation.domain.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author oogasawa
 */
@Mapper
public interface UserMapper2 {
    public int insertOne(User user);
    
    
        /** ユーザー取得 */
    public List<User> findMany(User user);

    /** ユーザー取得(1件) */
    public User findOne(String id);

    /** ユーザー更新(1件) */
    public void updateOne(@Param("userId") String userId,
            @Param("password") String password,
            @Param("userName") String userName);

    /** ユーザー削除(1件) */
    public int deleteOne(@Param("userId") String userId);
    
        /** ログインユーザー取得 */
    public User findLoginUser(String userId);

}
