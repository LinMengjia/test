package com.example.demo.controller;

/**
 * Create By Linmengjia on 2020/9/2.
 */

import com.example.demo.entity.ReturnView;
import com.example.demo.entity.User;
import io.swagger.annotations.*;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 一个用来测试swagger注解的控制器
 * 注意@ApiImplicitParam的使用会影响程序运行，如果使用不当可能造成控制器收不到消息
 *
 * @author SUNF
 */

/**
 *  paramType：表示参数放在哪个地方
 *     header-->请求参数的获取：@RequestHeader(代码中接收注解)
 *     query-->请求参数的获取：@RequestParam(代码中接收注解)
 *     path（用于restful接口）-->请求参数的获取：@PathVariable(代码中接收注解)
 *     body-->请求参数的获取：@RequestBody(代码中接收注解)
 *     form（不常用）
 */
@RestController
@RequestMapping("/say")
@Api(value = "SayController|一个用来测试swagger注解的控制器")
public class SayController {

    @ResponseBody
    @RequestMapping(value ="/getUserName", method= RequestMethod.GET)
    @ApiOperation(value="根据用户编号获取用户姓名", notes="test: 仅1和2有正确返回",code = 200)
    @ApiImplicitParam(paramType="query", name = "userNumber", value = "用户编号", required = true, dataType = "int")
    @ApiModelProperty(example = "1")
    public Map<String,Object> getUserName(@RequestParam Integer userNumber){
        User user = new User();
        Map<String ,Object> map = new HashMap<>();
        if(userNumber == 1){
            user.setUserNumber(userNumber);
            user.setUserName("我是1号");
        }
        else if(userNumber == 2){
            user.setUserNumber(userNumber);
            user.setUserName("我是2号");
        }
        else{
            user.setUserNumber(userNumber);
            user.setUserName("未知用户");
        }
        map.put("dataViews",user);
        map.put("resultCode",200);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/updatePassword",method= RequestMethod.GET)
    @ApiOperation(value="修改用户密码", notes="根据用户id修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户ID", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "password", value = "旧密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "newPassword", value = "新密码", required = true, dataType = "String")
    })
    public String updatePassword(@RequestParam(value="userId") Integer userId, @RequestParam(value="password") String password,
                                 @RequestParam(value="newPassword") String newPassword){
        if(userId <= 0 || userId > 2){
            return "未知的用户";
        }
        if(StringUtils.isEmpty(password) || StringUtils.isEmpty(newPassword)){
            return "密码不能为空";
        }
        if(password.equals(newPassword)){
            return "新旧密码不能相同";
        }
        return "密码修改成功!";
    }
}