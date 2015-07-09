import java.util.ArrayList;
import java.util.List;

public class Parent {
    List<Child> list;

    public void addChild(Child c) {
	if (list == null)
	    list = new ArrayList<Child>();
	list.add(c);
    }

    public List<Child> getList() {
	return list;
    }

    public void setList(List<Child> list) {
	this.list = list;
    }
}
