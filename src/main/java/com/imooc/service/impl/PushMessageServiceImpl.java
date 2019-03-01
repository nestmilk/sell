package com.imooc.service.impl;

import com.imooc.config.WechatAccountConfig;
import com.imooc.dto.OrderDTO;
import com.imooc.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @ Author     ：nestmilk
 * @ Date       ：Created in  2018/10/20 16:10
 */
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {
    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig accountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));
        templateMessage.setToUser(orderDTO.getBuyerOpenid());

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first","亲，记得收货。"),
                new WxMpTemplateData("keyword1",orderDTO.getOrderId()),
                new WxMpTemplateData("keyword2",orderDTO.getOrderStatusEnum().getMessage()),
                new WxMpTemplateData("keyword3","顺丰快递"),
                new WxMpTemplateData("keyword4","555656545"),
                new WxMpTemplateData("keyword5",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderDTO.getUpdateTime())),
                new WxMpTemplateData("remark","来自庞碧自然")
        );

        templateMessage.setData(data);

        try{
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch(WxErrorException e) {
            log.error("【微信模板消息】发送失败，{}",e.getMessage());
        }
    }
}
