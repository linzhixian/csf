public class Child {
    private String name;    
    private boolean enable;
    private String code;
    private int id;
    private byte[] bb;
    public Child(String string) {
	this.name = string;
	bb=new byte[1000];
	for(int i=0;i<1000;i++) {
	    bb[i]=(byte)i;
	}
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
