/**
 * 
 */
package com.lzx.framework.web.springjson.framework.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzx.framework.web.springjson.framework.Config;

/**
 * @author linzhixian 2014年10月24日下午2:39:34 classpath:conf/ 下 有： version.txt
 *         配置文件版本号，每当clientNeedFiles.txt自身及其里面的文件有改变都必须改变 clientNeedFiles.txt
 *         客户端需要的配置文件列表
 * 
 */

@Service
public class ConfigService  implements InitializingBean {
    protected static Logger logger = LoggerFactory.getLogger(ConfigService.class);

    private Map<String, RawFile> configFileMap = new HashMap<String, RawFile>();

    @Autowired
    private Config config;

    @Override
    public void afterPropertiesSet() throws Exception {
	//加载 web-inf/classes/data 作为数据目录
	String dataDir=this.getClass().getClassLoader().getResource("/").getPath()+"data";
	System.out.println(dataDir);
	loadAllRawFile(dataDir,config.getDataclasspath());
    }
    /**
     * 把指定目录下的所有文件读入RawFile，并缓存到configFileMap
     * @author lzx 2015年4月21日下午3:27:25
     */
    private void loadAllRawFile(String dataPath,String beanClasspath) throws IOException {
	// load version file
	File dir = new File(dataPath);
	 System.err.println(dir.getAbsolutePath());
	if (!dir.isDirectory()) {
	    System.err.println(dir.getParent() + " must a direction");
	    throw new IllegalArgumentException();
	}
	File[] files = dir.listFiles();
	for (File f : files) {
	    if (!f.isFile())
		continue;
	    String fileName = f.getName();
	    RawFile configFile = configFileMap.get(fileName);
	    if (configFile == null || configFile.isNew(f.lastModified())) {
		logger.info("load conf file:" + fileName);
		System.out.println("load conf file:" + fileName);
		configFile = new RawFile(f);
		configFileMap.put(fileName, configFile);
		if(fileName.equals("permission.txt")) {		    
		    ParserConfigUtil.parserConfigTxt(configFile,"com.lzx.framework.web.springjson.framework.config");
		} else {
		    ParserConfigUtil.parserConfigTxt(configFile,beanClasspath);
		}
	    }
	    // 不再使用，清除文件内容，释放内存
	    configFile.setContent(null);
	}
    }
}