package com.example.mall.controller;


import org.springframework.web.bind.annotation.*;

/**
 * @author lala
 */
@RestController
@RequestMapping(value="/user")     // 通过这里配置使下面的映射都在/users下，可去除
public class UserController {


    /**
     * @api {get} /user/:id  获取用户信息
     * @apiName GetUser
     * @apiGroup User
     *
     * @apiParam {Number} id Users unique ID.
     *
     * @apiSuccess {String} firstname Firstname of the User.
     * @apiSuccess {String} lastname  Lastname of the User.
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *       "firstname": "John",
     *       "lastname": "Doe"
     *     }
     *
     * @apiError UserNotFound The id of the User was not found.
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 404 Not Found
     *     {
     *       "error": "UserNotFound"
     *     }
     */
    @RequestMapping(value = "{/id}", method = RequestMethod.GET)
    public String getUser(@PathVariable(name = "id")long id) {
        return "用户id为" + id;
    }


}
