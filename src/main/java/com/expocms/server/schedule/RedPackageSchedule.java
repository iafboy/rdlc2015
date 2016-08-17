package com.expocms.server.schedule;

import com.expocms.server.db.dao.RdActivitygiftMapper;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Administrator on 2015/9/22.
 */
@Component("redPackageSchedule")
public class RedPackageSchedule {

    @Autowired
    private RdAppuserMapper mapper;

    @Autowired
    private RdActivitygiftMapper giftMapper;

    public void scanExpiredRedPackage(){
//        List<RdActivitygift> elist = giftMapper.queryUnExpiredGift();
//        Map<String,PackageTipEntity> map = new HashMap<String,PackageTipEntity>();
//        for (RdActivitygift gift:elist){
//            String ids = gift.getIds();
//            RdAppuser fixed_u = mapper.queryAppUserByFixedPackage(ids);
//            RdAppuser activity_u = mapper.queryAppUserByActivityPackage(ids);
//            if(fixed_u != null){
//                if(!map.containsKey(fixed_u.getIds())){
//                    PackageTipEntity p = new PackageTipEntity();
//                    p.setUserName(fixed_u.getUsername());
//                    p.setPhone(fixed_u.getPhone());
//                    p.setSex(fixed_u.getSex());
//                    List<SendablePackageInfo> list = new ArrayList<SendablePackageInfo>();
//                    SendablePackageInfo s = new SendablePackageInfo();
//                    s.setIds(gift.getIds());
//                    s.setAmount(gift.getValue());
//                    s.setTipType(SendablePackageInfo.TIP_TYPE_EXPIRE);
//                    s.setType(SendablePackageInfo.TYPE_FIXED);
//                    list.add(s);
//                    p.setGifts(list);
//                    map.put(fixed_u.getIds(),p);
//                }else{
//                    PackageTipEntity p = map.get(fixed_u.getIds());
//                    List<SendablePackageInfo> list = p.getGifts();
//                    SendablePackageInfo s = new SendablePackageInfo();
//                    s.setIds(gift.getIds());
//                    s.setAmount(gift.getValue());
//                    s.setTipType(SendablePackageInfo.TIP_TYPE_EXPIRE);
//                    s.setType(SendablePackageInfo.TYPE_FIXED);
//                    list.add(s);
//                }
//            }
//            if(activity_u != null){
//                if(!map.containsKey(activity_u.getIds())){
//                    PackageTipEntity p = new PackageTipEntity();
//                    p.setUserName(activity_u.getUsername());
//                    p.setPhone(activity_u.getPhone());
//                    p.setSex(activity_u.getSex());
//                    List<SendablePackageInfo> list = new ArrayList<SendablePackageInfo>();
//                    SendablePackageInfo s = new SendablePackageInfo();
//                    s.setIds(gift.getIds());
//                    s.setAmount(gift.getValue());
//                    s.setTipType(SendablePackageInfo.TIP_TYPE_EXPIRE);
//                    s.setType(SendablePackageInfo.TYPE_ACTIVITY);
//                    list.add(s);
//                    p.setGifts(list);
//                    map.put(activity_u.getIds(),p);
//                }else{
//                    PackageTipEntity p = map.get(activity_u.getIds());
//                    List<SendablePackageInfo> list = p.getGifts();
//                    SendablePackageInfo s = new SendablePackageInfo();
//                    s.setIds(gift.getIds());
//                    s.setAmount(gift.getValue());
//                    s.setTipType(SendablePackageInfo.TIP_TYPE_EXPIRE);
//                    s.setType(SendablePackageInfo.TYPE_ACTIVITY);
//                    list.add(s);
//                }
//            }
//        }
//        Set<Map.Entry<String,PackageTipEntity>> set =   map.entrySet();
//        for(Map.Entry<String,PackageTipEntity> entry : set){
//            PackageTipEntity value = entry.getValue();
//            value.setGiftCount(value.getGifts().size());
//            double amount = 0;
//            for(SendablePackageInfo s: value.getGifts()){
//                amount += s.getAmount();
//            }
//            value.setTotalAmount(amount);
//            String msg = value.toString();
//            String phone = value.getPhone();
//            sendMsg(phone,msg);
//        }

    }
    private void sendMsg(String phone,String msg){
        // send msg
        System.out.println("====MSG SENDING ...====="+phone+":::::::"+msg);
    }
}
