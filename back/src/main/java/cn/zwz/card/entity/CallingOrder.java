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
 * 电话卡预定 实体类
 * @author 
 * CSDN: Designer 小郑
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_calling_order")
@TableName("a_calling_order")
@ApiModel(value = "电话卡预定")
public class CallingOrder extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "卡ID")
    private String cardId;

    @ApiModelProperty(value = "卡号码")
    private String cardNumber;

    @ApiModelProperty(value = "预订人ID")
    private String userId;

    @ApiModelProperty(value = "预订人")
    private String userName;

    @ApiModelProperty(value = "套餐")
    private String pa;

    @ApiModelProperty(value = "获取方式")
    private String way;

    @ApiModelProperty(value = "物流信息")
    private String wayData;
}
