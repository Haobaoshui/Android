package xyz.hbs.huarongdao.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.hbs.huarongdao.model.Page;
import xyz.hbs.huarongdao.model.User;
import xyz.hbs.huarongdao.service.UserService;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("user/v1")
public class UserController {


    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody User user) {

        return userService.add(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public int delete(@RequestBody User user) {
        return userService.delete(user);

    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public int update(@RequestBody User user) {
        return userService.update(user);
    }


    /**
     * 分页查询
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Page<User> getPage(@RequestParam(value = "pageNo", required = false) Integer pageNo,
                              @RequestParam(value = "pageSize", required = false) Integer pageSize) {


        pageNo = pageNo == null ? 1 : (pageNo < 1 ? 1 : pageNo);
        pageSize = pageSize == null ? 10 : (pageSize < 10 ? 10 : pageSize);
        return userService.getPage(pageNo, pageSize);
    }

    /**
     * 带查询的分页查询
     *
     * @param searchText
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "searchpage", method = RequestMethod.GET)
    public Page<User> getSearchedPage(@RequestParam(value = "searchText", required = true) String searchText,
                                        @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize) {


        pageNo = pageNo == null ? 1 : (pageNo < 1 ? 1 : pageNo);
        pageSize = pageSize == null ? 10 : (pageSize < 10 ? 10 : pageSize);
        return userService.getSearchedPage(searchText, pageNo, pageSize);
    }


}
