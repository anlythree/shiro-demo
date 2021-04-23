package top.anly.business.user.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wangli
 * @date 2021/4/11 14:32
 */
@Data
@TableName("user_desc")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String account;

    private String userName;

    private String passWord;

    private String sale;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String email;

    private Integer status;
}
