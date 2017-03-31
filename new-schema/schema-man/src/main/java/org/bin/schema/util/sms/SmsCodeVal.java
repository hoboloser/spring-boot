package org.bin.schema.util.sms;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;

import org.bin.schema.util.DateUtil;

/**
 * 
 * <p>Title:SmsCode</p>
 * <p>
 * Description:
 * 	短信发送验证，用于对IP/号码进行限制
 * 	<br>
 *  </br>
 *  默认限制为：
 *   1) 单个IP每天发送50条
 *   2)	单个手机号每天发送10条
 *   3)	同一手机号每2次发送间隔30秒
 *   4)	同一IP每2次发送间隔30秒
 *  
 * </p>
 * @author binH
 * @date 2017年3月30日 上午11:17:25
 */
public class SmsCodeVal {
	
	/**
	 * The maximum number of times each ip is sent per day
	 */
	private int ip_beyond = 50;

	/**
	 * The maximum number of times each phone number is sent per day
	 */
	private int mobile_beyond = 10;

	/**
	 * Request interval every 2 times
	 */
	private long interval_time = 5 * 1000;
	
	/**
	 * Whether to enable thread safety
	 */
	private boolean isSynInstance = false;
	/**
	 * the last request date
	 */
	private Date lastDay;
	
	private Map<String, IpStatis> ipstatis;

	private Map<String, MobileStatis> mobilestatis; 
	
	private ReentrantLock lock = new ReentrantLock();
	
	private SmsCodeVal() {	
		this.ipstatis = new HashMap<String, IpStatis>(64);
		this.mobilestatis = new HashMap<String, MobileStatis>(64);
	}
	
	private SmsCodeVal(boolean isSynInstance) {
		this.isSynInstance = true;
		this.ipstatis = new ConcurrentHashMap<String, IpStatis>(64);
		this.mobilestatis = new ConcurrentHashMap<String, MobileStatis>(64);
	}

	private static class HolderClass {
		private final static SmsCodeVal instance = new SmsCodeVal();
	}
	
	private static class HolderClassSyn {
		private final static SmsCodeVal instance = new SmsCodeVal(true);
	}
	/**
	 * 普通模式 
	 * @return SmsCodeVal
	 */
	public static SmsCodeVal getInstance() {
		return HolderClass.instance;
	}
	/**
	 * 线程安全
	 * @return SmsCodeVal
	 */
	public static SmsCodeVal getSynInstance() {
		return HolderClassSyn.instance;
	}

	/**
	 * 验证手机号码是否可以进行短信发送
	 * <p>
	 * 默认单个手机号最多发送 10
	 * </p>
	 * <p>
	 * 默认单个ip最多发送 50
	 * </p>
	 * @param mobile
	 *            手机号码
	 * @param request
	 * @throws SmsValException
	 * @return true
	 */
	public boolean canSendCode(String mobile, HttpServletRequest request)
			throws SmsValException {

		return canSendCode(mobile, mobile_beyond, ip_beyond, request);
	}

	/**
	 * 
	 * check 是否可以发送 默认单个ip最多发送 50
	 * 
	 * @param mobile
	 *            电话号码
	 * @param mobileBeyond
	 *            每个手机号最多发送数量
	 * @param request
	 * @return
	 */
	public boolean canSendCode(String mobile, int mobileBeyond,
			HttpServletRequest request) throws SmsValException {
		return canSendCode(mobile, mobileBeyond, ip_beyond, request);
	}

	/**
	 * 
	 * check 是否可以发送
	 *     加锁型
	 * @param mobile
	 *            电话号码
	 * @param mobileBeyond
	 *            每个手机号最多发送数量
	 * @param ipBeyond
	 *            每个ip最多发送数量
	 * @param request
	 * @return
	 */
	public boolean canSendCode(String mobile, int mobileBeyond, int ipBeyond,
			HttpServletRequest request) throws SmsValException {
		try {
			lock.lock();
			return send(mobile, mobileBeyond, ipBeyond, request);
		} catch (SmsValException e) {
			throw new SmsValException(e.getEnums());
		} finally{
			lastDay = new Date();
			lock.unlock();
		}
	}

	private boolean send(String mobile, int mobileBeyond, int ipBeyond, HttpServletRequest request) throws SmsValException{
		boolean flag = false;
		if(!isSameDay()){
			clear();
		}
		long now = new Date().getTime();
		String ip = ipCheck(request);
		flag = mobileCan(mobile, mobileBeyond) && ipCountCheck(ip, ipBeyond, now);
		if(flag){
			System.out.println("ip:"+ip);
			System.out.println("mobile:"+mobile);
			
			System.out.println("ipSize:"+ipstatis.size());
			if(null != ipstatis.get(ip)){
				System.out.println("ipSizeCount:"+ipstatis.get(ip).count);
			}
			System.out.println("mobileSize:"+mobilestatis.size()+":");
			
			increAdd(ip,mobile,now);
		}
		return flag;
	}
	/**
	 * 
	 * check 是否可以发送
	 * <p>
	 * 		非加锁，多线程情况下会导致判断不准确
	 * </p>
	 * @param mobile
	 *            电话号码
	 * @param mobileBeyond
	 *            每个手机号最多发送数量
	 * @param ipBeyond
	 *            每个ip最多发送数量
	 * @param request
	 * @return
	 */
	public boolean canSendCodeUnLock(String mobile, int mobileBeyond, int ipBeyond,
			HttpServletRequest request) throws SmsValException {
		try {
			return send(mobile, mobileBeyond, ipBeyond, request);
		} catch (SmsValException e) {
			throw new SmsValException(e.getEnums());
		} finally{
			lastDay = new Date();
		}
	}
	
	/**
	 * add to map
	 * @param ip
	 * @param mobile
	 * @param now
	 */
	private void increAdd(String ip,String mobile,long now){
		IpStatis ipsta = ipstatis.get(ip);
		if(null != ipsta){
			ipsta.lastSendTime = now;
			ipsta.inAdd();
		}else{
			ipsta = new IpStatis();
			ipsta.ip = ip;
			ipsta.lastSendTime = now;
			ipsta.inAdd();
			ipstatis.put(ip, ipsta);
		}
		
		MobileStatis mobilesta = mobilestatis.get(ip);
		if(null != mobilesta){
			mobilesta.lastSendTime = now;
			mobilesta.inAdd();
		}else{
			mobilesta = new MobileStatis();
			mobilesta.mobile = mobile;
			mobilesta.lastSendTime = now;
			mobilesta.inAdd();
			mobilestatis.put(mobile, mobilesta);
		}
	}
	
	/**
	 * clear the old data
	 */
	private void clear(){
		ipstatis.clear();
		mobilestatis.clear();
	}
	
	/**
	 * check last request day is same day with now
	 * @return
	 */
	private boolean isSameDay(){
		if(null == lastDay) return true;
		return DateUtil.isSameDate(lastDay, new Date());
	}
	
	/**
	 * ip check
	 * @param request
	 * @return ip
	 */
	private String ipCheck(HttpServletRequest request){
		String ip = null;
		try {
			ip = getIpAddr(request);
			if (!ValUtil.isBoolIp(ip)) {
				throw new SmsValException(SmsValEnums.IP_ERROR);
			}
		} catch (UnknownHostException e) {
			throw new SmsValException(SmsValEnums.IP_ERROR);
		}
		if (null == ip || "".equals(ip)) {
			throw new SmsValException(SmsValEnums.IP_ERROR);
		}
		return ip;
	}
	/**
	 * IP count check
	 * @param ip
	 * @return
	 */
	private boolean ipCountCheck(String ip, int ipBeyond,long now) {
		IpStatis ipsta = ipstatis.get(ip);
		if (null != ipsta) {
			int count = ipsta.count.get();
			if (count > ipBeyond) {
				throw new SmsValException(SmsValEnums.IP_BEYOND);
			}
			long lasttime = ipsta.lastSendTime;
			if ((now - lasttime) < interval_time) {
				throw new SmsValException(SmsValEnums.TIME_END);
			}
		}
		
		return true;
	}

	/**
	 * phone number check
	 * 
	 * @param mobile
	 * @return
	 */
	private boolean mobileCan(String mobile, int mobileBeyong) {
		if (null == mobile || "".equals(mobile)) {
			throw new SmsValException(SmsValEnums.NULL);
		}
		if (!ValUtil.isPhoneLegal(mobile)) {
			throw new SmsValException(SmsValEnums.PHONE_ERROR);
		}
		long now = new Date().getTime();

		MobileStatis mobilesta = mobilestatis.get(mobile); 
		if (null != mobilesta) {
			int count = mobilesta.count.get();
			if (count > mobileBeyong) {
				throw new SmsValException(SmsValEnums.SEND_BEYOND);
			}
			long lasttime = mobilesta.lastSendTime;
			if ((now - lasttime) < interval_time) {
				throw new SmsValException(SmsValEnums.TIME_END);
			}
			
		}
		return true;
	}

	/**
	 * get IP information
	 * 
	 * @param request
	 * @return
	 * @throws UnknownHostException
	 */
	private String getIpAddr(HttpServletRequest request)
			throws UnknownHostException {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")
					|| ipAddress.equals("0:0:0:0:0:0:0:1")) {
				InetAddress inet = InetAddress.getLocalHost();
				ipAddress = inet.getHostAddress();
			}
		}
		if (ipAddress != null && ipAddress.length() > 15) {
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	static class IpStatis {

		String ip;

		long lastSendTime;
		
		AtomicInteger count;

		public void inAdd() {
			if (null == count) {
				count = new AtomicInteger();
			}
			count.incrementAndGet();
		}
	}

	static class MobileStatis {

		String mobile;

		long lastSendTime;

		AtomicInteger count;

		public void inAdd() {
			if (null == count) {
				count = new AtomicInteger();
			}
			count.incrementAndGet();
		}
	}

}
