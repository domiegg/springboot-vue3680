package cn.zwz.card.entity;

import cn.zwz.basics.baseClass.ZwzBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * 电话卡 实体类
 * @author 
 * CSDN: Designer 小郑
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_calling_card")
@TableName("a_calling_card")
@ApiModel(value = "电话卡")
public class CallingCard extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "电话卡号码")
    private String number;

    @ApiModelProperty(value = "所属门店")
    private String shop;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "预订人ID")
    private String userId;

    @ApiModelProperty(value = "预订人")
    private String userName;

    @ApiModelProperty(value = "套餐")
    private String pa;
}
