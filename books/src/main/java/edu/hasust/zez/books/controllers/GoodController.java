package edu.hasust.zez.books.controllers;

import edu.hasust.zez.books.RepErrorCode;
import edu.hasust.zez.books.ResultCode;
import edu.hasust.zez.books.entities.Good;
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
        return ResultCode.ok("获取成功", goodWrapper.getUserGoods(userId));
    }

    @RequestMapping("/delete_user_good")
    @Validated
    public ResultCode deleteUserGood(
            @NotNull @Min(value = 0, message = "商品 id 不能小于 0") Integer id,
            HttpSession session
    ) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        UserView user = userWrapper.getUserInfo(userId);
        if(user == null) {
            return ResultCode.err(RepErrorCode.USER_LEVEL_ERROR);
        }
        Good good = goodWrapper.getGoodById(id);
        if(good.getStatus() != 0) {
            return ResultCode.err(RepErrorCode.GOOD_STATUS_NOT_DELETE);
        }
        goodWrapper.deleteUserGood(id, userId);
        return ResultCode.ok("删除成功", null);
    }

    @RequestMapping("/delete_good")
    @Validated
    public ResultCode deleteGood(
            @NotNull @Min(value = 0, message = "商品 id 不能小于 0") Integer id,
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
        Good good = goodWrapper.getGoodById(id);
        if(good==null) {
            return ResultCode.err(RepErrorCode.GOOD_NOT_FOUND);
        }
        goodWrapper.deleteGood(id);
        logWrapper.addSystemLog(user.getUsername() + " 删除商品 " + good.getName(), user.getId());
        return ResultCode.ok("删除成功", null);
    }

    @RequestMapping("/get_available_goods")
    @Validated
    public ResultCode getAvailableGoods() throws SQLException {
        return ResultCode.ok("获取成功", goodWrapper.getAllAvailableGoods());
    }

    @RequestMapping("/get_all_goods")
    @Validated
    public ResultCode getAllGoods(HttpSession session) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        UserView user = userWrapper.getUserInfo(userId);
        if(user == null || user.getLevel() <= 90) {
            return ResultCode.err(RepErrorCode.USER_LEVEL_ERROR);
        }
        return ResultCode.ok("获取成功", goodWrapper.getAllGoods());
    }

    @RequestMapping("/change_good_approved")
    @Validated
    public ResultCode ChangeGoodApprove(
            @NotNull @Min(value = 0, message = "商品 id 不能小于 0") Integer id,
            @NotNull @Min(value = 0, message = "商品 id 不能小于 0") Integer approved,
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
        Good good = goodWrapper.getGoodById(id);
        if(good == null) {
            return ResultCode.err(RepErrorCode.GOOD_NOT_FOUND);
        }
        goodWrapper.updateGoodApproved(id, approved);
        if(approved == 0) {
            logWrapper.addSystemLog(user.getUsername() + " 通过商品 " + good.getName(), user.getId());
        } else {
            logWrapper.addSystemLog(user.getUsername() + " 下架商品 " + good.getName(), user.getId());
        }
        return ResultCode.ok("更改成功", null);
    }

}
