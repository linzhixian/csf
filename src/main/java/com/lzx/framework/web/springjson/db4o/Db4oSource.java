package com.lzx.framework.web.springjson.db4o;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import com.db4o.query.QueryComparator;
import com.lzx.framework.utils.StringUtil;
import com.lzx.framework.web.springjson.framework.jsonbean.ResetPwd;

/**
 * 单例模式：db4o 数据源
 * @author Administrator 2015年4月21日上午10:24:32
 *
 */
public class Db4oSource {
    protected static Logger logger = LoggerFactory.getLogger(Db4oSource.class);
    
    private ObjectContainer db;

    private static Db4oSource instance;

    public static String DB_URL;
    


    private Db4oSource() {
	if(DB_URL==null)
	    DB_URL = this.getClass().getClassLoader().getResource("/").getPath() + "db.db4o";
	System.out.println("db4o path:" +DB_URL);
	this.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DB_URL);
	doShutDownWork();
    }

    public static Db4oSource getInstance() {
	if(instance==null) instance=new Db4oSource();
	return instance;
    }

    public static ObjectContainer getDB() {
	return getInstance().db;
    }
    public static void close() {
	System.out.println("close db");
	//Db4oSource.printAllObj();
	
	getInstance().db.close();
    }

    private void doShutDownWork() {
	Runtime.getRuntime().addShutdownHook(new Thread() {
	    public void run() {
		db.close();
	    }
	});
    }
    public static void printAllObj() {
/*	List<Object> list2=getDB().queryByExample(Object.class);
	for(Object o:list2) {
	    logger.debug(StringUtil.beanToString(o));
	}*/
    }
}
