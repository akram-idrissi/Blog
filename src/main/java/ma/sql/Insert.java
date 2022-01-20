
package ma.sql;


public class Insert implements DBUpdateBehavior{
    
    private Object object;
    
    public Insert(Object object) {
        this.object = object;
    }
    
    @Override
    public void performUpdateBehavior() {
    }
    
    public Insert() {
    }
    
    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return object + "";
    }
    
}
