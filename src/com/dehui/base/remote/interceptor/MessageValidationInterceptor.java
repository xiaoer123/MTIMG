package com.dehui.base.remote.interceptor;

import com.digitalchina.base.util.DataUtil;
import com.digitalchina.cptl.remote.interceptor.AbstractRemoteServiceInterceptor;
import com.digitalchina.cptl.remote.message.ServiceMessageExt;
import com.digitalchina.cptl.service.context.CspServiceContext;
import com.digitalchina.frame.exception.BusinessException;
import com.digitalchina.frame.message.ServiceMessage;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

public class MessageValidationInterceptor extends AbstractRemoteServiceInterceptor
{
 public boolean before(ServiceMessage requestMessage, CspServiceContext sc, Object bean, Method method)
    throws Exception
  {
    if (versionCheck(requestMessage, "2.0"))
    {
      String appid = (String)requestMessage.getHead().get("appid");
      String sign = (String)requestMessage.getHead().get("sign");
      System.out.println("====appid==="+appid);
      System.out.println("====body=="+((ServiceMessageExt)requestMessage).getRawMessage());
      System.out.println("====sign==="+sign);

   /*   if (!DataUtil.checkSign(appid, ((ServiceMessageExt)requestMessage).getRawMessage(), sign)) {
        throw new BusinessException("900901", "验签失败");
      }*/
      return true;
    }
    return true;
  }

  private static boolean versionCheck(ServiceMessage requestMessage, String availableV) throws Exception
  {
    /*String version = (String)requestMessage.getHead().get("version");
    if (StringUtils.isEmpty(version)) {
      version = "1.0";
    }
    double rv = Double.valueOf(version).doubleValue();
    double av = Double.valueOf(availableV).doubleValue();*/
   // return rv >= av;
    return true;
  }
}