package test;

import java.io.Serializable;
import java.util.Date;

/**
 * t_user_address
 * @author 
 */
public class UserAddress implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户主键
     */
    private Integer userid;

    /**
     * 地址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 是否是默认地址（1:是 0否）
     */
    private Integer isdefault;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}