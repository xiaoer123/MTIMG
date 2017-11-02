<%@ page contentType="text/html; charset=utf-8" isErrorPage="true"%>
<%@ page import="com.digitalchina.frame.exception.ExceptionParser"%>
<%
String appPath = request.getContextPath();
String statusCode="";
String httpErrorMsg="";

String exceptionType="";
String exceptionMsg="";
String exceptionDesc="";
//String exceptionAddons="";
//String exceptionStackTrace="";

String url=request.getRequestURL().toString();
String queryString=request.getQueryString();

if(queryString!=null && !queryString.trim().equals("")){
	url+="?"+queryString;
}

Integer status_code =(Integer)request.getAttribute("javax.servlet.error.status_code");

if(status_code!=null){
	statusCode=status_code.toString();
	httpErrorMsg=ExceptionParser.getHttpErrorMessage(statusCode);
}

Exception ex=(Exception)request.getAttribute("javax.servlet.jsp.jspException");
if(ex==null){
	ex=(Exception)request.getAttribute("javax.servlet.error.exception");
}
if(ex!=null){
    exceptionType=ExceptionParser.getExceptionType(ex);
    exceptionMsg=ExceptionParser.getExceptionMessage(ex);
    exceptionDesc=ExceptionParser.getExceptionDescription(ex);
    //exceptionAddons=ExceptionParser.getExceptionAddons(ex);
    //exceptionStackTrace=ExceptionParser.getExceptionStackTrace(ex);
}

%>
<html>
<head>
<title>异常信息</title>
<link href="<%=appPath%>/css/style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="98%" border="0" align="center" cellpadding="3" cellspacing="0">
  <tr>
    <td height="40"><strong><img src="<%=appPath%>/common/images/mouse.gif" width="40" height="34" align="absmiddle" />异常信息</strong></td>
  </tr>
</table>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<form name="DATAFORM" id="DATAFORM" method="post" action="">
	<input type="hidden" id="RequestID" name="RequestID" value="">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="10"><img src="<%=appPath%>/console/images/printer_1.gif" width="10" height="30" /></td>
          <td width="98%" background="<%=appPath%>/console/images/printer_2.gif"></td>
          <td width="10" align="right"><img src="<%=appPath%>/console/images/printer_3.gif" width="10" height="30" /></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="10" background="<%=appPath%>/console/images/printer_4.gif">&nbsp;</td>
          <td width="98%" valign="top" bgcolor="#FFFFFF"><table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#E4E4E4">
              <tr bgcolor="#EFEFEF">
                <td>
					<%
					if (status_code==null || status_code.intValue()>=500) {
						out.print("<pre><b>应用异常：</b>"+url+"</pre>");
					} else {
						out.print("<pre><b>系统异常：</b>"+url+"</pre>");
					}
					%>
                </td>
              </tr>
              <tr bgcolor="#FFFFFF">
                <td>
				<pre><b>错误代码：</b><%=statusCode%></pre>
                </td>
              </tr>
              <tr bgcolor="#EFEFEF">
                <td>
				<pre><b>错误描述：</b><%=httpErrorMsg%></pre>
                </td>
              </tr>
              <tr bgcolor="#FFFFFF">
                <td>
				<font color=red>
				<pre><b>异常类型：</b><%=exceptionType%></pre>
				</font>
                </td>
              </tr>
              <tr bgcolor="#EFEFEF">
                <td>
				<font color=red>
				<pre><b>异常信息：</b><%=exceptionMsg%></pre>
				</font>
                </td>
              </tr>
              <tr bgcolor="#FFFFFF">
                <td>
				<font color=red>
				<pre><b>异常描述：</b><%=exceptionDesc%></pre>
				</font>
                </td>
              </tr>
              <tr bgcolor="#EFEFEF">
                <td>
				<p align=right>					
						<input type="button" value="返回" class="ButtonStyle" style="width:30pt" onclick="javascript:history.go(-1)">
				</p>
                </td>
              </tr>
              <tr bgcolor="#FFFFFF">
                <td>
		<div id="WebPagePrint" style="display:none">
		<TABLE BORDER="1" CELLPADDING="2" CELLSPACING="0" WIDTH="100%">
			<tr>
				<td WIDTH="100%" height=20><b>
					<%
					if (status_code==null || status_code.intValue()>=500) {
						out.print("应用异常："+url);
					} else {
						out.print("系统异常："+url);
					}
					%>
				</b></td>
			</tr>
			<tr>
				<td WIDTH="100%">
				<br>
				<pre><b>错误代码：</b><%=statusCode%></pre>
				<pre><b>错误描述：</b><%=httpErrorMsg%></pre>
				<hr>
				<font color=red>
				<pre><b>异常类型：</b><%=exceptionType%></pre>
				<pre><b>异常信息：</b><%=exceptionMsg%></pre>
				<pre><b>异常描述：</b><%=exceptionDesc%></pre>
				<br>
				</font>
				</td>
			</tr>
		</table>
		</div>
                &nbsp;</td>
              </tr>
            </table>
            </td>
          <td width="10" align="right" background="<%=appPath%>/console/images/printer_8.gif"><img src="<%=appPath%>/images/printer_8.gif" width="10" height="30" /></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="10"><img src="<%=appPath%>/console/images/printer_18.gif" width="10" height="28" /></td>
          <td width="98%" background="<%=appPath%>/console/images/printer_19.gif">&nbsp;</td>
          <td width="10" align="right"><img src="<%=appPath%>/console/images/printer_20.gif" width="20" height="28" /></td>
        </tr>
      </table></td>
  </tr>
</table>
</form>
</body>
</html>
