package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/21 15:22
 * @desc
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "prod_define")
public class ProdDefine implements Serializable {

    @Id
    private Integer id;

    @Column(name = "prod_num")
    private String prodNum;

    @Column(name = "prod_name")
    private String prodName;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "create_user_id")
    private String createUserId;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "update_user_id")
    private String updateUserId;
}
