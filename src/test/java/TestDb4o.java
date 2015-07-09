import java.io.File;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.lzx.framework.utils.StringUtil;
import com.lzx.framework.web.springjson.db4o.Db4oSource;


public class TestDb4o {
    public static void main(String[] args) {
	ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"./test.db4o");
	for(int i=0;i<1000;i++) {
	 Child a=new Child(String.valueOf(i));
	 a.setId(i);
	 if(i % 10 ==0)
	 a.setCode("test");
	 
	 db.store(a);
	}
	long s1=System.currentTimeMillis();
	ObjectSet<Child> set = db.query(new Predicate<Child>() {
	    private static final long serialVersionUID = 1L;

	    public boolean match(Child g) {
		if(!g.isEnable()) return false;
		if(g.getId()>10) return false;
		if(g.getCode().equals("test")) return false;
		return g.getName().equals("999");
	    }
	});
	long s2=System.currentTimeMillis();
	printCost(s1,s2,"test");
	for(Child c:set) {
	    System.out.println(StringUtil.beanToString(c));
	}
	
/*	List<Parent> list=db.queryByExample(Parent.class);
	System.out.println(StringUtil.beanToString(list.get(0)));*/
	new File("./test.db4o").delete();
    }
    private  static void printCost(long s1,long s2,String flag) {
	System.out.println(flag+":"+(s2-s1));
    }
}
