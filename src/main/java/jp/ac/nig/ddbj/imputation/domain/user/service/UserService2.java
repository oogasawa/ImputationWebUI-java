/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.ac.nig.ddbj.imputation.domain.user.service;


import java.util.List;

import jp.ac.nig.ddbj.imputation.domain.user.model.User;

public interface UserService2 {

    /** ユーザー登録 */
    public void signup(User user);

    /** ユーザー取得 */
    public List<User> getUsers(User user);

    /** ユーザー取得(1件) */
    public User getUserOne(String id);

    /** ユーザー更新(1件) */
    public void updateUserOne(String id,
            String password,
            String userName);

    /** ユーザー削除(1件) */
    public void deleteUserOne(String id);

    /** ログインユーザー情報取得 */
    public User getLoginUser(String id);
}