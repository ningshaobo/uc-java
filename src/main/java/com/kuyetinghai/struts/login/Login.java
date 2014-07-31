package com.kuyetinghai.struts.login;

import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;




import api.ucenter.Client;
import api.ucenter.XMLHelper;

import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport implements RequestAware,SessionAware,ApplicationAware{
	
	private String username;
	private String password;
	private Map<String, Object> request;
	private Map<String, Object> session;
	private Map<String, Object> application;
	
	public String login(){
		String uc_username = getUsername();
		String uc_password = getPassword();
		Client uc = new Client();
		Cookie mycookie[] = ServletActionContext.getRequest().getCookies();
		String result = uc.uc_user_login(uc_username, uc_password);  
		  
		LinkedList<String> rs = XMLHelper.uc_unserialize(result); 
		
		if(mycookie != null){
			Cookie c1;
			for(int i=0; i<mycookie.length; i++)
			{
				c1 = mycookie[i] ;
				System.out.println("cookie name : " + c1.getName() + "nsb name") ;
				System.out.println("cookie value :" + c1.getValue() + "nsb value");
			}
		}
		if(rs.size()>0){  
		    int uid = Integer.parseInt(rs.get(0));  
		    String $username = rs.get(1);  
		    String $password = rs.get(2);  
		    String $email = rs.get(3);  
		    if(uid > 0) {  
		       // response.addHeader("P3P"," CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");  
		        //System.System.out.println();
		        ServletActionContext.getResponse().addHeader("P3P", "CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
		        
		        System.out.println("��¼�ɹ�");  
		        System.out.println($username);  
		        System.out.println($password);  
		        System.out.println($email);  
		          
		        String ucsynlogin = uc.uc_user_synlogin(uid);
		        System.out.println("��¼�ɹ�"+ucsynlogin);
		  
		        //���ص�½����  
		        //TODO ... ....  
		        
		        Cookie auth = new Cookie("auth", uc.uc_authcode($password+"\t"+uid, "ENCODE"));  
		        auth.setMaxAge(31536000);  
		        auth.setPath("/");
		        //auth.setDomain("localhost");
		        ServletActionContext.getResponse().addCookie(auth);//response.addCookie(auth);  
		          
		        Cookie user = new Cookie("uchome_loginuser", $username); 
		        user.setPath("/");
		        ServletActionContext.getResponse().addCookie(user);//response.addCookie(user);  /*  */
		        
		        session.put("username", getUsername());
				session.put("password", getPassword());
		        return SUCCESS;
		          
		    } else if(uid == -1) {  
		        System.out.println("�û�������,���߱�ɾ��");  
		    } else if(uid == -2) {  
		        System.out.println("�����");  
		    } else {  
		        System.out.println("δ����");  
		    }  
		}else{  
		    System.out.println("Login failed");  
		}  
		//end
		/*
		if("admin".equals(getUsername()) && "admin".equals(getPassword())){
			session.put("username", getUsername());
			session.put("password", getPassword());
			return SUCCESS;
		}*/
		return ERROR;	
		
		
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
	
}
