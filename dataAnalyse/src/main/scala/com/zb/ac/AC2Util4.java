package com.zb.ac;

import java.beans.Transient;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.zb.ac.trie.Emit;
import com.zb.ac.trie.Trie;
import com.zb.common.TagParam;
import com.zb.util.ExcelUtil;
import com.zb.util.db.DBConnPool;
import com.zb.util.db.DBExecUtil;

public class AC2Util4 implements Serializable{
	public ArrayList<TagParam> tagParams;
	public String config_file;
	public String config_file_regStr=",";
//	public static AhoCorasick tree;
	public Trie trie = null;
	public java.util.List list1 = new ArrayList();
	/*static {
		list1.add("jd001,www.kingdee-sh.com/,jd001");
		list1.add("jd002,www.kingdeesoft.net.cn/,jd002");
		list1.add("jd003,www.li-king.cn/,jd003");
		list1.add("jd004,www.auistech.com/index.asp,jd004");
		list1.add("jd005,www.diepoo.cn,jd005");
		list1.add("jd006,www.kinggon.com,jd006");
		list1.add("jd007,www.phoniu.com/index.php,jd007");
		list1.add("jd008,www.shpolong.com,jd008");
		list1.add("jd009,www.polong.net/,jd009");
		list1.add("jd010,www.shxiaokun.cn/,jd010");
		list1.add("jd011,www.021kingdee.com/,jd011");
		list1.add("jd012,www.yondie.com.cn/,jd012");
		list1.add("jd013,www.cotong.com/service/,jd013");
		list1.add("jd014,www.kingdee.sh,jd014");
		list1.add("jd015,www.kingdee-sh.cn/,jd015");
		list1.add("jd016,www.shhwadee.com/,jd016");
		list1.add("jd017,www.shkingdee.cn/lxwm,jd017");
		list1.add("jd018,www.fushierp.com/,jd018");
		list1.add("jd019,www.ik3cloud.com,jd019");
		list1.add("jd020,www.kingdee.com,jd020");
		list1.add("jd021,www.kingdee-shanghai.com/,jd021");
		list1.add("jd022,www.kingdeek.com,jd022");
		list1.add("jd023,www.dpskill.cn,jd023");
		list1.add("jd024,www.kingdee-021.com,jd024");
		list1.add("jd025,www.oa8000.com/,jd025");
		list1.add("jd026,www.whir.net/cn/cpzx/index_2.html,jd026");
		list1.add("jd027,www.landray.com.cn/contact/index.html,jd027");
		list1.add("jd028,www.tongda2000.com/?F=baidu_search&K=A,jd028");
		list1.add("jd029,www.lonyusoft.com/guanyu/lianxi.html,jd029");
		list1.add("jd030,www.senshine.cn/channels/45.html,jd030");
		list1.add("jd031,www.dimix.com.cn/about.shtml,jd031");
		list1.add("jd032,www.shopex.cn/index.php?from=baidushopex,jd032");
		list1.add("jd033,www.seeyon.com/,jd033");
		list1.add("jd034,www.mingdao.com/contact.htm,jd034");
		list1.add("jd035,www.weaver.com.cn/subpage/contact/#sh,jd035");
		list1.add("jd036,www.concur.cn/,jd036");
		list1.add("jd037,www.bokesoft.com/boke/contact,jd037");
		list1.add("jd038,www.foneplatform.com/?page_id=21670,jd038");
		list1.add("jd039,www.zbintel.com/company-center/contact.shtml,jd039");
		list1.add("jd040,www.pm2.com.cn/,jd040");
		list1.add("jd041,www.ctrlbox.cn/,jd041");
		list1.add("jd042,www.sosen.com.cn/,jd042");
		list1.add("jd043,www.zitoo.com.cn/,jd043");
		list1.add("jd044,cd.1card1.biz/index.php?r=article/Category/index&class_id=2,jd044");
		list1.add("jd045,www.scrmtech.com/about.html,jd045");
		list1.add("jd046,www.salesforce.com/cn/form/contact/contactme.jsp?d=70130000000EgLr,jd046");
		list1.add("jd047,www.guanyiyun.com/,jd047");
		list1.add("jd048,www.ufidaoa.com/,jd048");
		list1.add("jd049,www.todayyl.com/,jd049");
		list1.add("jd050,www.cnoa.cn/,jd050");
		list1.add("jd051,www.chinawitsky.com.cn/html/about/contact.html,jd051");
		list1.add("jd052,www.norming.com.cn/,jd052");
		list1.add("jd053,baidu.com,jd053");
		list1.add("jd054,qq.com,jd054");
		list1.add("jd055,sina.com,jd055");

	}*/
	public Map<String, String> carTagConfigMap = new HashMap<String, String>();
	
	/*public AC2Util(String config_file,String config_file_regStr){
		this.config_file=config_file;
		this.config_file_regStr=config_file_regStr;
		this.readParamFile();//从配置文件中初始化tag标签库
//		this.readParamFileByMySQL();//从mysql中初始化tag标签库
//		this.readParamFileByExcel("/data/hb_test/gw_tag/conf/tag");
		this.buidTrie();
	}*/
	public void inita(){
//		String path = AC2Util2.class.get
		this.config_file="./tag_conf.txt";
		this.config_file_regStr=",";
//		this.readParamFile();//从配置文件中初始化tag标签库
//		this.readParamFileByMySQL();//从mysql中初始化tag标签库
//		this.readParamFileByExcel("/data/hb_test/gw_tag/conf/tag");
//		list1.add("001,baidu.com,001");
//		list1.add("002,sina.com,002");
//		list1.add("003,www.baidu.com,003");
//		list1.add("004,qq.com,004");
		/*System.out.println(this.trie==null);
		if(this.trie==null) {
			this.trie = new Trie();
			this.readParamByList(list1);
			this.buidTrie();
		}*/
		
	} 
	
	public void readParamByList(List<String> list) {
		try {
			String[] tmpStrs = null;
			tagParams = new ArrayList<TagParam>();
				//System.out.println("以行为单位读取文件内容，一次读一整行：");
				for(String tempString:list) {
					System.out.println("Line"  + ":" + tempString);
					tmpStrs = tempString.split(config_file_regStr);
					if(tmpStrs.length>=3){
						TagParam tagparam = new TagParam(tmpStrs[0], tmpStrs[1], tmpStrs[2]);
						tagParams.add(tagparam);
					}
				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
//		return tagParams;
	}
	
	/**
	 * 初始化配置表
	 * @param filename
	 * @param regStr
	 */
	public void readParamFile() {
		try {
			System.out.println("tag config file ----  ====> "+config_file);
			File file = new File(config_file);
			BufferedReader reader = null;
			String tempString = null;
			int line = 1;
			String[] tmpStrs = null;
			tagParams = new ArrayList<TagParam>();
			try {
				//System.out.println("以行为单位读取文件内容，一次读一整行：");
				reader = new BufferedReader(new FileReader(file));
				while ((tempString = reader.readLine()) != null) {
					System.out.println("Line" + line + ":" + tempString);
					tmpStrs = tempString.split(config_file_regStr);
					if(tmpStrs.length>=3){
						TagParam tagparam = new TagParam(tmpStrs[0], tmpStrs[1], tmpStrs[2]);
						tagParams.add(tagparam);
					}
					line++;
				}
				reader.close();
//				return tagParams;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
//		return tagParams;
	}
	
	/**
	 * 初始化配置表
	 * @param filename
	 * @param regStr
	 */
	public void readParamFileByMySQL() {
		try {
			System.out.println("tag init from mysql");
			tagParams = new ArrayList<TagParam>();
			DBConnPool dbConnPool = new DBConnPool();
			DBExecUtil execUtil = new DBExecUtil();
			Connection connection = dbConnPool.getConnection("mysql");
			String sql = "SELECT uli.label_name AS tag,ui.url AS url,ul.* " +
					"FROM ut_website_label ul LEFT JOIN ut_website_info ui " +
					"ON ul.website_id = ui.website_id " +
					"LEFT JOIN ut_label_library uli " +
					"ON ul.label_id = uli.label_id";
			List<Map<String, Object>> results = DBExecUtil.execQuery(connection, sql);
			/*for(Map<String, Object> map : results){
				System.out.println(map.get("TAG").toString()+"---->"+map.get("URL").toString()+"---->"+map.get("LABEL_ID").toString());
				if(map.get("TAG")!=null&&map.get("URL")!=null&&map.get("LABEL_ID")!=null){
					String tag_name=map.get("TAG").toString();
					String url = map.get("URL").toString();
					String tag_id = map.get("LABEL_ID").toString();
					if(!"".equals(tag_name)&&!"".equals(url)&&!"".equals(tag_id)){
						TagParam tagparam = new TagParam(map.get("TAG").toString(), map.get("URL").toString(), map.get("LABEL_ID").toString());
						tagParams.add(tagparam);
					}
				}
			}*/
			/*Map<String, Object> map = new HashMap<String, Object>();
			TagParam tagparam = new TagParam("qqQzone", "qzone.qq.com", "qqQzone");
			tagParams.add(tagparam);
			TagParam tagparam1 = new TagParam("taobaoCart", "cart.taobao.com", "taobaoCart");
			tagParams.add(tagparam1);
//			buyertrade.taobao.com
			TagParam tagparam2 = new TagParam("buyertrade", "buyertrade.taobao.com", "buyertrade");
			tagParams.add(tagparam2);*/
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
//		return tagParams;
	}
	
	
	/**
	 * 建立搜索树
	 * @param infileName
	 */
	public void buidTrie(){
		//准备搜索树

		int length = tagParams.size();
		for (int i = 0; i < length; i++) {
			if (tagParams.get(i).getUrl().length() != 0) {
				trie.addKeyword(tagParams.get(i).getUrl(), tagParams
						.get(i).getTagname().trim());
			}
		}
	}
	
	/**
	 * 获取tag
	 * @return
	 */
	public synchronized String getTag(String content){
		String tagStr="";
		Collection<Emit>  findResult = null;
		Iterator iret = null;
		/*if(trie==null) {
			trie = new Trie();
			readParamByList(list1);
			buidTrie();
		}*/
		findResult = trie.parseText(content);
		if (findResult.size() > 0) {
			iret = findResult.iterator();
			while (iret.hasNext()) {
				Emit obj1 = (Emit)iret.next();
				//System.out.println(obj1.toString());
				if (tagStr.equalsIgnoreCase("")) {
					tagStr = obj1.getLabkey();
				} else{
					if(!tagExist(tagStr,obj1.getLabkey())){
						tagStr = tagStr + "," + obj1.getLabkey();
					}
				}
			}
		}
//			tempString = line + "|" + tagStr + "|" + tempString;
		
		return tagStr;
	}
	
	public synchronized String getTag2(String content){
		String tagStr="";
		Collection<Emit>  findResult = null;
		Iterator iret = null;
		if(this.trie==null) {
			this.trie = new Trie();
			this.readParamByList(getList1());
			this.buidTrie();
		}
		findResult = trie.parseText(content);
		if (findResult.size() > 0) {
			iret = findResult.iterator();
			while (iret.hasNext()) {
				Emit obj1 = (Emit)iret.next();
				//System.out.println(obj1.toString());
				if (tagStr.equalsIgnoreCase("")) {
					tagStr = obj1.getLabkey();
				} else{
					if(!tagExist(tagStr,obj1.getLabkey())){
						tagStr = tagStr + "," + obj1.getLabkey();
					}
				}
			}
		}
//			tempString = line + "|" + tagStr + "|" + tempString;
		
		return tagStr;
	}
	/**
	 * 检查是否重复打标签
	 * @param tag
	 * @param one_tag
	 * @return
	 */
	public boolean tagExist(String tag,String one_tag){
		//检测是否已经打了标签
		boolean tagExist=false;
		String[] tmpTagStr=tag.split(",");
		for(int i=0;i<tmpTagStr.length;i++){
			if(one_tag.equals(tmpTagStr[i])){
				tagExist=true;
			}
		}
		return tagExist;
	}
	
	/**
	 * 读一个文件夹里所有excel
	 * @param filePath
	 */
	public void readParamFileByExcel(String filePath){
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(new File(filePath+"/tag_id.txt"),true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//获取目录文件下所有excel
		System.out.println("tag init from excels");
		tagParams = new ArrayList<TagParam>();
		File configPath = new File(filePath);
		File[] files = configPath.listFiles();
		List<String> fileNameLists = new ArrayList<String>();
		for(File file : files){
//			System.out.println(file.getName().replace("~$", "").replace(".xlsx", ""));
			String fileName = file.getName();
			String fileStuff = fileName.substring(fileName.lastIndexOf(".")+1);
			if("xlsx".equals(fileStuff)){
				fileNameLists.add(file.getName().replace("~$", ""));
			}
		}
		for(String filename : fileNameLists){
			try {
				System.out.println(filename);
				String fileNameWithOutStuff = filename.replace(".xlsx", "");
//				if(!carTagConfigMap.containsKey(fileNameWithOutStuff)){
//					continue;
//				}
//				String tagId = carTagConfigMap.get(fileNameWithOutStuff);
				String tagId=fileNameWithOutStuff;
				 InputStream is2 = new FileInputStream(filePath+"/"+filename);
		         Map<Integer, String> map = new ExcelUtil().readExcelContent(is2);
		         System.out.println("获得Excel表格的内容:");
		         for (int i = 1; i <= map.size(); i++) {
		        	String[]  arr = map.get(i).split("\t");
		        	TagParam tagparam = new TagParam(tagId+arr[0].trim().replace(".0", ""), arr[2].trim(), tagId+arr[0].trim().replace(".0", ""));
		        	System.out.println(tagId+arr[0].trim().replace(".0", "")+"--->"+arr[2]);
		        	String content = tagId+arr[0].trim().replace(".0", "")+"|"+arr[1].trim()+"|"+arr[2].trim();
		        	bw.write(content);
		        	bw.write("\n");
					tagParams.add(tagparam);
		         }
			} catch (Exception e) {
				// TODO: handle exception  
				e.printStackTrace();
			}
			
		}
		try {
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		/*File configPath = new File("G:/tmp/carData");
		File[] files = configPath.listFiles();
		for(File file : files){
			String fileName = file.getName();
			System.out.println(fileName);
//			String newFileName = carTagConfigMap.get(fileName.replace("~$", "").replace(".xlsx", ""))+".xlsx";
//			file.renameTo(new File("G:/tmp/carData/"+newFileName));
//			System.out.println(fileName+"===>"+newFileName);
		}*/
		/*String fileName = "aaa.xlsx";
		String fileStuff = fileName.substring(fileName.lastIndexOf(".")+1);
		System.out.println(fileStuff);*/
		
//		readParamFileByExcel("G:/tmp/carData");
	}

	public Trie getTrie() {
		return trie;
	}

	public void setTrie(Trie trie) {
		this.trie = trie;
	}

	public java.util.List getList1() {
		return list1;
	}

	public void setList1(java.util.List list1) {
		this.list1 = list1;
	}
	
	
}
