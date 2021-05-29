/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.ac.nig.ddbj.webui.imputation.controller;

import java.util.List;
import jp.ac.nig.ddbj.webui.imputation.domain.user.model.MUser;
import jp.ac.nig.ddbj.webui.imputation.domain.user.service.UserService;
import jp.ac.nig.ddbj.webui.imputation.form.UserListForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author oogasawa
 */

@Controller
@RequestMapping("/user")
public class UserJobListController {
 
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    /** ユーザー一覧画面を表示 */
    @GetMapping("/jobList")
    public String getUserList(@ModelAttribute UserListForm form, Model model) {

        // formをMUserクラスに変換
        MUser user = modelMapper.map(form, MUser.class);

        // ユーザー一覧取得
        List<MUser> userList = userService.getUsers(user);

        // Modelに登録
        model.addAttribute("userList", userList);

        // ユーザー一覧画面を表示
        return "user/jobList";
    }

    /** ユーザー検索処理 */
    @PostMapping("/jobList")
    public String postUserList(@ModelAttribute UserListForm form, Model model) {

        // formをMUserクラスに変換
        MUser user = modelMapper.map(form, MUser.class);

        // ユーザー検索
        List<MUser> userList = userService.getUsers(user);

        // Modelに登録
        model.addAttribute("userList", userList);

        // ユーザー一覧画面を表示
        return "user/jobList";
    }   
}
