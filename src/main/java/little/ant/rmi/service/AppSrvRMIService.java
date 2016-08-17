package little.ant.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import little.ant.payment.pojo.GetDynTr2Entity;
import little.ant.payment.pojo.IndAuthEntity;
import little.ant.payment.pojo.IndAuthVerifyEntity;
import little.ant.payment.pojo.Pay2BankEntity;
import little.ant.payment.pojo.QuickPayReqEntity;
import little.ant.payment.pojo.RedPackageEntity;
import little.ant.sms.bo.SMSMsgEntity;
import little.ant.sms.bo.SMSSendResult;

public interface AppSrvRMIService extends Remote {

    public Map<String, String> quickPay (QuickPayReqEntity quickPayReqEntity)
            throws RemoteException;


    public void refound (Pay2BankEntity pay2account) throws RemoteException;


    public SMSSendResult sendSMSYes (SMSMsgEntity smsMsgEntity) throws RemoteException;


    public SMSSendResult sendSMSCF (SMSMsgEntity smsMsgEntity) throws RemoteException;


    public Map<String, String> getDynTr2 (GetDynTr2Entity getDynTr2Entity) throws RemoteException;


    public void indAuth (IndAuthEntity indAuthEntity) throws RemoteException;


    public void indAuthVerify (IndAuthVerifyEntity indAuthVerifyEntity) throws RemoteException;


    public List<RedPackageEntity> getRedPackage (String userId) throws RemoteException;


    public int applyRedPackage (String userId, String redPackageID) throws RemoteException;


    public void autoOnboard (String productType) throws RemoteException;
}
