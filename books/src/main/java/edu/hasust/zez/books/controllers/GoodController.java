package edu.hasust.zez.books.controllers;

import edu.hasust.zez.books.RepErrorCode;
import edu.hasust.zez.books.ResultCode;
import edu.hasust.zez.books.entities.GoodType;
import edu.hasust.zez.books.entities.UserRole;
import edu.hasust.zez.books.entities.UserView;
import edu.hasust.zez.books.wrappers.GoodWrapper;
import edu.hasust.zez.books.wrappers.LogWrapper;
import edu.hasust.zez.books.wrappers.UserWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/good")
public class GoodController {
    @Resource
    UserWrapper userWrapper;
    @Resource
    LogWrapper logWrapper;
    @Resource
    GoodWrapper goodWrapper;
    @Resource
    HttpSession session;

    @RequestMapping("/get_good_types")
    public ResultCode getGoodTypes() throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        UserView user = userWrapper.getUserInfo(userId);
        if(user == null || user.getLevel() <= 90) {
            return ResultCode.err(RepErrorCode.USER_LEVEL_ERROR);
        }
        return ResultCode.ok("获取成功", goodWrapper.getGoodTypes());
    }

    @RequestMapping("/add_good_type")
    @Validated
    public ResultCode addGoodType(
            @NotNull String typeName,
            HttpSession session
    ) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        UserView user = userWrapper.getUserInfo(userId);
        if(user == null || user.getLevel() <= 90) {
            return ResultCode.err(RepErrorCode.USER_LEVEL_ERROR);
        }
        if(goodWrapper.getGoodTypeByName(typeName) != null) {
            return ResultCode.err(RepErrorCode.GOOD_TYPE_ALREADY_EXISTS);
        }
        goodWrapper.addGoodType(typeName);
        logWrapper.addSystemLog(user.getUsername() + " 添加商品类别 " + typeName, user.getId());
        return ResultCode.ok("添加成功", null);
    }

    @RequestMapping("/delete_good_type")
    @Validated
    public ResultCode deleteGoodType(
            @NotNull @Min(value = 0, message = "商品类别 id 不能小于 0") Integer id,
            HttpSession session
    ) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        UserView user = userWrapper.getUserInfo(userId);
        if(user == null || user.getLevel() <= 90) {
            return ResultCode.err(RepErrorCode.USER_LEVEL_ERROR);
        }
        if(id <= 1) {
            return ResultCode.err(RepErrorCode.GOOD_TYPE_DELETE_NESTED);
        }
        GoodType goodType = goodWrapper.getGoodTypeById(id);
        goodWrapper.deleteGoodTypeById(id);
        logWrapper.addSystemLog(user.getUsername() + " 删除商品类别 " + goodType.getTypeName(), user.getId());
        return ResultCode.ok("删除成功", null);
    }

    @RequestMapping("/add_good")
    @Validated
    public ResultCode addGood(
            @NotNull String name,
            @NotNull @Min(value = 0, message = "价格不能小于 0") Double price,
            @NotNull String descript,
            @NotNull Integer type,
            @NotNull String cover,
            HttpSession session
    ) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        UserView user = userWrapper.getUserInfo(userId);
        if(user == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_FOUND);
        }
        goodWrapper.addGood(name, userId, descript, cover, type, price);
        return ResultCode.ok("添加成功", null);
    }

    @RequestMapping("/get_user_goods")
    @Validated
    public ResultCode getUserGoods(HttpSession session) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        UserView user = userWrapper.getUserInfo(userId);
        if(user == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_FOUND);
        }
        return ResultCode.ok("删除成功", goodWrapper.getUserGoods(userId));
    }

}
